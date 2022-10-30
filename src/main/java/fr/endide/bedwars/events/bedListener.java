package fr.endide.bedwars.events;

import fr.endide.bedwars.Bedwars;
import fr.endide.bedwars.arena.arena;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class bedListener implements Listener {
    Bedwars bedwars;
    public bedListener(Bedwars bedwars) {
        this.bedwars = bedwars;
    }
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent e) {

        Player player = e.getPlayer();
        Block block = e.getBlock();
        if (e.getBlock() != null && e.getBlock().getType() != null) {
            if (block.getType() == Material.GLASS) {
                if (bedwars.getArenaManager().ifPlayerIsInArena(player)) {
                    arena arena = bedwars.getArenaManager().getArenaByPlayer(player);
                    for (String playerTeam : arena.getPlayers().keySet()) {
                        if (arena.getPlayers().get(playerTeam) == player) {
                            if (!bedwars.getArenaManager().checkLocation(arena.getBedLocations().get(playerTeam), block.getLocation())) {
                                for (String key : arena.getBedLocations().keySet()) {
                                    if (bedwars.getArenaManager().checkLocation(arena.getBedLocations().get(key), block.getLocation())) {
                                        System.out.println("Is the bed of " + key);
                                        for (int index = 0; index < arena.getTeams().size(); index++) {
                                            if (arena.getTeams().get(index).equalsIgnoreCase(key)) {
                                                arena.getTeams().remove(index);
                                            }
                                        }
                                        for (Player players : arena.getPlayers().values()) {
                                            players.sendMessage("§cLe lit de l'equipe " + key + " a ete detruit, BOUGER VOUS LES AUTRES");
                                        }
                                    }
                                }
                            } else {
                                e.setCancelled(true);
                                player.sendMessage("§cT'est serieux negro Fou pas la merde, tu dois pas casser ton lit après peut etre que t'a pas compris les regles");
                            }

                        }
                    }
                }
            }
        }
    }
}
