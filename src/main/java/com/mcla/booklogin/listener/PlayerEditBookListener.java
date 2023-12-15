package com.mcla.booklogin.listener;

import fr.xephi.authme.api.v3.AuthMeApi;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

/**
 * @Description:
 * @ClassName: PlayerEditBookListener
 * @Author: ice_light
 * @Date: 2023/12/15 16:06
 * @Version: 1.0
 */
public class PlayerEditBookListener implements Listener {
    public PlayerEditBookListener() {
    }

    @EventHandler
    public void EditBook(PlayerEditBookEvent e) {
        Player p = e.getPlayer();
        BookMeta Book = e.getNewBookMeta();
        AuthMeApi API = AuthMeApi.getInstance();
        String BookData = Book.getPage(1);
        if (BookData.contains("▲") & !API.isAuthenticated(p)) {
            String Password = BookData.split("▲")[1].replace("\n", "").replace(" ", "");
            if (API.isRegistered(p.getName())) {
                if (API.checkPassword(p.getName(), Password)) {
                    API.forceLogin(p);
                } else {
//                    p.sendMessage("§c");
                    p.kickPlayer("密码错误");
                }
            } else if (API.registerPlayer(p.getName(), Password)) {
                API.forceLogin(p);
            } else {
                p.sendMessage("§c未知错误，请重新进入服务器。");
            }
        } else if (!API.isAuthenticated(p)) {
            p.sendMessage("§c请勿删除书本中的▲符号，请换一本书重试。");
        }

    }
}
