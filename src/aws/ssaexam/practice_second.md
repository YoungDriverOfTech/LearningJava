# Practice 1
## S3Ta 加速文件上传
如果上传完发现没有被加速，那么就不需要付加速的费用。S3Ta只会请求被加速上传的部分

## GuardDuty可以对哪些服务进行可靠性check
![img_85.png](img_85.png)

## 当复制一个AMI镜像从一个region到另一个region的时候
当新的 AMI 从区域 A 复制到区域 B 时，它会自动在区域 B 中创建快照，因为 AMI 基于底层快照。
如果在B region中创建了一个instance，那么就会有3个新的资源来B region：AMI，snapshot，instance

## 给DynamoDB做缓存有限DAX（Amazon DynamoDB Accelerator (DAX)）
虽然用Redis也可以，但是会更复杂，题目一般要求更加的快捷，所以用DAX

## Lambda支持的并发数量
AWS Lambda 目前支持每个区域每个 AWS 账户 1000 个并发执行，如果超过需要联系support提高limit

## ASG的max/min/desired数量
- 如果想要一个固定数量：desired
- 如果想要一个数量范围：min - max

## Practice 2
## S3在2个AZ中的桶，一个是另一个的副本，然后必须使用同样的key来加密解密
Create a new Amazon S3 bucket in the us-east-1 region with replication enabled from this new bucket into another bucket in us-west-1 region. Enable SSE-KMS encryption on the new bucket in us-east-1 region by using an AWS KMS multi-region key. Copy the existing data from the current Amazon S3 bucket in us-east-1 region into this new Amazon S3 bucket in us-east-1 region

在 us-east-1 区域中创建一个新的 Amazon S3 存储桶，并启用从该新存储桶到 us-west-1 区域中的另一个存储桶的复制。使用 AWS KMS 多区域密钥对 us-east-1 区域中的新存储桶启用 SSE-KMS 加密。将现有数据从 us-east-1 区域中的当前 Amazon S3 存储桶复制到 us-east-1 区域中的新 Amazon S3 存储桶中


## ASG停止EC2 顺序
1. on-demand or spot instance
2. oldest launch template（如果使用了configuration，那么先停止oldest launch configuration）
3. oldest launch configuration
4. 下一个费用结算周期最近的实例

## 本地机器和EFS API相连
To establish a private connection between your virtual private cloud (VPC) and the Amazon EFS API, you can create an interface VPC endpoint. You can also access the interface VPC endpoint from on-premises environments or other VPCs using AWS VPN, AWS Direct Connect, or VPC peering.
AWS Direct Connect provides three types of virtual interfaces: public, private, and transit.

## RDS副本加密相关
On a database instance running with Amazon RDS encryption, data stored at rest in the underlying storage is encrypted, as are its automated backups, read replicas, and snapshots. Therefore, this option is correct.


## 下列policy代表
```json
{
  // 2 在看这个，只有当条件满足的时候，这个policy才生效
  // 意味着，当ec2的区域不是us-west-1，这个才生效，和1结合起来就是，
  // 关闭别的区域的ec2的时候，就生效，
  // 但是关闭us-west-1的时候，不生效。所以可以关闭us-west-1的实例
    "Version":"2012-10-17",
    "Id":"EC2TerminationPolicy",
    "Statement":[
        {
            "Effect":"Deny",
            "Action":"ec2:*",
            "Resource":"*",
            "Condition":{
                "StringNotEquals":{
                    "ec2:Region":"us-west-1"
                }
            }
        },
      // 1 先看这个，代表可以关闭所有的ec2
        {
            "Effect":"Allow",
            "Action":"ec2:TerminateInstances",
            "Resource":"*",
            "Condition":{
                "IpAddress":{
                    "aws:SourceIp":"10.200.200.0/24"
                }
            }
        }
    ]
}
```

## Amazon RDS MySQL 存储枯竭
Enable storage auto-scaling for Amazon RDS MySQL

## Practice 3
### DynamoDB发现脏数据后回滚
![img_86.png](img_86.png)

### SecurityGroup不能把Internet Gateway ID 当作target
一下是可以当作SG目标的资源
- 单个ip
- ip范围
- aws服务的前缀list id
- 另一个sg
![img_87.png](img_87.png)

### EC2的tenancy转换
dedicate 和 host相互转化 OK

## Practice 4
### DynamoDB改变了以后，发送email
使用DynamoDB stream + Lambda。
![img_88.png](img_88.png)
![img_89.png](img_89.png)

### identity-based policy vs resource-based policy
identity-based policy： 就是作用在user/account/organization上面的policy
resource-based policy： 作用在s3服务或者资源上面的，比如s3 桶和object

以上两种都支持的是trust policy，就是规定哪些人能用哪些服务。哪些服务可以被哪些人或者主体使用
![img_90.png](img_90.png)

### Disaster Recovery Strategies
- For Pilot light, RPO/RTO is in 10s of minutes
- For Backup and Restore, RPO in hours
- 
![img_91.png](img_91.png)
![img_92.png](img_92.png)
![img_93.png](img_93.png)
![img_94.png](img_94.png)
![img_95.png](img_95.png)

