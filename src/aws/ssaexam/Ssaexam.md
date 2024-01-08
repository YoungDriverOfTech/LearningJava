# Practice 1
## Points

### 1. ASG scaling policy
- target tracking scaling policy：可以根据【CPU使用率】/【ALB的目标组的单台服务器的最大请求数】等指标来进行伸缩
  - ![img.png](img.png)
- step/simple scaling policy：设定指标临界值，CloudWatch检测到临界值被打破后，触发伸缩。
  - 这种的policy不能基于CPU的目标使用率
- ASG不能直接使用CloudWatch去观测CPU使用率

### 2. AWS WAF(Web Application Firewall)
- AWS WAF可以监视请求和保护web app，使用WAF可以屏蔽或着允许ip访问你的程序
  - Geographic (Geo) Match允许你根据访问者的地理位置进行限制
- CloudFront的Geo Restriction可以根据用户的地区限制流量，但是CLoudFront在edge location工作，不属于某一个VPC
- Security group不能根据地理位置进行访问的限制

### 3. Integrate data files between on-premises and AWS cloud through NFS interface
- AWS Storage Gateway：给本地权限去接入AWS云存储（S3之类），有3种不同类型
  - File Gateway：支持SMB / NFS接入
  - Volume Gateway：不支持NFS接入，支持配置Volume网关口，提供ISCSI存储快都本地app
  - Tape Gateway：磁带网关允许将磁带备份移动到云。 磁带网关不支持 NFS 接口
- AWS Site-to-Site VPN：可以讲本地网络和AWS VPC相连，可以使用Site-to-Site VPN把本地数据扩展到云，这使用的是两个站点之间建立的互联网协议安全 (IPSec)隧道，不支持NFS

### 4. File Server
- Amazon FSx for Windows File Server: 完全托管的，高可用的文件存储，基于Windows Server构建
  - 提供：用户配置，终端用户文件存储，AD（Microsoft Active Directory）整合
- Amazon FSx for Lustre：高性能文件系统，用于机器学习，高性能计算，影像处理，金融模型。不支持微软DFS
- AWS Directory Service for Microsoft Active Directory (AWS Managed Microsoft AD)
  - AWS Managed Microsoft AD 基于实际的 Microsoft Active Directory 构建，不需要您将数据从现有 Active Directory 同步或复制到云。 AWS Managed Microsoft AD 不支持 Microsoft 的分布式文件系统 (DFS)

### 5. 