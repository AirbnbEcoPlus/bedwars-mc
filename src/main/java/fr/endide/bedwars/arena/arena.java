package fr.endide.bedwars.arena;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class arena {
    private String name;
    private int maxPlayers;
    private int minPlayers;
    private boolean isStarted;
    private Location waitLobby;
    private HashMap<String, Location> teamSpawns;
    private HashMap<String, Location> bedLocations;
    private HashMap<String, Player> players;
    private List<String> teams;
    private List<Location> diamsSpawners;
    private List<Location> emeraldsSpawners;
    private HashMap<String, Location> teamSpawners;
    List<playerLevel> playerLevels;
    private int time;
    private int countDown;
    public arena(String name, int maxPlayers, int minPlayers, boolean isStarted, Location waitLobby, HashMap<String, Location> teamSpawns, HashMap<String, Location> bedLocations, HashMap<String, Player> players, List<String> teams, List<Location> diamsSpawners, List<Location> emeraldsSpawners, HashMap<String, Location> teamSpawners, List<playerLevel> playerLevels, int time, int countDown) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.isStarted = isStarted;
        this.waitLobby = waitLobby;
        this.teamSpawns = teamSpawns;
        this.bedLocations = bedLocations;
        this.players = players;
        this.teams = teams;
        this.diamsSpawners = diamsSpawners;
        this.emeraldsSpawners = emeraldsSpawners;
        this.teamSpawners = teamSpawners;
        this.playerLevels = playerLevels;
        this.time = time;
        this.countDown = countDown;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMaxPlayers() {
        return maxPlayers;
    }
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
    public int getMinPlayers() {
        return minPlayers;
    }
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }
    public boolean isStarted() {
        return isStarted;
    }
    public void setStarted(boolean started) {
        isStarted = started;
    }
    public Location getWaitLobby() {
        return waitLobby;
    }
    public void setWaitLobby(Location waitLobby) {
        this.waitLobby = waitLobby;
    }
    public HashMap<String, Location> getTeamSpawns() {
        return teamSpawns;
    }
    public void setTeamSpawns(HashMap<String, Location> teamSpawns) {
        this.teamSpawns = teamSpawns;
    }
    public HashMap<String, Location> getBedLocations() {
        return bedLocations;
    }
    public void setBedLocations(HashMap<String, Location> bedLocations) {
        this.bedLocations = bedLocations;
    }
    public HashMap<String, Player> getPlayers() {
        return players;
    }
    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
    }
    public List<String> getTeams() {
        return teams;
    }
    public void setTeams(List<String> teams) {
        this.teams = teams;
    }
    public List<Location> getDiamsSpawners() {
        return diamsSpawners;
    }
    public void setDiamsSpawners(List<Location> diamsSpawners) {
        this.diamsSpawners = diamsSpawners;
    }
    public List<Location> getEmeraldsSpawners() {
        return emeraldsSpawners;
    }
    public void setEmeraldsSpawners(List<Location> emeraldsSpawners) {
        this.emeraldsSpawners = emeraldsSpawners;
    }
    public HashMap<String, Location> getTeamSpawners() {
        return teamSpawners;
    }
    public void setTeamSpawners(HashMap<String, Location> teamSpawners) {
        this.teamSpawners = teamSpawners;
    }
    public List<playerLevel> getPlayerLevels() {
        return playerLevels;
    }
    public void setPlayerLevels(List<playerLevel> playerLevels) {
        this.playerLevels = playerLevels;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public int getCountDown() {
        return countDown;
    }
    public void setCountDown(int countDown) {
        this.countDown = countDown;
    }

    public static class playerLevel {
        private String name;
        private String armor;
        private int pickaxeLevel;
        private int axeLevel;
        private int power;
        private int protection;
        private int spawner;
        public playerLevel(String name, String armor, int pickaxeLevel, int axeLevel, int power, int protection, int spawner) {
            this.name = name;
            this.armor = armor;
            this.pickaxeLevel = pickaxeLevel;
            this.axeLevel = axeLevel;
            this.power = power;
            this.protection = protection;
            this.spawner = spawner;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getArmor() {
            return armor;
        }
        public void setArmor(String armor) {
            this.armor = armor;
        }
        public int getPickaxeLevel() {
            return pickaxeLevel;
        }
        public void setPickaxeLevel(int pickaxeLevel) {
            this.pickaxeLevel = pickaxeLevel;
        }
        public int getAxeLevel() {
            return axeLevel;
        }
        public void setAxeLevel(int axeLevel) {
            this.axeLevel = axeLevel;
        }
        public void setPower(int power){
            this.power = power;
        }
        public int getPower(){
            return power;
        }
        public void setProtection(int protection){
            this.protection = protection;
        }
        public int getProtection(){
            return protection;
        }
        public void setSpawner(int spawner){
            this.spawner = spawner;
        }
        public int getSpawner(){
            return spawner;
        }
    }
}
