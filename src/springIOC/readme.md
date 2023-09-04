# IOC
## JavaBean

### 谁创建javabean
Controller 层的 Servlet 对象是由 Tomcat 创建的，Service 层和 Dao 层的对象由来创建呢？  
Q：如果我们在依赖这些对象的类中创建, 有什么问题？  
A：高层模块依赖底层模块，违反了依赖倒置原则。
![img.png](../../images/createBeanBySelf.png)

 ![img.png](../../images/yilaidaozhi.png)
 
### 什么是依赖倒置
依赖倒置原则，DIP（Dependency Inversion Principle）。DIP原则是指高层模块不应该依赖于底层模块，它们都应该依赖于抽象。面向接口编程是 DIP 的一种实现方式。
![img.png](../../images/dip.png)

## IOC思想：由第三方来创建javabean
![img.png](../../images/createbeanbythirdparty.png)
![img.png](../../images/getimplclass.png)
![img.png](../../images/ijf.png)

- 为了满足 DIP 原则，我们把创建对象的控制权交给了第三方。Java 程序员完成类的定义，第三方获取类定义，通过反射创建对象并完成依赖注入（DI），这种思想叫做 IoC。
- IoC，Inverse of Control，控制反转。指控制权的转移，将对象的创建和管理交给容器来完成，而不是由程序员来完成。
- DI，Dependency Injection，依赖注入。指将一个对象所依赖的其他对象通过构造函数、属性或方法参数的方式传递给它，通过注入的方式实现依赖关系的管理，DI 是实现 IoC 的一种手段。
- Spring 是一个基于 IoC 的框架，换句话说，Spring 帮 Java 程序员完成了对象的创建和管理，让 Java 程序员能更专注于业务功能的实现。

**DIP、IoC、DI、Spring 的关系**
![img.png](../../images/relationship.png)
- DIP，Dependency Inversion Principle，依赖倒置原则。指高层模块不应该依赖于底层模块，它们都应该依赖于抽象。
- IoC， Inverse of Control，控制反转。指控制权的转移，将对象的创建和管理交给容器来完成，而不是由程序员。IoC 是实现 DIP 的一种手段。
- DI， Dependency Injection，依赖注入。指将一个对象所依赖的其他对象通过构造函数、属性或方法参数的方式传递给它，通过注入的方式实现依赖关系的管理，DI 是实现 IoC 的一种手段。
- Spring 是一个基于 IoC 的框架，Spring 帮 Java 程序员完成了对象的创建和管理。IoC 是 Spring框架最底层的核心。

## 循环依赖
### 概念
什么叫循环依赖？A 依赖 B，B 依赖 A，依赖关系形成一个环。一旦执行程序会overFlowStack
![img.png](../../images/circaldependency.png)

### 原因分析
![img.png](../../images/reason.png)

### 解决思路
增加二级缓存map，bean 对象创建完成之后就放入二级缓存map，属性注入完成后再放入一级缓存map（ApplicationContext）。
![img.png](../../images/level2cache.png)

![img.png](../../images/solution.png)