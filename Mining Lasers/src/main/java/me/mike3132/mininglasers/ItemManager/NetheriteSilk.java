package me.mike3132.mininglasers.ItemManager;

import me.mike3132.mininglasers.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class NetheriteSilk {

    private final Main plugin;

    public NetheriteSilk(Main plugin) {
        this.plugin = plugin;
    }

    public static ItemStack laser;

    public static void init() {
        createLaser();
    }

    //This is where the item is created and where I set the name, lore, and enchantments
    private static void createLaser() {
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        meta.setDisplayName(Main.chatColor("&9&lNetherite Mining Laser (Silk Touch)"));
        lore.add(Main.chatColor("&a&lRight click to shoot"));
        lore.add(Main.chatColor("&2&lBreaks any blocks the laser touches"));
        meta.setLore(lore);
        meta.addEnchant(Enchantment.SILK_TOUCH,1,true);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setCustomModelData(1002);
        item.setItemMeta(meta);
        laser = item;
    }
}
