package com.mcla.booklogin.book;

import com.mcla.booklogin.BookLogin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Description:
 * @ClassName: Book
 * @Author: ice_light
 * @Date: 2023/12/15 16:06
 * @Version: 1.0
 */
public class Book {
    public static HashMap<Player, ItemStack[]> Inventory = new HashMap();

    public Book() {
    }

    public static ItemStack createbook(Player p) {
        ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
        BookMeta bm = (BookMeta)book.getItemMeta();
        bm.setDisplayName("§c请右键进入本书进行登陆/注册");
        String bookcontent = BookLogin.content.replace("[name]", p.getDisplayName()).replace("[ip]", Objects.requireNonNull(p.getAddress()).getHostName());
        bm.setPages(bookcontent);
        bm.setAuthor("BookLogin");
        bm.setTitle("BookLogin");
        book.setItemMeta(bm);
        return book;
    }

    public static void sendbook(Player p) {
        Inventory.put(p, p.getInventory().getContents());
        p.getInventory().clear();

        for(int i = 0; i < 9; ++i) {
            p.getInventory().addItem(createbook(p));
        }

    }
}
