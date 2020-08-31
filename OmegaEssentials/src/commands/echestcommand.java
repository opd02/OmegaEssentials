package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class echestcommand implements CommandExecutor {
	main plugin;
	public echestcommand(main passedplugin){
		this.plugin = passedplugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("echest")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "This command is for players only.");
				return true;
			}
			Player p = (Player) sender;
			p.openInventory(p.getEnderChest());
			return true;
		}
		return false;
	}

}
