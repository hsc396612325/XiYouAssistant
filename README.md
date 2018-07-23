### XiYouAssistant
#### 一、组件化
1. 组件划分
    * module_app：壳工程。无任何业务代码，声明应用的Application，相当于最终集成模式的壳。
    * lib_common：提供基础类，工具类，添加依赖包，公共资源，声明app权限。
    * module_main：业务组件的集成，调用其他业务组件。
    * module_education：业务组件，教务相关功能。
    * module_library：业务组件，图书模块相关功能。
    * module_event：业务组件，活动模块相关功能。
    * module_mine：业务组件，我的模块相关功能。
    * module_login：业务组件，登录模块相关功能。

2. 实现相关
    * [ARouter](https://github.com/alibaba/ARouter)实现组件间通信
    * [组件化参考资料](https://blog.csdn.net/guiying712/article/details/55213884)

#### 二、ui适配
1. px-->dp 使用[AndroidAutoLayout](https://github.com/hongyangAndroid/AndroidAutoLayout)实现适配。

2. 字体，自定义MyTextView控件中将字体自定义为"Adobe-Heiti-Std-"。

#### 三.逻辑架构
项目逻辑架构使用 MVP。

#### 四.网络相关框架
1. 主要网络框架，[Retrofit](https://github.com/square/okhttp) + [OkHttp](https://github.com/square/retrofit)
2. 图片加载，[Glide](https://github.com/bumptech/glide)
3. 解析器，[Gson](https://github.com/google/gson) + JsonObject解析（简单数据）
4. 其他，[RxJava2](https://github.com/ReactiveX/RxJava)