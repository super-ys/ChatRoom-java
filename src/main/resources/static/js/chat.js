

var message = {
    'content':'',
    'from' :$('.head').val(),
    'to':''
}


var to = "";
var isgroup = true;
var username =  $('#username').text();
var socket = null;
console.log("username===>" + $('#username').text())
var socketUrl = "ws://localhost:8080/imserver/"+$('#username').text()

function checkSocket(){
    if(typeof(WebSocket) == 'undefined'){
        console.log('您的浏览器不支持 web Socket')

    }else{
        console.log('您的浏览器支持 web Socket')
        if(socket != null){
            socket.close();
            socket = null;
        }
    }
}

function concent(){
    checkSocket();
    socket = new WebSocket(socketUrl);
    socket.onopen = () =>{console.log('WebSocket 已连接')}
    socket.onmessage = (msg) =>{
        console.log(msg.data)
        analysisMessage(msg.data)
    }

}

function close(){
    if(socket != null){
        socket.close();
        socket = null;
        console.log("链接已关闭")
    }else {
        console.log("未开启链接")
    }
}

function analysisMessage(message){
    message = JSON.parse(message)
    console.log("message" + message)
    if(message.users != null){
        // 展示在线用户
        showOnlines(message.users);
    }else {
        // 展示信息
        showMessage(message);
    }

}

function sendMessage(){
    if(socket == null){
        console.log("WebSocket 链接未开启")
        return;
    }
    socket.send(JSON.stringify({
        content:$('textarea').val(),
        from:$('#username').text(),
        to:to
    }));
    sh
    $('#username').html("")
}

function showOnlines(list){
    console.log("展示在线用户。。。" + list)
    // 限制空
    $('#users').html("")
    $.each(list, (key, item) => {
        console.log("key ==> "+ key)
        console.log("item ==> "+ item['username'])
        if(username != item['username']){
            li = "<li>" + item['username'] + "<button  type='button' onclick=\"addChat('" +item['username'] +"')\">去私聊</button>"
            $('#users').append(li)
        }

    });
}

function addChat(name, data){
    console.log("name==>" + name)
    if(isgroup){
        to = name;
        $('#biaoti').html("聊天室(" + name + ")")


    }else {
        to = "";
        $('#biaoti').html("聊天室")

    }
    $("#chat-block").html("")
    isgroup = !isgroup;
    console.log("isgroup==>" + isgroup)
}

function showMessage(message){
    console.log("展示信息。。。")
    div = "<div>" + message.from + "说：" + message.content + "</div>"
    $('#chat-block').append(div)
}



// function receiveMessage(){
//     socket.on
// }






$(() => {
    // showUser(onlineUsers);

    concent();

    $('#send').click(() => sendMessage())



})