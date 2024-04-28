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

## 