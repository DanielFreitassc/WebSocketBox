var socket = new SockJS('/websocket');
var stompClient = Stomp.over(socket);

stompClient.connect({}, function() {
    stompClient.subscribe('/topic/status', function(message) {
        var status = JSON.parse(message.body);
        updateStatus(status);
    });
});

function updateStatus(status) {
    var statusElement = document.getElementById('status');
    

    var statusDiv = document.getElementById('statusDiv');
    statusDiv.style.backgroundColor = status.disponivel ? 'black' : 'gray';
}

document.getElementById('statusDiv').addEventListener('click', function() {
    var statusDiv = document.getElementById('statusDiv');
    var disponivel = !statusDiv.classList.contains('disponivel'); 

    statusDiv.classList.toggle('disponivel'); 

    var status = { "id": 1, "disponivel": disponivel }; 

    stompClient.send("/app/status", {}, JSON.stringify(status));
});
