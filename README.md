# ChatRoom-java
聊天室springBoot后端代码

 **接口一：登录**

````
地址
http://localhost:8080/user/login
请求方式
post
参数	x-www-form-urlencoded
userid、password
````

**接口二：注册**

```
地址
http://localhost:8080/user/register
请求方式
post
参数	raw
{
    "userid": "10475",
    "password": "123456",
    "username": "timeless",
    "sex" : "1",
    "avatar": "/default/default.jpg"
}
```

**接口三：id查询**

```
地址
http://localhost:8080/user/query/{userid}
请求方式
get
参数
userid
返回数据
{
    "userid": "10475",
    "username": "timeless",
    "password": null,
    "sex": 1,
    "avatar": "/default/default.jpg"
}
```

**接口四：添加好友**

```
地址
http://localhost:8080/relation/addfriend/{userid}/{friendid}
请求方式
post
参数 
userid、friendid
返回数据
{
    "msg": "添加好友成功",
    "code": "200"
}
```

**接口五：添加群**

```
地址
http://localhost:8080/relation/addgroup/{user_id}/{group_id}
请求方式
post
参数 
user_id、group_id
返回数据
{
    "msg": "添加群聊成功",
    "code": "200"
}
```

**接口六：获取好友列表**

```
地址
http://localhost:8080/relation/friends/{userid}/
请求方式
get
参数 
userid
返回数据
{
    "msg": "好友加载成功",
    "code": "200",
    "data": [
        {
            "userid": "10475",
            "username": "timeless",
            "password": null,
            "sex": null,
            "avatar": "/default/default.jpg"
        }
    ]
}
```

**接口七：获取群聊列表**

```
地址
http://localhost:8080/relation/group/{userid}/
请求方式
get
参数 
userid
返回数据
{
    "msg": "群聊加载成功",
    "code": "200",
    "data": [
        {
            "group_id": "33333",
            "group_name": "相亲相爱一家人",
            "gruop_head": null,
            "group_owner": null
        },
        {
            "group_id": "44444",
            "group_name": "麻豆工作室",
            "gruop_head": null,
            "group_owner": null
        }
    ]
}
```

**接口八：获取好友历史消息**

```
地址
http://localhost:8080/message/friend/{userid}/{friend_id}
请求方式
get
参数 
userid、friend_id
返回数据
{
    "msg": "历史消息拉取成功",
    "code": "200",
    "data": [
        {
            "user_id": "10475",
            "avatar": "/default/default.jpg",
            "name": "timeless",
            "content": "你好",
            "isMe": true,
            "create_time": null
        }
    ]
}
```

**接口九：获取群聊历史消息**

```
地址
http://localhost:8080/message/group/{group_id}/{user_id}
请求方式
get
参数 
group_id、user_id
返回数据
{
    "msg": "历史消息拉取成功",
    "code": "200",
    "data": [
        {
            "user_id": "10475",
            "avatar": "/default/default.jpg",
            "name": "timeless",
            "content": "你好",
            "isMe": true,
            "create_time": null
        },
        {
            "user_id": "10475",
            "avatar": "/default/default.jpg",
            "name": "timeless",
            "content": "啦啦啦",
            "isMe": true,
            "create_time": null
        }
    ]
}
```

**接口十：websocket接口，单聊与群聊**

```
地址
ws://localhost:8080/chatserver/{userid}
请求方式
websocket
参数 
userid
```

