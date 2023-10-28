# AWS Storage Extras
## AWS Snow Family
### 总览
- 高度安全的便携式设备，用于在Edge收集和处理数据，以及将数据迁入和迁出AWS
- 数据迁移
  - ![img.png](img.png)
- Edge computing
  - ![img_1.png](img_1.png)

### 使用AWS Snow Family进行数据迁移
- 使用普通的数据迁移
  - 有限制的链接
  - 有限制的宽带
  - 网络花费高
  - 共享带宽，不能最大化利用带宽
  - 链接稳定性不好
  - 花费时间太多
  - ![img_2.png](img_2.png)
- 使用Snow Family
  - 离线设备来完成数据迁移
  - 如果使用网络来进行数据迁移需要花费1周多，那么就应该使用snow family
- 图解
  - ![img_3.png](img_3.png)

## 设备
### Snowball Edge（用来转移数据）
- 物理数据传输解决方案：将 TB 或 TB 数据移入或移出 AWS
- 可选则使用网络来进行传输（需要支付网络费用）
- 按每个数据转移job付费
- 提供块存储和Amazon S3兼容的对象存储
- Snowball 边缘存储优化
  - 80TB的块级数据卷容量和s3对象兼容存储
- Snowball 边缘计算优化
  - 42TB HDD或者28TB NVMe的块级数据卷容量和s3对象兼容存储
- 使用case：云端大数据迁移，数据中心退役，灾难恢复
- ![img_4.png](img_4.png)

### AWS Snowcone & Snowcone SSD
- 小型，便携，坚固耐用&安全，适应艰难的环境
- 轻，2.1千克
- 设备被用来做edge 计算，存储和数据转移
- Snowcone - 8TB HDD（Hard Disk Drive）硬盘
- Snowcone SSD - 14 TB硬盘
- 在Snowball不能用的地方使用Snowcone（比如有限制的空间环境）
- 必须提供自己的电视，电缆
- 可以发回AWS办公室，也可以使用网络，使用AWS Data Sync来发送数据
- ![img_5.png](img_5.png)

### AWS Snowmobile
- 传输艾字节数据（1 EB = 1,000 PB = 1,000,000 TBs）
- 每个Snowmobile有100PB的容量
- 安全性高：可控温度，GPS，24/7影像监视
- 如果传输10PB以上的数据，这个要好于Snowball
- ![img_6.png](img_6.png)

### 3钟设备对比
![img_7.png](img_7.png)

### 使用步骤
- 1，从aws console请求一个设备
- 2，安全snowball 客户端/aws opshub在你的服务器上
- 3，把snowball链接到你的服务器上，复制文件到awsball的客户端上
- 4，把设备发回给aws
- 5，数据将会被load到S3的桶中
- 6，Snowball被抹掉

## 什么是Edge Computing
### 概念
- 当数据在edge location被创建，就在edge location进行数据处理
  - 像客车在陆上，船在海洋上，煤矿站在地底
- 这些location可能有
  - 有限或者没有网络访问
  - 有限或者不容易访问计算电源
- 建立Snowball Edge/Snowcone设备去做edge计算
- Edge 计算的使用case
  - 预处理数据
  - 机器学习
  - 转码媒体流
- 如果有需要可以把设备发回aws

### Edge Computing
- Snowcone & Snowcone SSD（smaller）
  - 2 CPUs，4GB内存，有线或者无线网络接入
  - 使用电源线或者电池
- Snowball Edge - Compute Optimized
  - 104 vCPUs， 416 GiB内存
  - 可选的GPU（对于影像处理或者机器学习游泳）
  - 28TB NVMe 或者42TB硬盘存储
  - 存储集群（最大16节点）
- Snowball Edge - 存储优化
  - 最大49vCPUs， 80GiB内存，80TB硬盘存储
- 都可以在EC2&AWS Lambda上运行
- 长期部署选项：1和3年打折价格

### AWS OpsHub
- 历史上，要使用SnowFamily设备，需要CLI
- 现在，只需要用AWS OpsHub（装在电脑上的软件）就可以管理SnowFamily设备
  - 结果和配置单个或者集群设备
  - 传输文件
  - 启动和管理运行SnowFamily设备的实例
  - 监听设备指标（存储容量，活动的实例）

### 解决架构：Snowball into Glacier
- Snowball不能直接导入到Glacier
- 必须先使用S3，然后结合S3的lifecycle policy
- ![img_8.png](img_8.png)

## Amazon FSx
### 总览
- 在 AWS 上启动第三方高性能文件系统
- aws完全控制的服务
- ![img_9.png](img_9.png)

### Windows的文件服务器 - Fsx
- FSx for Windows 是完全托管的 Windows 文件系统共享驱动器
- 支持SMB 协议和Windows NTFS
- Microsoft Active Directory 集成、ACL、用户配额
- 可以安装在Linux EC2 实例上
- 支持 Microsoft 的分布式文件系统 (DFS) 命名空间（跨多个 FS 的文件组）
- 可扩展至 10 GB/s、数百万 IOPS、100 PB 数据
- 存储选项：
  - SSD – 延迟敏感的工作负载（数据库、媒体处理、数据分析……）
  - HDD – 广泛的工作负载（主目录、CMS……）
- 可以从您的本地基础设施（VPN 或 Direct Connect）进行访问
- 可配置为多可用区（高可用性）
- 数据每天备份到S3

### FSx for Lustre
- Lustre是一种平行分布式文件系统，大规模计算使用
- 名字的由来是Linux和cluster
- 机器学习，高性能计算（HPC：High Performance Computing）
- 影像处理，金融模型，电子设计动画
- 扩展规模到100s GB/s，百万或者千万的IOPS，低延迟
- 存储选项
  - SSD-低延迟，IOPS集约型负载，小型&随机文件操作
  - HDD-吞吐集约型负载，大型&顺序的文件操作
- 无缝整合S3
  - 可以作为一个文件系统（通过FSx）读s3
  - 可以回写给s3（通过FSx）
- 可以在本地服务器使用（需要使用VPN或者直连）

### FSx Lustre - 文件系统部署选项
- Scratch File System
  - 临时存储
  - 数据无备份（不会被持久化，如果文件系统down了）
  - 高性能
  - 使用：短期处理，优化cost
- Persistent File System
  - 长期存储
  - 数据在同一个AZ中被备份
  - 在几分钟内替换失败的文件
  - 使用：长期数据处理，敏感数据
- ![img_10.png](img_10.png)

### FSx for NetApp ONTAP
- 在aws上管理NetApp ONTAP
- 和NFS，SMB，ISCSI协议兼容的的文件系统
- 移动在ONTAP或者NAS的工作负载到AWS
- 可以和下面的服务器一起工作
  - Linux，Windows，MacOS，VMware Cloud on AWS，Amazon Workspace & AppStream 2.0， Amazon EC2，ECS和EKS
- 存储自动伸缩
- 快照，副本，低花费，压缩和数据冗余
- 时间点瞬时克隆（有助于测试新的工作负载）
- ![img_11.png](img_11.png)

