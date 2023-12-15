package com.mcla.booklogin.listener;

import com.mcla.booklogin.book.Book;
import fr.xephi.authme.api.v3.AuthMeApi;
import fr.xephi.authme.events.LoginEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

/**
 * @Description:
 * @ClassName: PlayerLoginListener
 * @Author: ice_light
 * @Date: 2023/12/15 16:04
 * @Version: 1.0
 */
public class PlayerLoginListener implements Listener {
    AuthMeApi API;
    public PlayerLoginListener() {
        API = AuthMeApi.getInstance();
    }

    @EventHandler
    public void PlayerLogin(LoginEvent e) {
        Player p = e.getPlayer();
        if(!API.isAuthenticated(p)){
            ItemStack[] I = Book.Inventory.get(p);

            for(int i = 0; i < I.length; ++i) {
                if (((ItemStack[])Book.Inventory.get(p))[i] != null) {
                    p.getInventory().setItem(i, ((ItemStack[])Book.Inventory.get(p))[i]);
                } else {
                    ItemStack item = p.getInventory().getItem(i);
                    if (item != null && item.getType() == Material.WRITABLE_BOOK) {
                        BookMeta b1 = (BookMeta)item.getItemMeta();
                        if (b1.getPage(1).contains("▲")) {
                            p.getInventory().clear(i);
                        }
                    }
                }
            }
        }
    }
}
