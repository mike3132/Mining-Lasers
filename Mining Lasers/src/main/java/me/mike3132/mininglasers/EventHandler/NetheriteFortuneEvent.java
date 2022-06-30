package me.mike3132.mininglasers.EventHandler;

import me.mike3132.mininglasers.ItemManager.NetheriteFortune;
import me.mike3132.mininglasers.Main;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetheriteFortuneEvent implements Listener {

    private final Main plugin;

    public NetheriteFortuneEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent pc) {
        //This is checking to see if the player is right clicking the item
        if (pc.getAction().equals(Action.RIGHT_CLICK_AIR) || pc.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = pc.getPlayer();
            Location start = player.getEyeLocation();
            Location location = start.clone();
            Vector direction = location.getDirection().normalize();
            int i = 0;
            //This checks the players inventory to make sure it is a Netherite Fortune Laser
            if (player.getInventory().getItemInMainHand().equals(NetheriteFortune.laser)) {
                while (i < 8 && !location.getBlock().getType().isSolid()) {
                    //This is the particle creator.
                    location.getWorld().spawnParticle(Particle.REDSTONE, location.add(direction), 1, new Particle.DustOptions(Color.YELLOW, 1));
                    i++;
                    Block block = location.getBlock();

                    List<Material> blacklistedBlocks = new ArrayList<>();

                    //This is a blacklist of blocks that cannot be broken by this laser
                    blacklistedBlocks.addAll(Arrays.asList(Material.BARRIER, Material.BEDROCK, Material.WATER, Material.LAVA, Material.END_PORTAL_FRAME, Material.END_PORTAL, Material.END_GATEWAY, Material.NETHER_PORTAL));

                    if (blacklistedBlocks.contains(block.getType())) {
                        //Sends the player a title if they try and break a blacklisted block
                        player.sendTitle(Main.chatColor("&b&lLasers"), Main.chatColor("&4&lCannot break this"), 10, 10, 10);
                    } else {
                        player.breakBlock(location.getBlock());
                    }
                }
            }
        }

    }

    //This stops players from placing the laser in an anvil.
    @EventHandler
    public void playerRename(InventoryClickEvent ic) {
        if (ic.getInventory().getType().equals(InventoryType.ANVIL)) {
            if (ic.getCurrentItem().equals(NetheriteFortune.laser)) {
                ic.setCancelled(true);
            }
        }
    }
}
