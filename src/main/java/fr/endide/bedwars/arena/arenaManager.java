package fr.endide.bedwars.arena;

import fr.endide.bedwars.Bedwars;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class arenaManager {
    private List<arena> arenas = new ArrayList<>();
    private Bedwars bedwars;
    public arenaManager(Bedwars bedwars) {
        this.bedwars = bedwars;
    }

    public void addArena(arena arena) {
        arenas.add(arena);
        Bukkit.getWorld(arena.getName()).setAutoSave(false);

    }

    public void removeArena(arena arena) {
        arenas.remove(arena);
    }
    public void joinArena(Player player, String name, String team){
        arena nextArena = getArena(name);
        if(nextArena != null) {
            if(nextArena.getPlayers().get(team) == null){
                nextArena.getPlayers().put(team, player);
                arena.playerLevel playerLevel = new arena.playerLevel(player.getName(), "WOOD", 0, 0, 0, 0, 0);
                nextArena.getPlayerLevels().add(playerLevel);
                player.teleport(nextArena.getWaitLobby());
                player.getInventory().clear();
                player.setGameMode(GameMode.ADVENTURE);



            }else{
                player.sendMessage("pas de place choisi une autre team (ou ptet que t'a mal marqué la team");
                player.sendMessage("RED, ORANGE, PURPLE, PINK");
            }
            if(nextArena.getPlayers().size() == nextArena.getMaxPlayers()){
                launchArena(nextArena);


            }
        }else{
            player.sendMessage("Euuh poto y'a pas d'arene avec ce nom");
        }
    }
    public List<arena> getArenas() {
        return arenas;
    }
    private arena getArena(String name) {
        for (arena arena : arenas) {
            if (arena.getName().equalsIgnoreCase(name)) {
                return arena;
            }
        }
        return null;
    }
    public arena getArenaByPlayer(Player player) {
        for (arena arena : arenas) {
            if (arena.getPlayers().containsValue(player)) {
                return arena;
            }
        }
        return null;
    }
    public boolean ifPlayerIsInArena(Player player){
        for (arena arena : arenas) {
            for (String team : arena.getPlayers().keySet()) {
                if(arena.getPlayers().get(team) == player){
                    return true;
                }
            }
        }
        return false;
    }

    private arena getNextArena(){
        for (arena arena : arenas) {
            if (arena.isStarted() == false) {
                return arena;
            }
        }
        return null;
    }
    public void resetArena(arena arena){
        arena.getPlayers().clear();
        arena.getTeams().clear();
        arena.setStarted(false);
        arena.getTeams().add("RED");
        arena.getTeams().add("ORANGE");
        arena.getTeams().add("PURPLE");
        arena.getTeams().add("PINK");


    }

    private void launchArena(arena nextArena){
        nextArena.setStarted(true);

        //Spawn beds
        nextArena.getBedLocations().forEach((team, location) -> {
            World world = Bukkit.getWorld(nextArena.getName());
            world.getBlockAt(location).setType(Material.GLASS);
        });

        //Teleport players
        Player red = nextArena.getPlayers().get("RED");
        Player orange = nextArena.getPlayers().get("ORANGE");
        Player purple = nextArena.getPlayers().get("PURPLE");
        Player pink = nextArena.getPlayers().get("PINK");
        red.teleport(nextArena.getTeamSpawns().get("RED"));
        orange.teleport(nextArena.getTeamSpawns().get("ORANGE"));
        purple.teleport(nextArena.getTeamSpawns().get("PURPLE"));
        pink.teleport(nextArena.getTeamSpawns().get("PINK"));
        red.setGameMode(GameMode.SURVIVAL);
        orange.setGameMode(GameMode.SURVIVAL);
        purple.setGameMode(GameMode.SURVIVAL);
        pink.setGameMode(GameMode.SURVIVAL);
        addItemToPlayer(red, nextArena);
        addItemToPlayer(orange, nextArena);
        addItemToPlayer(purple, nextArena);
        addItemToPlayer(pink, nextArena);


        //Spawn spawners
        nextArena.getEmeraldsSpawners().forEach(emerald -> {
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncRepeatingTask((Plugin) bedwars, new BukkitRunnable() {
                @Override
                public void run() {
                    World world = Bukkit.getWorld(nextArena.getName());
                    if(nextArena.getTime() > 300){
                        world.dropItemNaturally(emerald, new ItemStack(Material.EMERALD));
                        world.dropItemNaturally(emerald, new ItemStack(Material.EMERALD));
                    }
                    if(nextArena.getTime() > 600){
                        world.dropItemNaturally(emerald, new ItemStack(Material.EMERALD));
                        world.dropItemNaturally(emerald, new ItemStack(Material.EMERALD));
                    }
                    if (nextArena.getTime() < 300){
                        world.dropItemNaturally(emerald, new ItemStack(Material.EMERALD));
                    }
                    if(nextArena.isStarted() == false){
                        cancel();
                    }
                }
            }, 0, 800);


        });
        nextArena.getDiamsSpawners().forEach(diam -> {
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncRepeatingTask((Plugin) bedwars, new BukkitRunnable() {
                @Override
                public void run() {
                    World world = Bukkit.getWorld(nextArena.getName());
                    if(nextArena.getTime() > 600){
                        world.dropItemNaturally(diam, new ItemStack(Material.DIAMOND));
                        world.dropItemNaturally(diam, new ItemStack(Material.DIAMOND));
                    }
                    if(nextArena.getTime() > 900){
                        world.dropItemNaturally(diam, new ItemStack(Material.DIAMOND));
                        world.dropItemNaturally(diam, new ItemStack(Material.DIAMOND));
                    }
                    if (nextArena.getTime() < 600){
                        world.dropItemNaturally(diam, new ItemStack(Material.DIAMOND));
                    }
                    if(nextArena.isStarted() == false){
                        cancel();
                    }

                }
            }, 0, 600);
        });

        nextArena.getTeamSpawners().forEach((teamName, spawner) -> {
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncRepeatingTask((Plugin) bedwars, new BukkitRunnable() {
                @Override
                public void run() {

                    Player player = nextArena.getPlayers().get(teamName);
                    for(int index = 0; index < nextArena.getPlayerLevels().size(); index++) {
                        if (nextArena.getPlayerLevels().get(index).getName() == player.getName()) {
                            World world = Bukkit.getWorld(nextArena.getName());
                            switch (nextArena.getPlayerLevels().get(index).getSpawner()) {
                                case 0:
                                    world.dropItemNaturally(spawner, new ItemStack(Material.IRON_INGOT));
                                    break;
                                case 1:
                                    world.dropItemNaturally(spawner, new ItemStack(Material.IRON_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.IRON_INGOT));
                                    break;
                                case 2:
                                    world.dropItemNaturally(spawner, new ItemStack(Material.IRON_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.IRON_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.IRON_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.IRON_INGOT));
                                    break;
                                case 3:
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.EMERALD));
                                    break;
                            }
                        }
                    }

                    if(nextArena.isStarted() == false){
                        cancel();
                    }

                }
            }, 0, 100);
        });
        nextArena.getTeamSpawners().forEach((teamName, spawner) -> {
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.scheduleSyncRepeatingTask((Plugin) bedwars, new BukkitRunnable() {
                @Override
                public void run() {
                    Player player = nextArena.getPlayers().get(teamName);
                    for(int index = 0; index < nextArena.getPlayerLevels().size(); index++) {
                        if (nextArena.getPlayerLevels().get(index).getName() == player.getName()) {
                            World world = Bukkit.getWorld(nextArena.getName());
                            switch (nextArena.getPlayerLevels().get(index).getSpawner()) {
                                case 0:
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    break;
                                case 1:
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    break;
                                case 2:
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    break;
                                case 3:
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.GOLD_INGOT));
                                    world.dropItemNaturally(spawner, new ItemStack(Material.EMERALD));
                                    break;
                            }
                        }
                    }
                    if(nextArena.isStarted() == false){
                        cancel();
                    }

                }
            }, 0, 200);
        });

        //time and bossBar
        time(nextArena);
    }
    public void addItemToPlayer(Player player, arena arena){
        Inventory inv = player.getInventory();
        inv.clear();
        inv.addItem(new ItemStack(Material.WOOD_SWORD));
        for(int index = 0; index < arena.getPlayerLevels().size(); index++){
            if(arena.getPlayerLevels().get(index).getName() == player.getName()){
                switch (arena.getPlayerLevels().get(index).getAxeLevel()){
                    case 1:
                        inv.addItem(new ItemStack(Material.WOOD_AXE));
                        break;
                    case 2:
                        inv.addItem(new ItemStack(Material.STONE_AXE));
                        break;
                    case 3:
                        inv.addItem(new ItemStack(Material.IRON_AXE));
                        break;
                    case 4:
                        inv.addItem(new ItemStack(Material.DIAMOND_AXE));
                        break;
                }
                switch (arena.getPlayerLevels().get(index).getPickaxeLevel()){
                    case 1:
                        inv.addItem(new ItemStack(Material.WOOD_PICKAXE));
                        break;
                    case 2:
                        inv.addItem(new ItemStack(Material.STONE_PICKAXE));
                        break;
                    case 3:
                        inv.addItem(new ItemStack(Material.IRON_PICKAXE));
                        break;
                    case 4:
                        inv.addItem(new ItemStack(Material.DIAMOND_PICKAXE));
                        break;
                }
                switch (arena.getPlayerLevels().get(index).getArmor()){
                    case "WOOD":
                        inv.addItem(new ItemStack(Material.LEATHER_HELMET));
                        inv.addItem(new ItemStack(Material.LEATHER_CHESTPLATE));
                        inv.addItem(new ItemStack(Material.LEATHER_LEGGINGS));
                        inv.addItem(new ItemStack(Material.LEATHER_BOOTS));
                        break;
                    case "IRON":
                        inv.addItem(new ItemStack(Material.LEATHER_HELMET));
                        inv.addItem(new ItemStack(Material.LEATHER_CHESTPLATE));
                        inv.addItem(new ItemStack(Material.IRON_LEGGINGS));
                        inv.addItem(new ItemStack(Material.IRON_BOOTS));
                        break;
                    case "DIAMOND":
                        inv.addItem(new ItemStack(Material.LEATHER_HELMET));
                        inv.addItem(new ItemStack(Material.LEATHER_CHESTPLATE));
                        inv.addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
                        inv.addItem(new ItemStack(Material.DIAMOND_BOOTS));
                        break;

                }
                switch (arena.getPlayerLevels().get(index).getPower()){
                    case 1:
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE ,900000000, 1));

                }
                switch (arena.getPlayerLevels().get(index).getProtection()){
                    case 1:
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE ,900000000, 1));
                    case 2:
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE ,900000000, 2));
                    case 3:
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE ,900000000, 3));
                }
            }
        }

    }
    public boolean checkLocation(Location loc1, Location loc2){
        if(loc1.getBlockX() == loc2.getBlockX() && loc1.getBlockY() == loc2.getBlockY() && loc1.getBlockZ() == loc2.getBlockZ()){
            return true;
        }
        return false;
    }
    private void time(arena arena){
        BossBar bossBar = Bukkit.createBossBar("Time", BarColor.BLUE, BarStyle.SOLID);
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask((Plugin) bedwars, new BukkitRunnable() {
            @Override
            public void run() {
                if(arena.isStarted() == true){
                    arena.setTime(arena.getTime() + 1);
                    bossBar.setTitle("Time: " + arena.getTime());
                    arena.getPlayers().forEach((teamName, player)-> {
                        bossBar.addPlayer(player);
                    });
                    if(arena.getPlayers().size() == 1){
                        for(Player p : Bukkit.getOnlinePlayers()){
                            if(p.getWorld().getName().equals(arena.getName())){
                                p.sendMessage("Fin de partie !");
                                p.teleport(new Location(Bukkit.getWorld("world"), 9, 65, -4));
                                p.getInventory().clear();
                                p.sendMessage("Arret du server pour un reset des maps dans 10 secondes");
                            }
                        }
                        arena.getPlayers().clear();
                        arena.getTeams().clear();
                        arena.setStarted(false);
                        arena.getTeams().add("RED");
                        arena.getTeams().add("ORANGE");
                        arena.getTeams().add("PURPLE");
                        arena.getTeams().add("PINK");
                        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                        scheduler.scheduleSyncDelayedTask(bedwars, new BukkitRunnable() {
                            @Override
                            public void run() {
                                Bukkit.getServer().getConsoleSender().sendMessage("restart");
                            }
                        }, 200);
                    }
                }
                if(arena.isStarted() == false){
                    cancel();
                }
            }
        }, 0, 20);
    }

}
