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

