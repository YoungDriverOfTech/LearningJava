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

