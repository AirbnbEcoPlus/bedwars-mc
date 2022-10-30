package fr.endide.bedwars.commands;

import fr.endide.bedwars.Bedwars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class bw implements CommandExecutor {
    Bedwars bedwars;
    public bw(Bedwars bedwars) {
        this.bedwars = bedwars;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage("§c/bw join <name>");
            sender.sendMessage("§c/bw list");
            sender.sendMessage("§c/bw leave");
        }
        if(args.length == 1 || args.length == 3){
            if(args[0].equalsIgnoreCase("list")){
                bedwars.getArenaManager().getArenas().forEach(arena -> {
                    sender.sendMessage("§a" + arena.getName());
                });
            }
            Player player = (Player) sender;
            if(args[0].equalsIgnoreCase("join")){
                bedwars.getArenaManager().joinArena(player, args[1], args[2]);
            }
        }

        return false;
    }
}
