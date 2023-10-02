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







