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

### 13. 







