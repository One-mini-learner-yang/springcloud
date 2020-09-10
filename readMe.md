#springCloud
##Nginx
```text
Nginx是一款轻量级的Web 服务器/反向代理服务器及电子邮件（IMAP/POP3）代理服务器，在BSD-like 协议下发行。其特点是占有内存少，并发
能力强，事实上nginx的并发能力在同类型的网页服务器中表现较好，中国大陆使用nginx网站用户有：百度、京东、新浪、网易、腾讯、淘宝等。
```
###nginx三大配置
```text
一·反向代理
二·负载均衡
三·动静分离
```
```text
一·
    反向代理
        介绍反向代理前说明下正向代理
        正向代理：用户知道目标服务器地址，但由于网络限制等原因，无法直接访问。这时候需要先连接代理服务器，然后再由代理服务器访问
        目标服务器。
```
![正向代理](./resources/正向代理.jpg "正向代理")
```text
        反向代理：反向代理对用户则是不可知的，比如我们访问百度网站，百度的代理服务器对外的域名为 https://www.baidu.com 。具体内
        部的服务器节点我们不知道，现实中我们通过访问百度的代理服务器后，代理服务器给我们转发请求到他们N多的服务器节点中的一个给我
        们进行搜索后将结果返回。
        此时在用户角度，代理服务器和访问的服务器是一体的。
```
![反向代理](./resources/反向代理.jpg "反向代理")
```text
        在nginx配置反向代理
        实例一：反向代理一个tomcat服务器
        location / {
                    root   html;
                    proxy_pass  http://127.0.0.1:8080;
                    index  index.html index.htm;
                }
        实例二：反向代理两个tomcat服务器，根据访问路径不同进行区分服务器
        location ~ /aaa/ {
                    proxy_pass  http://127.0.0.1:8080;
                }
        location ~ /bbb/ {
                    proxy_pass http://127.0.0.1:8081;
               }
        其中 ~/aaa/是对路径进行正则表达式判断，若访问路径包含/aaa/则代理对应服务器
```
```text
二·
    负载均衡 
          负载均衡：分摊到多个操作单元上进行执行，和它的英文名称很匹配。就是我们需要一个调度者，保证所有后端服务器都将性能充分发
    挥，从而保持服务器集群的整体性能最优。即将请求根据负载均衡策略分配给不同的服务器上。
    在nginx上配置负载均衡
    upstream myServer{
            server 192.168.234.128:8080;
            server 192.168.234.128:8081;
       }
    server {
            listen       80;
            server_name  192.168.234.128;
    
            #charset koi8-r;
    
            #access_log  logs/host.access.log  main;
    
            location / {
                root   html;
                proxy_pass  http://myServer;
                index  index.html index.htm;
            }
        .......
    nginx提供的负载均衡策略
        1.权重：根据权重不同进行倾向性的分配请求。（在对应端口后配置其权重即可） 
            upstream [服务器组名称]{
            　　server [IP地址]:[端口号] weight=2;
            　　server [IP地址]:[端口号];
            　　....
            }
        2.ip_hash：基于客户端ip的分配方式，解决了session不可跨服务器问题。
            upstream [服务器组名称]{
            　　ip_hash;
            　　server [IP地址]:[端口号] weight=2;
            　　server [IP地址]:[端口号];
            　　....
            }
        3.轮询：平均的将请求分配，默认为轮询
        4.fair：根据服务器对请求的响应时间长短进行分配
            upstream [服务器组名称]{
            　　server [IP地址]:[端口号] weight=2;
            　　server [IP地址]:[端口号];
            　　....
            　　fair;
            }
```
```text
三·
    动静分离
        将动态和静态访问资源分开
        两种实现方式：
            一是动态资源交给tomcat，静态资源交给nginx，一般为这种
            二是动态静态一起，再由nginx对请求进行区分
        本文以第一中方法进行配置
        nginx配置动静分离
         location /www/{
                    root /data/;
                }
         location /image/{
                    root /data/;
                   #autoindex on是将文件所在母炉列出
                    autoindex on;
                }
```