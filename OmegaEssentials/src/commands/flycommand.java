package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class flycommand implements CommandExecutor {
	main plugin;
	public flycommand(main passedplugin){
		this.plugin = passedplugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("fly")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "This command is for players only.");
				return true;
			}
			Player p = (Player) sender;
			if(!(p.hasPermission("essentials.fly"))){
				p.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
				return true;
			}
			if(p.getAllowFlight()==false){
				p.sendMessage("§8[§2§lOmega§r§8] §7Flight has been §a§lENABLED§r§7.");
				p.setAllowFlight(true);
				return true;
			}
			p.sendMessage("§8[§2§lOmega§r§8] §7Flight has been §c§lDISABLED§r§7.");
			p.setAllowFlight(false);
			return true;
		}
		return false;
	}
}
