package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class listcommand implements CommandExecutor {
	main plugin;
	public listcommand(main passedplugin){
		this.plugin = passedplugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("list")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "Ethan, that command is for players only!");
				return true;
			}
			Player p = (Player) sender;
			int staff = 0;
			String misare = "are";
			String sisare = "are";
			String mplayer = "players";
			String mstaff = "members";
			for(Player player : Bukkit.getServer().getOnlinePlayers()){
				if(player.hasPermission("essentials.staff.list")){
					staff++;
				}
			}
			if(Bukkit.getServer().getOnlinePlayers().size()==1){
				misare = "is";
				mplayer = "player";
			}
			if(staff==1){
				sisare = "is";
				mstaff = "member";
			}
			p.sendMessage("§8§m---------------------------------------------");
			p.sendMessage("");
			p.sendMessage("§8[§2§lOmega§r§8] §7There " + misare + " currently §a" + Bukkit.getServer().getOnlinePlayers().size() + " §7" + mplayer + " online.");
			p.sendMessage("");
			p.sendMessage("§8[§2§lOmega§r§8] §7There " + sisare + " currently §2" + staff + " §7staff " +  mstaff + " online.");
			p.sendMessage("");
			p.sendMessage("§8§m---------------------------------------------");
			p.playSound(p.getLocation(), Sound.IRONGOLEM_THROW, 1, 1);
		}
		return false;
	}
	
}
