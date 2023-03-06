# FluxFrame
android-Flux架构基础模板

层次结构：(UI与业务完全独立)

    app -- UI（如登陆、注册等具体页面等）
    appbase  -- 基础UI库（如BaseAct、BaseFrag、等等）
    business -- 具体业务层（不包含任何UI，只含有具体事件业务的处理）
    sundy -- 二次封装层（将flux和net等进行组装为适合自身业务）
    flux net cache -- 架构封装

Flux module：
    
    FluxObser -- View层用来接收对应Action的事件回调。  参数为action和当前thread。 被注解函数有且只能有一个参数，即该action对应的Action对象
    Dispatcher -- 事件发送器。由各个View或者业务，发送具体事件出去。
    FluxAction -- 所有事件Action的父类
    FluxStore -- 所有事件store处理类的父类，回调时判断act/frag是否存活，根据传递的obj对象反射获取对应action的注解函数，并执行该函数。

Net module：
    
    NetMgr -- retrofit的入口，用于获取具体的retrofit类
    NetInterceptor -- http的父类拦截器，具体业务需要继承此父类以实现自身业务的拦截器
    NetObser -- http请求的回调父类，具体业务需要酌情对此进行继承并适应自身业务
    NetDomain -- 对retrofit的interface类进行注解，传递domain、moduleId、interceptor
    NetTimeOut -- 对retrofit的interface类进行注解，传递http的三个超时时长，单位秒

Cache module：
    
    CacheSp -- 封装mmkv，并对fileName与key进行base64

Sunndy module:

    flux/
        LoadAction -- loading事件（典型应用于http请求时转圈事件）
        LoadStore -- 对loading事件请求进行处理，直接将loading状态(true/false)传递给具体业务View，由View控制后续代码
        SundyFluxActions -- Sundy模块中定义的事件清单
    
    http/
        HttpDealCall、HttpDealMgr -- 当http异常时，回调给业务/View，进行对应的处理
        HttpInterceptor -- 继承自NetInterceptor，实现适合自身业务的拦截器
        HttpObser -- 继承自NetObser，对http的数据回调进行适应自身业务的转换
        HttpResp -- 当前业务下的response格式

Business module：

    action/
        SplashAction -- 具体业务-获取闪屏数据
    
    model/
       SplashItem -- 具体的闪屏数据对象

    net/
        BusiHttpService、BusiHttp -- 当前业务的http清单

    store/
        SplashStore -- 具体业务执行-（获取本地闪屏数据，回调，调用http请求获取闪屏数据，存储本地并回调）

    BusiActions -- 当前业务下的各类action事件清单
    BusiApplication -- 当前业务下的application父类，其中主要是对各种store添加到Dispatcher中
    BusiSp -- 当前业务下的Sp存储

AppBase module：

    BAct -- 基类Activity（实现LoadAction监听和loading对话框）
    BFrag -- 基类Fragment（实现LoadAction监听和告知Activity处理loading对话框）
    BDig -- 基类Dialog（实现LoadAction监听和告知Activity处理loading对话框）

App module：

    MainActivity -- 继承自BAct（首页，调用Splash事件）
    MainApplication -- 继承自BusiApplication（主要是对HttpDealMgr设置回调，以便进行通用toast与特定业务处理）