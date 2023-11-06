# Container 容器技术
## Docker
### 什么是docker
- 部署app的软件开发平台
- app被打包成一个容器，可以在任何OS上运行
  - 任何机器
  - 没有兼容性问题
  - 可预测的行为
  - 更小的工作量
  - 更容易维护和部署
  - 适配任何语言
- 使用case：微服务架构，从本地服务器迁移app到aws云
![img.png](img.png)

### Docker image存放在哪里
- docker image 存放在docker仓库中
- Docker hub
  - 公有仓库
- Amazon ECR（Amazon Elastic Container Registry）
  - 私有仓库
  - 公有仓库(Amazon ECR Public Galler y https://galler y.ecr.aws)

### Docker vs VM
- DOcker是有一点虚拟化的技术，但不是完全是
- 资源和宿主机共享，许多容器在一台服务器
![img_1.png](img_1.png)

###