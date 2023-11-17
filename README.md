# Sherioc05_ManagementSystem
# Java档案管理系统

## 课程目标

![1699576803100](C:\Users\张杰\AppData\Roaming\Typora\typora-user-images\1699576803100.png)

![1699576825846](C:\Users\张杰\AppData\Roaming\Typora\typora-user-images\1699576825846.png)

```java
封装、继承与多态	1．档案管理系统中相关类的创建与使用
2．系统各类用户继承结构中类的创建与使用
3．档案类继承结构中类、接口的创建与使用
异常处理	1．使用try-catch-finally进行异常的捕获处理 
2. 使用throw与throws子句产生、抛出异常
输入输出流	1. 文件与目录信息的获取和维护
2. 使用I/O流进行文件的拷贝
3. 读写基本类型数据和对象
GUI设计	1. 编写图形用户界面程序
2．添加事件代码程序
3．常用图形组件的使用与事件处理
4．菜单、对话框的使用
JDBC数据库操作	1．使用JDBC连接数据库
2．使用JDBC创建基本表和视图
3．使用JDBC执行数据库查询
4．使用JDBC更新数据库
网络编程	1．使用URL类访问网络资源 
2．档案管理系统Socket服务端程序设计 
3．档案管理系统Socket客户端程序设计
多线程	1．通过继承Thread类创建线程 
2．通过实现Runnable接口构造线程 
3．线程的互斥与同步

```



### 系统功能

![1699576771434](C:\Users\张杰\AppData\Roaming\Typora\typora-user-images\1699576771434.png)

```java
1.系统基于C/S模式，包括客户端子系统，服务器端子系统；
2.用户需登录，验证口令通过后才能使用系统。用户分为系统管理人员、档案录入人员，档案浏览人员；
3.系统中相关用户信息、档案属性存放于关系数据库中，档案本身以文件形式存放于服务器相关目录中；
4.客户端和服务器端基于Socket实现通信，服务器端需实现多线程功能，可同时处理、响应多个客户端的数据请求；
5.用户信息管理功能，普通用户登录后可对自己基本信息(用户名、密码、角色)的查询、修改（只能修改密码，不可修改用户名、角色）。
6.系统管理人员除上述功能外可增加、删除用户、修改用户的相关属性；
6.档案数据录入功能，档案录入人员可输入新的档案文件信息，并将档案文件上传至服务器
7.档案数据浏览功能，普通用户可查询相应的档案文件信息，对相关档案文件可以下载保存
```



## 问题

### CS/BS模式

GUI（图形用户界面）的CS模式和BS模式是指在软件系统中，客户端和服务器之间的交互方式。

CS模式（Client-Server模式）是指客户端和服务器之间直接建立连接，客户端发送请求给服务器，服务器处理请求并返回结果给客户端。在CS模式中，客户端负责用户界面的呈现和用户交互，服务器负责处理业务逻辑和数据存储。客户端和服务器之间通过网络进行通信。

BS模式（Browser-Server模式）是指客户端使用浏览器作为用户界面，通过HTTP协议向服务器发送请求，服务器处理请求并返回HTML页面给客户端浏览器进行展示。在BS模式中，客户端不需要安装额外的软件，只需要一个浏览器即可访问服务器上的应用程序。服务器负责处理业务逻辑和数据存储，生成HTML页面并发送给客户端浏览器。

CS模式和BS模式各有优缺点：

CS模式的优点是客户端可以提供更丰富的用户界面和交互体验，可以利用客户端的计算能力进行一部分业务处理，减轻服务器的负担。缺点是需要在不同的平台上开发和维护客户端软件，并且客户端需要安装和更新。

BS模式的优点是客户端使用浏览器作为用户界面，无需安装额外的软件，跨平台兼容性好。服务器端负责处理业务逻辑和数据存储，减轻了客户端的负担。缺点是用户界面和交互相对简单，无法提供与本地客户端相同的丰富体验。

选择使用CS模式还是BS模式，取决于具体的应用需求和技术考虑。如果需要较为复杂的用户界面和交互体验，以及对客户端计算能力的利用，可以选择CS模式。如果希望简化客户端的部署和维护，并且用户界面和交互相对简单，可以选择BS模式。

## 第一次实验

```java
项目结构：
|
|-src
    |
    |-DataProcessing.java 	数据处理类
    |-User.java				抽象类
    	|-Browser.java			子类-档案浏览者
		|-Administator.java		子类-管理员
    	|-Operator.java			子类-档案录入员
```

总结

1、Scanner

三个子类中都要使用Scanner对象进行输入，于是我多次创建关闭Scanner对象：

```java
//Administartor中的方法
void show(){
    Scanner scanner = new Scanner(System.in);
    ...
    scanner.close();
}
//Operator中的方法
void addUser(){
    Scanner scanner = new Scanner(System.in);
    ...
    scanner.close();
}
```

但运行时出现如下报错：

![1699590678224](C:\Users\张杰\AppData\Roaming\Typora\typora-user-images\1699590678224.png)

```java
原因：scanner.close()一旦调用就无法再次创建Scanner对象也无法继续输入
结局方案：
在main程序中定义静态变量
public static Scanner scanner = new Scanner(System.in)
在其他类中使用时只需调用Main.scanner.方法
Main.scanner.nextLine()
```

2、HashMap

```java
HashMap的方法：


```

## 第二次实验

```java
项目结构：
|
|-src
    |
    |-DataProcessing.java 	数据处理类
    |-User.java				抽象类
    	|-Browser.java			子类-档案浏览者
		|-Administator.java		子类-管理员
    	|-Operator.java			子类-档案录入员
```

总结

1、try-catch

在DataProcessing类中，并没有处理异常，而是直接抛出

```java
public static User searchUser(String name) throws SQLException{
	if( !connectToDB ) 
	throw new SQLException( "Not Connected to DB" );
		...//业务逻辑
}
```

在实体类中，对异常情况进行处理

```java
public boolean modifyUser() {
	...
    try {
        DataProcessing.update(name, password, role);
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
```

