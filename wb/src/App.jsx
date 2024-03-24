import React, { useEffect, useState } from 'react';
import axios from 'axios';
import SockJS from "sockjs-client/dist/sockjs"
import { Client } from '@stomp/stompjs';

const App = () => {
  const [colors, setColors] = useState([]);
  const [stompClient, setStompClient] = useState(null);

  useEffect(() => {
    const client = new Client();
    client.webSocketFactory = () => new SockJS('http://localhost:8080/ws');
    client.onConnect = (frame) => {
      client.subscribe('/topic/newColor', (message) => {
        const newColor = JSON.parse(message.body);
        setColors((prevColors) => {
          const colorIndex = prevColors.findIndex((c) => c.id === newColor.id);
          if (colorIndex !== -1) {
            prevColors[colorIndex] = newColor;
          } else {
            prevColors.push(newColor);
          }
          return [...prevColors];
        });
      });
      fetchColors();
    };
    client.activate();
    setStompClient(client);

    return () => {
      client.deactivate();
    };
  }, []);

  const fetchColors = async () => {
    for (let id = 1; id <= 40; id++) {
      const response = await axios.get(`http://localhost:8080/color/${id}`);
      const color = response.data;
      setColors((prevColors) => {
        const colorIndex = prevColors.findIndex((c) => c.id === color.id);
        if (colorIndex !== -1) {
          prevColors[colorIndex] = color;
        } else {
          prevColors.push(color);
        }
        return [...prevColors];
      });
    }
  };

  const handleClick = async (id) => {
    if (stompClient && stompClient.connected) {
      const color = colors.find((c) => c.id === id);
      const newColor = { ...color, disponivel: !color.disponivel };
  
      // Envia a atualização para o servidor
      stompClient.publish({ destination: '/app/setcolor', body: JSON.stringify(newColor) });
    } else {
      console.error('STOMP client is not connected');
    }
  };

  return (
    <div>
      {colors.map((color) => (
        <button
          key={color.id}
          style={{ backgroundColor: color.disponivel ? 'black' : 'gray' }}
          onClick={() => handleClick(color.id)}
        >
          {color.id}
        </button>
      ))}
    </div>
  );
};

export default App;
