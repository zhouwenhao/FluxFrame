# FluxFrame
android-Flux架构基础模板

层次结构：(UI与业务完全独立)

    app -- UI（如登陆、注册等具体页面等）
    appbase  -- 基础UI库（如BaseAct、BaseFrag、等等）
    business -- 具体业务层（不包含任何UI，只含有具体事件业务的处理）
    sundy -- 二次封装层（将flux和net等进行组装为适合自身业务）
    flux net cache -- 架构封装
