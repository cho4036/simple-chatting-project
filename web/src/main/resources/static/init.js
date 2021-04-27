var stompClient = null;

function enter() {
    var socket = new SockJS('gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/users', function(user) {
            showMembers(JSON.parse(user.body).content);
        });
    });

    stompClient.send("/app/enter", {}, JSON.stringify({'name': $("#name").val(), 'color': $("#color").val()}));

    location.href = "mainpage.html";

}

$(function(){
    $( "#enter" ).click(function(){ enter(); });
 });