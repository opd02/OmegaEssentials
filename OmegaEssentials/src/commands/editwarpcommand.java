package commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class editwarpcommand implements CommandExecutor {
	main plugin;
	public editwarpcommand(main passedplugin){
		this.plugin = passedplugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("delwarp")){
			if(!(sender.hasPermission("essentials.delwarp"))){
				sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
				return true;
			}
			if(args.length==0){
				sender.sendMessage(ChatColor.RED + "Usage: /delwarp (warp)");
				return true;
			}
			String warp = args[0].toUpperCase();
			if(plugin.getConfig().contains("warps." + warp)){
				plugin.getConfig().set("warps." + warp, null);
				plugin.saveConfig();
				sender.sendMessage("§8[§2§lOmega§r§8] §7Warp '§9" + warp + "§7' has been removed!");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "'" + warp + "' is not a warp!");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("setwarp")){
			Player p = (Player) sender;
			if(!(p.hasPermission("essentials.setwarp"))){
				p.sendMessage(ChatColor.RED + "You do not have permissin to use that command!");
				return true;
			}
			if(args.length==0){
				p.sendMessage(ChatColor.RED + "Usage: /setwarp (name)");
				return true;
			}
			Location loc = p.getLocation();
			String warp = args[0].toUpperCase();
			if(plugin.getConfig().contains("warps." + warp)){
				p.sendMessage(ChatColor.RED + "That warp already exists!");
				return true;
			}
			ConfigurationSection section = plugin.getConfig().createSection("warps." + warp);
			section.set("world", loc.getWorld().getName());
			section.set("X", loc.getX());
			section.set("Y", loc.getY());
			section.set("Z", loc.getZ());
			section.set("PITCH", loc.getPitch());
			section.set("YAW", loc.getYaw());
			p.sendMessage("§8[§2§lOmega§r§8] §7Warp '§9" + warp + "§7' has been created!");
			plugin.saveConfig();
		}
		return false;
	}

}
