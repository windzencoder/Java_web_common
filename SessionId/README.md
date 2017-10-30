# SessionID

记录项目中SessionID的相关问题



## 过滤SessionID

参考[去除URL后面的jsessionid](http://chembo.iteye.com/blog/904850)

为什么需要出去URL后面的jsessionid？

+ [XSS危害——session劫持](http://www.cnblogs.com/dolphinX/p/3403027.html)

> session劫持
>
> 服务器生成的用以标识客户信息的cookie一般被称为sessionId，而通过一些手段获取其它用户sessionId的攻击就叫session劫持。
>
> 说的这么恐怖，那么被别人知道了我的sessionId后会有什么危险呢？通过上面交互过程可以看出来服务器是靠sessionId识别客户端是张三、李四还是王二麻子的，当其它用户获知了你的sessionId后，在其有效期内就可以凭此sessionId欺骗服务器，获取你的身份登录使用网站。

