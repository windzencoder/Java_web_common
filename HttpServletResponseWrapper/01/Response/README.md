# HttpServletResponseWrapper

javax.servlet.http.HttpServletResponseWrapper

+ 实现了HttpServletResponse接口
+ 使用它可以用来改变servlet的响应

修改response的步骤：

+ 创建一个response wrapper 继承自HttpServletResponseWrapper
+ 创建一个PrintWriter用来buffers output，重写getWriter方法，返回一个PrintWriter
+ 把wrapper传递给doFilter
+ 提取和修改输出
+ 修改后的输出发送到客户端


可参考的文章：

+ [Modifying the Servlet Response using Filters](http://javaravin.blogspot.com/2012/05/modifying-response-using-filters.html)
+ [Modify html response using filter](https://www.leveluplunch.com/java/tutorials/034-modify-html-response-using-filter/)
+ [servlet中使用HttpServletResponseWrapper截获返回的页面内容](http://blog.csdn.net/zmx729618/article/details/52125954)
