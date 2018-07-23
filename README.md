# XiYouAssistant

### 一.组件化
#### 1.划分
西邮助手一共分为8个组件：
* lib_common：common组件提供基础类，工具类，添加依赖包，公共资源，声明app权限。
* module_app：壳工程。空壳工程，没有任何业务代码，声明应用的Application，提供Android应用的根表单。相当于最终集成模式的壳。
* module_maon：main组件，在这个组件中调用其他组件的fragment。
* module_education:业务组件，实现教务相关的功能
* module_Library:业务组件，实现图书相关的功能
* module_event:业务组件，实现活动相关的功能
* module_mine:业务组件，实现我的相关功能
* module_login:业务组件，实现登录相关组件。

### 2.插件化的第三方库
使用阿里的ARouter实现路由功能

### 3.插件化参考资料
[https://blog.csdn.net/guiying712/article/details/55213884](https://blog.csdn.net/guiying712/article/details/55213884)

### 二.ui适配
#### 1.px-->dp
使用AutoLayout第三方库实现适配

#### 2.字体的更改
自定义MyTextView控件中将字体自定义为Adobe-Heiti-Std-