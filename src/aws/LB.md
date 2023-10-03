# High Availability & Scalability
## Scalability 扩展
- 两种形式
  - Vertical Scalability 纵向扩展： 加机器的配置
  - Horizontal Scalability 横向扩展： 加机器数量

## High Availability
- 跨数据中心部署app

# Load Balancing
## AWS上面Load Balancer的种类
- Classic Load Balancer： 2009 CLB。基本不用了
  - HTTP，HTTPS，TCP，SSL（Secure TCP）
- Application Load Balancer： 2016 ALB
  - HTTP，HTTPS，WebSocket
- Network Load Balancer： 2017 NLB
  - TCP，TLS（Secure TCP），UDP
- Gateway Load Balancer： 2020 GWLB
  - Operates at layer 3（Network layer） IP Protocol

## Load Balancer Security Groups
![img.png](img.png)

## Application Load Balancer
- 根据url转发
![img_1.png](img_1.png)

- 根据参数转发
![img_2.png](img_2.png)

### ALB注意点
- 固定的hostname（xxx.region.elb.amazonaws.com）
- app服务器不直接看用户的ip地址
  - 真正的用户ip被嵌入到了请求头X-Forwarded-For
  - 可以获得端口（X-Forwarded-Port）和协议（X-Forwarded-Proto）
![img_3.png](img_3.png)

### ALB hands on
- 创建ALB
- ![img_4.png](img_4.png)
- ![img_5.png](img_5.png)
- 创建完成之后，找到dns name访问即可
- 如果不要用ip来访问ec2，只有alb能访问到ec2，那么就把ec2的的安全组，入口流量改成alb就行
- 修改ALB的rule，可以根据路径，header之类的东西，控制转发的目标组

## Network Load Balancer
### 概念
- NLB允许：
  - 转发TCP/UDP请求到实例
  - 处理百万QPS请求
  - 低延迟
- 每个可用区每个NLB有一个静态IP，但是也可以分配动态
- NLB适用于即高性能的TCP/UDP转发
- ![img_6.png](img_6.png)
- ![img_7.png](img_7.png)


## Gateway Load Balancer
### 概念
- 用来部署，扩展和管理第三方网络虚拟应用
- 比如：防火墙入侵检测和预防系统之类
- 发生在网络的第三层
- ![img_8.png](img_8.png)
- ![img_9.png](img_9.png)


## Sticky Session（Session Affinity）- 亲和度
### 概念
- 可以修改LB，让某个请求只被转发到固定的EC2实例上
- CLB，ALB，NLB适用
- CLB/ALB，使用cookie来实现
- 使用case：确保user不会丢失session数据

### 分类
- Application-based Cookies
  - Custom cookie
    - 由转发目标生成，可包含由app生成任何自定义的属性
    - Cookie名字必须由每个转发目标单独指定（不能重复）
    - 已被aws占用的名字：AWSLB, AWSALBAPP, AWSALBTG
  - Application cookie
    - 被LB生成
    - 名字是AWSALBAPP
- Duration-based Cookies
  - cookie由LB生成
  - cookie名字是AWSALB（ALB），AWSELB（CLB）

## 跨区负载均衡 - Cross-Zone Load Balancing
### 有无跨区的差别
- 有：请求会被平均分给ec2
- ![img_10.png](img_10.png)
- 无：请求会被平均分给LB
- ![img_11.png](img_11.png)

### ALB/NLB/CLB默认开启状况
- ALB
  - 默认开启
  - 跨区负载不收钱
- NLB&GLB
  - 默认不开启
  - 跨区负载收钱
- CLB
  - 默认不开启
  - 跨区负载不收钱

## SSL/TLS
### 概念
- SSL整数允许数据加密，从客户端发到LB的这段距离，数据是加密的
  - SSL指加密sockets层
  - TLS指传输层加密
  - 现在TLS整数是被主要使用的，但大多数人都把TLS当成SSL
- 有过期时间，过期的时候需要更新

### LB - SSL Certificates
![img_12.png](img_12.png)

### Server Name Indication(SNI)
- 用来解决多个整数绑定到同一个web服务器的问题
- 是一种新的协议，要求用户在初始的SSL握手的时候，指明目标服务器hostname
- 服务器就能发现正确的证书
- 只在ALB&NLB起作用
![img_13.png](img_13.png)

