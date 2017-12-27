## 打包命令
```
mvn clean package -DskipTests
```

## 配置清单 (Config List)

### 基础配置 (Basic Config)

配置	描述	默认值
server.address	web服务地址	0.0.0.0
server.port	web服务端口	9000
app.devMode	是否是开发者模式	true
app.name	应用名称	blade
app.thread-name	启动线程名	(:3」∠)
app.banner-path	启动banner路径	NULL

开发者模式下异常会输出在网页上，而生产环境应避免

### MVC 配置 (MVC Config)

配置	描述	默认值
mvc.view.404	404页面地址	无
mvc.view.500	500页面地址	无
mvc.statics	静态资源目录，多个用逗号隔开.	/static/,/upload/
mvc.statics.show-list	是否显示文件列表，显示后类似于FTP服务	false
mvc.template.path	模板文件目录,位于classpath	templates

### HTTP 配置 (HTTP Config)

配置	描述	默认值
http.gzip.enable	是否开启gzip压缩	false
http.cors.enable	是否开启cors	false
http.session.key	session在cookie中的id	SESSION
http.session.timeout	session超时时长，单位分钟	1800
http.auth.username	basic认证用户名，当开启Basic认证时需要	无
http.auth.password	basic认证密码，当开启Basic认证时需要	无

### SSL配置

当你想直接使用 Blade 来搭建一个 https 的站点时可以用到这个配置，但是笔者不是特别推荐这种做法。 如果你的服务器只有一个站点你可以这么干，否则还是乖乖使用 nginx 这样的专业户吧。

配置	描述	默认值
server.ssl.enable	是否开启SSL	false
server.ssl.cert-path	X.509 certificate chain 文件，如 cert.pem	NULL
server.ssl.private-key-path	私钥路径，如 private.pem	NULL
server.ssl.private-key-pass	私钥密码(如果有的话)	NULL
