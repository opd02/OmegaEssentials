package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class warpcommand implements CommandExecutor {
	main plugin;
	public warpcommand(main passedplugin){
		this.plugin = passedplugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("warp")){
			if(!(sender instanceof Player)){
				sender.sendMessage("Buddy, no, only players please");
				return true;
			}
			Player p = (Player) sender;
			if(args.length==0){
				StringBuilder sb = new StringBuilder();
				int num = plugin.getConfig().getConfigurationSection("warps").getKeys(false).size();
				sb.append("§8[§2§lWarps§r§8] §7");
				for(String s : plugin.getConfig().getConfigurationSection("warps").getKeys(false)){
					String add = s.substring(0, 1) + s.substring(1, s.length()).toLowerCase();
					num--;
					if(num!=0){
						sb.append(add + ", ");
					}else{
						sb.append(add);	
					}
				}
				p.sendMessage(sb.toString());
				return true;
			}
			if(plugin.spawning.contains(p)){
				p.sendMessage(ChatColor.RED + "You are already teleporting!");
				return true;
			}
			String warp = args[0].toUpperCase();
			if(!(plugin.getConfig().getConfigurationSection("warps").contains(warp))){
				p.sendMessage(ChatColor.RED + "'" + args[0] + " is not a warp!");
				return true;
			}
			ConfigurationSection section = plugin.getConfig().getConfigurationSection("warps." + warp);
			double x = section.getDouble("X");
			double y = section.getDouble("Y");
			double z = section.getDouble("Z");
			double pitch = section.getDouble("PITCH");
			double yaw = section.getDouble("YAW");
			Location loc = new Location(Bukkit.getWorld(section.getString(".world")), x, y, z, (float) yaw, (float) pitch);
			plugin.getConfig().set("dontmove." + p.getName() + ".X", p.getLocation().getBlockX());
			plugin.getConfig().set("dontmove." + p.getName() + ".Z", p.getLocation().getBlockZ());
			plugin.saveConfig();
			p.sendMessage("§8[§2§lOmega§r§8] §7Warping to §9" + warp.toLowerCase() + "§7, Do not move. (5 seconds)");
			plugin.spawning.add(p);
			p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			    @Override
			    public void run() {
			        if(p.getLocation().getBlockX()==plugin.getConfig().getInt("dontmove." + p.getName() + ".X") && p.getLocation().getBlockZ()==plugin.getConfig().getInt("dontmove." + p.getName() + ".Z")){
			        	p.teleport(loc);
			        	p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
			        	plugin.getConfig().set("dontmove." + p.getName(), null);
			        	plugin.spawning.remove(p);
			        	plugin.saveConfig();
			        }else{
			        	p.sendMessage("§8[§2§lOmega§r§8] §7Your request has been canceled becuase you moved!");
			        	p.playSound(p.getLocation(), Sound.FIZZ, 1, 1);
			        	plugin.getConfig().set("dontmove." + p.getName(), null);
			        	plugin.spawning.remove(p);
			        	plugin.saveConfig();
			        	return;
			        }
			    }
			}, 100L);
		}
		return false;
	}

}
