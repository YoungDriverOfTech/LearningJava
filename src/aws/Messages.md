# 消息系列
## 章节介绍
- 当部署多个应用的时候，多个应用之间会进行交流
- 有两种方式进行app 交流
![img.png](img.png)

- 不同的app之间进行同步的时候，如果有流量高峰会产生问题
- 假如你想要加密100个video，但通常是10个video
- 在这种情况下，最好取结偶app
  - 使用SQS：队列模型
  - 使用SNS：发布订阅模型
  - 使用Kinesis：实时流处理模型
- 这些应用可以独立的进行伸缩

## Amazon SQS
### 什么是SQS
![img_1.png](img_1.png)

### Standard Queue
- 最老的队列（超过10年）
- 完全托管的应用，被用来结偶app
- 特点
  - 无限的吞吐量，队列里的消息容量也是无限
  - 默认信息保留时间：4天，最大14天
  - 低延迟（小于10ms）
  - 每天消息最大size256KB
- 可以有重复的消息（至少一次消息推送）
- 可以有乱序的消息（最好是有序的）

### 生产消息
- 使用SDK生产消息（SendMessage API）
- 信息被持久化到SQS中，直到消费者删除他
- 信息保留：默认4天，最长14天
- 例子：发送一个将被处理的订单
  - 订单id
  - 消费者id
  - 想要的任何属性
- SQS standard：无限的吞吐量
![img_2.png](img_2.png)

### 消费消息
- 消费者：EC2实例，服务器，Lambda
- 拉取消息：一次最大10条
- 处理消息：插入消息到RDS数据库
- 使用DeleteMessage API删除消息
![img_3.png](img_3.png)

### 多个EC2实例消费者
- 消费者平行的接受和处理消息
- 至少一次消息推送
- 尽量完成消息排序
- 消费者在处理完消息以后删除消息
- 可以水平扩展消费者以提高处理吞吐量
![img_4.png](img_4.png)

### SQS和Auto Scaling Group（ASG）
![img_5.png](img_5.png)

### SQS在两个app间结偶
![img_6.png](img_6.png)

### SQS Security
- 加密
  - 使用https api进行传输加密
  - 使用 KMS 密钥进行静态加密
  - 如果客户想要在自己的端加密，可以自己加密
- 访问控制：IAM policy图闺房SQS API的访问
- SQS Access Policies（类似于s3 bucket policies）
  - 对于跨账户的SQS访问是有用的
  - 允许其他服务（SNS，S3）写消息到SQS

### 消息可见Timeout
- 在一条消息被一个消费者拉取以后，对于其他的消费者来说，这条消息就是不可见
- 默认消息的不可见市场是30秒
- 这意味着消息有30秒时间可以被处理
- 在消息可见timeout到期后，消息在SQS中又是可见的
![img_7.png](img_7.png)
- 如果一条消息在过期时间内没有被处理，那么这条消息就会被处理两次
- 一个消费者可以call ChangeMessageVisibility API去延长过期时间
- 如果可见性超时很高（小时）并且消费者崩溃，重新处理将需要时间
- 如果可见性超时太低（秒），我们可能会得到重复项

### 消息长拉取（轮询）
- 如果消费者从queue中取消息，可以设置消费者等待，直到queue中出现消息。这被叫做长拉取（Long Polling）
- 长拉取可以减少SQS api的调用次数，增加效率和降低延迟
- 等待时间介于1-20秒
- 长轮询优于短轮询
- 可以在队列级别启用长轮询或者在 API 级别使用 WaitTimeSeconds

### FIFO Queue 先进先出队列
- 吞吐量有限制：300msg/s 非批量处理。 3000msg/s 批量处理
- 一次性发送功能（通过删除重复项）
- 消息被消费者按照顺序处理
![img_8.png](img_8.png)

### SQS整合ASG
![img_9.png](img_9.png)
![img_10.png](img_10.png)
![img_11.png](img_11.png)
![img_12.png](img_12.png)

## Amazon SNS
### 什么是SNS（发布订阅模型）
- 想要一条消息发送给多消费者
- 生产者只发送消息到SNS topic
- 消费者订阅topic
- 每个订阅topic的消费者会获得所有的信息（最新功能：过滤）
- 每个topic最多12，500，000个订阅者
- topic最多100，000个
![img_13.png](img_13.png)
![img_14.png](img_14.png)

### SNS整合其他服务
![img_15.png](img_15.png)

### 怎么publish消息
- Topic发布（使用SDK）
  - 创建topic
  - 创建订阅者
  - 发送消息到topic
- 直接发送（针对手机apps SDK）
  - 创建平台应用
  - 创建平台endpoint
  - 发布到平台endpoint
  - 和Google GSM， Apple APNS，Amazon ADM共同工作。。。

### SNS 安全
- 加密
  - 使用 HTTPS API 进行动态加密
  - 使用 KMS 密钥进行静态加密
  - 用户如果想，可以在客户端自己加密
- 访问控制
  - IAM policies规范SNS api的访问
- SNS Access Policies（类似于S3bucket policy）
  - 跨账户访问SNS api
  - 允许其他服务写入sns topic




