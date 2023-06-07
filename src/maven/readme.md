# Maven
## 概念以及作用
### 是什么
Maven是一个项目管理工具

### 有什么用
Maven可以对Java项目进行构建，依赖管理

### 什么是构建（build）
构建是以源代码（例如java代码，配置文件）为原材料，生产出可以运行的项目（例如war包）的过程

## 下载/安装

1. 打开maven官网，下载maven
2. 将压缩包移动到目标文件家（~/Library）后解压缩
3. 配置环境变量，编辑~/.bash_profile
    > export M2_HOME=/Users/xxx/Library/apache-maven-3.9.1  
   > export PATH=$PATH:$M2_HOME/bin
4. 执行mvn -version验证maven安装完成,(没有配置环境变量的话，就拿bin目录执行./mvn -version)

## 基本概念-坐标
- 坐标的作用-用来描述资源在仓库中的位置，maven根据坐标来定位资源
- 组成部分
  - groupId：组id，隶属的组织名称，通常是域名反写
  - artifactId：项目名称
  - version：版本号

## 配置仓库
### 配置本地仓库
- 通常不需要配置，IDEA初次使用maven时，会自动创建
- 默认的本地仓库是~/.m2/repository，M2_HOME/conf/settings.xml里配置仓库目录
- 如果要手动配置本地仓库，到~/目录下面输入mvn命令，则会自动创建一个仓库

### 远程仓库配置
- 可以到M2_HOME/conf/settings.xml搜索mirror字段
