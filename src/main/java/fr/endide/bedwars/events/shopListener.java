package fr.endide.bedwars.events;

import fr.endide.bedwars.Bedwars;
import fr.endide.bedwars.arena.item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Bed;

import java.util.List;

public class shopListener implements Listener {
    Bedwars bedwars;
    public shopListener(Bedwars bedwars) {
        this.bedwars = bedwars;
    }
    @EventHandler
    public void onPlayerInventory(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (e.getClickedBlock() != null && e.getClickedBlock().getType() != null) {
            Block block = e.getClickedBlock();
            if (block.getType() == Material.WORKBENCH) {
                e.setCancelled(true);
                player.closeInventory();
                createShopInventory(player);
            }
        }
    }
    private void createShopInventory(Player player){
        Inventory inv = Bukkit.createInventory(null, 9, "§6Shop");

        ItemStack block = new ItemStack(Material.GRASS, 1);
        ItemMeta blockM = block.getItemMeta();
        blockM.setDisplayName(ChatColor.GOLD + "Blocs");
        block.setItemMeta(blockM);

        ItemStack weapon = new ItemStack(Material.STICK, 1);
        ItemMeta weaponM = weapon.getItemMeta();
        weaponM.setDisplayName(ChatColor.GOLD + "Armes");
        weapon.setItemMeta(weaponM);

        ItemStack armor = new ItemStack(Material.ARMOR_STAND, 1);
        ItemMeta armorM = armor.getItemMeta();
        armorM.setDisplayName(ChatColor.GOLD + "Armures");
        armor.setItemMeta(armorM);

        ItemStack outils = new ItemStack(Material.WOOD_HOE, 1);
        ItemMeta outilsM = outils.getItemMeta();
        outilsM.setDisplayName(ChatColor.GOLD + "Outils");
        outils.setItemMeta(outilsM);

        ItemStack objects = new ItemStack(Material.CHEST, 1);
        ItemMeta objectsM = objects.getItemMeta();
        objectsM.setDisplayName(ChatColor.GOLD + "Objets");
        objects.setItemMeta(objectsM);

        ItemStack upgrade = new ItemStack(Material.DIAMOND, 1);
        ItemMeta upgradeM = upgrade.getItemMeta();
        upgradeM.setDisplayName(ChatColor.GOLD + "Upgrades");
        upgrade.setItemMeta(upgradeM);

        inv.setItem(1, block);
        inv.setItem(2, weapon);
        inv.setItem(3, armor);
        inv.setItem(4, outils);
        inv.setItem(5, objects);
        inv.setItem(6, upgrade);

        player.openInventory(inv);
    }
}
