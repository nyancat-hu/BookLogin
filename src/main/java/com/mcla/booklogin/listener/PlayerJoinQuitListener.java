package com.mcla.booklogin.listener;

import com.mcla.booklogin.BookLogin;
import com.mcla.booklogin.book.Book;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @Description:
 * @ClassName: PlayerJoinQuitListener
 * @Author: ice_light
 * @Date: 2023/12/15 16:05
 * @Version: 1.0
 */
public class PlayerJoinQuitListener implements Listener {

    public PlayerJoinQuitListener() {
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        (new BukkitRunnable() {
            public void run() {
                Book.sendbook(p);
                this.cancel();
            }
        }).runTaskLater(BookLogin.getMain(), 10L);
    }

    @EventHandler
    public void leave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (!AuthMeApi.getInstance().isAuthenticated(p)) {
            ItemStack[] I = (ItemStack[])Book.Inventory.get(p);

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

        Book.Inventory.remove(p);
    }
}
