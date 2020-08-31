package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class cicommand implements CommandExecutor {
	main plugin;
	public cicommand(main passedplugin){
		this.plugin = passedplugin;
	}
	public void clearInventory(Player player){
		player.getInventory().clear();
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
		}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("ci")){
			if(!(sender.hasPermission("essentials.ci"))){
				sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
				return true;
			}
			if(args.length==0 && sender instanceof Player){
				Player p = (Player) sender;
				clearInventory(p);
				p.sendMessage("§8[§2§lOmega§r§8] §7You inventory has been cleared!");
				return true;
			}
			if(!(sender.hasPermission("essentials.ci.others"))){
				sender.sendMessage(ChatColor.RED + "You do not have permission to clear other people's inventory!");
				return true;
			}
			try{
				Player target = Bukkit.getPlayer(args[0]);
				sender.sendMessage("§8[§2§lOmega§r§8] §7You have cleared §a" + target.getName() + "'s §r§7inventory.");
				clearInventory(target);
			}catch(Exception e){
				sender.sendMessage(ChatColor.RED + args[0] + " is not an online player!");
			}
		}
		return false;
	}
}