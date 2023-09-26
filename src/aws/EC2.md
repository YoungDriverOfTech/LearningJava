# 预算 - Budget
## 使用根用户，开启admin用户查看bill权限
右上 - Account - IAM user and role access to Billing information
![img.png](img.png)

## 查看服务收费
右上 - Billing Dashboard - Bills（left side） - Charge by services
![img_1.png](img_1.png)

## 免费使用的额度 - free tier
这个菜单标记了现在免费的额度使用了多少了

## 创建费用通知
![img_2.png](img_2.png)

# EC2
## 基础

- EC2 = Elastic Compute Cloud = Infrastructure as a Service
- 主要包括
  - 租虚拟机（EC2）
  - 在虚拟设备上存储数据（EBS）
  - 跨机器分布式负载（ELB）
  - 自动伸缩服务（ASG）

## 建立第一个EC2 Instance

### 步骤
- 搜索EC2 - 选择左边栏Instances - Launch Instance
![img_3.png](img_3.png)
- 配置选择默认的即可，keypair生成一个新的，用来login
![img_4.png](img_4.png)
- 配置网络和安全组
![img_5.png](img_5.png)
- Advance Details有用户数据的选项，这个是当首次launch instance之后会只执行一次的脚本
![img_6.png](img_6.png)
- 给分配的公网IP，每次重启都会变化（注意点）
- 访问公网IP，可以看到之前在user data里面执行脚本生成的html的文件
![img_7.png](img_7.png)

## EC2实例的类型
### Overview
- 可以在这个网站查看EC2实例的类型： https://aws.amazon.com/cn/ec2/instance-types/
- AWS有如下的命名规则： m5.2xlarge
  - m：实例类型
  - 5：代（aws会提升他们随时间上升）
  - 2xlarge：实例的size，比如内存的大小，存储大小

### 通用型
- 多种类型资源的平衡，比如服务器资源和代码仓库
- 这些平衡包括
  - 计算
  - 内存
  - 网络
![img_8.png](img_8.png)

### 计算优化型
- 擅长高性能计算任务
  - 批处理
  - 视频转义
  - 高性能服务器
  - 高性能计算
  - 科学模型，机器学习
  - 专属服务器
![img_9.png](img_9.png)

**详细参见上面的网站**

## 安全组
### 介绍
- 安全组是aws网络安全的基石
- 控制流量的进出
- 安全组只包含允许的规则
- 安全组可以背ip和其他安全组引用
![img_10.png](img_10.png)

### 安全组详细介绍
- 扮演防火墙的角色
- 他们控制：
  - 接入端口
  - 授权ip范围
  - 控制inbound 网络
  - 控制outbound 网络
![img_11.png](img_11.png)
![img_12.png](img_12.png)

### 安全组须知知识点
- 可以绑定到多个实例
- 依附于region/vpc
- 最好单独维护一个ssh的安全组
- 如果app不能反问（timeout），那是安全组的问题
- 如果给出了connection refused错误，那是app的错误或者app没有被launched
- 所有的入口流量默认被blocked
- 所有的出口流量默认放开

### 安全组之间相互引用
![img_13.png](img_13.png)

### 安全组端口须知
- 22：SSH，log into Linux实例
- 21：FTP（File Transfer Protocol） 上传文件
- 22：SFTP（Secure File Transfer Protocol） 使用SSH上传文件
- 80：HTTP 链接非安全的网站
- 443：HTTPS 链接安全的网站
- 3389：RDP（Remote Desktop Protocol） log into到一个Windows实例

## 链接EC2
### 本地连接
```shell
ssh -i key ec2-user@ip
```

### 网页链接
![img_14.png](img_14.png)
![img_15.png](img_15.png)

**给EC2追加role**
![img_16.png](img_16.png)
![img_17.png](img_17.png)
![img_18.png](img_18.png)

## 网络

### 概念
- 网络分为两种：ipv4， ipv6
- ipv4是最常用的格式
- ipv6是新的，用来解决ipv4不足的问题

### 内网 vs 公网 ip
![img_19.png](img_19.png)
- 公网ip - public ip
  - 公网ip意味着机器可以被网络识别到
  - 在整个网络种必须唯一
  - 可以背简单的定位
- 内网ip - private ip
  - 内网ip意味着机器只能在内部网络环境中被识别
  - ip在整个内网中是唯一的，但是不同的内部网络，可以有相同的ip
  - 机器链接网络是使用的NAT+网管
  - 只有特定的ip范围可以被当作内网ip使用

### 弹性IP - Elastic IP
- 当重启EC2的时候，公网IP会自动变换
- 如果需要一个固定的公网ip，那么就需要弹性ip
- 一个弹性ip只要不删掉他，就会一直存在于公网
- 可以把弹性ip绑定到一个EC2上
![img_20.png](img_20.png)

- 有了动态ip，可以通过重新绑定动态ip到不同的EC2上来防止因为EC2宕机而影响到自己的程序
- 只能创建5个动态ip，但是可以要求AWS的人帮忙加
- 总的来说，要避免使用动态ip
  - 使用它反应出架构烂
  - 相反，要使用随机的公网ip和注册域名到DNS
  - 或者使用负载均衡器

## 替换组 - Placement Groups
- 有事想要控制EC2实例的替换策略
- 这个策略可以由替换组来定义
- 当创建一个替换组时，要指明使用的策略是以下哪种
  - Cluster - 集群所有的实例到一个低延迟的组里面，这些实例在同一个az
  - Spread - 传播实例，跨硬件（每个可用区，每个组，最大7个实例）
  - Partition - 在同一个az种，分散实例到不同的分区（依赖不同的硬件）

### Cluster
![img_21.png](img_21.png)
- 优点：网络好
- 缺点：如果硬件down了，所有的实例一起死
- 使用case：
  - 需要快速处理的大数据工作
  - 需要极低延迟的应用

### Spread
![img_22.png](img_22.png)
- 优点：
  - 可以跨可用区
  - 减少同时down的风险
  - EC2实例在不同的硬件上
- 缺点：
  - 每个az，每个组，最大7个实例
- 使用case：
  - 需要高可用的程序
  - 每个实例的失败都需要和其他实例分割开的重要应用

### Partition
![img_23.png](img_23.png)
- 每个AZ可以创建7个分区
- 可以在同一个region下跨多个可用区
- 一个分区的down会影响本分区的实例，但是不会影响其他分区

### hands on
![img_24.png](img_24.png)
![img_25.png](img_25.png)
![img_26.png](img_26.png)

## 弹性网络接口 - Elastic Network Interfaces（ENI）
- VPC中的逻辑组件，代表了一个虚拟网卡
- ENI有下列属性
  - 一个主内网ip，一个副内网ip
  - 每个内网IP有一个弹性ip
  - 一个公网ip
  - 一个或者多个安全组
  - 一个MAC地址
- 可以独立创建ENI，并且绑定到EC2的实例上
- 必须在一个特定的可用区
![img_27.png](img_27.png)
![img_28.png](img_28.png)
![img_29.png](img_29.png)

## EC2休眠 - Hibernate
- 在重启EC2实例的时候，下面的事情将会发生
  - OS启动， EC2的userdata被执行
  - 自己的App启动，缓存热身（花时间）

### 概念
- 内存状态会被保存
- 实例启动会更快
- 内存状态被写进一个文件，这个文件存在根EBS数据卷上面
- 根EBS数据卷必须是加密的

使用case
- 长时间运行的任务
- 需要保存内存状态

![img_30.png](img_30.png)

在launch新的实例的时候
![img_31.png](img_31.png)
![img_32.png](img_32.png)

# EC2 - Instance Storage 实例存储
## EBS Volume - 数据卷
### 概念
- EBS - Elastic Block Store 就是一个网络优盘，可以mount到EC2上面
- 可以持久化数据，一次只能mount到一个实例上，区分可用区
- 每次创建实例，都会创建一个root的EBS数据卷
  - root数据卷默认在EC2被删除的时候删除，但是可以改
  - 非root的EBS数据卷，默认EC2被删除的时候保留

### 图解
![img_33.png](img_33.png)
![img_34.png](img_34.png)
![img_35.png](img_35.png)