# Session

记录项目中Session的相关问题



## Session相关设置



### 设置Session超时时间

参考[Java Web开发Session超时设置](http://zmx.iteye.com/blog/1846181)



设置Session超时时间方式：

方式一：

在`web.xml中`设置`session-config`如下：

```
 <session-config>
  <session-timeout>2</session-timeout>
 </session-config>
```



即客户端连续两次与服务器交互间隔时间最长为2分钟，2分钟后`session.getAttribute()`获取的值为空

API信息：
`session.getCreationTime()`   获取session的创建时间
`session.getLastAccessedTime()`  获取上次与服务器交互时间
`session.getMaxInactiveInterval()` 获取session最大的不活动的间隔时间，以秒为单位120秒。



方式二：
 在Tomcat的`/conf/web.xml`中`session-config`,默认值为：30分钟

```
 <session-config>
	<session-timeout>30</session-timeout>
  </session-config>
```



方式三：

```
在Servlet中设置
HttpSession session = request.getSession();
session.setMaxInactiveInterval(60);//单位为秒
```



优先级：Servlet中API设置 > 程序/web.xml设置 > Tomcat/conf/web.xml设置



## 过滤SessionID

参考[去除URL后面的jsessionid](http://chembo.iteye.com/blog/904850)

为什么需要出去URL后面的jsessionid？

+ [XSS危害——session劫持](http://www.cnblogs.com/dolphinX/p/3403027.html)

> session劫持
>
> 服务器生成的用以标识客户信息的cookie一般被称为sessionId，而通过一些手段获取其它用户sessionId的攻击就叫session劫持。
>
> 说的这么恐怖，那么被别人知道了我的sessionId后会有什么危险呢？通过上面交互过程可以看出来服务器是靠sessionId识别客户端是张三、李四还是王二麻子的，当其它用户获知了你的sessionId后，在其有效期内就可以凭此sessionId欺骗服务器，获取你的身份登录使用网站。

