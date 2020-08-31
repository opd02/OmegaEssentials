package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class feedcommand implements CommandExecutor {
	main plugin;
	public feedcommand(main passedplugin){
		this.plugin = passedplugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("feed")){
			if(!(sender.hasPermission("essentials.feed"))){
				sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
				return true;
			}
			if(args.length==0 && sender instanceof Player){
				Player p = (Player) sender;
				p.setFoodLevel(22);
				p.sendMessage("§8[§2§lOmega§r§8] §7You have been fed!");
				return true;
			}
			if(!(sender.hasPermission("essentials.feed.others"))){
				sender.sendMessage(ChatColor.RED + "You do not have permission to feed other people!");
				return true;
			}
			try{
				Player target = Bukkit.getPlayer(args[0]);
				target.setFoodLevel(22);
				target.sendMessage("§8[§2§lOmega§r§8] §7You have been fed!");
				sender.sendMessage("§8[§2§lOmega§r§8] §7You have fed §a" + target.getName() + "§r§7.");
			}catch(Exception e){
				sender.sendMessage(ChatColor.RED + args[0] + " is not an online player!");
			}
			return true;
		}
		return false;
	}

}
