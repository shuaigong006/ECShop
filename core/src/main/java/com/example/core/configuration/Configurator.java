package com.example.core.configuration;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

//全局配置类
public class Configurator {

    private static final HashMap<String, Object> SHOP_CONFIGS = new HashMap<>();

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    //初始化配置
    private Configurator() {
        SHOP_CONFIGS.put(ConfigureType.CONFIG_READY, false);
    }

    //实例化配置类
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    //配置域名地址
    public final Configurator withApiHost(String url) {
        SHOP_CONFIGS.put(ConfigureType.API_HOST, url);
        return this;
    }

    //初始化Icon
    private void initIcon() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    //配置字体图标
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    //配置完成
    public final void configure() {
        initIcon();
        SHOP_CONFIGS.put(ConfigureType.CONFIG_READY, true);
    }

    //获取配置类型
    @SuppressWarnings({"unchecked", "SuspiciousMethodCalls", "UnusedReturnValue"})
    public final <T> T getConfiguration(ConfigureType type) {
        final boolean isReady = (boolean) SHOP_CONFIGS.get(ConfigureType.CONFIG_READY);
        if (isReady) {
            return (T) SHOP_CONFIGS.get(type);
        } else {
            throw new RuntimeException("Configurator is not ready");
        }
    }
}
