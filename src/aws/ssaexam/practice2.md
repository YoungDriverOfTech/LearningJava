# Practice 2
## Points

### 1. 想赋予AdministratorAccess权限，但是又想做一些限制
- 定义IAM permission boundary entities（users 或者 roles），然后attach到开发人员。但是不能被attach到IAM组

### 2. 存储数据库密码到安全的地方，并且可以自动rotation
- AWS Secrets Manager： Secrets Manager 通过 Amazon RDS、Amazon Redshift 和 Amazon DocumentDB 的内置集成提供秘密轮换
- AWS CloudHSM - AWS CloudHSM 是一种基于云的硬件安全模块 (HSM)，使您能够在 AWS 云上轻松生成和使用加密密钥。AWS CloudHSM 也是一种加密服务，而不是秘密存储。
- AWS Systems Manager Parameter Store - AWS Systems Manager Parameter Store（又名 SSM Parameter Store）为配置数据管理和机密管理提供安全的分层存储。 您可以将密码、数据库字符串、Amazon EC2 实例 ID、Amazon 系统映像 (AMI) ID 和许可证代码等数据存储为参数值。但是不支持dynamic rotation

### 3. 把一个AWS账户从一个Oranization迁移到另一个Oranization的步骤
- 要将账户从一个组织迁移到另一个组织，您必须对成员账户和主账户都具有 root 或 IAM 访问权限
- Steps
  1. Remove the member account from the old organization
  2. Send an invite to the member account from the new Organization
  3. Accept the invite to the new organization from the member account
 
### 5. AWS S3中的Objects的访问权限问题
- 默认的，S3的Objects是由上传这个Object的account所有的，即使这个bucket的owner是另一个account，owner也没有默认访问这些objects的权限
- 解决办法
  1. 在owner的account，创建有bucket权限的IAM role
  2. 从上传object的account下，创建有assume to bucket role权限的IAM role
  3. 更新存储桶角色以授予存储桶访问权限并与集群角色创建信任关系。

### 6. 一家公司管理多个AWS账户，使用AWS Transit Gateway的方式辐射链接（ub-and-spoke）这些账户。在这些账户中配置AWS Vitual Private Cloud（Amazon VPC），以促进网络隔离。怎么减少成本，同时提供一个公共的入口（接入方式）到各个VPC中的服务
- 创建shared services VPC：提供给各个VPC的服务提供公共的接入，这可能包括目录服务或 VPC 终端节点。 从中央位置共享资源而不是在每个 VPC 中构建资源可能会减少管理开销和成本。
- TODO（补充图片）：via - https://aws.amazon.com/blogs/architecture/reduce-cost-and-increase-security-with-amazon-vpc-endpoints/

### 7. ASG Terminate EC2的时候，优先级选择
1. On-Demand & Spot instances
2. 有最老的launch template（使用launch configuration的实例除外）
3. 有最老的launch configuration
4. 接近下个billing时间的

### 10. AWS IAM Policy
- AWS的IAM policy是一种定义用户、组和角色权限的对象。IAM policy由一组语句组成，每个语句都包含一个或多个字段。以下是IAM policy中常见字段的含义和举例：
  - Effect: 语句的效果，即允许或拒绝访问。例如，"Effect": "Allow"表示允许访问，"Effect": "Deny"表示拒绝访问。
  - Action: 操作或操作列表，用于指定允许或拒绝的操作。例如，"Action": "s3:GetObject"表示允许获取S3对象，"Action": ["s3:GetObject", "s3:PutObject"]表示允许获取和上传S3对象。
  - Resource: 操作的资源或资源列表。例如，"Resource": "arn:aws:s3:::my_bucket/*"表示允许对名为my_bucket的S3存储桶中的所有对象执行操作，"Resource": ["arn:aws:s3:::my_bucket", "arn:aws:s3:::my_bucket/*"]表示允许对存储桶及其所有对象执行操作。
  - Condition: 附加条件，用于限制语句的适用范围。例如，"Condition": {"IpAddress": {"aws:SourceIp": "192.0.2.0/24"}}表示只有来自IP地址192.0.2.0/24的请求才能匹配该语句。

### 13. ASG管理不同type的EC2实例（On-demand or Spot）
- A launch template: 可以使用On-demand和Spot实例，跨实例类型
- A launch configuration：不可以跨实例类型

### 20. S3跨account访问bucket的文件
- Amazon S3 Bucket Policies

### 21. 数百台EC2实例同时访问文件，要考虑成本问题
- Amazon Elastic File System (EFS) Standard–IA storage class：可以绑定到多台EC2上
  - Amazon S3 Standard–IA 存储类别可降低不每天访问的文件的存储成本。
  - 它可以在不牺牲 Amazon EFS 提供的高可用性、高耐用性、弹性和 POSIX 文件系统访问的情况下实现这一点。
  - 如果您需要轻松访问完整数据集并希望自动节省访问频率较低的文件的存储成本，AWS 建议使用标准 IA 存储。

### 22. IAM Policy 视力问题
- aws:RequestedRegion： request有ed，是被请求的区域
- aws:RequestRegion：request没有ed，不是被请求的区域

### 24. SQS FIFO 多个消费者
- 在发送消息到SQS时，定义GroupID属性，这样就可以有多个消费者了

### 25. 公司分享保密数据给另一个审查公司，审查公司有自己的AWS账户，并且需要一个数据库的copy，怎么做最安全
- 创建加密的数据库snapshot，共享这个快照，然后允许审查公司访问KMS的encryption key

### 26. 公司想测试蓝绿部署（想导一部分流量到新app），什么方案最好
- Amazon Route 53 weighted routing： 容易收到缓存影响，修改了record以后，生效时间不确定
- AWS Global Accelerator：是一个网络加速服务，它通过AWS全球网络提供以下主要功能，它提供两个静态IP地址，充当单个或多个AW 区域中的应用程序入口点，例如单个或多个AWS区域中的应用程序负载均衡器、网络负载均衡器、弹性 IP 地址或 Amazon EC2 实例，把AWS Global Accelerator当成一个LB，LB可以修改指向，比重指向
  - 性能提升：最多可将应用程序的网络性能提高60%。
  - 高可用性：为多区域和多可用区架构提供快速故障转移，确保应用程序的高可用性。
  - 确定性路由：通过静态公共IP作为应用程序入口点，消除了对DNS缓存的依赖，实现确定性路由。
  - DDoS防护：在源头更靠近用户的地方保护应用程序免受DDoS攻击。
  - 此外，AWS Global Accelerator支持在多个AWS区域中的端点，适用于需要静态IP地址或快速区域故障转移的非HTTP用例，如游戏、物联网或VoIP，以及特定需要静态IP地址的HTTP用例


### 27. Aurora Architecture
- todo: 补图  https://docs.aws.amazon.com/AmazonRDS/latest/AuroraUserGuide/Aurora.Overview.html

### 28. Route53 Record的routing policy
- Simple routing policy：路有一个域名到单一的资源
- Failover routing policy：配置故障转移的时候使用
- Geolocation routing policy：想要根据用户所在位置来路有流量
- Geoproximity routing policy：具有自己资源所在的位置路有流量
- Latency routing policy：想要在多个regions提供最佳的latency体验的时候
- Multivalue answer routing policy：当您希望 Route 53 使用最多随机选择的八个正常记录来响应 DNS 查询时使用。 您可以使用多值应答路由在私有托管区域中创建记录
- Weighted routing policy：指定的比例将流量路由到多个资源。 您可以使用加权路由在私有托管区域中创建记录

### 29. AWS Certificate Manager(ACM), SSL证书到期预警 - AWS Config
- AWS Certificate Manager 是一项服务，可让您轻松预置、管理和部署公共和私有的SSL证书
- AWS Config 提供您的 AWS 账户中 AWS 资源配置的详细视图。 这包括资源如何相互关联以及它们过去的配置方式
-  您可以利用 AWS Config 托管规则来检查您账户中的任何 ACM 证书是否被标记为在指定天数内过期。 ACM 提供的证书会自动更新。 ACM 不会自动续订您导入的证书。 如果您的证书即将过期，则该规则为 NON_COMPLIANT。

### 30. 本地服务器处在灾难多发地，想要AWS作为failover，要求最小的downtime
- 设置 Amazon Route 53 故障转移记录。 在 Auto Scaling 组中的 Application Load Balancer 后面的 Amazon EC2 实例上运行应用程序服务器。 设置具有存储卷的 AWS Storage Gateway 以将数据备份到 Amazon S3
  - Route 53配置DNS failover，转发流量到健康的资源上面
  - ALB自动转发请求到健康的jinstance上面
  - AWS Storage Gateway：混合云存储，可以让本地的服务器访问无限的云存储空间

### 31. 研究小组希望，启动EC2时候，需要执行一些辅助软件APP才能启动，想要最大限度的减少程序自举时间
- EC2 Hibernate

### 32. 本地DB迁移到云DB，为了提升性能和用户体验
- 就算本地不是MYsql，迁移到云上，可以换成其他的DB的，比如Aurora，Mysql等等

### 37. 一个account的Lambda想要访问另一个account的S3
- 如果 IAM 角色和存储桶位于不同账户中，则您需要授予 Amazon S3 对 IAM 角色和存储桶policy的权限
-  Lambda 函数创建的 IAM 角色与存储桶位于同一 AWS 账户中，则您无需同时授予对 IAM 角色和存储桶policy的 Amazon S3 权限


### 38. 创建了private hoted zone并且绑定到了VPC上，但是private hosted zone的DNS查询不可用
- 为private hosted zones启用DNS hostname / DNS resulution（DNS解析）
  - DNS hostnames / DNS resulution 默认是禁用的
 
### 49. 为了维持用户基本满意的服务，至少需要4个实例可用，应该怎么选择
- 部署实例在2个AZ，每个AZ部署2个实例 -> ❌　一旦某个AZ的实例down了，那么只剩下2个实例提供服务，不能满足最少的4个实例的要求
- 部署实例在3个AZ，每个AZ部署2个实例 -> ⭕️　一旦某个AZ的实例down了，那么只剩下4个实例提供服务，OK

### 50. DynamoDB
- 键值对/document数据库，可在任何规模下提供个位数毫秒的性能
- 完全托管，多region，多master，安全性高的持久型数据库，备份和恢复，内存缓存
- 看到key-value pairs，首先想到DynamoDB

### 52. EBS root volume在EC2被terminated之后回自动删除，怎么阻止这个默认行为
- 设置***DeleteOnTermination***属性成 false

### 53. 媒体公司想要建立一个流程把本地的video，同步到EFS上面
- 已经创建了AWS Direct Connect链接，想要啊APP迁移到AWS上。本地APP每天会将数百个Video写到NFS，迁移后会在安装有EFS的EC2上运行APP。在迁移前，需要建立一个流程从本地把video同步到EFS上
- 办法：在本地配置AWS DataSync 代理，用AWS Direct Connect connection传输数据到AWS PrivateLink interface VPC endpoint for Amazon EFS, 设置AWS DataSync定时任务，把video发到nfs上
- 为什么不用：本地 -》 S3 -〉 Lambda —》 NFS  因为绕了好几圈，不是最容易操作的

### 54. ASG没有停止一个不健康的EC2实例
- 实例没有通过Elastic Load Balancing (ELB)健康检查状态
  - 默认情况下，当组的运行状况检查配置设置为 EC2 时，Amazon EC2 Auto Scaling 不会使用 ELB 运行状况检查的结果来确定实例的运行状况。
- 实例可能处于受损状态（Impaired status）
  - ASG不会立即停止受损状态的EC2，相反会等一段时间等EC2恢复，Amazon EC2 Auto Scaling 还可能会延迟或不终止无法报告状态检查数据的实例。 当 Amazon CloudWatch 中的状态检查指标数据不足时，通常会发生这种情况。
- 实例的健康检查宽限期（grace period）尚未到期

### 57. 在us-east-1有一个S3，想要在us-west-1也有一个S3，然后数据和east用同样的key加密解密
- 在 us-east-1 区域中创建一个新的 Amazon S3 存储桶，并启用从该新存储桶到 us-west-1 区域中的另一个存储桶的复制。 使用 AWS KMS 多区域密钥对 us-east-1 区域中的新存储桶启用 SSE-KMS 加密。 将现有数据从 us-east-1 区域中的当前 Amazon S3 存储桶复制到 us-east-1 区域中的新 Amazon S3 存储桶中

### 58. EC2运行在ALB后，最外层有CloudFront，想要吧认证流程结偶，怎么费力最少
- Use Amazon Cognito Authentication via Cognito User Pools for your Application Load Balancer
- 为什么是Cognito User Pools 而不是 Identity Pool
  - User Pools：用户通过可以Cognito或者第三方的认证系统sign in到app
  - Identity Pool：用户可以获临时的AWS credentials去接入AWS服务，比如s3，dynamodb
 
### 59. 有多个AWS账号在同一个组织下，想要吧多个account的EC2进行私有访问，怎么办
- 在一个account A中创建一个VPC，使用Resource Access Manager与其他account分享一个或者多个account A的子网
- Resource Access Manager可以轻松且安全的在一个或者多个account，或者多个aws organization分享Aws的的资源，
  - 比如AWS Transit Gateways, Subnets, AWS License Manager configurations, and Amazon Route 53 Resolver rules resources with RAM
  - ![image](https://github.com/YoungDriverOfTech/LearningJava/assets/55799836/92e2595f-1c34-4f94-ac85-ae0719640691)
  - ![image](https://github.com/YoungDriverOfTech/LearningJava/assets/55799836/b0ab025b-e312-4d5a-b8ca-859bdc56f365)



### 60. 某公司在多个账户中拥有多个 Amazon Virtual Private Cloud (Amazon VPC)，这些 VPC 需要相互连接成星型网络，并通过 AWS Direct Connect 与本地网络连接
- AWS Transit Gateway 是一项服务，使客户能够将其 Amazon Virtual Private Cloud (VPC) 及其本地网络连接到单个网关。
- 借助 AWS Transit Gateway，您只需创建和管理从中央网关到网络中每个 Amazon VPC、本地数据中心或远程办公室的单个连接。
- Transit Gateway 充当枢纽，控制如何在所有连接的网络（充当辐条）之间路由流量。
  - 如果没有Transit Gateway
    - ![image](https://github.com/YoungDriverOfTech/LearningJava/assets/55799836/f985a87c-a8e6-40cf-823a-1dbf085598d2)
  - 如果有Transit Gateway
    - ![image](https://github.com/YoungDriverOfTech/LearningJava/assets/55799836/7a83601e-8459-4050-a3b4-0a660d192ba5)

