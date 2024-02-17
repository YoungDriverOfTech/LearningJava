# AWS
## IAM
[IAM](./IAM.md)

## EC2
[EC2](./EC2.md)

## High Availability & Scalability
[LB](./LB.md)

## Database
[Database](./Database.md)

## Route53
[Route53](./Route53.md)

## Beanstalk
[Beanstalk](./Beanstalk.md)

## Amazon S3
[S3](./S3.md)

## Cloud Front & Global Accelerator
[Cloud Front](./CloudFront.md)

## AWS Storage Extras（存储附加功能）
[Storage Extras](./StorageExtras.md)

## Messages
[Messages](./Messages.md)

## Container
[Container](./Container.md)

## SSA Exam
[practice 1](./ssaexam/practice1.md)  
[practice 2](./ssaexam/practice2.md)  
[practice 3](./ssaexam/practice3.md)  
[practice 4](./ssaexam/practice4.md)  
[practice 5](./ssaexam/practice5.md)  

```java
int a = 0;

App b = new App();
b.setId(20);



public void methodA() {
    int a = 10;
    
    // App b，会在stack内生成一个引用（指针），他的值是 new App()在内存的地址值
    // new App()会在heap中生成一个真正的对象，然后把对象的地址值赋值给等号左边的变量
    App b = new App();
    
    // 调用别的方法的时候，会把变量复制一份，传递过去
    methodB(a, b);
}

public void methodB(int a, int b) {
    // 接受到的变量是复制的值
    System.out.print(a);    
}
```

