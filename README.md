# Como rodar a api? 
# 1 Entre na pasta socket
![image](https://github.com/DanielFreitassc/WebSocketBox/assets/129224303/352b544f-621c-4b8b-8626-b0ef35412a70)

# 2 aperte com o botão direito e procure abrir com o terminal 
![image](https://github.com/DanielFreitassc/WebSocketBox/assets/129224303/06c91010-df8f-4f5d-927d-17e3fcd3f9b2)

# 3 Caso esteja no CMD execute o comando abaixo
```
start powershell
```
![image](https://github.com/DanielFreitassc/WebSocketBox/assets/129224303/c8269771-5a8a-43f3-ae60-910f84423ee8)


# 4 Copie e cole o scipt abaixo e precione enter lembre se de estar com o Windows PowerShell 

```
docker build -t wb . ; docker run -d -p 8080:8080 wb
````
![image](https://github.com/DanielFreitassc/WebSocketBox/assets/129224303/14043eaf-596c-45c9-a1b4-d21ec79a86b3)

# Como rodar o frontend
# 1 Entre na pasta wb
![image](https://github.com/DanielFreitassc/WebSocketBox/assets/129224303/949f3b29-a5cf-4001-88ea-49687f3a9b26)

# 2 Abra o terminal nela e cole este comando
```
code .
```
![image](https://github.com/DanielFreitassc/WebSocketBox/assets/129224303/0c31d2c7-b4a4-4569-b781-97c2d2ea8cb4)

# 3 Agora abra um ternal no vscode apertando ctr + " e cole os comando abaixo
### baixa as bibliotecas necessarias
```
npm i
```
![image](https://github.com/DanielFreitassc/WebSocketBox/assets/129224303/0a28db7f-159c-45a8-840e-a4c7399ad546)

# Iniciar o projeto
```
npm run dev
```
![image](https://github.com/DanielFreitassc/WebSocketBox/assets/129224303/e49b378d-51de-4942-acd2-d869949e900d)

