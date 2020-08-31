package commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class ignorecommand implements CommandExecutor{
	main plugin;
	public ignorecommand(main passedplugin){
		this.plugin = passedplugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("ignore")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "Ethan! Only players can ignore eachother!");
				return true;
			}
			Player p = (Player) sender;
			if(args.length==0){
				p.sendMessage(ChatColor.RED + "Usage: /ignore (player)");
				return true;
			}
			try{
				Player target = Bukkit.getPlayer(args[0]);
				if(plugin.getConfig().getStringList("ignore." + p.getName()).contains(target.getName())){
					p.sendMessage(ChatColor.RED + target.getName() + " is already on your ignore list!");
					return true;
				}
				ArrayList<String> list = new ArrayList<String>();
				for(Object s : plugin.getConfig().getList("ignore." + p.getName())){
					list.add((String)s);
				}
				list.add(args[0]);
				plugin.getConfig().set("ignore." + p.getName(), list);
				plugin.saveConfig();
			}catch(Exception e){
				p.sendMessage(ChatColor.RED + args[0] + " is not an online player!");
				return true;
			}
			
		}
		return false;
	}
	
}
