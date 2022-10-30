package fr.endide.bedwars.events;

import fr.endide.bedwars.Bedwars;
import fr.endide.bedwars.arena.arena;
import fr.endide.bedwars.arena.item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class invListener implements Listener {
    Bedwars bedwars;
    public invListener(Bedwars bedwars) {
        this.bedwars = bedwars;
    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        String name = player.getName();
        ItemStack current = e.getCurrentItem();
        List<item> items = bedwars.getItemManager().getItems();
        if(e.getInventory().getName().equalsIgnoreCase("§6Shop")){
            e.setCancelled(true);
            if(current == null) return;
            //get all block item
            if(current.getType() == Material.GRASS){
                Inventory inv = Bukkit.createInventory(null, 27, "§6Buy");
                for(item item : items){
                    if(item.getType().equals("block")){
                        ItemStack  b = new ItemStack(Material.matchMaterial(item.getName()), item.getAmount());
                        ItemMeta bM = b.getItemMeta();
                        bM.setDisplayName(ChatColor.GOLD + "Price " + item.getPrice() + " " + item.getMoney());
                        b.setItemMeta(bM);
                        inv.setItem(item.getSlot(), b);
                    }
                }
                player.closeInventory();
                player.openInventory(inv);
            }
            if(current.getType() == Material.STICK){
                Inventory inv = Bukkit.createInventory(null, 27, "§6Buy");
                for(item item : items){
                    if(item.getType().equals("weapon")){
                        ItemStack  b = new ItemStack(Material.matchMaterial(item.getName()), item.getAmount());
                        ItemMeta bM = b.getItemMeta();
                        bM.setDisplayName(ChatColor.GOLD + "Price " + item.getPrice() + " " + item.getMoney());
                        b.setItemMeta(bM);
                        inv.setItem(item.getSlot(), b);
                    }
                }
                player.closeInventory();
                player.openInventory(inv);
            }
            if(current.getType() == Material.ARMOR_STAND){
                Inventory inv = Bukkit.createInventory(null, 27, "§6Armor");

                ItemStack  b = new ItemStack(Material.IRON_BOOTS, 1);
                ItemMeta bM = b.getItemMeta();
                bM.setDisplayName(ChatColor.GOLD + "Price 12 iron");
                b.setItemMeta(bM);
                inv.setItem(1, b);

                ItemStack  ba = new ItemStack(Material.DIAMOND_BOOTS, 1);
                ItemMeta baM = ba.getItemMeta();
                baM.setDisplayName(ChatColor.GOLD + "Price 6 émeraudes");
                ba.setItemMeta(baM);
                inv.setItem(2, ba);


                player.closeInventory();
                player.openInventory(inv);
            }
            if(current.getType() == Material.WOOD_HOE){
                arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                Inventory inv = Bukkit.createInventory(null, 27, "§6Tools");
                for(int index = 0 ; arena.getPlayerLevels().size() > index; index++){
                   if(arena.getPlayerLevels().get(index).getName().equals(name)){
                       switch (arena.getPlayerLevels().get(index).getAxeLevel()){
                           case 0:
                               ItemStack b = new ItemStack(Material.WOOD_AXE, 1);
                               ItemMeta bM = b.getItemMeta();
                               bM.setDisplayName(ChatColor.GOLD + "Prix 4 fer");
                               b.setItemMeta(bM);
                               inv.setItem(1, b);
                               break;
                           case 1:
                               ItemStack ba = new ItemStack(Material.STONE_AXE, 1);
                               ItemMeta baM = ba.getItemMeta();
                               baM.setDisplayName(ChatColor.GOLD + "Prix 4 or");
                               ba.setItemMeta(baM);
                               inv.setItem(1, ba);
                               break;
                           case 2:
                               ItemStack bb = new ItemStack(Material.IRON_AXE, 1);
                               ItemMeta bbM = bb.getItemMeta();
                               bbM.setDisplayName(ChatColor.GOLD + "Prix 4 diamant");
                               bb.setItemMeta(bbM);
                               inv.setItem(1, bb);
                               break;
                           case 3:
                               ItemStack bc = new ItemStack(Material.DIAMOND_AXE, 1);
                               ItemMeta bcM = bc.getItemMeta();
                               bcM.setDisplayName(ChatColor.GOLD + "Prix 4 emeraude");
                               bc.setItemMeta(bcM);
                               inv.setItem(1, bc);
                               break;
                       }
                       switch (arena.getPlayerLevels().get(index).getPickaxeLevel()){
                           case 0:
                               ItemStack b = new ItemStack(Material.WOOD_PICKAXE, 1);
                               ItemMeta bM = b.getItemMeta();
                               bM.setDisplayName(ChatColor.GOLD + "Prix 4 fer");
                               b.setItemMeta(bM);
                               inv.setItem(2, b);
                               break;
                           case 1:
                               ItemStack ba = new ItemStack(Material.STONE_PICKAXE, 1);
                               ItemMeta baM = ba.getItemMeta();
                               baM.setDisplayName(ChatColor.GOLD + "Prix 4 or");
                               ba.setItemMeta(baM);
                               inv.setItem(2, ba);
                               break;
                           case 2:
                               ItemStack bb = new ItemStack(Material.IRON_PICKAXE, 1);
                               ItemMeta bbM = bb.getItemMeta();
                               bbM.setDisplayName(ChatColor.GOLD + "Prix 4 diamant");
                               bb.setItemMeta(bbM);
                               inv.setItem(2, bb);
                               break;
                           case 3:
                               ItemStack bc = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                               ItemMeta bcM = bc.getItemMeta();
                               bcM.setDisplayName(ChatColor.GOLD + "Prix 4 emeraude");
                               bc.setItemMeta(bcM);
                               inv.setItem(2, bc);
                               break;
                       }
                   }
                }
                player.closeInventory();
                player.openInventory(inv);
            }
            if(current.getType() == Material.CHEST){
                Inventory inv = Bukkit.createInventory(null, 27, "§6Buy");
                for(item item : items){
                    if(item.getType().equals("object")){
                        ItemStack  b = new ItemStack(Material.matchMaterial(item.getName()), item.getAmount());
                        ItemMeta bM = b.getItemMeta();
                        bM.setDisplayName(ChatColor.GOLD + "Price " + item.getPrice() + " " + item.getMoney());
                        b.setItemMeta(bM);
                        inv.setItem(item.getSlot(), b);
                    }
                }
                player.closeInventory();
                player.openInventory(inv);
            }
            if(current.getType() == Material.DIAMOND){
                Inventory inv = Bukkit.createInventory(null, 27, "§6Upgrades");
                arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                for(int index = 0 ; arena.getPlayerLevels().size() > index; index++){
                    if(arena.getPlayerLevels().get(index).getName().equals(name)){
                        switch (arena.getPlayerLevels().get(index).getSpawner()){
                            case 0:
                                ItemStack block = new ItemStack(Material.FURNACE, 1);
                                ItemMeta blockM = block.getItemMeta();
                                blockM.setDisplayName(ChatColor.GOLD + "Generator Upgrade (*50) 4 diamond");
                                block.setItemMeta(blockM);
                                inv.setItem(1, block);
                                break;
                            case 1:
                                ItemStack blocka = new ItemStack(Material.ANVIL, 1);
                                ItemMeta blockaM = blocka.getItemMeta();
                                blockaM.setDisplayName(ChatColor.GOLD + "Generator Upgrade (*100) 8 diamond");
                                blocka.setItemMeta(blockaM);
                                inv.setItem(1, blocka);
                                break;
                            case 2:
                                ItemStack blockb = new ItemStack(Material.BEACON, 1);
                                ItemMeta blockbM = blockb.getItemMeta();
                                blockbM.setDisplayName(ChatColor.GOLD + "Generator Upgrade (Emeraude) 16 diamond");
                                blockb.setItemMeta(blockbM);
                                inv.setItem(1, blockb);
                                break;
                        }

                        switch (arena.getPlayerLevels().get(index).getPower()){
                            case 0:
                                ItemStack block = new ItemStack(Material.IRON_AXE, 1);
                                ItemMeta blockM = block.getItemMeta();
                                blockM.setDisplayName(ChatColor.GOLD + "Strengh 1 4 diamond");
                                block.setItemMeta(blockM);
                                inv.setItem(2, block);
                                break;
                        }
                        switch (arena.getPlayerLevels().get(index).getProtection()){
                            case 0:
                                ItemStack block = new ItemStack(Material.IRON_CHESTPLATE, 1);
                                ItemMeta blockM = block.getItemMeta();
                                blockM.setDisplayName(ChatColor.GOLD + "P1 4 diamond");
                                block.setItemMeta(blockM);
                                inv.setItem(3, block);
                                break;
                            case 1:
                                ItemStack blocka = new ItemStack(Material.GOLD_CHESTPLATE, 1);
                                ItemMeta blockaM = blocka.getItemMeta();
                                blockaM.setDisplayName(ChatColor.GOLD + "P2 8 diamond");
                                blocka.setItemMeta(blockaM);
                                inv.setItem(3, blocka);
                                break;
                            case 2:
                                ItemStack blockb = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
                                ItemMeta blockbM = blockb.getItemMeta();
                                blockbM.setDisplayName(ChatColor.GOLD + "P3 16 diamond");
                                blockb.setItemMeta(blockbM);
                                inv.setItem(3, blockb);
                                break;
                        }

                    }
                }
                player.closeInventory();
                player.openInventory(inv);
            }


        }
        if(e.getInventory().getName().equalsIgnoreCase("§6Armor")){
            e.setCancelled(true);
            if(current == null) return;
            if(current.getType() == Material.DIAMOND_BOOTS){
                if(player.getInventory().contains(Material.EMERALD, 6)){
                    player.getInventory().removeItem(new ItemStack(Material.EMERALD, 6));
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_BOOTS, 1));
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setArmor("DIAMOND");
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
            if(current.getType() == Material.IRON_BOOTS){
                if(player.getInventory().contains(Material.GOLD_INGOT, 12)){
                    player.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 12));
                    player.getInventory().addItem(new ItemStack(Material.IRON_BOOTS, 1));
                    player.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS, 1));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setArmor("IRON");
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
        }
        if(e.getInventory().getName().equalsIgnoreCase("§6Buy")){
            e.setCancelled(true);
            if(current == null) return;
            for(item item : items){
                if(current.getType() == Material.matchMaterial(item.getName())){
                    switch (item.getMoney()){
                        case "emerald":
                            if(player.getInventory().contains(Material.EMERALD, item.getPrice())){
                                player.getInventory().removeItem(new ItemStack(Material.EMERALD, item.getPrice()));
                                player.getInventory().addItem(new ItemStack(Material.matchMaterial(item.getName()), item.getAmount()));
                            }else {
                                player.sendMessage("§cVous n'avez pas assez d'argent");
                            }
                            break;
                        case "iron":
                            if(player.getInventory().contains(Material.IRON_INGOT, item.getPrice())){
                                player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, item.getPrice()));
                                player.getInventory().addItem(new ItemStack(Material.matchMaterial(item.getName()), item.getAmount()));
                            }else {
                                player.sendMessage("§cVous n'avez pas assez d'argent");
                            }
                            break;
                        case "gold":
                            if(player.getInventory().contains(Material.GOLD_INGOT, item.getPrice())){
                                player.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, item.getPrice()));
                                player.getInventory().addItem(new ItemStack(Material.matchMaterial(item.getName()), item.getAmount()));
                            }else {
                                player.sendMessage("§cVous n'avez pas assez d'argent");
                            }
                            break;
                    }

                }
            }
        }

        if(e.getInventory().getName().equalsIgnoreCase("§6Upgrades")){
            e.setCancelled(true);
            if(current.getType() == Material.FURNACE){
                if(player.getInventory().contains(Material.DIAMOND, 4)){
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 4));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setSpawner(1);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
            if(current.getType() == Material.ANVIL){
                if(player.getInventory().contains(Material.DIAMOND, 8)){
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 8));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setSpawner(2);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
            if(current.getType() == Material.BEACON){
                if(player.getInventory().contains(Material.DIAMOND, 16)){
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 16));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setSpawner(3);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }



            if(current.getType() == Material.IRON_AXE){
                if(player.getInventory().contains(Material.DIAMOND, 4)){
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 4));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE ,900000000, 1));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setPower(1);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }


            if(current.getType() == Material.IRON_CHESTPLATE){
                if(player.getInventory().contains(Material.DIAMOND, 4)){
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 4));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE ,900000000, 1));


                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setProtection(1);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
            if(current.getType() == Material.GOLD_CHESTPLATE){
                if(player.getInventory().contains(Material.DIAMOND, 8)){
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 8));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE ,900000000, 2));


                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setProtection(2);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
            if(current.getType() == Material.DIAMOND_CHESTPLATE){
                if(player.getInventory().contains(Material.DIAMOND, 16)){
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 16));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE ,900000000, 3));


                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setProtection(3);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
        }


        if(e.getInventory().getName().equalsIgnoreCase("§6Tools")){
            e.setCancelled(true);
            if(current.getType() == Material.WOOD_AXE){
                if(player.getInventory().contains(Material.IRON_INGOT, 4)){
                    player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 4));
                    player.getInventory().addItem(new ItemStack(Material.WOOD_AXE, 1));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setAxeLevel(1);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
            if(current.getType() == Material.STONE_AXE){
                if(player.getInventory().contains(Material.GOLD_INGOT, 4)){
                    player.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                    player.getInventory().addItem(new ItemStack(Material.STONE_AXE, 1));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setAxeLevel(2);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
            if(current.getType() == Material.IRON_AXE){
                if(player.getInventory().contains(Material.DIAMOND, 4)){
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 4));
                    player.getInventory().addItem(new ItemStack(Material.IRON_AXE, 1));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setAxeLevel(3);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
            if(current.getType() == Material.DIAMOND_AXE){
                if(player.getInventory().contains(Material.EMERALD, 4)){
                    player.getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE, 1));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setAxeLevel(4);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }



            if(current.getType() == Material.WOOD_PICKAXE){
                if(player.getInventory().contains(Material.IRON_INGOT, 4)){
                    player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 4));
                    player.getInventory().addItem(new ItemStack(Material.WOOD_PICKAXE, 1));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setPickaxeLevel(1);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
            if(current.getType() == Material.STONE_PICKAXE){
                if(player.getInventory().contains(Material.GOLD_INGOT, 4)){
                    player.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                    player.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE, 1));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setPickaxeLevel(2);
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
            if(current.getType() == Material.IRON_PICKAXE){
                if(player.getInventory().contains(Material.DIAMOND, 4)){
                    player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 4));
                    player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE, 1));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setPickaxeLevel(3);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }
            if(current.getType() == Material.DIAMOND_PICKAXE){
                if(player.getInventory().contains(Material.EMERALD, 4)){
                    player.getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE, 1));
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for(int index = 0 ; arena.getPlayerLevels().size() > index; index++) {
                        if (arena.getPlayerLevels().get(index).getName().equals(name)) {
                            arena.getPlayerLevels().get(index).setPickaxeLevel(4);
                            player.closeInventory();
                        }
                    }
                }else {
                    player.sendMessage("§cVous n'avez pas assez d'argent");
                }
            }


        }
    }
}
