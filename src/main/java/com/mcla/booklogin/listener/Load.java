package com.mcla.booklogin.listener;

import com.mcla.booklogin.BookLogin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * @Description:
 * @ClassName: Load
 * @Author: ice_light
 * @Date: 2023/12/15 16:04
 * @Version: 1.0
 */
public class Load {
    public Load() {
    }

    public static void load() {
        regPlugins(new PlayerLoginListener());
        regPlugins(new PlayerEditBookListener());
        regPlugins(new PlayerJoinQuitListener());
    }

    static void regPlugins(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, BookLogin.getMain());
    }

}
