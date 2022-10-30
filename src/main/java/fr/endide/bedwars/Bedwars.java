package fr.endide.bedwars;

import fr.endide.bedwars.arena.arena;
import fr.endide.bedwars.arena.arenaManager;
import fr.endide.bedwars.arena.item;
import fr.endide.bedwars.arena.itemManager;
import fr.endide.bedwars.commands.bw;
import fr.endide.bedwars.events.bedListener;
import fr.endide.bedwars.events.deathListener;
import fr.endide.bedwars.events.invListener;
import fr.endide.bedwars.events.shopListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.Configuration;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Bedwars extends JavaPlugin {
    private arenaManager arenaManager = new arenaManager(this);
    private itemManager itemManager = new itemManager();

    @Override
    public void onEnable() {
        System.out.println("Bedwars plugin is enabled");
        initialSetup();
        dataInit();
        getCommand("bw").setExecutor(new bw(this));
        getServer().getPluginManager().registerEvents(new shopListener(this), this);
        getServer().getPluginManager().registerEvents(new deathListener(this), this);
        getServer().getPluginManager().registerEvents(new bedListener(this), this);
        getServer().getPluginManager().registerEvents(new invListener(this), this);
        getItemFromConfig();

    }

    @Override
    public void onDisable() {
        System.out.println("Bedwars disabled");
    }
    private void initialSetup(){
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        if(!new File(getDataFolder(), "config.yml").exists()){
            this.saveDefaultConfig();
        }
    }
    public void getItemFromConfig(){
        ConfigurationSection section = getConfig().getConfigurationSection("market");
        for(String key : section.getKeys(false)){
            String name = section.getString(key + ".name");
            int price = section.getInt(key + ".price");
            String money = section.getString(key + ".money");
            String type = section.getString(key + ".type");
            int slot = section.getInt(key + ".slot");
            int amount = section.getInt(key + ".amount");
            itemManager.addItem(new item(name, price, money, type, slot, amount));
        }
    }
    public void getArenaFromConfig(){
        ConfigurationSection arenaSection = getConfig().getConfigurationSection("arenas");
        for(String str : arenaSection.getKeys(false)){
            String name = arenaSection.getString(str + ".name");
            System.out.println(name);
            int maxPlayers = arenaSection.getInt(str + ".maxPlayers");
            System.out.println(maxPlayers);
            int minPlayers = arenaSection.getInt(str + ".minPlayers");
            System.out.println(minPlayers);
            String waitLobbySection = arenaSection.getString(str + ".waitLobby");
            System.out.println(waitLobbySection);
            ConfigurationSection teamSpawnsSection = arenaSection.getConfigurationSection(str + ".teamSpawns");
            ConfigurationSection bedLocationsSection = arenaSection.getConfigurationSection(str + ".bedLocations");
            List<String> teamsSection = (List<String>) arenaSection.getList(str + ".teams");
            List<String> diamsSpawnersSection = (List<String>) arenaSection.getList(str + ".diamsSpawners");
            List<String> emeraldsSpawnersSection = (List<String>) arenaSection.getList(str + ".emeraldsSpawners");
            ConfigurationSection teamSpawnersSection = arenaSection.getConfigurationSection(str + ".teamSpawners");
            //parser
            System.out.println(teamSpawnsSection.get("RED"));

        }
    }
    public Location parseLocationsByString(String name, String str){
        String[] split = str.split(",");
        double x = Double.parseDouble(split[0]);
        double y = Double.parseDouble(split[1]);
        double z = Double.parseDouble(split[2]);
        return new Location(Bukkit.getWorld(name), x, y, z);
    }
    public List<Location> parseLocationsByList(String name, List<String> list){
        List<Location> locations = null;
        for(String str : list){
            locations.add(parseLocationsByString(name, str));
        }
        return locations;
    }
    public HashMap<String, Location> parseLocationsByHashMap(String name, ConfigurationSection section){
        HashMap<String, Location> locations = null;
        for(String str : section.getKeys(false)){
            System.out.println(name);
            System.out.println(str);
            locations.put(str + ".team", parseLocationsByString(name, section.getString(str + ".location")));
        }
        return locations;
    }
    public void dataInit(){
        Location waitLobby = new Location(Bukkit.getWorld("arena2"), 0.5, 88, 0.5);

        HashMap<String, Location> teamSpawns = new HashMap<>();
        teamSpawns.put("RED", new Location(Bukkit.getWorld("arena2"), 44.5, 78, 0.5));
        teamSpawns.put("ORANGE", new Location(Bukkit.getWorld("arena2"), 0.5, 78, 44.5));
        teamSpawns.put("PURPLE", new Location(Bukkit.getWorld("arena2"), -42.5, 78, 0.5));
        teamSpawns.put("PINK", new Location(Bukkit.getWorld("arena2"), 0.5, 78, -40.5));

        HashMap<String, Location> bedLocations = new HashMap<>();
        bedLocations.put("RED", new Location(Bukkit.getWorld("arena2"), 35, 78, 2));
        bedLocations.put("ORANGE", new Location(Bukkit.getWorld("arena2"), -1.5, 78, 35.5));
        bedLocations.put("PURPLE", new Location(Bukkit.getWorld("arena2"), -33.5, 78, -1.5));
        bedLocations.put("PINK", new Location(Bukkit.getWorld("arena2"), 3.5, 78, -31.5));

        List<String> teams = new ArrayList<>();
        teams.add("RED");
        teams.add("ORANGE");
        teams.add("PURPLE");
        teams.add("PINK");
        List<Location> diamsSpawner = new ArrayList<>();
        diamsSpawner.add(new Location(Bukkit.getWorld("arena2"), 33.5, 78, -32.5));
        diamsSpawner.add(new Location(Bukkit.getWorld("arena2"), 33.5, 78, 33.5));
        diamsSpawner.add(new Location(Bukkit.getWorld("arena2"), -32.5, 78, 33.5));
        diamsSpawner.add(new Location(Bukkit.getWorld("arena2"), -32.5, 78, -32.5));
        List<Location> emeraldsSpawner = new ArrayList<>();
        emeraldsSpawner.add(new Location(Bukkit.getWorld("arena2"), 3.5,78,3.5));
        emeraldsSpawner.add(new Location(Bukkit.getWorld("arena2"), -2.5,78,-2.5));

        HashMap<String, Location> teamSpawners = new HashMap<>();
        teamSpawners.put("RED", new Location(Bukkit.getWorld("arena2"), 38.5,78,0.5));
        teamSpawners.put("ORANGE", new Location(Bukkit.getWorld("arena2"), 0.5,78,38.5 ));
        teamSpawners.put("PURPLE", new Location(Bukkit.getWorld("arena2"), -36.5,78,0.5));
        teamSpawners.put("PINK", new Location(Bukkit.getWorld("arena2"), .5,78,-34.5));


        arena arena = new arena("arena2", 4, 2, false, waitLobby, teamSpawns, bedLocations, new HashMap<String, Player>(), teams, diamsSpawner, emeraldsSpawner,teamSpawners, new ArrayList<arena.playerLevel>(), 0, 180);
        arenaManager.addArena(arena);
    }
    public arenaManager getArenaManager(){
        return arenaManager;
    }
    public itemManager getItemManager(){
        return itemManager;
    }
}
