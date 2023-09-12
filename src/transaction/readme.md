# Spring 集成 MyBatis 与事务管理
## Spring集成Mybatis

### 步骤
- 第 1 步：准备数据表，使用 account 表
- 第 2 步：新建模块并引入依赖
  - spring-context
  - spring-jdbc
  - mysql 驱动
  - mybatis
  - mybatis-spring：mybatis 提供的与 spring 框架集成的依赖
  - druid 连接池
  - Junit
  - mybatis-generator 插件
- 第 3 步：创建包和类
- 第 4 步：创建 mapper 接口、mapper.xml 映射文件、pojo 类（使用 mybatis 插件自动创建）
- 第 5 步：编写 jdbc.properties 配置文件
- 第 6 步：编写 DataSource（数据源） 配置类
![img.png](images/img.png)
- 第 7 步：编写 MyBatis 配置类
![img_1.png](images/img_1.png)
- 第 8 步：编写 Spring 核心配置类
![img_2.png](images/img_2.png)

## Spring事务管理
### 步骤
- 第 1 步：配置事务管理器 bean
![img_3.png](images/img_3.png)
- 第 2 步：配置开启事务的注解支持，会自动加载事务管理器
![img_4.png](images/img_4.png)
- 第 3 步：在目标方法上增加 @Transactional 注解
![img_5.png](images/img_5.png)

### 思考：同一个类内直接调用标有 @Transactional 的方法，事务是否生效？
![img_6.png](images/img_6.png)

同一个类内直接调用，事务不生效。因为同一个类内调用，调用的是非代理类的方法。也即没有进行 AOP 增强，@Transactional 注解也就不生效。  
两种解决方案：  

方案 1：属性注入当前 bean 本身
![img_7.png](images/img_7.png)
![img_8.png](images/img_8.png)


方案 2：获取当前 bean 的代理类对象
![img_9.png](images/img_9.png)
![img_10.png](images/img_10.png)
![img_11.png](images/img_11.png)

方案 3：写代码的时候避免类之内的方法相互调用，写成类之间的方法相互调用