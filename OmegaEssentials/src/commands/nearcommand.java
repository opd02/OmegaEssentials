package commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class nearcommand implements CommandExecutor{
	main plugin;
	public nearcommand(main passedplugin){
		this.plugin = passedplugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("near")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "This command is only for players!");
				return true;
			}
			Player p = (Player) sender;
			if(!(p.hasPermission("essentials.near"))){
				p.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
				return true;
			}
			List<Entity> nearent = p.getNearbyEntities(150, 150, 150);
			String message = "§8[§2§lOmega§r§8] §7Players nearby§8:§7 ";
			int arenear = 0;
			for(Entity e : nearent){
				if(e.getType().equals(EntityType.PLAYER)){
					arenear = 1;
					Player near = (Player) e;
					double distance = near.getLocation().distance(p.getLocation());
					message += near.getName() + "§r§7(§r§l" + Math.round(distance) + "m§r§7) ";
				}
			}
			if(arenear == 0){
				message += "§7none";	
			}
			p.sendMessage(message);
		}
		return false;
	}

}