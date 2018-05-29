package com.example.core.configuration;

//配置类的对外方法
public final class ShopConfigure {

    private static Configurator configurator;

    public static Configurator init() {
        return getConfigurator();
    }

    private static Configurator getConfigurator() {
        //判断配置类是否为空,如果是就创建,否则直接返回
        if (configurator == null) {
            configurator = Configurator.getInstance();
        }
        return configurator;
    }

    public static <T> T getConfiguration(ConfigureType type) {
        return configurator.getConfiguration(type);
    }
}
