package fr.endide.bedwars.events;

import fr.endide.bedwars.Bedwars;
import fr.endide.bedwars.arena.arena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class deathListener implements Listener {
    Bedwars bedwars;
    public deathListener(Bedwars bedwars) {
        this.bedwars = bedwars;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player player = e.getEntity();
        if(bedwars.getArenaManager().ifPlayerIsInArena(player)) {
            arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
            player.spigot().respawn();
            player.setGameMode(GameMode.ADVENTURE);
            player.teleport(arena.getWaitLobby());
            player.getInventory().clear();
            player.sendMessage("§cTu est mort BG");
            for (String playerTeam : arena.getPlayers().keySet()) {
                if (arena.getPlayers().get(playerTeam) == player) {
                    if(arena.getTeams().contains(playerTeam)){
                        player.sendMessage("§cHeuresement NEGRO ton lit n'a pas ete detruit, réaparition dans 5 secondes");
                        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                        scheduler.scheduleSyncDelayedTask((Plugin) bedwars, new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.teleport(arena.getTeamSpawns().get(playerTeam));
                                player.setGameMode(GameMode.SURVIVAL);
                                bedwars.getArenaManager().addItemToPlayer(player, arena);

                            }
                        }, 100L);
                    }else{
                        player.sendMessage(ChatColor.RED + "Ton lit a ete detruit, tu est mort, Fin partie tu est un loooooooooser");
                        arena.getPlayers().remove(playerTeam);

                    }

                }
            }
        }
    }
}
