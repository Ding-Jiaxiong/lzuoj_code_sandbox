### OJ在线判题系统项目



前端：https://github.com/Ding-Jiaxiong/lzu_oj_frontend

后端：https://github.com/Ding-Jiaxiong/lzu_oj_backend

微服务后端：https://github.com/Ding-Jiaxiong/lzuoj-backend-microservice

代码沙箱：https://github.com/Ding-Jiaxiong/lzuoj_code_sandbox



#### 1. 项目介绍



基于Vue3+Spring Boot+Spring Cloud微服务+Docker的编程题目在线评测系统(简称` OJ `)



在系统前台，管理员可以创建、管理题目；用户可以自由搜索题目、阅读题目、编写并提交代码。

在系统后端，能够根据管理员设定的题目测试，用例在 代码沙箱 中对代码进行编译、运行、判断输出是否正确。

其中，代码沙箱可以作为独立服务，提供给其他开发者使用。



**技术栈**



- 前端
    - Vue 3
    - Vue-CLI脚手架
    - Vuex 状态管理
    - Arco Design组件库
    - 前端工程化：ESLint+Prettier+TypeScript
    - ☆手写前端项目模板（通用布局、权限管理、状态管理、菜单生成）
    - ☆Markdown富文本编辑器
    - ☆Monaco Editor代码编辑器
    - ☆OpenAPI前端代码生成
- 后端
    - ☆Java Spring Cloud+Spring Cloud Alibaba微服务
        - Nacos注册中心
        - OpenFeign客户端调用
        - GateWay网关
        - 聚合接口文档
    - Java Spring Boot (万用后端模板)
    - Java进程控制
    - ☆ Java安全管理器
    - ★Docker代码沙箱实现
    - ★虚拟机+远程开发
    - MySQL数据库
    - MyBatis-Plus及MyBatis X自动生成
    - Redis分布式Session
    - ★RabbitMQ消息队列
    - ★多种设计模式
        - 策略模式
        - 工厂模式
        - 代理模式
        - 模板方法模式
    - 其他：部分并发编程、`JVM`小知识



##### 1.1 OJ 系统常用概念



###### 1.1.1 项目介绍



`OJ` = Online Judge在线判题评测系统

用户可以选择题目，在线敏题，编写代码并且提交代码：系统会对用户提交的代码，根据我们出题人设置的答案，来判断用户的提交结果是否正确。

ACM(程序设计竞赛)，也是需要依赖判题系统来检测参赛者的答案是否合理。



北大OJ：http://poj.org/



![image-20240715184625316](./assets/image-20240715184625316.png)



`OJ`系统最大的难点在于判题系统

用于在线评测编程题目代码的系统，能够根据用户提交的代码、出题人预先设置的题目输入和输出用例，进行编译代码、运行代码、判断代码运行结果是否正确。

判题系统作为一个开放`API`提供给大家，便于开发者开发自己的`OJ`系统。



###### 1.1.2 OJ 系统常用概念



`ac`表示你的题目通过，结果正确

题目限制：时间限制、内存限制

题目介绍

题目输入

题目输出
题目输入用例

题目输出用例

普通测评：管理员设置题目的输入和输出用例，比如我输入1，你要输出2才是正确的；交给判题机去执行用户的代码，给用户的代码喂输入用例，比如1，看用户程序的执行结果是否和标准答案的输出一致。



（比对用例文件）



特殊测评(S)：管理员设置题目的输入和输出，比如我输入1，用户的答案只要是>0或<2都是正确的；特判程序，不是通过对比用例文件是否一致这种死板的程序来检验，而是要专门根据这道题目写一个特殊的判断程序，程序接收题目的输入(1)、标准输出用例(2)、用户的结果(1.5)，特判程序根据这些值来比较是否正确。

交互测评：让用户输入一个例子，就给一个输出结果，交互比较灵活，没办法通过简单的、死板的输入输出文件来搞定



------



不让用户随便引入包、随便遍历、暴力破解，需要使用正确的算法。=>安全性

判题过程是异步的=>异步化

提交之后，会生成一个提交记录，有运行的结果以及运行信息（时间限制、内存限制）



**为什么做这个项目**



- 新颖，区分度
- 这个项目的CRUD成分很少，更多的在于一些编程思想、计算机基础、架构设计方面的知识
- 复杂度高
- 可扩展性强





##### 1.2 企业项目开发流程、主流 OJ 系统调研



###### 1.2.1 项目开发流程



1. 项目介绍、项目调研、需求分析
2. 核心业务流程
3. 项目要做的功能（功能模块）
4. 技术选型（技术预研）
5. 项目初始化
6. 项目开发
7. 测试
8. 优化
9. 代码提交、代码审核
10. 产品验收
11. 上线



写文档、持续调研、持续记录总结



###### 1.2.2 主流 OJ 系统调研



- https://github.com/HimitZH/HOJ【适合学习】
- https://github.com/QingdaoU/OnlineJudge【python，不好学，很成熟】
- https://github.com/hzxie/voj【星星没那么多，没那么成熟，但相对好学】
- https://github.com/vfleaking/uoj【PHP】
- https://github.com/zhblue/hustoj【成熟，但是PHP】
- https://github.com/hydro-dev/Hydro【功能强大，`Node.js` 实现】



##### 1.3 核心实现模块介绍、核心业务流程、系统功能梳理、技术选型



###### 1.3.1 实现核心



1. 权限校验

   谁能提代码，谁不能提代码

2. 代码沙箱（安全沙箱）

    - 用户代码藏毒：写个木马文件、修改系统权限

    - 沙箱：隔离的、安全的环境，用户的代码不会影响到沙箱之外的系统的运行

    - 资源分配：系统的内存就2个G,用户疯狂占用资源占满你的内存，其他人就用不了了。所以要限制用户程序的占用资源。

3. 判题规则

   题目用例的比对，结果的验证

4. 任务调度

   服务器资源有限，用户要排队，按照顺序去依次执行判题，而不是直接拒绝



###### 1.3.2 核心业务流程



![image-20240715190858208](./assets/image-20240715190858208.png)



为什么要编译？因为有些语言不编译不能运行



![image-20240715190946514](./assets/image-20240715190946514.png)



判题服务：获取题目信息、预计的输入输出结果，返回给主业务后端：用户的答案是否正确

代码沙箱：只负责运行代码，给出结果，不管什么结果是正确的。



**实现解耦**



###### 1.3.3 系统功能



1. 题目模块
    1. 创建题目（管理员）
    2. 删除题目（管理员）
    3. 修改题目（管理员）
    4. 搜索题目（用户）
    5. 在线做题
    6. 提交题目代码
2. 用户模块
    1. 注册
    2. 登录
3. 判题模块
    1. 提交判题(结果是否正确与错误)
    2. 错误处理（内存溢出、安全性、超时）
    3. 自主实现 代码沙箱（安全沙箱）
    4. 开放接口（提供一个独立的新服务）



> 项目扩展点：
>
> - 支持多种语言
> - Remote Judge
> - 完善的评测功能：普通测评、特殊测评、交互测评、在线自测、子任务分组评测、文件IO
> - 统计分析用户判题记录
> - 权限校验



###### 1.3.4 技术选型



前端：Vue3、Arco Design组件库、手撸项目模板、在线代码编辑器、在线文档浏览

Java进程控制、Java安全管理器、部分JVM知识点

虚拟机（云服务器）、Docker(代码沙箱实现)

Spring Cloud微服务、消息队列、多种设计模式



##### 1.4 系统架构设计（架构设计图）



###### 1.4.1 架构设计



![image-20240715192220429](./assets/image-20240715192220429.png)



##### 1.5 OJ 系统实现方案（5种）



开发原则：能用别人现成的，就不要自己写



###### 1.5.1 用现成的OJ系统



网上有很多开源的`OJ`项目，比如青岛`OJ`、`HustOJ` 等，可以直接下载开源代码自己部署。



https://github.com/judge0/judge0



###### 1.5.2 用现成的服务



如果你不希望完整部署一套大而全的代码，只是想复用他人已经实现的、最复杂的判题逻辑，那么可以直接使用现成的判题`API`、或者现成的代码沙箱等服务：

比如`judge0`提供的判题APl,非常方便易用。只需要通过HTTP调用`submissions`判题接口，把用户的代码、输入值、预期的执行结果作为请求参数发送给`judge0`的服务器，它就能自动帮你编译执行程序，并且返回程序的运行结果



API 地址：https://rapidapi.com/judge0-official/api/judge0-ce

官方文档：https://ce.judge0.com/#submissions-submission-post



流程：

1. 注册
2. 开通订阅
3. 测试 language 接口
4. 测试执行代码接口 submissions



###### 1.5.3 自主开发



判题服务和代码沙箱都要自己实现，适合学习，但不适用于商业项目。



###### 1.5.4 把AI当做代码沙箱



把`AI`当做代码沙箱，我们直接扔给他一段代码、输入参数，问他能否得到预期的结果，就实现了在线判题逻辑



只要你脑洞够大，`AI + 编程 = 无限的可能`



###### 1.5.5 移花接木



通过让程序来操作模拟浏览器的方式，用别人已经开发好的`OJ`系统来帮咱们判题



比如使用Puppeteer+无头浏览器，把咱们系统用户提交的代码，像人一样输入到别人的 OJ 网页中，让程序点击提交按钮，并且等别人的 OJ 系统返回判题结果后，再把这个结果返回给我们自己的用户。

这种方式的缺点就是把核心流程交给了别人，如果别人服务挂了，你的服务也就挂了；而且别人 OJ 系统不支持的题目，可能你也支持不了。



#### 2. 项目开发流程



##### 2.1 前端项目初始化



###### 2.1.1 Vue-CLI 初始项目搭建、组件库引入



**确认环境**



我的环境是：

- `Node`：`18.16.0`

  ![image-20240715193730405](./assets/image-20240715193730405.png)

- `npm`：`9.5.1`

  ![image-20240715193755497](./assets/image-20240715193755497.png)





**初始化**



使用`vue-cli` 脚手架：https://cli.vuejs.org/zh/，安装命令：`npm install -g @vue/cli`



![image-20240715193927163](./assets/image-20240715193927163.png)



创建项目：`vue create xiongoj_frontend`



![image-20240715194245444](./assets/image-20240715194245444.png)



![image-20240715194519293](./assets/image-20240715194519293.png)



`webstorm` 打开



![image-20240715194625004](./assets/image-20240715194625004.png)



> 依赖都不用自己装



运行



![image-20240715194703549](./assets/image-20240715194703549.png)



![image-20240715194739810](./assets/image-20240715194739810.png)



访问呢：



![image-20240715194753673](./assets/image-20240715194753673.png)



没问题



**前端工程化配置**



脚手架已经帮我们配置了代码美化、自动校验、格式化插件等，无需再自行配置

但是需要在`webstorm`里开启代码美化插件：



![image-20240715194904680](./assets/image-20240715194904680.png)



![image-20240715194957538](./assets/image-20240715194957538.png)



Eslint 默认开启

在`vue`文件中执行格式化快捷键，不报错，表示配置工程化成功

脚手架自动整合了vue-router



> 自己整合
>
> - 代码规范：https://eslint.org/docs/latest/use/getting-started
> - 代码美化：https://prettier.io/docs/en/install.html
> - 直接整合：https://github.com/prettier/eslint-plugin-prettier#recommended-configuration



**引入组件**



Vue Router路由组件已自动引入，无需再引入：https://router.vuejs.org/zh/introduction.html



组件库：https://arco.design/vue/docs/start

快速上手：https://arco.design/vue/docs/start



执行 `npm install --save-dev @arco-design/web-vue` 安装：



![image-20240715195316592](./assets/image-20240715195316592.png)



修改`main.ts`，全量引入



![image-20240715195503205](./assets/image-20240715195503205.png)



引入一个组件，如果显示出来，就表示引入成功



![image-20240715195610499](./assets/image-20240715195610499.png)



###### 2.1.2 项目通用布局开发及优化



新建一个布局，

![image-20240715200913950](./assets/image-20240715200913950.png)



在app.vue中引入，`app.vue` 代码如下：



![image-20240715201050936](./assets/image-20240715201050936.png)



选用arco design的`layout` 组件：https://arco.design/vue/component/layout

先把上中下布局编排好，然后再填充内容：



![image-20240715201144698](./assets/image-20240715201144698.png)



![image-20240715210137384](./assets/image-20240715210137384.png)



**实现通用路由菜单**

菜单组件：https://arco.design/vue/component/menu

目标：根据路由配置信息，自动生成菜单内容。实现更通用、更自动的菜单配置。



步骤：

1. 提取通用路由文件
2. 菜单组件读取路由，动态渲染菜单项
3. 绑定跳转事件
4. 同步路由的更新到菜单项高亮

同步高亮原理：首先点击菜单项=>触发点击事件，跳转更新路由=>更新路由后，同步去更新菜单栏的高亮状态。

使用Vue Router的`afterEach`路由钩子实现：



![image-20240715211317423](./assets/image-20240715211317423.png)



![image-20240715211325137](./assets/image-20240715211325137.png)





###### 2.1.3 全局状态管理



vuex：https://vuex.vuejs.org/zh/guide/



什么是全局状态管理？

所有页面全局共享的变量，而不是局限在某一个页面中。

适合作为全局状态的数据：已登录用户信息（每个页面几乎都要用）

vuex 的本质：给你提供了一套增删改查全局变量的`API`,只不过可能多了一些功能（比如时间旅行）



![image-20240715211436808](./assets/image-20240715211436808.png)



> 可以直接参考购物车示例：https://github.com/vuejs/vuex/tree/main/examples/classic/shopping-cart



state:存储的状态信息，比如用户信息

mutation(尽量同步)：定义了对变量进行增删改（更新）的方法

actions(支持异步)：执行异步操作，并且触发mutation的更改(actions调用mutation)

modules(模块)：把一个大的state(全局变量)划分为多个小模块，比如user专门存用户的状态信息



**实现**

先在store目录下定义user模块，存储用户信息：



![image-20240715211707257](./assets/image-20240715211707257.png)



然后在store目录下定义index.ts文件，导入user模块：



![image-20240715211735585](./assets/image-20240715211735585.png)



在Vue页面中可以获取已存储的状态变量：



![image-20240715212830689](./assets/image-20240715212830689.png)



在Vue页面中可以修改状态变量：

使用dispatch来调用之前定义好的actions：



![image-20240715213123085](./assets/image-20240715213123085.png)





###### 2.1.4 全局权限管理



**权限管理**



目标：能够直接以一套通用的机制，去定义哪个页面需要那些权限。而不用每个页面独立去判断权限，提高效率。

思路：

1. 在路由配置文件，定义某个路由的访问权限
2. 在全局页面组件app.vue中，绑定一个全局路由监听。每次访问页面时，根据用户要访问页面的路由信息，先判断用户是否有对应的访问权限。
3. 如果有，跳转到原页面；如果没有，拦截或跳转到401鉴权或登录页



示例代码如下：



![image-20240715214601905](./assets/image-20240715214601905.png)



这里稍微不一样，按照代码提示敲得，能用



![image-20240715214535182](./assets/image-20240715214535182.png)



![image-20240715214543919](./assets/image-20240715214543919.png)





###### 2.1.5 通用菜单组件开发



**优化页面布局**



1. 底部footer布局优化

   ![image-20240715214943130](./assets/image-20240715214943130.png)

2. 优化content、globalHeader的样式

   ![image-20240715215520320](./assets/image-20240715215520320.png)

3. 优化导航栏用户名称的换行

   ![image-20240715215031903](./assets/image-20240715215031903.png)

   ![image-20240715215233177](./assets/image-20240715215233177.png)





**通用导航栏组件** - 根据配置控制菜单的显隐



1. routes.ts给路由新增一个标志位，用于判断路由是否显隐

   ![image-20240715215959494](./assets/image-20240715215959494.png)

2. 不要用`v-if` + `v-for`去条件渲染元素，这样会先循环所有的元素，导致性能的浪费

   推荐：先过滤只需要展示的元素数组

   ![image-20240715220239231](./assets/image-20240715220239231.png)





**根据权限隐藏菜单**



需求：只有具有权限的莱单，才对用户可见

原理：类似上面的控制路由显示隐藏，只要判断用户没有这个权限，就直接过滤掉



1. 新建access目录，专门用一个文件来定义权限

   ![image-20240715220511732](./assets/image-20240715220511732.png)

2. 定义一个公用的权限校验方法

   为什么？因为菜单组件中要判断权限、权限拦截也要用到权限判断功能，所以抽离成公共方法
   创建`checkAccess.ts`文件，专门定义检测权限的函数：

   ![image-20240715220745796](./assets/image-20240715220745796.png)

3. 修改GlobalHeader动态菜单组件，根据权限来过滤菜单

   注意，这里使用计算属性，是为了当登录用户信息发生变更时，触发莱单栏的重新渲染，展示新增权限的菜单项

   ![image-20240715221750069](./assets/image-20240715221750069.png)





###### 2.1.6 全局项目入口



`app.vue`中预留一个可以编写全局初始化逻辑的代码：



![image-20240715221903599](./assets/image-20240715221903599.png)



![image-20240715221925258](./assets/image-20240715221925258.png)



##### 2.2 后端项目初始化



###### 2.2.1 Spring Boot 万用模板讲解



这里仍然用的`2.7.2` 版本的，即新版模板



![image-20240716144316632](./assets/image-20240716144316632.png)



然后就是改改改



![image-20240716144511649](./assets/image-20240716144511649.png)



帅气banner



![image-20240716144757632](./assets/image-20240716144757632.png)



包名和mapper



创建数据库：`xiongoj`



![image-20240716144833421](./assets/image-20240716144833421.png)



![image-20240716144948296](./assets/image-20240716144948296.png)



现在就可以启动了



![image-20240716145117459](./assets/image-20240716145117459.png)



还要改下版本



![image-20240716145138797](./assets/image-20240716145138797.png)



OK



![image-20240716145209325](./assets/image-20240716145209325.png)



8101 端口启动了，访问接口文档



![image-20240716145247962](./assets/image-20240716145247962.png)



没问题

创建数据表



![image-20240716145358227](./assets/image-20240716145358227.png)





测试

创建用户



![image-20240716145425594](./assets/image-20240716145425594.png)



![image-20240716145442343](./assets/image-20240716145442343.png)



![image-20240716145518953](./assets/image-20240716145518953.png)



用户登录



![image-20240716145534439](./assets/image-20240716145534439.png)



获取当前登录用户



![image-20240716145542202](./assets/image-20240716145542202.png)



没问题。



**初始化模板讲解**



1. 先阅读README.md
2. sql/create_table.sql定义了数据库的初始化建库建表语句
3. sql/post_es_mapping.json帖子表在ES中的建表语句
4. aop:用于全局权限校验、全局日志记录
5. common:万用的类，比如通用响应类
6. config:用于接收application.yml中的参数，初始化一些客户端的配置类（比如对象存储客户端）
7. constant:定义常量
8. controller:接受请求
9. esdao:类似mybatis的mapper,用于操作ES
10. exception:异常处理相关
11. job:任务相关（定时任务、单次任务）
12. manager:服务层（一般是定义一些公用的服务、对接第三方`API`等）
13. mapper:mybatis的数据访问层，用于操作数据库
14. mode:数据模型、实体类、包装类、枚举值
15. service:服务层，用于编写业务逻辑
16. utils:工具类，各种各样公用的方法
17. wxmp:公众号相关的包
18. test:单元测试
19. MainApplication:项目启动入口
20. Dockerfile:用于构建Docker镜像





##### 2.3 前后端联调



###### 2.3.1 前端请求代码生成



问：前端和后端怎么连接起来的？接口/请求

答：前端发送请求调用后端接口



【1】安装请求工具类Axios

官方文档：https://axios-http.com/docs/intro

安装命令：`npm install axios`



![image-20240716150425389](./assets/image-20240716150425389.png)



【2】编写调用后端的代码

传统情况下，每个请求者邵要单独编写代码。至少得写一个请求路径

完全不用！！！



自动生成：https://github.com/ferdikoomen/openapi-typescript-codegen

安装命令：`npm install openapi-typescript-codegen --save-dev`



![image-20240716150855793](./assets/image-20240716150855793.png)



执行代码生成命令：`openapi --input http://localhost:8101/api/v2/api-docs --output ./generated --client axios`



![image-20240716150937051](./assets/image-20240716150937051.png)



生成完成，这里我换个名字吧，`openapi --input http://localhost:8101/api/v2/api-docs --output ./xiongoj_backendapi --client axios`



![image-20240716151113654](./assets/image-20240716151113654.png)



这样稍微那啥一点



【3】直接使用，调用函数发送请求



![image-20240716151531991](./assets/image-20240716151531991.png)



这里生成的代码格式问题可以直接配置忽略掉



![image-20240716151620925](./assets/image-20240716151620925.png)



试一下



![image-20240716151715633](./assets/image-20240716151715633.png)



我们这里稍微有些问题，调的接口不太对



![image-20240716151816932](./assets/image-20240716151816932.png)



这里把这儿的api 删了



![image-20240716151851305](./assets/image-20240716151851305.png)



这样就行了，调用后端成功



如果想要自定义请求参数，怎么办？

【1】使用代码生成器提供的全局参数修改对象：





![image-20240716152024303](./assets/image-20240716152024303.png)



就我们刚刚改的这个



【2】直接定义axios请求库的全局参数，比如全局请求响应拦截器

文档：https://axios-http.com/docs/interceptors



![image-20240716152221681](./assets/image-20240716152221681.png)



然后再引入一下啊



![image-20240716152252052](./assets/image-20240716152252052.png)



这样就能生效了



![image-20240716152323451](./assets/image-20240716152323451.png)



没问题



###### 2.3.2 用户自动登录



**自动登录**



【1】在store\user.ts编写获取远程登陆用户信息的代码：



![image-20240716152459573](./assets/image-20240716152459573.png)



【2】在哪里去触发getLoginUser函数的执行？应当在一个全局的位置



有很多选择：

1. 路由拦截 `√`
2. 全局页面入口app.vue
3. 全局通用布局（所有页面都共享的组件）



此处选择第一种方案，可以直接在全局权限管理的路由拦截中判断用户是否已经登录了。



**全局权限管理优化**



【1】新建access\index.ts文件，把原有的格由拦截、权限校验逻辑放在独立的文件中

优势：只要不引入、就不会开启、不会对项目有影响

【2】编写权限管理和自动登录逻辑

如果没登陆过，自动登录：



![image-20240716153354454](./assets/image-20240716153354454.png)



如果用户访问的页面不需要登录，是否需要强制跳转到登录页？

答：不需要

access\index.ts示例代码：



![image-20240716153414861](./assets/image-20240716153414861.png)



###### 2.3.3 多套布局支持



【1】在routes路由文件中新建一套用户路由，使用vue-router自带的子路由机制，实现布局和嵌套路由



![image-20240716154003692](./assets/image-20240716154003692.png)



【2】新建UserLayout、UserLoginView、UserRegisterView页面，并且在routes中引入



![image-20240716154518177](./assets/image-20240716154518177.png)



【3】在app.vue根页面文件，根据路由去区分多套布局



![image-20240716154532119](./assets/image-20240716154532119.png)



注意，当前这种app.vue中通过if else区分布局的方式，不是最优雅的，理想情况下是直接读取routes.ts,在这个文件中定义多套布局，然后自动使用页面布局。



![image-20240716154907731](./assets/image-20240716154907731.png)



![image-20240716154918632](./assets/image-20240716154918632.png)





###### 2.3.4 用户登录注册页面开发



俩页面的核心都是表单，只需要从组件库中找到表单组件，修改表单字段名称和后端完全匹配就足够了。



![image-20240716155710303](./assets/image-20240716155710303.png)



开启携带 cookie



![image-20240716155750743](./assets/image-20240716155750743.png)



![image-20240716155802386](./assets/image-20240716155802386.png)





快速实现一下注册页面：

给登录页加上一个跳转



![image-20240716160900182](./assets/image-20240716160900182.png)



![image-20240716161203961](./assets/image-20240716161203961.png)



![image-20240716161212902](./assets/image-20240716161212902.png)



![image-20240716161224481](./assets/image-20240716161224481.png)



没问题。



##### 2.4 后端接口开发



###### 2.4.1 库表设计



**用户表**



这个直接用的模板的



![image-20240716161954810](./assets/image-20240716161954810.png)



**题目表**



题目标题

题目内容：存放题目的介绍、输入输出提示、描述、具体的详情

题目标签( json 数组字符串)：栈、队列、链表、简单、中等、困难

题目答案：管理员/用户设置的标准答案

提交数、通过题目的人数等：便于分析统计（可以考虑根据通过率自动给题目打难易度标签）

判题相关字段：

> 如果说题目不是很复杂，用例文件大小不大的话，可以直接存在数据库表里但是如果用例文件比较大，`> 512KB`建议单独存放在一个文件中，数据库中只保存文件`url`（类似存储用户头像]



- 输入用例：1、2
- 输出用例：3、4
- 时间限制
- 内存限制



judgeConfig判题配置Gson对象)：

- 时间限制timeLimit

- 内存限制memoryLimit

judgeCase判题用例（json数组）

- 每一个元素是：一个输入用例对应一个输出用例

  ![image-20240716162146971](./assets/image-20240716162146971.png)



存json的好处：便于扩展，只需要改变对象内部的字段，而不用修改数据库表（可能会影响数据库）



![image-20240716162227078](./assets/image-20240716162227078.png)



存json的前提：

1. 你不需要根据某个字段去倒查这条数据
2. 你的字段含义相关，属于同一类的值
3. 你的字段存储空间占用不能太大



其他扩展字段：

- 通过率
- 判题类型



建表 `sql`：



```sql
-- 题目表
create table if not exists question
(

    id          bigint auto_increment comment 'id' primary key,
    title       varchar(512)                       null comment '标题',
    content     text                               null comment '内容',
    tags        varchar(1024)                      null comment '标签列表（json 数组）',
    answer      varchar(1024)                      null comment '题目答案',
    submitNum   int      default 0                 not null comment '题目提交数',
    acceptedNum int      default 0                 not null comment '题目通过数',
    judgeCase   text                               null comment '判题用例（json 数组）',
    judgeConfig text                               null comment '判题配置（json 对象）',
    thumbNum    int      default 0                 not null comment '点赞数',
    favourNum   int      default 0                 not null comment '收藏数',
    userId      bigint                             not null comment '创建用户 id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)

) comment '题目' collate = utf8mb4_unicode_ci;
```



**题目提交表**



哪个用户提交了哪道题目，存放判题结果等

提交用户id:userld

题目id:questionld

语言：language

用户的代码：code

判题状态：status(0-待判题、1-判题中、2-成功、3-失败)

判题信息（判题过程中得到的一些信息，比如程序的失败原因、程序执行消耗的时间、空间）：

judgelnfo ( json 对象)



![image-20240716163008603](./assets/image-20240716163008603.png)



判题信息枚举值：

- Accepted成功
- Wrong Answer答案错误
- Compile Error编译错误
- Memory Limit Exceeded内存益出
- Time Limit Exceeded超时
- Presentation Error展示错误
- Output Limit Exceeded输出益出
- Waiting等待中
- Dangerous Operation危险操作
- Runtime Error运行错误（用户程序的问题）
- System Error系统错误（做系统人的问题）



建表`sql`：





```sql
-- 题目提交表
create table if not exists question_submit
(
    id         bigint auto_increment comment 'id' primary key,
    language   varchar(128)                       not null comment '编程语言',
    code       text                               not null comment '用户代码',
    judgeInfo  text                               null comment '判题信息（json 对象）',
    status     int      default 0                 not null comment '判题状态 (0-待判题、1-判题中、2-成功、3-失败) ',
    questionId bigint                             not null comment '题目 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_questionId (questionId),
    index idx_userId (userId)
) comment '题目提交';
```







###### 2.4.2 数据库索引知识、后端开发流程讲解、代码自动生成





**小知识 - 数据库索引**

什么情况下适合加索引？如何选择给哪个字段加索引？

答：首先从业务出发，无论是单个索引、还是联合索引，都要从你实际的查询语句、字段枚举值的区分度、字段的类型考虑(whr条件指定的字段)

比如：where userld=1 and questionld=2

可以选择根据userld和questionld分别建立索引（需要分别根据这两个字段单独查询）；也可以选择给这两个字段建立联合索引（所查询的字段是绑定在一起的）。

原则上：能不用索引就不用索引；能用单个索引就别用联合/多个索引；不要给没区分度的字段加索引（比如性别，就男/女）。因为索引也是要占用空间的。



直接创建吧，两个表



![image-20240716173538974](./assets/image-20240716173538974.png)



**后端接口开发流程**



1. 根据功能设计库表
2. 自动生成对数据库基本的增删改查(mapper和service层的基本功能)
3. 编写Controller层，实现基本的增删改查和权限校验（复制粘贴）
4. 去根据业务定制开发新的功能/编写新的代码





**代码生成方法**



1. 安装MyBatisX插件

2. 根据项目去调整生成配置，建议生成代码到独立的包，不要影响老的项目

   ![image-20240716174414392](./assets/image-20240716174414392.png)

   ![image-20240716174513164](./assets/image-20240716174513164.png)

3. 把代码从生成包中移到实际项目对应目录中

   ![image-20240716174642052](./assets/image-20240716174642052.png)





###### 2.4.3 开发题目相关接口



4. 找相似的代码去复制 Controller

    - 单表去复制单表Controller(比如question=>post)
    - 关联表去复制关联表（比如question_submit=>post_thumb)

   ![image-20240716175508090](./assets/image-20240716175508090.png)

5. 复制实体类相关的DTO、VO、枚举值字段（用于接受前端请求、或者业务间传递信息）

   复制之后，调整需要的字段

   updateRequest和editRequest的区别：前者是给管理员更新用的，可以指定更多字段；后者是给普通用户试用的，只能指定部分字段。

   ![image-20240716180606834](./assets/image-20240716180606834.png)

   ![image-20240716180624071](./assets/image-20240716180624071.png)

6. 为了更方便地处理json字段中的某个字段，需要给对应的json字段编写独立的类，比如judgeConfig、judgelnfo、judgeCase。



![image-20240716180630626](./assets/image-20240716180630626.png)



![image-20240716180636454](./assets/image-20240716180636454.png)



![image-20240716180844107](./assets/image-20240716180844107.png)





小知识：什么情况下要加业务前缀？什么情况下不加？

加业务前缀的好处，防止多个表都有类似的类，产生冲突；不加的前提，因为可能这个类是多个业务之间共享的，能够复用的。

定义`VO`类：作用是专门给前端返回对象，可以节约网络传输大小、或者过滤字段（脱致）、保证安全性。



![image-20240716181226737](./assets/image-20240716181226737.png)



比如judgeCase、answer字段，一定要删，不能直接给用户答案。



记得加上逻辑删除注解



![image-20240716181327184](./assets/image-20240716181327184.png)



7. 校验Controller层的代码，看看除了要调用的方法缺失外，还有无报错



![image-20240716181520888](./assets/image-20240716181520888.png)



8. 实现Service层的代码，从对应的已经编写好的实现类复制粘贴，全局替换（比如question=>post)



![image-20240716182148460](./assets/image-20240716182148460.png)





9. 编写QuestionVO的json 对象转换工具类



![image-20240716182337523](./assets/image-20240716182337523.png)



10. 用同样的方法，编写questionSubmit提交类，这次参考postThumb相关文件



![image-20240716182928822](./assets/image-20240716182928822.png)



11. 编写枚举类



![image-20240716183715386](./assets/image-20240716183715386.png)



![image-20240716184014915](./assets/image-20240716184014915.png)



![image-20240716184525756](./assets/image-20240716184525756.png)



修改实现类



![image-20240716194253051](./assets/image-20240716194253051.png)



编写好基本代码后，记得通过Swagger或者编写单元测试去验证。



创建题目：

![image-20240716194754742](./assets/image-20240716194754742.png)



![image-20240716194812381](./assets/image-20240716194812381.png)



创建成功

查询根据ID



![image-20240716195029515](./assets/image-20240716195029515.png)



获取列表



![image-20240716195140181](./assets/image-20240716195140181.png)



编辑

![image-20240716195228702](./assets/image-20240716195228702.png)



![image-20240716195237175](./assets/image-20240716195237175.png)



不应该这样编，应该只传标题和ID的



![image-20240716195330012](./assets/image-20240716195330012.png)



![image-20240716195341201](./assets/image-20240716195341201.png)



更细的修改



![image-20240716195524830](./assets/image-20240716195524830.png)



![image-20240716195538521](./assets/image-20240716195538521.png)



有点问题，judgeConfig 和 case 都没改



![image-20240716195647152](./assets/image-20240716195647152.png)



只设置了标签



![image-20240716195810844](./assets/image-20240716195810844.png)



其余接口同理



![image-20240716195839427](./assets/image-20240716195839427.png)



![image-20240716195908160](./assets/image-20240716195908160.png)



再试一次



![image-20240716195929845](./assets/image-20240716195929845.png)



数据库：

![image-20240716195943817](./assets/image-20240716195943817.png)



进去了



**小知识**



为了防止用户按照id顺序爬取题目，建议把id的生成规则改为ASSIGN_ID而不是从1开始自增，示例代码如下：

![image-20240716194502989](./assets/image-20240716194502989.png)



这里加了一个管理员用的方法，其实模板也有了



![image-20240716200135090](./assets/image-20240716200135090.png)



测试提交：



![image-20240716200256934](./assets/image-20240716200256934.png)



![image-20240716200307630](./assets/image-20240716200307630.png)





如果语言错误



![image-20240716200343568](./assets/image-20240716200343568.png)



没问题



**查询提交信息接口**



功能：能够根据用户id、或者题目d、编程语言、题目状态，去查询提交记录

注意事项：

仅本人和管理员能看见自己（提交userld和登录用户id不同）提交的代码

实现方案：先查询，再根据权限去脱敏

核心代码：



![image-20240716201248479](./assets/image-20240716201248479.png)



测试一下



![image-20240716202528759](./assets/image-20240716202528759.png)



我现在是用户而且这条记录也是我提交的，所以查出来了代码，换个用户登录



![image-20240716202618266](./assets/image-20240716202618266.png)



再来一次



![image-20240716202636748](./assets/image-20240716202636748.png)



这样就查不出来代码了



##### 2.5 前端页面开发



###### 2.5.1 整合MarkDown编辑器



**MarkDown编辑器**



为什么用Markdown?

一套通用的文本编辑语法，可以在各大网站上统一标准、渲染出统一的样式，比较简单易学。

推荐的Md编辑器：https://github.com/bytedance/bytemd



![image-20240716203353132](./assets/image-20240716203353132.png)



阅读官方文档，下载编辑器主体、以及gfm(表格支持)插件、highlight代码高亮插件

安装命令：

- `npm i @bytemd/vue-next`

  ![image-20240716203718377](./assets/image-20240716203718377.png)

  `main.ts` 引入样式

  ![image-20240716203826836](./assets/image-20240716203826836.png)

- `npm i @bytemd/plugin-highlight @bytemd/plugin-gfm`

  ![image-20240716204117399](./assets/image-20240716204117399.png)



新建MdEditor组件，编写代码：



![image-20240716204958834](./assets/image-20240716204958834.png)



找个地方引用



![image-20240716205105907](./assets/image-20240716205105907.png)



出来了



![image-20240716205208759](./assets/image-20240716205208759.png)



隐藏GitHub 图标



![image-20240716205342423](./assets/image-20240716205342423.png)



定义属性，要把MdEditor当前输入的值暴露给父组件，便于父组件去使用，同时也是提高组件的通用性，需要定义属性，把vaue和handleChange事件交给父组件去管理



![image-20240716210115871](./assets/image-20240716210115871.png)





###### 2.5.2 整合Monaco Editor 代码编辑器



**代码编辑器**

微软官方编辑器：https://github.com/microsoft/monaco-editor



![image-20240716210236131](./assets/image-20240716210236131.png)



官方提供的整合教程：https://github.com/microsoft/monaco-editor/blob/main/docs/integrate-esm.md



![image-20240716210255101](./assets/image-20240716210255101.png)



【1】安装编辑器

`npm install monaco-editor`



![image-20240716210651591](./assets/image-20240716210651591.png)



【2】vue-cli项目 (webpack项目) 整合monaco-editor

先安装 `monaco-editor-webpack-plugin` https://github.com/microsoft/monaco-editor/blob/main/webpack-plugin/README.md

`npm install monaco-editor-webpack-plugin`



![image-20240716210754031](./assets/image-20240716210754031.png)



在vue.config.js中配置webpack插件：

全量加载：



![image-20240716210903287](./assets/image-20240716210903287.png)



>  也可以按需加载
>
> ![image-20240716210926421](./assets/image-20240716210926421.png)



如何使用Monaco Editor?查看示例教程：

https://microsoft.github.io/monaco-editor/playground.html?source=v0.40.0#example-creating-the-editor-hello-world



示例整合代码如下：



![image-20240716212113125](./assets/image-20240716212113125.png)



![image-20240716212058497](./assets/image-20240716212058497.png)



我这儿直接挂了，让加一个依赖试试



![image-20240716212146965](./assets/image-20240716212146965.png)



本来就有啊，重启看看



![image-20240716212239570](./assets/image-20240716212239570.png)



还是有问题 https://blog.csdn.net/weixin_45800258/article/details/136307605



![image-20240716212343263](./assets/image-20240716212343263.png)





![image-20240716212446865](./assets/image-20240716212446865.png)



再来一次



![image-20240716212538519](./assets/image-20240716212538519.png)



这次出来了



![image-20240716212734541](./assets/image-20240716212734541.png)



一点点，这样就出来了



指定高度

![image-20240716212716535](./assets/image-20240716212716535.png)



![image-20240716212755889](./assets/image-20240716212755889.png)



没问题

注意，monaco editor在读写值的时候，要使用toRaw(编辑器实例) 的语法来执行操作，否则会卡死。

并且同Md编辑器一样，也要接受父组件的传值，把显示的输入交给父组件去控制，从而能够让父组件实时得到用户输入的代码：



![image-20240716213956825](./assets/image-20240716213956825.png)



效果：

![image-20240716213945746](./assets/image-20240716213945746.png)



到此，其实两个组件都算整合完成了





###### 2.5.3 题目页面开发（自定义代码模板）



**创建题目页面**



生成联调代码：`openapi --input http://localhost:8101/api/v2/api-docs --output ./xiongoj_backendapi --client axios`



![image-20240716214455559](./assets/image-20240716214455559.png)



![image-20240716214508186](./assets/image-20240716214508186.png)



没问题



**小知识 - 自定义代码模板**



在JetBrains系列编辑器中打开设置，搜索live Templates,先创建一个自定义模板组，在组下创建代码模板。

效果：输入缩写，即可生成模板代码。

示例模板：



![image-20240716215103247](./assets/image-20240716215103247.png)



![image-20240716215335793](./assets/image-20240716215335793.png)



![image-20240716215344764](./assets/image-20240716215344764.png)



![image-20240716215354362](./assets/image-20240716215354362.png)



`camelCase(fileNameWithoutExtension())`



```
<template>
  <div id="$ID$"></div>
</template>

<script setup lang="ts">$END$</script>

<style scoped>
#$ID$ {
}
</style>
```



![image-20240716215743866](./assets/image-20240716215743866.png)





###### 2.5.4 创建题目页面开发



添加路由



![image-20240716220028765](./assets/image-20240716220028765.png)



![image-20240716220243624](./assets/image-20240716220243624.png)



这里注意，重新生成了代码后要手动改一下啊



![image-20240716220353867](./assets/image-20240716220353867.png)



![image-20240716220400267](./assets/image-20240716220400267.png)



现在还是光的



→ 需要用户输入的值：



![image-20240716214603449](./assets/image-20240716214603449.png)



接口文档中的值

创建题目页面：



![image-20240717093508358](./assets/image-20240717093508358.png)



测试一下



![image-20240717093531497](./assets/image-20240717093531497.png)



![image-20240717093538665](./assets/image-20240717093538665.png)



数据库



![image-20240717093553632](./assets/image-20240717093553632.png)



没问题



总的就是使用表单组件，先复制示例代码，再修改：https://arco.design/vue/component/form

此处我们用到了：

- 嵌套表单：https://arco.design/vue/component/form#nest
- 动态增减表单：https://arco.design/vue/component/form#dynamic



注意，我们自定义的代码编辑器组件不会被组件库识别， 需要手动指定value和handleChange函数。





###### 2.5.5 题目管理页面开发



1. 使用表格组件 https://arco.design/vue/component/table#custom （自定义操作示例）

2. 查询数据

3. 定义表格列

4. 加载数据

5. 调整格式

   比如json格式不好看，有2种方法调整：

    - 使用组件库自带的语法，自动格式化（更方便）
    - 完全自定义渲染，想展示什么就展示什么（更灵活）

6. 添加删除、更新操作



删除后要执行loadData刷新数据



效果：



![image-20240717094928317](./assets/image-20240717094928317.png)



![image-20240717094909592](./assets/image-20240717094909592.png)





###### 2.5.6 题目更新页面开发



策略：由于更新和创建都是相同的表单，所以完全没有必要开发/复制2遍，可以直接复用创建页面。

关键实现：如何区分两个页面？

1. 路由(/add/question和update/question)
2. 请求参数(id=1)

更新页面相比于创建页面，多了2个改动：

- 在加载页面时，更新页面需要加载出之前的数据
- 在提交时，请求的地址不同



这里要加个接口，获取到题目所有信息的接口，根据ID ， 之前都是VO 封装过的



![image-20240717100213879](./assets/image-20240717100213879.png)



重新生成一下代码：



![image-20240717100601348](./assets/image-20240717100601348.png)



这样就可以把原来信息拿过来了



测试一下

![image-20240717100633989](./assets/image-20240717100633989.png)



![image-20240717100621139](./assets/image-20240717100621139.png)



![image-20240717100644800](./assets/image-20240717100644800.png)



![image-20240717100654214](./assets/image-20240717100654214.png)



没问题



直接用更新给第一道加上配置和用例



![image-20240717101122687](./assets/image-20240717101122687.png)



![image-20240717101135930](./assets/image-20240717101135930.png)



没问题





###### 2.5.7 历史遗留问题解决



Git 拉取等代码格式问题：`git` 命令设置上传代码时不修改格式



![image-20240717101540048](./assets/image-20240717101540048.png)



**当前代码优化**



【1】先处理菜单项的权限控制和显示隐藏

通过meta.hidelnMenu和meta.access属性控制



![image-20240717102233442](./assets/image-20240717102233442.png)



【2】管理页面分页问题修复

可以参考聚合搜索项目的搜索条件改变和 `url` 状态同步

核心原理：在分页页号改变时，触发 `@page-change` 事件，通过改变searchParams的值，并且通过watchEffect监听searchParams的改变（然后执行loadData重新加载)，实现了页号变化时触发数据的重新加载。



![image-20240717102307746](./assets/image-20240717102307746.png)



![image-20240717101901913](./assets/image-20240717101901913.png)



【3】修改刷新页面未登录问题



修改 access\index.ts 中的获取登录用户信息，把登录后的信息更新到loginUser变量上



![image-20240717102602920](./assets/image-20240717102602920.png)



在非主页的地方刷新就会出现这个问题



![image-20240717102818156](./assets/image-20240717102818156.png)



这样就行了



![image-20240717102902142](./assets/image-20240717102902142.png)



还有登录属性为未登录 也要带到登录页面



###### 2.5.8 题目列表搜索页面开发



核心实现：表格组件



1. 复制管理题目页的表格

2. 只保留需要的columns字段

3. 自定义表格列的渲染

    - 标签：使用tag组件

    - 通过率：自行计算

    - 创建时间：使用moment库进行格式化( https://momentjs.com/docs/#/displaying/format/)

   ![image-20240717103608411](./assets/image-20240717103608411.png)

   `npm install moment`

   ![image-20240717103706014](./assets/image-20240717103706014.png)

   操作按钮：补充跳转到做题页的按钮

   ![image-20240717103959054](./assets/image-20240717103959054.png)

4. 编写搜索表单，使用form的layout-inline布局，让用户的输入和searchParams同步，并且给提交按钮绑定修改searchParams,从而被watchEffect监听到，触发查询
   ![image-20240717103949954](./assets/image-20240717103949954.png)



###### 2.5.9 在线做题页面开发



【1】先定义动态参数路由，开启props为true,可以在页面的props中直接获取到动态参数（题目id)

![image-20240717104817652](./assets/image-20240717104817652.png)

【2】定义布局：左侧是题目信息，右侧是代码编辑器

【3】左侧题目信息：

- tabs切换展示的内容
- 定义MdViewer组件展示题目内容
- 使用descriptions组件展示判题配置 https://arco.design/vue/component/descriptions

【4】使用select组件让用户选择编程语言

在代码编辑器中监听属性的变化，注意监听props要使用箭头函数





![image-20240717110411299](./assets/image-20240717110411299.png)



这里直接调用接口



![image-20240717112510536](./assets/image-20240717112510536.png)



效果：



![image-20240717112553963](./assets/image-20240717112553963.png)



直接提交



请求参数

![image-20240717112851411](./assets/image-20240717112851411.png)



![image-20240717112605078](./assets/image-20240717112605078.png)



数据库



![image-20240717112621684](./assets/image-20240717112621684.png)



没问题，插进去了【前端就算基本完成了】





##### 2.6 判题机架构



###### 2.6.1 判题机模板划分



> 先跑通完整的业务流程，再进行代码沙箱复杂的实现



**判题模板和代码沙箱的关系**



判题模块：调用代码沙箱，把代码和输入交给代码沙箱去执行

代码沙箱：只负责接受代码和输入，返回编译运行的结果，不负责判题（可以作为独立的项目/服务，提供给其他的需要执行代码的项目去使用）



这两个模板是完全解耦的



![image-20240717113006593](./assets/image-20240717113006593.png)





思考：为什么代码沙箱要接受和输出一组运行用例？



前提：我们的每道题目有多组测试用例

如果是每个用例单独调用一次代码沙箱，会调用多次接口、需要多次网络传输、程序要多次编译、记录程序的执行状态（重复的代码不重复编译）

这是一种很常见的性能优化方法！  (批处理)





###### 2.6.2 代码沙箱架构开发



新建包



![image-20240717114228317](./assets/image-20240717114228317.png)



【1】定义代码沙箱的接口，提高通用性

之后我们的项目代码只调用接口，不调用具体的实现类，这样在你使用其他的代码沙箱实现类时，就不用去修改名称了，便于扩展。



![image-20240717113733788](./assets/image-20240717113733788.png)



> 扩展：增加一个查看代码沙箱状态的接口



【2】定义多种不同的代码沙箱实现



- 示例代码沙箱：仅为了跑通业务流程

  ![image-20240717114701990](./assets/image-20240717114701990.png)

- 远程代码沙箱：实际调用接口的沙箱

  ![image-20240717114736807](./assets/image-20240717114736807.png)

- 第三方代码沙箱：调用网上现成的代码沙箱，https://github.com/criyle/go-judge

  ![image-20240717114745765](./assets/image-20240717114745765.png)





【3】编写单元测试，验证单个代码沙箱的执行



![image-20240717114954962](./assets/image-20240717114954962.png)



运行结果



![image-20240717115015691](./assets/image-20240717115015691.png)



###### 2.6.3 工厂模式优化



但是现在的问题是，我们把new某个沙箱的代码写死了，



![image-20240717115041056](./assets/image-20240717115041056.png)



如果后面项目要改用其他沙箱，可要改很多地方的代码。



【4】使用工厂模式，根据用户传入的字符串参数（沙箱类别），来生成对应的代码沙箱实现类

此处使用静态工厂模式，实现比较简单，符合我们的需求。



![image-20240717115216283](./assets/image-20240717115216283.png)



由此，我们可以根据字符串动态生成实例，提高了通用性：



![image-20240717115308915](./assets/image-20240717115308915.png)



运行结果



![image-20240717115343613](./assets/image-20240717115343613.png)



【5】参数配置化，把项目中的一些可以交给用户去自定义的选项或字符串，写到配置文件中。这样开发者只需要改配置文件，而不需要去看你的项目代码，就肩能够自定义使用你项目的更多功能。

application.yml配置文件中指定变量：



![image-20240717115448112](./assets/image-20240717115448112.png)



在Spring的Bean中通过@Value注解读取：



![image-20240717115533547](./assets/image-20240717115533547.png)



新的测试方法：

![image-20240717115619046](./assets/image-20240717115619046.png)



![image-20240717115638183](./assets/image-20240717115638183.png)



改一下啊



![image-20240717115647511](./assets/image-20240717115647511.png)



再来一次



![image-20240717115708189](./assets/image-20240717115708189.png)



没问题



###### 2.6.4 代理模式优化



【6】代码沙箱能力增强

比如：我们需要在调用代码沙箱前，输出请求参数日志；在代码沙箱调用后，输出响应结果日志，便于管理员去分析。

每个代码沙箱类都写一遍 `log.info` ?难道每次调用代码沙箱前后都执行log?

使用代理模式，提供一个Proxy,来增强代码沙箱的能力（代理模式的作用就是增强能力）

原本：需要用户自己去调用多次



![image-20240717115825747](./assets/image-20240717115825747.png)



使用代理后：不仅不用改变原本的代码沙箱实现类，而且对调用者来说，调用方式几乎没有改变，也不需要在每个调用沙箱的地方去写统计代码。



![image-20240717115843103](./assets/image-20240717115843103.png)



代理模式的实现原理：

1. 实现被代理的接口
2. 通过构造函数接受一个被代理的接口实现类
3. 调用被代理的接口实现类，在调用前后增加对应的操作



![image-20240717120019954](./assets/image-20240717120019954.png)



使用方式：

![image-20240717120542617](./assets/image-20240717120542617.png)



运行结果



![image-20240717120616010](./assets/image-20240717120616010.png)



没问题



【7】实现示例代码沙箱



![image-20240717120733735](./assets/image-20240717120733735.png)



测试结果：



![image-20240717120920938](./assets/image-20240717120920938.png)



666



> 小知识 **Lombok Builder** 注解
>
> 以前我们是使用new对象后，再逐行执行set方法的方式来给对象 赋值 的。
>
> 还有另外一种可能更方便的方式 builder。
>
> 1. 实体类加上@Builder等注解：
     >
     >    ```java
>    @Data
>    @Builder
>    @NoArgsConstructor
>    @AllArgsConstructor
>    public class ExecuteCodeRequest {
>    
>        private List<String> inputList;
>    
>        private String code;
>    
>        private String language;
>    }
>    ```
>
> 2. 可以使用链式的方式更方便地给对象赋值：
     >
     >    ```java
>    ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
>        .code(code)
>        .language(language)
>        .inputList(inputList)
>        .build();
>    ```



###### 2.6.5 判题服务开发



接口：



![image-20240717121051570](./assets/image-20240717121051570.png)



定义单独的judgeService类，而不是把所有判题相关的代码写到questionSubmitService里，有利于后续的模块抽离、微服务改造。



**判题服务业务流程**



1. 传入题目的提交d,获取到对应的题目、提交信悬（包含代码、编程语言等）
2. 如果题目提交状态不为等待中，就不用重复执行了
3. 更改判题（题目提交）的状态为“判题中”，防止重复执行，也能让用户即时看到状态
4. 调用沙箱，获取到执行结果
5. 根据沙箱的执行结果，设置题目的判题状态和信息



**判断逻辑**



1. 先判断沙箱执行的结果输出数量是否和预期输出数量相等
2. 依次判断每一项输出和预期输出是否相等
3. 判题题目的限制是否符合要求
4. 可能还有其他的异常情况



实现类：

![image-20240717124020389](./assets/image-20240717124020389.png)



相当一大段



###### 2.6.6 策略模式优化



我们的判题策略可能会有很多种，比如：我们的代码沙箱本身执行程序需要消耗时间，这个时间可能不同的编程语言是不同的，比如沙箱执行Java 要额外花10秒。

我们可以采用策略模式，针对不同的情况，定义独立的策略，便于分别修改策略和维护。而不是把所有的判题逻辑、`if...else...`代码全部混在一起写。

实现步骤如下：

【1】定义判题策略接口，让代码更加通用化：



![image-20240717135759067](./assets/image-20240717135759067.png)



【2】定义判题上下文对象，用于定义在策略中传递的参数（可以理解为一种DTO):



![image-20240717135810127](./assets/image-20240717135810127.png)



【3】实现默认判题策略，先把judgeService中的代码搬运过来



![image-20240717160106036](./assets/image-20240717160106036.png)



在业务层就简单了



![image-20240717161018324](./assets/image-20240717161018324.png)





【4】再新增一种判题策略，通过`if...else...`的方式选择使用哪种策略：



![image-20240717161120108](./assets/image-20240717161120108.png)



```java
JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
if (language.equals("java")) {
    judgeStrategy = new JavaLanguageJudgeStrategy();
}
JudgeInfo judgeInfo = judgeStrategy.doJudge(judgeContext);
```



![image-20240717161406477](./assets/image-20240717161406477.png)



但是，如果选择某种判题策略的过程比较复杂，如果都写在调用判题服务的代码中，代码会越来越复杂，会有大量`ie…lse...`,所以建议单独编写一个判断策略的类。



【5】定义JudgeManager,目的是尽量简化对判题功的调用，让调用方写最少的代码、调用最简单。对于判题策略的选取，也是在ludgeManager里处理的。

示例代码如下：



![image-20240717161509207](./assets/image-20240717161509207.png)



这下就可以直接用到业务中



![image-20240717161641531](./assets/image-20240717161641531.png)





OK，到这里测试一下吧



先改下接口：

题目提交上去之后让他执行



![image-20240717162335112](./assets/image-20240717162335112.png)



注意有循环依赖



![image-20240717162411292](./assets/image-20240717162411292.png)



懒加载一下啊



直接运行提交一道题目

![image-20240717162449735](./assets/image-20240717162449735.png)



数据库



![image-20240717162505184](./assets/image-20240717162505184.png)



判题成功，虽然这个沙箱还是假的，至少是调用了我们的判题服务而且更新数据库完成



##### 2.7 代码沙箱 Java 原生实现



###### 2.7.1 执行原理



**历史问题修复**



代码编辑器切换语言失败问题

解决方案：监听language属性，动态更改编辑器的语言

代码如下：



![image-20240717163015466](./assets/image-20240717163015466.png)



![image-20240717163038266](./assets/image-20240717163038266.png)



Java 有高亮，换一种语言



![image-20240717163050655](./assets/image-20240717163050655.png)



高亮消失



![image-20240717163246801](./assets/image-20240717163246801.png)



**代码沙箱初始化**



代码沙箱的定位：只负责接受代码和输入，返回编译运行的结果，不负责判题（可以作为独立的项目/服务，提供给其他的需要执行代码的项目去使用）

以 Java 编程语言为主，带大家实现代码沙箱，重要的是学思想、学关键流程。

>扩展：可以自行实现C++语言的代码沙箱
>

由于代码沙箱是够通过APl调用的独立服务，所以新建一个Spring Boot Web项目。最终这个项目要提供一个够执行代码、操作代码沙箱的接口。

使用IDEA的Spring Boot项目初始化工具，一定要选择Java8、Spring Boot2.7版本！！！



![image-20240717163634217](./assets/image-20240717163634217.png)



> 其实这个项目原本是创建的`xiongoj`，但是中途想着用学校的名字，所以就换了一下，但是代码里面很多东西是没变的，就改了下壳儿



![image-20240717163804101](./assets/image-20240717163804101.png)



他奶奶的，最新版本太狂了，换的阿里云的镜像



![image-20240717163919407](./assets/image-20240717163919407.png)



先就这三个，创建



![image-20240717163944037](./assets/image-20240717163944037.png)



这个后面我会直接放到另一个包里面【好像也不用，就是一个通用的东西】

配置在8090 端口启动



![image-20240717164120127](./assets/image-20240717164120127.png)



来个测试接口



![image-20240717164303549](./assets/image-20240717164303549.png)



启动



![image-20240717164355555](./assets/image-20240717164355555.png)



没问题



将主项目的Judgelnfo类移到model目录下，然后复制model包和CodeSandbox接口到该沙箱项目，便于字段的统一。



![image-20240717164906744](./assets/image-20240717164906744.png)





**Java 原生实现代码沙箱**



原生：尽可能不借助第三方库和依赖，用最干净、最原始的方式实现代码沙箱



【通过命令行执行】



**Java 程序执行流程**



接收代码=>编译代码(javac)=>执行代码（Java）

先编写示例代码，注意要去掉包名，放到resources目录下：



![image-20240717165117941](./assets/image-20240717165117941.png)



![image-20240717165423340](./assets/image-20240717165423340.png)



javac 编译：



![image-20240717170025116](./assets/image-20240717170025116.png)





java 命令执行：



![image-20240717170116172](./assets/image-20240717170116172.png)



**程序中文乱码问题**



为什么编译后的class文件出现中文乱码呢？

原因：命令行终端的编码是GBK,和jva代码文件本身的编码UTF-8不一致，导致乱码。

通过chcp命令查看命令行编码，GBK是936，UTF-8是65001。

但是不建议大家改变终瑞编码来解决编译乱码，因为其他运行你代码的人也要改变环境，兼容性很差。

推荐的javac编译命令，用`-encoding utf-8`参数解决：



![image-20240717170226635](./assets/image-20240717170226635.png)



这样就行了



**统一类名**



实际的`OJ`系统中，对用户输入的代码会有一定的要求。便于系统进行统一处理和判题。

此处我们把用户输入代码的类名限制为 `Main` (参考北大 OJ )，可以减少编译时类名不一致的风险；而且不用从用户代码中提取类名，更方便。

文件名`Main.java`,示例代码如下：



```java
public class Main {

    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        System.out.println("结果:" + (a + b));
    }
}
```



![image-20240717170608887](./assets/image-20240717170608887.png)



这样就能统一执行的命令了



```bash
D:\PlanetProjects\7_Oj_judgment\lzuoj_code_sandbox\src\main\resources\testCode\simpleComputeArgs>javac -encoding utf-8 Main.java

D:\PlanetProjects\7_Oj_judgment\lzuoj_code_sandbox\src\main\resources\testCode\simpleComputeArgs>java -cp . Main 100 200
结果:300
```



![image-20240717170655080](./assets/image-20240717170655080.png)



###### 2.7.2 核心流程开发



**核心流程实现**



先把实现类做出来

![image-20240717171048440](./assets/image-20240717171048440.png)



原生Java 沙箱。



核心实现思路：用程序代替人工，用程序来操作命令行，去编译执行代码

核心依赖：Java进程类Process

1. 把用户的代码保存为文件
2. 编译代码，得到class文件
3. 执行代码，得到输出结果
4. 收集整理输出结果
5. 文件清理，释放空间
6. 错误处理，提升程序健壮性





【1】保存代码文件

引入Hutool工具类，提高操作文件效率：



![image-20240717171751545](./assets/image-20240717171751545.png)



新建目录，将每个用户的代码都存放在独立目录下，通过UUID随机生成目录名，便于隔离和维护：



![image-20240717172037054](./assets/image-20240717172037054.png)



测试一下



![image-20240717172733200](./assets/image-20240717172733200.png)



运行结果



![image-20240717172807036](./assets/image-20240717172807036.png)



![image-20240717172818355](./assets/image-20240717172818355.png)



没问题，代码文件保存成功



【2】编译代码

使用Process类在终端执行命令：



![image-20240717173131138](./assets/image-20240717173131138.png)



执行process.waitFor等待程序执行完成，并通过返回的exitValue判断程序是否正常返回，



![image-20240717173445841](./assets/image-20240717173445841.png)



执行一次



![image-20240717173515104](./assets/image-20240717173515104.png)



编译出来了



然后从Process的输入流inputStream和错误流errorStream获取控制台输出。【这里提取成了一个工具类 ProcessUtils， 执行进程并获取输出，并且使用 SB 拼接控制台输出信息】

示例代码如下：



![image-20240717173733429](./assets/image-20240717173733429.png)



【3】执行程序

同样是使用Process类运行java命令，



![image-20240717175508737](./assets/image-20240717175508737.png)



命令中记得增加`-Dfile.encodings=UTF-8`参数，解决中文乱码：



![image-20240717175557684](./assets/image-20240717175557684.png)



上述命令适用于执行从输入参数(args)中获取值的代码。、

很多 OJ 都是ACM模式，需要和用户交互，让用户不断输入内容并获取输出，比如：



```java
import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String args[]) throws Exception
    {
        Scanner cin=new Scanner(System.in);
        int a=cin.nextInt(),b=cin.nextInt();
        System.out.println(a+b);
    }
}
```



![image-20240717175806631](./assets/image-20240717175806631.png)



对于此类程序，我们需要使用OutputStream向程序终端发送参数，并及时获取结果，注意最后要关闭流释放资源。

示例代码如下：



![image-20240717174802364](./assets/image-20240717174802364.png)



试试：

![image-20240717180814101](./assets/image-20240717180814101.png)



大概就是这么个意思【但是我们的项目还是以命令行参数为主】



【4】整理输出

1. 通过 for 循环遍历执行结果，从中获取输出列表
2. 获取程序执行时间



可以使用Spring的StopWatch获取一段程序的执行时间：

```java
StopWatch stopWatch = new StopWatch();
stopWatch.start();
... 程序执行
stopWatch.stop();
stopWatch.getLastTaskTimeMillis(); // 获取时间
```



此处我们使用最大值来统计时间，便于后续判题服务计算程序是否超时：



![image-20240717201250336](./assets/image-20240717201250336.png)



3. 获取内存信息



实现比较复杂，因为无法从Process对象中获取到子进程号，也不推荐在 Java 原生实现代码沙箱的过程中获取。



到这里的代码：



![image-20240717201302278](./assets/image-20240717201302278.png)











【5】文件清理



防止服务器空间不足，删除代码目录：

![image-20240717202007533](./assets/image-20240717202007533.png)



【6】错误处理

封装一个错误处理方法，当程序抛出异常时，直接返回错误响应。

示例代码如下：



![image-20240717202015033](./assets/image-20240717202015033.png)



现在测试一下



![image-20240717202338397](./assets/image-20240717202338397.png)





###### 2.7.3 Java 程序漏洞讲解（6 种）



**Java 程序异常情况**



到目前为止，核心流程已经实现，但是想要上线的话，安全么？

用户提交恶意代码，怎么办？



【1】执行超时

占用时间资源，导致程序卡死，不释放资源：



![image-20240717202740798](./assets/image-20240717202740798.png)



试着执行一下



![image-20240717202920884](./assets/image-20240717202920884.png)



![image-20240717203123810](./assets/image-20240717203123810.png)



一直卡住了，结束不了运行



> 要把写好的代码复制到resources中，并且一定要把类名改为Main！包名也一定要去掉！





【2】占用内存

占用内存资源，导致空间浪费：



![image-20240717203317549](./assets/image-20240717203317549.png)



实际运行上述程序时，我们会发现，内存占用到达一定空间后，程序就自动报错：`java.lang.OutofMemoryError: Java heap space`,而不是无限增加内存占用，直到系统死机。

这是`JVM`的一个保护机制。

可以使用`JVisualVM`或`JConsole`工具，连接到JVM虚拟机上来可视化查看运行状态。

![image-20240717203655813](./assets/image-20240717203655813.png)





【3】读文件，信息泄露



比如直接通过相对路径获取项目配置文件，获取到密码：

![image-20240717203906390](./assets/image-20240717203906390.png)





![image-20240717203934428](./assets/image-20240717203934428.png)



![image-20240717203948677](./assets/image-20240717203948677.png)



危险



【4】写文件，植入木马



可以直接向服务器上写入文件，比如一个木马程序：`java -version 2>&1` (示例命令)

> 1. java -version用于显示Java版本信息。这会将版本信息输出到标准错误流(stderr)而不是标准输出流(stdout)
> 2. 2>&1将标准错误流重定向到标准输出流。这样，Java版本信息就会被发送到标准输出流。



![image-20240717204230221](./assets/image-20240717204230221.png)



![image-20240717204302049](./assets/image-20240717204302049.png)



运行



![image-20240717204353842](./assets/image-20240717204353842.png)



危险



【5】运行其他程序



直接通过Process执行危险程序，或者电脑上的其他程序：



![image-20240717204455399](./assets/image-20240717204455399.png)



![image-20240717204609164](./assets/image-20240717204609164.png)



运行结果



![image-20240717204626696](./assets/image-20240717204626696.png)



炸裂



【6】执行高危操作

甚至都不用写木马文件，直接执行系统自带的危险命令！

- 比如删除服务器的所有文件（太残暴、不演示）
- 比如执行dir(windows)、ls(linux)获取你系统上的所有文件信息





###### 2.7.4 超时控制、资源控制、权限控制



**Java 程序安全控制**



针对上面的异常情况，分别有如下方案，可以提高程序安全性。

1. 超时控制
2. 限制给用户程序分配的资源
3. 限制代码-黑白名单
4. 限制用户的操作权限
5. 运行环境隔离





【1】超时控制



通过创建一个守护线程，超时后自动中断Process实现。

代码如下：



![image-20240717205023146](./assets/image-20240717205023146.png)



![image-20240717205058913](./assets/image-20240717205058913.png)



再次执行那个超时文件



![image-20240717205137587](./assets/image-20240717205137587.png)



这样就行了



【2】限制资源分配

我们不让每个java进程的执行占用的 JVM 最大堆内存空间都和系统默认的一致（鱼皮的 JVM 默认最大占用8G内存），实际上应该更小（执行用户的题目代码也不需要这么多)，比如说256MB。

在启动 Java 程序时，可以指定JVM的参数：-Xmx256m (最大堆空间大小)

示例命令如下：


`java -Xmx256m`



![image-20240717205342480](./assets/image-20240717205342480.png)



再试一次那个



![image-20240717205408992](./assets/image-20240717205408992.png)



![image-20240717205433663](./assets/image-20240717205433663.png)



注意！-Xmx 参数、JVM的堆内存限制，不等同于系统实际占用的最大资源，可能会超出。



如果需要更严格的内存限制，要在系统层面去限制，而不是JVM 层面的限制。

如果是Linux系统，可以使用cgroup来实现对某个进程的CPU、内存等资源的分配。



**小知识 - 什么是cgroup **



cgroup是Linux内核提供的一种机制，可以用来限制进程组（包括子进程）的资源使用，例如内存、CPU、磁盘`I/O`等。通过将Java进程放置在特定的cgroup中，你可以实现限制其使用的内存和CPU数。



**小知识 - 常用JVM 启动参数**



1. 内存相关参数：



- -Xms:设置JVM的初始堆内存大小。
- -Xmx:设置JVM的最大堆内存大小。
- -Xss:设置线程的栈大小。
- -XX:MaxMetaspaceSize:设置Metaspace(元空间)的最大大小。
- -XX:MaxDirectMemorySize:设置直接内存(Direct Memory)的最大大小。2.垃圾回收相关参数：
- -XX:+UseSerialGC:使用串行垃圾回收器。
- -XX:+UseParallelGC:使用并行垃极回收器。
- -XX:+UseConcMarkSweepGC:使用CMS垃圾回收器。
- -XX:+UseG1GC:使用G1垃圾回收器。3.线程相关参数：
- -XX:ParallelGCThreads:设置并行垃圾回收的线程数。
- -XX:ConcGCThreads:设置并发垃圾回收的线程数。
- -XX:ThreadStackSize:设置线程的钱大小。4.JlT编译器相关参数：
- -XX:TieredStopAtLevel::设置JIT编译器停止编译的层次。5.其他资源限制参数：
- -XX:MaxRAM:设置JVM使用的最大内存。





【3】限制代码 - 黑白名单



**实现**



先定义一个黑白名单，比如哪些操作是禁止的，可以就是一个列表：



![image-20240717205938955](./assets/image-20240717205938955.png)

判断就简单了，危险代码已经没必要编译了



![image-20240717210548048](./assets/image-20240717210548048.png)



![image-20240717210602592](./assets/image-20240717210602592.png)



来一次



![image-20240717210618738](./assets/image-20240717210618738.png)



没毛病。



还可以使用字典树代替列表存储单词，用更少的空间存储更多的敏感词汇，并且实现更高效的敏感词查找。

字典树的原理：



![image-20240717210014042](./assets/image-20240717210014042.png)





> 字典树相关的应用可以写在简历上



此处使用HuTool工具库的字典树工具类：WordTree,不用自己写字典树！



1. 先初始化字典树，插入禁用词：

   ![image-20240717210830462](./assets/image-20240717210830462.png)

2. 校验用户代码是否包含禁用词：

   ![image-20240717210859015](./assets/image-20240717210859015.png)

3. 执行结果

   ![image-20240717210914912](./assets/image-20240717210914912.png)

   也能查出来



**本方案缺点**

1. 你无法遍历所有的黑名单
2. 不同的编程语言，你对应的领域、关键词都不一样，限制人工成本很大



###### 2.7.5 安全管理器、环境隔离



【4】限制权限 - Java 安全管理器



目标：限制用户对文件、内存、CPU、网络等资源的操作和访问。



**Java 安全管理器使用**



Java安全管理器(Security Manager))是Java提供的保护JVM、Java安全的机制，可以实现更严格的资源和操作限制。

编写安全管理器，只需要继承Security Manager。.



1. 所有权限放开

   ![image-20240717211141630](./assets/image-20240717211141630.png)

   用的话就直接在业务里面设置

   ![image-20240717212125157](./assets/image-20240717212125157.png)

   试试

   ![image-20240717212201165](./assets/image-20240717212201165.png)

   不做的话，还是输出了

2. 所有权限拒绝

   ![image-20240717211206568](./assets/image-20240717211206568.png)

   ![image-20240717212225321](./assets/image-20240717212225321.png)

3. 限制读权限

   ![image-20240717211449314](./assets/image-20240717211449314.png)



4. 限制写文件权限

   ![image-20240717211507623](./assets/image-20240717211507623.png)



5. 限制执行文件权限

   ![image-20240717211532663](./assets/image-20240717211532663.png)



6. 限制网络连接权限

   ![image-20240717211549478](./assets/image-20240717211549478.png)



**结合项目运用**



实际情况下，不应该在主类（开发者自己写的程序）中做限制，只需要限制子程序的权限即可。

启动子进程执行命令时，设置安全管理器，而不是在外层设置（会限制住测试用例的读写和子命令的执行）

具体操作如下：



1. 根据需要开发自定义的安全管理器（比如MySecurity小Manager)
2. 复制MySecurityManager类到resources/security目录下，移除类的包名
3. 手动输入命令编译MySecurityManager类，得到class文件
4. 在运行java程序时，指定安全管理器class文件的路径、安全管理器的名称。





命令如下：

```java
java -Dfile.encoding=UTF-8 -cp %s;%s -Djava.security.manager=MySecurityManager Main
```



依次执行之前的所有测试用例，发现资源成功被限制。



![image-20240717213449329](./assets/image-20240717213449329.png)



![image-20240717213531888](./assets/image-20240717213531888.png)



运行结果



![image-20240717213559740](./assets/image-20240717213559740.png)



擦，我命令行用的11，项目用的8 ，重新来一次



![image-20240717213659803](./assets/image-20240717213659803.png)



我这里是懒得重启了，



![image-20240717213750724](./assets/image-20240717213750724.png)



再试一次



![image-20240717213809032](./assets/image-20240717213809032.png)



没毛病。



**安全管理器优点**



1. 权限控制很灵活
2. 实现简单



**安全管理器缺点**



1. 如果要做比较严格的权限限制，需要自己去判断哪些文件、包名需要允许读写。粒度太细了，难以精细化控制。
1. 安全管理器本身也是Java 代码，也有可能存在漏洞。本质上还是程序层面的限制，没深入系统的层面。



【5】运行环境隔离

原理：操作系统层面上，把用户程序封装到沙箱里，和宿主机（我们的电脑/服务器）隔离开，使得用户的程序无法影响宿主机。

实现方式：Docker容器技术（底层是用cgroup、namespace等方式实现的），也可以直接使用cgroup实现。





##### 2.8 Docker 从入门到实战



###### 2.8.1 Docker入门讲解



**Docker 容器技术**



为什么要用Docker容器技术？

为了进一步提升系统的安全性，把不同的程序和宿主机进行隔离，使得某个程序（应用）的执行不会影响到系统本身。

Docker技术可以实现程序和宿主机的隔离。





**什么是容器？**



理解为对一系列应用程序、服务和环境的封装，从而把程序运行在一个隔离的、密闭的、隐私的空间内，对外整体提供服务。

可以把一个容器理解为一个新的电脑（定制化的操作系统）。



![image-20240717214742121](./assets/image-20240717214742121.png)



**Docker 基本概念**



镜像：用来创建容器的安装包，可以理解为给电脑安装操作系统的系统镜像

容器：通过镜像来创建的一套运行环境，一个容器里可以运行多个程序，可以理解为一个电脑实例

Dockerfile：制作镜像的文件，可以理解为制作镜像的一个清单



![image-20240717214821386](./assets/image-20240717214821386.png)



镜像仓库：存放镜像的仓库，用户可以从仓库下载现成的镜像，也可以把做好的镜像放到仓库里



推荐使用docker官方的镜像仓库：https://hub.docker.com/search?q=nginx

![image-20240717214911378](./assets/image-20240717214911378.png)





**Docker 实现核心**



> Docker 能够实现哪些资源的隔离？



![image-20240717215002173](./assets/image-20240717215002173.png)



看图理解：

1. Docker运行在Linux内核上
2. CGroups:实现了容器的资源隔离，底层是Linux Cgroup命令，能够控制进程使用的资源
3. Network网络：实现容器的网络隔离，docker容器内部的网络互不影响
4. Namespaces命名空间：可以把进程隔离在不同的命名空间下，每个容器他都可以有自己的命名空间，不同的命名空间下的进程互不影响。
5. Storage存储空间：容器内的文件是相互隔离的，也可以去使用宿主机的文件



docker compose:是一种同时启动多个容器的集君操作工具（容器管理工具），一般情况下，开发者仅做了解即可，实际使用docker compose时去百度配置文件



###### 2.8.2 虚拟机 + 远程开发环境搭建、Docker命令实操



**安装Docker **



我这里，就不用虚拟机了，我直接上了服务器（腾讯云的），还有几天到期



docker 我之前也装过了



![image-20240717215706583](./assets/image-20240717215706583.png)



直接干，有问题再说



> 常用操作就不学了，贴一些笔记，这玩意儿用过很久了



**命令行操作Docker **



1. 查看命令用法

   ```shell
   docker --help
   ```

   查看具体子命令用法

   ```shell
   docker run --help
   ```

2. 从远程仓库拉取现成的镜像

   ```shell
   docker pull [OPTIONS] NAME[:TAG|@DIGEST]
   ```

   示例

   ```shell
   docker pull hello-world
   ```



3. 根据镜像创建容器实例：

   ```shell
   docker create [OPTIONS] IMAGE [COMMAND] [ARG...]
   ```

   启动实例，得到容器实例containerld:

   ```shell
   sudo docker create hello-world
   ```

4. 查看容器状态

   ```shell
   sudo docker ps -a
   ```

5. 启动容器

   ```shell
   docker start [OPTIONS] CONTAINER [CONTAINER...]
   ```

   示例

   ```shell
   sudo docker start mystifying_shamir
   ```

6. 查看日志

   ```shell
   docker logs [OPTIONS] CONTAINER
   ```

   示例

   ```shell
   sudo docker logs mystifying_shamir
   ```

7. 删除容器实例

   ```shell
   docker rm [OPTIONS] CONTAINER [CONTAINER...]
   ```

   示例

   ```shell
   sudo docker rm mystifying_shamir
   ```

8. 删除镜像

   ```shell
   docker rmi --help
   ```

   示例【强制的】

   ```shell
   sudo docker rmi hello-world -f
   ```

9. 其他：构建镜像(build)、推送镜像(push)、运行容器(run)、执行容器命令(exec)等



![image-20240717220207153](./assets/image-20240717220207153.png)





###### 2.8.3 Java 操作 Docker



**前置准备**



使用 Docker-Java ：https://github.com/docker-java/docker-java

官方入门：https://github.com/docker-java/docker-java/blob/main/docs/getting_started.md



![image-20240717220420227](./assets/image-20240717220420227.png)





先引入依赖：

```xml
<!-- https://mvnrepository.com/artifact/com.github.docker-java/docker-java -->
<dependency>
    <groupId>com.github.docker-java</groupId>
    <artifactId>docker-java</artifactId>
    <version>3.3.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.github.docker-java/docker-java-transport-httpclient5 -->
<dependency>
    <groupId>com.github.docker-java</groupId>
    <artifactId>docker-java-transport-httpclient5</artifactId>
    <version>3.3.0</version>
</dependency>
```



![image-20240717220518166](./assets/image-20240717220518166.png)



DockerClientConfig:用于定义初始化DockerClient的配置（类比ySQL的连接、线程数配置）

DockerHttpClient:用于向Docker守护进程（操作Docker的接口）发送请求的客户端，低层封装（不推荐使用），你要自己构建请求参数（简单地理解成JDBC)

DockerClient(推荐)：才是真正和Docker守护进程交互的、最方便的SDK,高层封装，对DockerHttpClient再进行了一层封装（理解成MyBatis),提供了现成的增删改查





**远程开发**

使用IDEA Development先上传代码到Linux,然后使用JetBrains远程开发完全连接Linux实时开发。



![image-20240717220810276](./assets/image-20240717220810276.png)



![image-20240717220903691](./assets/image-20240717220903691.png)



这里没看那个安装教程，就估摸着来吧，直接ping 的服务器公网 `IP`



![image-20240717221151018](./assets/image-20240717221151018.png)



![image-20240717221217447](./assets/image-20240717221217447.png)



改成用户的根目录



![image-20240717221436081](./assets/image-20240717221436081.png)



这里就放在root 下面了



![image-20240717221503824](./assets/image-20240717221503824.png)



也没啥东西，之前用户中心项目用了用





外面也能连通



![image-20240717221256347](./assets/image-20240717221256347-1721225576600-1.png)





这里要直接全部甩到服务器上



![image-20240717221728627](./assets/image-20240717221728627.png)



![image-20240717221748864](./assets/image-20240717221748864.png)



![image-20240717221815876](./assets/image-20240717221815876.png)



![image-20240717221834059](./assets/image-20240717221834059.png)



![image-20240717221907640](./assets/image-20240717221907640.png)



同步所有



![image-20240717221927450](./assets/image-20240717221927450.png)



完成同步，看服务器上



![image-20240717222028948](./assets/image-20240717222028948.png)



这样就同步了，开启即时同步



![image-20240717222144532](./assets/image-20240717222144532.png)



![image-20240717222213579](./assets/image-20240717222213579.png)



加了一些空行，立即同步了，擦，但是这种方式不推荐. ..

还有一种



![image-20240717222321279](./assets/image-20240717222321279.png)



![image-20240717222400906](./assets/image-20240717222400906.png)



![image-20240717222520018](./assets/image-20240717222520018.png)



这里我直接强行继续了



![image-20240717222548502](./assets/image-20240717222548502.png)

选择项目根目录



![image-20240717222618995](./assets/image-20240717222618995.png)



![image-20240717222648697](./assets/image-20240717222648697.png)



这好似就是在Linux 上也装一个idea



![image-20240717222734268](./assets/image-20240717222734268.png)



> 这个怕是有点吃内存，等会儿看吧【实在不行，我也要上虚拟机了】



![image-20240717223411571](./assets/image-20240717223411571.png)



多半跑不动



![image-20240718130540591](./assets/image-20240718130540591.png)



好像行

![image-20240718130704843](./assets/image-20240718130704843.png)



![image-20240718130735723](./assets/image-20240718130735723.png)



居然跑起来了





![image-20240718130837021](./assets/image-20240718130837021.png)



虽然内存也快拉满 了



![image-20240718131304123](./assets/image-20240718131304123.png)



使用IDEA Development先上传代码到Linux,然后使用JetBrains远程开发完全连接Linux实时开发。



![image-20240718132553422](./assets/image-20240718132553422.png)



没问题能用，刷一下Maven



![image-20240718132804596](./assets/image-20240718132804596.png)



来吧直接试试，



![image-20240718132941784](./assets/image-20240718132941784.png)



应该是没问题【我的居然没报错】



常用操作：



【1】拉取镜像：



![image-20240718133454121](./assets/image-20240718133454121.png)



运行结果：

![image-20240718133635795](./assets/image-20240718133635795.png)



成功了



![image-20240718133648595](./assets/image-20240718133648595.png)



妙啊【皮总翻车】



【2】创建容器：



```java
CreateContainerCmd containerCmd = dockerClient.createContainerCmd(image);
CreateContainerResponse createContainerResponse = containerCmd
        .withCmd("echo", "Hello Docker")
        .exec();
System.out.println(createContainerResponse);
```



![image-20240718133947591](./assets/image-20240718133947591.png)



运行结果：



![image-20240718134017570](./assets/image-20240718134017570.png)



这个肯定就是容器ID 了



![image-20240718134045038](./assets/image-20240718134045038.png)



没毛病，确实创建成功了



【3】查看容器状态：



```java
ListContainersCmd listContainersCmd = dockerClient.listContainersCmd();
List<Container> containerList = listContainersCmd.withShowAll(true).exec();
for (Container container : containerList) {
    System.out.println(container);
}
```



![image-20240718134147262](./assets/image-20240718134147262.png)



运行结果：



![image-20240718134249399](./assets/image-20240718134249399.png)



没毛病



【4】启动容器：

```java
dockerClient.startContainerCmd(containerId).exec();
```



![image-20240718134433230](./assets/image-20240718134433230.png)



![image-20240718134450532](./assets/image-20240718134450532.png)



![image-20240718134508893](./assets/image-20240718134508893.png)



启动失败了，像鱼皮那样写试试



![image-20240718134632419](./assets/image-20240718134632419.png)



![image-20240718134648028](./assets/image-20240718134648028.png)



![image-20240718134701563](./assets/image-20240718134701563.png)



也没有跑起来，我知道 了，端口，我Linux 本机有一个NGINX 在运行



![image-20240718134940512](./assets/image-20240718134940512.png)



直接沙雕



![image-20240718135016826](./assets/image-20240718135016826.png)



OK，再来一次

![image-20240718135043053](./assets/image-20240718135043053.png)



![image-20240718135058548](./assets/image-20240718135058548.png)



啊这，我直接启动我的那个redis 容器试试



![image-20240718135239253](./assets/image-20240718135239253.png)



![image-20240718135320091](./assets/image-20240718135320091.png)



这样就行，离谱，哦，皮总也没运行出来，启动命令的问题，没用守护进程



【5】查看日志



```java
// 查看日志
LogContainerResultCallback logContainerResultCallback = new LogContainerResultCallback() {
    @Override
    public void onNext(Frame item) {
        System.out.println(item.getStreamType());
        System.out.println("日志：" + new String(item.getPayload()));
        super.onNext(item);
    }
};

// 阻塞等待日志输出
dockerClient.logContainerCmd(containerId)
        .withStdErr(true)
        .withStdOut(true)
        .exec(logContainerResultCallback)
        .awaitCompletion();
```



![image-20240718135930539](./assets/image-20240718135930539.png)



没问题，这个是我redis 的日志【还是之前那个伙伴匹配系统开的】



【6】删除容器

```java
dockerClient.removeContainerCmd(containerId).withForce(true).exec();
```



![image-20240718140229856](./assets/image-20240718140229856.png)



【7】删除镜像：

```java
dockerClient.removeImageCmd(image).exec();
```



![image-20240718140316992](./assets/image-20240718140316992.png)



必须容器先干完，再删



![image-20240718140448363](./assets/image-20240718140448363.png)



没问题





##### 2.9 代码沙箱 Docker 实现



###### 2.9.1 核心流程实现



实现思路：docker负责运行java程序，并且得到结果。

流程几乎和Java原生实现流程相同：

1. 把用户的代码保存为文件
2. 编译代码，得到class文件
3. 把编译好的文件上传到容器环境内
4. 在容器中执行代码，得到输出结果
5. 收集整理输出结果
6. 文件清理，释放空间
7. 错误处理，提升程序健壮性



直接复制了



![image-20240718141321348](./assets/image-20240718141321348.png)



![image-20240718141932992](./assets/image-20240718141932992.png)





来吧

**创建容器，上传编译文件**



自定义容器的两种方式：

1. 在已有镜像的基础上再扩充：比如拉取现成的Java环境（包含jdk),再把编译后的文件复制到容器里。适合新项目、跑通流程
2. 完全自定义容器：适合比较成熟的项目，比如封装多个语言的环境和实现



思考：我们每个测试用例都单独创建一个容器，每个容器只执行一次java命令？

浪费性能，所以要创建一个可交互的容器，能接受多次输入并且输出。

创建容器时，可以指定文件路径(Volumn)映射，【又叫容器挂载目录】作用是把本地的文件同步到容器中，可以让容器访问。



```java
HostConfig hostConfig = new HostConfig();
hostConfig.setBinds(new Bind(userCodeParentPath, new Volume("/app")));
```



![image-20240718143008739](./assets/image-20240718143008739.png)





![image-20240718143244174](./assets/image-20240718143244174.png)



到这里，先执行一下吧



![image-20240718143318839](./assets/image-20240718143318839.png)



文件改一下，启动



![image-20240718143503891](./assets/image-20240718143503891.png)



我这里直接没找到，配一个环境变量



![image-20240718144116711](./assets/image-20240718144116711.png)



我把open jdk 删掉了



再试一次



![image-20240718144157702](./assets/image-20240718144157702.png)



重新连接一下啊



![image-20240718144213510](./assets/image-20240718144213510.png)



应该是有命令的



![image-20240718144412888](./assets/image-20240718144412888.png)



还是没找到我的javac



![image-20240718144444077](./assets/image-20240718144444077.png)



这里都没有，那就是环境变量没对



![image-20240718144511936](./assets/image-20240718144511936.png)



再试一次



![image-20240718144523643](./assets/image-20240718144523643.png)



不对劲啊



![image-20240718144555870](./assets/image-20240718144555870.png)



这每次都要source 一下啊？



![image-20240718144739378](./assets/image-20240718144739378.png)



感觉是没权限啊



![image-20240718144857665](./assets/image-20240718144857665.png)



再来一次



![image-20240718144918812](./assets/image-20240718144918812.png)



还是不行，重启试试，我意思重启服务器



![image-20240718145056158](./assets/image-20240718145056158.png)



如果这都不行就再说



![image-20240718145114366](./assets/image-20240718145114366.png)



![image-20240718145206996](./assets/image-20240718145206996.png)



docker 重启一下啊，再来



![image-20240718145345234](./assets/image-20240718145345234.png)



这次应该行了



![image-20240718145437955](./assets/image-20240718145437955.png)



这次在跑了，应该在拉镜像了



![image-20240718145500364](./assets/image-20240718145500364.png)



![image-20240718145507031](./assets/image-20240718145507031.png)



没问题



![image-20240718145526476](./assets/image-20240718145526476.png)



拉下来了【果然重启解决90 % 问题】



![image-20240718145622228](./assets/image-20240718145622228.png)



而且这个容器还一直活着



![image-20240718145659141](./assets/image-20240718145659141.png)



**启动容器，执行代码**



就是因为这个，示例执行如下：



![image-20240718145826994](./assets/image-20240718145826994.png)



其实就是执行这个命令，妙啊

上面是在命令行里面，下面要在程序中实现，

注意，要把命令按照空格拆分，作为一个数组传递，否则可能会被识别为一个字符串，而不是多个参数。

创建命令：



```java
String[] inputArgsArray = inputArgs.split(" ");
String[] cmdArray = ArrayUtil.append(new String[]{"java", "-cp", "/app", "Main"}, inputArgsArray);
ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
        .withCmd(cmdArray)
        .withAttachStderr(true)
        .withAttachStdin(true)
        .withAttachStdout(true)
        .exec();
```



![image-20240718151215033](./assets/image-20240718151215033.png)



试试

![image-20240718151317932](./assets/image-20240718151317932.png)



没毛病，就是又去拉了一次镜像



![image-20240718151342065](./assets/image-20240718151342065.png)



3 的在这儿

![image-20240718151547259](./assets/image-20240718151547259.png)



容器启了一堆。

尽量复用之前的ExecuteMessage对象，在异步接口中填充正常和异常信息，这样之后流程的代码都可以复用。



**获取结果**



![image-20240718152338862](./assets/image-20240718152338862.png)



没啥问题



**获取程序执行时间**



和Java原生一样，使用StopWatch在执行前后统计时间。



![image-20240718152744857](./assets/image-20240718152744857.png)



试一次



![image-20240718153020963](./assets/image-20240718153020963.png)



没问题



**获取程序占用内存**



程序占用的内存每个时刻都在变化，所以你不可获取到所有时间点的内存。

我们要做的是，定义一个周期，定期地获取程序的内存。

Docker-Java提供了内存定期统计的操作，示例代码如下：



```java
// 获取占用的内存
StatsCmd statsCmd = dockerClient.statsCmd(containerId);
ResultCallback<Statistics> statisticsResultCallback = statsCmd.exec(new ResultCallback<Statistics>() {

    @Override
    public void onNext(Statistics statistics) {
        System.out.println("内存占用：" + statistics.getMemoryStats().getUsage());
        maxMemory[0] = Math.max(statistics.getMemoryStats().getUsage(), maxMemory[0]);
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void onStart(Closeable closeable) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
});
statsCmd.exec(statisticsResultCallback);
```



注意，程序执行完后，要关闭统计命令：

```java
statsCmd.close()
```



![image-20240718153215262](./assets/image-20240718153215262.png)



试试



![image-20240718153301603](./assets/image-20240718153301603.png)



第二次的有点问题，没拿到



![image-20240718153747997](./assets/image-20240718153747997.png)



好，皮总也没有【这玩意儿不稳定】



![](./assets/image-20240718153846638-1721288326946-4.png)



有时候一个，有时候两个，睡一会儿 ...？



试试



![image-20240718153932871](./assets/image-20240718153932871.png)



试试



![image-20240718154037223](./assets/image-20240718154037223.png)



这次行了，一个不到一兆，一个一兆多一点



###### 2.9.2 Docker 容器安全性



**超时控制**

执行容器时，可以增加超时参数控制值：



```java
dockerClient.execStartCmd(execId)
        .exec(execStartResultCallback)
        .awaitCompletion(TIME_OUT, TimeUnit.MICROSECONDS);
```



![image-20240718154544125](./assets/image-20240718154544125.png)



但是，这种方式无论超时与否，都会往下执行，无法判断是否超时。

解决方案：可以定义一个标志，如果程序执行完成，把超时标志设置为false。

示例代码如下：



```java
// 判断是否超时
final boolean[] timeout = {true};
String execId = execCreateCmdResponse.getId();
ExecStartResultCallback execStartResultCallback = new ExecStartResultCallback() {
    @Override
    public void onComplete() {
        // 如果执行完成，则表示没超时
        timeout[0] = false;
        super.onComplete();
    }
    
	...
};
```



![image-20240718154818720](./assets/image-20240718154818720.png)





**内存资源**



通过HostConfig的withMemory等方法，设置容器的最大内存和资源限制：



![image-20240718155131907](./assets/image-20240718155131907.png)





**网络资源**

创建容器时，设置网络配置为关闭：



![image-20240718155248842](./assets/image-20240718155248842.png)





**权限管理**



Docker容器已经做了系统层面的隔离，比较安全，但不能保证绝对安全。

1. 结合Java安全管理器和其他策略去使用
2. 限制用户不向root根目录写文件：



![image-20240718155329866](./assets/image-20240718155329866.png)



3. Linux自带的一些安全管理措施，比如seccomp(Secure Computing Mode)是一个用于Linux内核的安全功能，它允许你限制进程可以执行的系统调用，从而减少潜在的攻击面和提高容器的安全性。通过配置seccomp,你可以控制容器内进程可以使用的系统调用类型和参数。



示例seccomp配置文件profile.json:



```json
{
  "defaultAction": "SCMP_ACT_ALLOW",
  "syscalls": [
    {
      "name": "write",
      "action": "SCMP_ACT_ALLOW"
    },
    {
      "name": "read",
      "action": "SCMP_ACT_ALLOW"
    }
  ]
}
```





![image-20240718155551824](./assets/image-20240718155551824.png)



当然这里要把它换成那个配置文件的内容



###### 2.9.3 模板方法模式



**模板方法优化代码沙箱**



模板方法：定义一套通用的执行流程，让子类负责每个执行步聚的具体实现

模板方法的适用场景：适用于有规范的流程，且执行流程可以复用

作用：大幅节省重复代码量，便于项目扩展、更好维护



![image-20240718160109075](./assets/image-20240718160109075.png)



稍微同步一下啊



![image-20240718160143480](./assets/image-20240718160143480.png)



这就是之前在那个远程开发上写的服务器代码





【1】抽象出具体的流程



定义一个模板方法抽象类。

先复制具体的实现类，把代码从完整的方法抽离成一个一个子写法



> 这里皮总又回到本地进行开发了



![image-20240718161252924](./assets/image-20240718161252924.png)



这里就是复制，抽



【2】定义子类的具体实现

Java原生代码沙箱实现，直接复用模板方法定义好的方法实现：



![image-20240718161651316](./assets/image-20240718161651316.png)



这就简单了太多了，直接继承了，一个方法就不错了。



Docker代码沙箱实现，需要自行重写RunFile:



![image-20240718161925283](./assets/image-20240718161925283.png)



来吧，测试一下



![image-20240718162357810](./assets/image-20240718162357810.png)



没问题的，就随便写了一个main 方法



##### 2.10 代码沙箱开放API（API安全性）



直接在controller暴露CodeSandbox定义的接☐：



![image-20240718162656474](./assets/image-20240718162656474.png)



跑起来吧



![image-20240718162912595](./assets/image-20240718162912595.png)



8090 端口，这下要回到我们真正的项目里面了



![image-20240718163027341](./assets/image-20240718163027341.png)



哈哈，感觉好久没用了



![image-20240718163117642](./assets/image-20240718163117642.png)



之前都是假的，示例，这下来真的，改造远程代码沙箱实现



![image-20240718163518026](./assets/image-20240718163518026.png)



来吧测试



![image-20240718163544926](./assets/image-20240718163544926.png)



直接改成调用远程沙箱



![image-20240718163708346](./assets/image-20240718163708346.png)



直接单元测试，运行结果



![image-20240718163740738](./assets/image-20240718163740738.png)



妙啊妙啊【现在是原生的，所以没有内存信息】



`API ` 开放平台，哈，我也学过



**调用安全性**



如果将服务不做任何的权限校验，直接发到公网，是不安全的。

【1】调用方与服务提供方之间约定一个字符串（最好加密）

优点：实现最简单，比较适合内部系统之间相互调用（相对可信的环境内部调用）

缺点：不够灵活，如果ky泄露或变更，需要重启代码

代码沙箱服务，先定义约定的字符串： and 改造请求，从请求头中获取认证信息，并校验：



![image-20240718164233801](./assets/image-20240718164233801.png)



调用方，在调用时补充请求头：



![image-20240718164350311](./assets/image-20240718164350311.png)



测试一下



![image-20240718164408849](./assets/image-20240718164408849.png)



没问题，如果ak 或者 sk 不对



![image-20240718164423877](./assets/image-20240718164423877.png)



![image-20240718164645133](./assets/image-20240718164645133.png)



就直接报错了



【2】API签名认证

给允许调用的人员分配accessKey、secretKey,然后校验这两组key是否匹配

详细请见`API`开放平台项目。



![image-20240718164756383](./assets/image-20240718164756383.png)



##### 2.11 跑通项目流程



先合并一下Controller



【1】移动questionSubmitController代码到questionController中





![image-20240718165209588](./assets/image-20240718165209588.png)



![image-20240718165313566](./assets/image-20240718165313566.png)



全部注掉而且过时



直接重启项目，前端干

【2】由于后端改了接口地址，前端需要重新生成接口调用代码



`openapi --input http://localhost:8101/api/v2/api-docs --output ./xiongoj_backendapi --client axios`



![image-20240718165608578](./assets/image-20240718165608578.png)

还需要更改前端调用的Controller



![image-20240718165753398](./assets/image-20240718165753398.png)



这里要改



直接提交



![image-20240718170145960](./assets/image-20240718170145960.png)



![image-20240718170157518](./assets/image-20240718170157518.png)



返回了一个ID



![image-20240718170212053](./assets/image-20240718170212053.png)



这边也已经结束了



![image-20240718170245213](./assets/image-20240718170245213.png)



执行也已经结束了，36，



![image-20240718170353133](./assets/image-20240718170353133.png)



因为题目里面我给的判题是12 和 24 没问题的，改一下啊



![image-20240718170550902](./assets/image-20240718170550902.png)



![image-20240718170608022](./assets/image-20240718170608022.png)



数据库也改了，

再来一次



![image-20240718170651277](./assets/image-20240718170651277.png)



提交成功，看看结果



![image-20240718170710499](./assets/image-20240718170710499.png)



妙啊，ac 了



【3】后端调试

【4】开发提交列表页面



这里先把页面做了，就是能看见所有状态的页面



![image-20240718171251799](./assets/image-20240718171251799.png)



![image-20240718171237932](./assets/image-20240718171237932.png)





##### 2.12 微服务入门



###### 2.12.1 基本概念、微服务实现技术、Spring Cloud Alibaba入门、分布式登录



**单体项目改造为微服务**



新建一个项目



**什么是微服务**



服务：提供某类功能的代码

微服务：专注于提供某类特定功能的代码，而不是把所有的代码全部放到同一个项目里。会把整个大的项目按照一定的功能、逻辑进行拆分，拆分为多个子模块，每个子模块可以独立运行、独立负责一类功能，子模块之间相互调用、互不影响。

一个公司：一个人干活，这个人icu了，公司直接倒闭

一个公司有多个不同类的岗位，多个人干活，一个组跨了还有其他组可以正常工作，不会说公司直接倒闭。各组之间可需要交互，来完成大的目标。

微服务的几个重要的实现因素：服务管理、服务调用、服务拆分



**微服务实现技术**



Spring Cloud

Spring Cloud Alibaba(本项目采用)

Dubbo (DubboX)

RPC(GRPC、TRPC)

本质上是通过HTTP、或者其他的网络协议进行通讯来实现的。



**Spring Cloud Alibaba**

https://github.com/alibaba/spring-cloud-alibaba

中文文档：https://sca.aliyun.com/



![image-20240718171934931](./assets/image-20240718171934931.png)



本质：是在Spring Cloud的基础上，进行了增强，补充了一些额外的能力，根据阿里多年的业务沉淀做了一些定制化的开发

1. Spring Cloud Gateway:网关
2. Nacos:服务注册和配置中心
3. Sentinel:熔断限流
4. Seata:分布式事务
5. RocketMQ:消息队列，削峰填谷
6. Docker:使用Docker进行容器化部署
7. Kubernetes:使用k8s进行容器化部署



![image-20240718172112817](./assets/image-20240718172112817.png)



注意，一定要选择对应的版本：https://sca.aliyun.com/docs/2021/overview/version-explain/

此处选择2021.0.5.0：



![image-20240718172157062](./assets/image-20240718172157062.png)



Nacos:集中存管项目中所有服务的信息，便于服务之间找到彼此；同时，还支特集中存储整个项目中的配置。

整个微服务请求流程：



![image-20240718172221140](./assets/image-20240718172221140.png)





**改造前思考**



从业务需求出发，思考单机和分布式的区别。、

用户登录功能：需要改造为分布式登录
其他内容：

- 有没有用到单机的锁？改造为分布式锁（伙伴匹配系统讲过）
- 有没有用到本地缓存？改造为分布式缓存(Redis)
- 需不需要用到分布式事务？比如操作多个库



**改造分布式登录**



【1】application.yml增加redis配置



![image-20240718172432193](./assets/image-20240718172432193.png)



【2】补充依赖



![image-20240718172500805](./assets/image-20240718172500805.png)



模板自带了



【3】主类取消Redis自动配置的移除



![image-20240718172524639](./assets/image-20240718172524639.png)



【4】修改session存储方式：



![image-20240718172613586](./assets/image-20240718172613586.png)



【5】使用redis-cli或者redis管理工具，查看是否有登录后的信息



来吧试试



![image-20240718172626331](./assets/image-20240718172626331.png)



现在还啥也没有



![image-20240718172704944](./assets/image-20240718172704944.png)



登录成功



![image-20240718172716474](./assets/image-20240718172716474.png)



有了，这样就算完成了其实【一个主体，两个过期条件】



##### 2.13 微服务改造



###### 2.13.1 服务划分、路由划分、Nacos注册中心



**微服务的划分**



从业务出发，想一下哪些功能/职责是一起的？

> 公司老板给员工分工

依赖服务：

- 注册中心：Nacos
- 微服务网关(lzuoj_backend_gateway)：Gateway聚合所有的接口，统一接受处理前端的请求

公共模块：

- common公共模块(lzuoj-backend-common)：全局异常处理器、请求响应封装类、公用的工具类等
- model模型模块(lzuoj-backend-model):很多服务公用的实体类
- 公用接口模块(lzuoj-backend-service-client)：只存放接口，不存放实现（多个服务之间要共享）



业务功能：



1. 用户服务( `lzuoj-backend-user-service` :8102端☐)：
    - 注册（后端已实现）
    - 登录（后端已实现，前端已实现）
    - 用户管理
2. 题目服务( `lzuoj-backend-question-service` :8103)
    - 创建题目（管理员）
    - 删除题目（管理员）
    - 修改题目（管理员）
    - 搜索题目（用户）
    - 在线做题（题目详情页）
    - 题目提交
3. 判题服务( `lzuoj-backend-judge-service` ,8104端口，较重的操作)
    - 执行判题逻辑
    - 错误处理（内存溢出、安全性、超时）
    - 自主实现代码沙箱（安全沙箱）
    - 开放接口（提供一个独立的新服务）





> 代码沙箱服务本身就是独立的，不用纳入Spring Cloud的管理





**路由划分**



用springboot的context-path统一修改各项目的接☐前缀，比如：

用户服务：

- /api/user
- /api/user/inner(内部调用，网关层面要做限制)

题目服务：

- /api/question(也包括题目提交信息)
- /api/question/inner(内部调用，网关层面要做限制)

判题服务：

- /api/judge
- /api/judge/inner(内部调用，网关层面要故限制)





**Nacos 注册中心启动**



一定要选择 `2.2.0` 版本！！！



![image-20240718173626681](./assets/image-20240718173626681.png)



额，再来一个吧

教程：https://sca.aliyun.com/docs/2021/user-guide/nacos/overview/

Nacos 官网教程：https://nacos.io/zh-cn/docs/quick-start.html

官网下载：https://github.com/alibaba/nacos/releases/tag/2.2.0

【`https://github.com/alibaba/nacos/releases/download/2.2.0/nacos-server-2.2.0.zip`】



![image-20240718173839966](./assets/image-20240718173839966.png)



安装好后，进入bin目录启动：

```shell
startup.cmd -m standalone
```



![image-20240718173917728](./assets/image-20240718173917728.png)



8848 成功启动





###### 2.13.2 Maven 子父工程生成



**新建工程**



Spring Cloud有相当多的依赖，参差不齐，不建议大家随意找一套配置、或者自己写。



建议用脚手架：https://start.aliyun.com/



![image-20240718174508038](./assets/image-20240718174508038.png)



![image-20240718174714125](./assets/image-20240718174714125.png)

给项目增加全局依赖配置文件。

依赖勾了8 个，获取代码



> 从后面回来了，这里应该要重新做一下，名称G 了



![image-20240718174735460](./assets/image-20240718174735460.png)



![image-20240718174826885](./assets/image-20240718174826885.png)



直接干开

![image-20240718174913481](./assets/image-20240718174913481.png)



构建完成



创建完初始项目后，补充Spring Cloud依赖：



```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-dependencies</artifactId>
    <version>2021.0.5</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```



![image-20240718175201476](./assets/image-20240718175201476.png)



依次使用new modules和spring boot Initializr创建各模块：



![image-20240718175352448](./assets/image-20240718175352448.png)



![image-20240718175440281](./assets/image-20240718175440281.png)



![image-20240718175613043](./assets/image-20240718175613043.png)



![image-20240718175701164](./assets/image-20240718175701164.png)



![image-20240718175803140](./assets/image-20240718175803140.png)



![image-20240718175825454](./assets/image-20240718175825454.png)



![image-20240718175917502](./assets/image-20240718175917502.png)



![image-20240718175941698](./assets/image-20240718175941698.png)



![image-20240718180016501](./assets/image-20240718180016501.png)



![image-20240718180036638](./assets/image-20240718180036638.png)



![image-20240718180112390](./assets/image-20240718180112390.png)



![image-20240718180421507](./assets/image-20240718180421507.png)



![image-20240718180446947](./assets/image-20240718180446947.png)



ok ,创建完成



需要给各模块之间绑定子父依赖关系，效果如下：



![image-20240718181006645](./assets/image-20240718181006645.png)





父模块定义modules,子模块引入parent语法，可以通过继承父模块配置，统一项目的定义和版本号。





###### 2.13.3 代码依赖同步



**体力活**



**同步代码和依赖**



【1】common公共模块(lzuoj-backend-common)：全局异常处理器、请求响应封装类、公用的工具类等



![image-20240718182219913](./assets/image-20240718182219913.png)



【2】model模型模块(lzuoj-backend-model)：很多服务公用的实体类



![image-20240718182900059](./assets/image-20240718182900059.png)



【3】公用接口模块(lzuoj-backend-service-client):只存放接口，不存放实现（多个服务之间要共享）



先无脑搬运所有的service,judgeService也需要搬运



![image-20240718183902443](./assets/image-20240718183902443.png)



【4】具体业务服务实现

给所有业务服务引入公共依赖：【这里直接看的笔记，把client 一起粘了】



![image-20240718184157681](./assets/image-20240718184157681.png)



从用户服务开始



![image-20240718185154417](./assets/image-20240718185154417.png)



![image-20240718185301205](./assets/image-20240718185301205.png)



端口8102



运行一下啊



![image-20240718185452352](./assets/image-20240718185452352.png)



没问题



题目服务



![image-20240718190750012](./assets/image-20240718190750012.png)



![image-20240718190720149](./assets/image-20240718190720149.png)



端口8103



> 要吐了



判题服务



![image-20240718195028410](./assets/image-20240718195028410.png)



![image-20240718200713114](./assets/image-20240718200713114.png)



8104 端口【图里面没改到】



###### 2.13.4 Open Feign 服务内部调用



**服务内部调用**



现在的问题是，题目服务依赖用户服务，但是代码已经分到不同的包，找不到对应的Bean。

可以使用Open Feign组件实现跨服务的远程调用。

Open Feign:Http调用客户端，提供了更方便的方式来让你远程调用其他服务，不用关心服务的调用地址

Nacos注册中心获取服务调用地址



【1】梳理服务的调用关系，确定哪些服务（接口）需要给内部调用



用户服务：没有其他的依赖

题目服务：

- userService.getByld(userld)
- userService.getUserVO(user)
- userService.listBylds(userldSet)
- userService.isAdmin(loginUser)
- userService.getLoginUser(request)
- judgeService.doJudge(questionSubmitld)



判题服务：

- questionService.getByld(questionld)
- questionSubmitService.getByld(questionSubmitld)
- questionSubmitService.updateByld(questionSubmitUpdate)





【2】确认要提供哪些服务



用户服务：没有其他的依赖

- userService.getByld(userld)
- userService.getUserVO(user)
- userService.listBylds(userldSet)
- userService.isAdminlloginUser)
- userService.getLoginUser(request)

题目服务：

- questionService.getByld(questionld)
- questionSubmitService.getByld(questionSubmitld)
- questionSubmitService.updateByld(questionSubmitUpdate)

判题服务：

- judgeService.doJudge(questionSubmitld)



【3】实现Client 接口

对于用户服务，有一些不利于远程调用参数传递、或者实现起来非常简单（工具类），可以直接用默认方法，无需远程调用，节约性能



![image-20240718200922683](./assets/image-20240718200922683.png)



开启openfeign的支持，把我们的接口暴露出去（服务注册到注册中心上），作为 `API` 给其他服务调用（其他服务从注册中心寻找）

需要修改每个服务提供者的context-path全局请求路径



```yaml
server:
  address: 0.0.0.0
  port: 8104
  servlet:
    context-path: /api/judge
```



![image-20240718200645979](./assets/image-20240718200645979.png)



- 服务提供者：理解为接口的实现类，实际提供服务的模块（服务注册到注册中心上）

- 服务消费者：理解为接口的调用方，需要去找到服务提供者，然后调用。（其他服务从注册中心寻找）



注意事项：

1. 要给接口的每个方法打上请求注解，注意区分Get、Post
2. 要给请求参数打上注解，比如RequestParam、RequestBody
3. FeignClient定义的请求路径一定要和服务提供方实际的请求路径保特一致



![image-20240718200807515](./assets/image-20240718200807515.png)



【4】修改各业务服务的调用代码为feignClient



![image-20240718201441840](./assets/image-20240718201441840.png)



【5】编写feignClient服务的实现类，注意要和之前定义的客户端保持一致



![image-20240718201810228](./assets/image-20240718201810228.png)



题目这些也是同理的



![image-20240718202036138](./assets/image-20240718202036138.png)



还有判题服务



![image-20240718202059190](./assets/image-20240718202059190.png)







【6】开启Nacos的配置，让服务之间能够互相发现

所有模块引入Nacos依赖，然后给业务服务（包括网关）增加配置：



```yaml
spring:
    cloud:
        nacos:
          discovery:
            server-addr: 127.0.0.1:8848  
```



![image-20240718202400201](./assets/image-20240718202400201.png)



给业务服务项目启动类打上注解，开启服务发现、找到对应的客户端Bean的位置：



```java
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.dingjiaxiong.lzuoj_backend_service_client.service"})
```



![image-20240718202708060](./assets/image-20240718202708060.png)



题目服务都是一样的



![image-20240718202757981](./assets/image-20240718202757981.png)



![image-20240718203030448](./assets/image-20240718203030448.png)



网关就不用要服务了



全局引入负载均衡器依赖：

![image-20240718203132829](./assets/image-20240718203132829.png)





【7】启动项目，测试依赖能否注入，能否完成相互调用



![image-20240718203314700](./assets/image-20240718203314700.png)



码垛，我这儿没提示我



![image-20240718203723289](./assets/image-20240718203723289.png)



我超，这里遇到个巨坑，这个不能用下划线，炸裂，



![image-20240718204339909](./assets/image-20240718204339909.png)



全部都换了下，再来一次【大坑】这样改了就找不到主类了我的天



![image-20240718205424538](./assets/image-20240718205424538.png)



全部重新贴了一下啊，这次用中划线，不用下划线



又重新干了一遍



![image-20240718214045319](./assets/image-20240718214045319.png)



再来一次，



![image-20240718214135201](./assets/image-20240718214135201.png)



虽然还是没提示我加入，那我先启动用户服务



![image-20240718214254045](./assets/image-20240718214254045.png)



judge 那儿没改到



![image-20240718214424800](./assets/image-20240718214424800.png)



这里再来一次



![image-20240718214457176](./assets/image-20240718214457176.png)



question 也是，到这里我发现了，我好像只用改一下这个地方就可以了，救命。。。



![image-20240718214533091](./assets/image-20240718214533091.png)



用户服务8102 启动了



![image-20240718214714909](./assets/image-20240718214714909.png)



判题在8104



![image-20240718214738699](./assets/image-20240718214738699.png)



题目服务在 8103



注册中心：



![image-20240718214924342](./assets/image-20240718214924342.png)



没问题，三大服务提供者，这样就没问题了



试一下获取题目信息：



![image-20240718215233261](./assets/image-20240718215233261.png)



有点问题



![image-20240718215240998](./assets/image-20240718215240998.png)





![image-20240718220623918](./assets/image-20240718220623918.png)



像是在说，user 不能反序列化



死在这儿了，不知道什么原因



![image-20240718221305268](./assets/image-20240718221305268.png)



这个不是我原先那个项目



![image-20240718221422481](./assets/image-20240718221422481.png)



我把那边退出登录后，这次就进接口了



![image-20240718221447632](./assets/image-20240718221447632.png)



返回未登录，蛙趣，这也太离谱了，不同的类不能正反序列化【这说明接口还是调用通了】



当然这只是默认方法，试试远程方法



![image-20240718221958192](./assets/image-20240718221958192.png)



没问题，直接拿到封装后的题目信息



![image-20240718222353001](./assets/image-20240718222353001.png)





##### 2.14 GateWay微服务网关



###### 2.14.1 接口路由



微服务网关(lzuoj-backend-gateway):Gateway聚合所有的接口，统一接受处理前端的请求



为什么要用？

- 所有的服务端口不同，增大了前端调用成本
- 所有服务是分散的，你可能需要集中进行管理、操作，比如集中解决跨域、鉴权、接口文档、服务的路由、接口安全性、流量染色、限流



Gateway是应用层网关：会有一定的业务逻辑（比如根据用户信息判断权限）

Nginx是接入层网关：比如每个请求的日志，通常没有业务逻辑



**接口路由**

统一地接受前端的请求，转发请求到对应的服务

如何找到路由？可以编写一套路由配置，通过api地址前缀来找到对应的服务



![image-20240719091215652](./assets/image-20240719091215652.png)



启动网关



![image-20240719091414558](./assets/image-20240719091414558.png)



> 记得开Nacos



![image-20240719091453512](./assets/image-20240719091453512.png)



直接调用网关获取题目信息



![image-20240719091623716](./assets/image-20240719091623716.png)



直接通，没毛病，试试用户



![image-20240719091719039](./assets/image-20240719091719039.png)



没问题





###### 2.14.2 聚合文档



>  这里皮总也遇到了那个序列化的问题，我就放心了



以一个全局的视角集中查看管理接口文档

使用Knife4j接口文档生成器，非常方便：https://doc.xiaominfo.com/docs/middleware-sources/spring-cloud-gateway/spring-gateway-introduction



![image-20240719092110616](./assets/image-20240719092110616.png)





【1】先给所有业务服务引入依赖，同时开启接口文档的配置

https://doc.xiaominfo.com/docs/middleware-sources/spring-cloud-gateway/spring-gateway-introduction#%E6%89%8B%E5%8A%A8%E9%85%8D%E7%BD%AE%E8%81%9A%E5%90%88manual



```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-openapi2-spring-boot-starter</artifactId>
    <version>4.3.0</version>
</dependency>
```



![image-20240719092551705](./assets/image-20240719092551705.png)



三个服务都是这样，然后开启配置



```yaml
knife4j:
  enable: true
```



![image-20240719092712117](./assets/image-20240719092712117.png)



【2】给网关配置集中管理接口文档

网关项目引入依赖：



```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-gateway-spring-boot-starter</artifactId>
    <version>4.3.0</version>
</dependency>
```





![image-20240719092824537](./assets/image-20240719092824537.png)



引入配置



![image-20240719093003414](./assets/image-20240719093003414.png)



全部服务重新启动



【3】访问地址即可查看聚合接口文档：8101 端口的 `doc.html` http://localhost:8101/doc.html#/home



![image-20240719093207338](./assets/image-20240719093207338.png)



三个服务就可以切换查看了



![image-20240719093234392](./assets/image-20240719093234392.png)



![image-20240719093241887](./assets/image-20240719093241887.png)



没毛病



调试一下，用户登录



![image-20240719093514492](./assets/image-20240719093514492.png)



登录不了，换一个看看



![image-20240719093634570](./assets/image-20240719093634570.png)



注册可以成功



![image-20240719093647601](./assets/image-20240719093647601.png)



新用户就可以成功



获取登录用户

![image-20240719093702737](./assets/image-20240719093702737.png)



![image-20240719093721048](./assets/image-20240719093721048.png)



哦哦哦， 密码盐值的问题



![image-20240719093750869](./assets/image-20240719093750869.png)



之前单体项目中，这里是yupi ，

![image-20240719093816050](./assets/image-20240719093816050.png)



微服务中，我改了一下，dingjiaxiong，所以登不上去，盐值改回去吧



![image-20240719093852198](./assets/image-20240719093852198.png)



再试一次



![image-20240719093916498](./assets/image-20240719093916498.png)



这样就行了，现在直接去调用判题服务【在这之前，要引入redisson 依赖，使用分布式登录



![image-20240719094430732](./assets/image-20240719094430732.png)

】



![image-20240719094054221](./assets/image-20240719094054221.png)



没问题

![image-20240719094354724](./assets/image-20240719094354724.png)

这里判题沙箱没有开



再试一次



![image-20240719095225771](./assets/image-20240719095225771.png)



这里之前那个序列化问题破案了，携带的cookie 不是一个东西， 配置一下就能保证一致了



![image-20240719095504438](./assets/image-20240719095504438.png)



![image-20240719095513863](./assets/image-20240719095513863.png)





###### 2.14.3 跨域解决、权限校验



**跨域解决**



全局解决跨域配置：



![image-20240719095646670](./assets/image-20240719095646670.png)







**权限校验**



可以使用Spring Cloud Gateway的Filter请求拦截器，接受到请求后根据请求的路径判断能否访问。



![image-20240719095846363](./assets/image-20240719095846363.png)



Redisson RateLimiter也可以实现限流。



测试一下，直接使用内部的接口：



![image-20240719100042860](./assets/image-20240719100042860.png)



没毛病。



**思考**



真的有必要用微服务么？

真的有必要用Spring Cloud实现微服务么？

企业内部一般使用API(RPC、HTTP)实现跨部门、跨服务的调用，数据格式和调用代码全部自动生成，保持统一，同时解耦。





##### 2.15 消息队列解耦



###### 2.15.1 RabbitMQ 项目异步化改造



比处选用RabbitMQ消息队列改造项目，解耦判题服务和题目服务，题目服务只需要向消息队列发消息，判题服务从消息队列中取消息去执行判题，然后异步更新数据库即可



**基本代码引入**



【1】引入依赖

注意，使用的版本一定要和你的springboot版本一致！！！！！！！



```xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-amqp -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
    <version>2.7.2</version>
</dependency>
```



![image-20240719100424675](./assets/image-20240719100424675.png)



直接这样引入，他会根据父springboot 版本进行导入



【2】在yml 中引入配置

```java
spring:
    rabbitmq:
        host: localhost
        port: 5672
        password: guest
        username: guest
```



![image-20240719100612017](./assets/image-20240719100612017.png)



判题服务一样



【3】创建交换机和队列



![image-20240719100719497](./assets/image-20240719100719497.png)



> 这里换了个名字，之前BI 项目这队列和交换机已经存在了

![image-20240719100832483](./assets/image-20240719100832483.png)



当然后面就接着就改成了用Bean 的方式初始化消息队列



![image-20240719102756345](./assets/image-20240719102756345.png)



这样就不用在启动类里面去调 了



【4】生产者代码



在题目模块中：

![image-20240719101255034](./assets/image-20240719101255034.png)



【5】消费者



这个是在判题模块中写的



![image-20240719101403982](./assets/image-20240719101403982.png)



【6】单元测试执行



**项目异步化改造**



要传递的消息是什么？题目提交Id

题目服务中，把原本的本地异步执行改为向消息队列发送消息：



![image-20240719102604678](./assets/image-20240719102604678.png)



判题服务中，监听消息，执行判题：



![image-20240719102631046](./assets/image-20240719102631046.png)



测试一下，最后跑通完整流程

重新启动所有模块



![image-20240719102859220](./assets/image-20240719102859220.png)





![image-20240719102922552](./assets/image-20240719102922552.png)



消息队列启动成功



![image-20240719102945722](./assets/image-20240719102945722.png)



还有交换机



![image-20240719102957942](./assets/image-20240719102957942.png)



直接前端去执行，这里就不用重新生成代码了，直接改个端口



![image-20240719103120199](./assets/image-20240719103120199.png)



其实吧，之前单体项目的时候，我就没改，一直是8101，这下单体项目没跑，网关在 8101 【这里又要报序列化问题了】



![image-20240719103307917](./assets/image-20240719103307917.png)



我这儿倒是直接进来了【就是包的问题，改造前和改造后，那玩意儿不能正反序列化】



用户登录



![image-20240719103426710](./assets/image-20240719103426710.png)



![image-20240719103447370](./assets/image-20240719103447370.png)



![image-20240719103453453](./assets/image-20240719103453453.png)



登录成功



创建题目



![image-20240719103700242](./assets/image-20240719103700242.png)



![image-20240719103710644](./assets/image-20240719103710644.png)





数据库

![image-20240719103732800](./assets/image-20240719103732800.png)



来吧做题



![image-20240719103758380](./assets/image-20240719103758380.png)



![image-20240719104132917](./assets/image-20240719104132917.png)



提交



![image-20240719104142022](./assets/image-20240719104142022.png)



提交成功



![image-20240719104158789](./assets/image-20240719104158789.png)



AC 了



![image-20240719104216253](./assets/image-20240719104216253.png)



确实走MQ  里面过了



![image-20240719104231297](./assets/image-20240719104231297.png)



没毛病



提交记录



![image-20240719104328390](./assets/image-20240719104328390.png)



好好好。撒花 ~









































































































