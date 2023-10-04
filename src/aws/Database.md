# RDS
## Amazon RDS总览
### 概念
- RDS = Relational Database Service
- 包括大多数关系型数据库

### 优势
- RDS是一种管理型的服务
  - 自动配置
  - 持续自动备份
  - 监控
  - 读的DB副本用来提高performance
  - 跨可用区，灾难恢复
  - 横向/纵向扩展
  - 数据备份在EBS
- 不能用ssh到RDS的instance上

### 存储自动扩展 - Storage Auto Scaling
- 帮助用户动态增加DB实例
- 当监测到storage快用完，会自动扩展。避免了手动扩展操作
- 必须设置最大存储空间（Maximum Storage Threshold）
- 自动修改存储，如果发生下面的情况
  - 空闲空间小于分配空间的10%
  - 在过去5分钟就是空间紧张状态
  - 从上次修改后6个小时
- ![img.png](img.png)

### 仅可读副本 - RDS Read Replicas
- 最多15个读副本数据库
- 可以统一AZ，不同AZ，不同Region
- 副本数据会从Master数据库异步复制，最终的结果是满足一致性的
- 副本可以被提升为master
![img_1.png](img_1.png)

### RDS Read Replicas 使用场景
![img_2.png](img_2.png)

### RDS Read Replicas 网络花费
![img_3.png](img_3.png)

### 灾难恢复 - EDS Multi AZ(Disaster Recovery)
- 同步副本数据库
- 有一个dns名字，出现故障自动转移到被用数据库（auto fail-over）
- ![img_4.png](img_4.png)

### 从单一AZ到复数AZ
- 0 down时间
- 内部发生的魔法
  - 取快照
  - 新AZ的新DB存储快照数据
  - 同步机制在两个DB中建立
- ![img_5.png](img_5.png)









