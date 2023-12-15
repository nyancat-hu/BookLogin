package com.mcla.booklogin;

import com.mcla.booklogin.listener.Load;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class BookLogin extends JavaPlugin implements Listener {
    private static BookLogin PluginMain;
    public static String content;

    @Override
    public void onEnable() {
        PluginMain = this;
        this.getLogger().info("欢迎使用BOOKLOGIN");
        Bukkit.getPluginManager().registerEvents(this, this);
        if (!(new File(this.getDataFolder() + File.separator + "config.yml")).exists()) {
            this.getConfig().set("Setting.content", "§1欢迎使用书本登陆插件!\n§c★★§9[name]§c★★\n§8§o输入完密码后点击完成，请勿点击署名\n§c请在下方输入密码§l\n  ▲");
        }

        this.saveConfig();
        content = this.getConfig().getString("Setting.content");
        if (content.contains("▲")) {
            Load.load();
        } else {
            this.getLogger().info("书本内容中必须存在▲符号在输入密码位置前面作为标志，请重新修改config.yml中的content");
        }
    }
    public static BookLogin getMain() {
        return PluginMain;
    }
}
