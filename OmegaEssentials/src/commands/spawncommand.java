package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class spawncommand implements CommandExecutor {
	main plugin;
	public spawncommand(main passedplugin){
		this.plugin = passedplugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("setspawn")){
			if(!(sender instanceof Player)){
				sender.sendMessage("I thought we talked about this Ethan....");
				return true;
			}
			Player p = (Player) sender;
			if(!(p.hasPermission("essentials.setspawn"))){
				p.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
				return true;
			}
			Location loc = p.getLocation();
			plugin.getConfig().set("spawnlocation.world", loc.getWorld().getName());
			plugin.getConfig().set("spawnlocation.X", loc.getX());
			plugin.getConfig().set("spawnlocation.Y", loc.getY());
			plugin.getConfig().set("spawnlocation.Z", loc.getZ());
			plugin.getConfig().set("spawnlocation.PITCH", loc.getPitch());
			plugin.getConfig().set("spawnlocation.YAW", loc.getYaw());
			plugin.saveConfig();
			p.sendMessage("§8[§2§lOmega§r§8] §aSpawn has been set to your current location!");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("spawn")){
			if(!(sender instanceof Player)){
				sender.sendMessage("I thought we talked about this Ethan....");
				return true;
			}
			Player p = (Player) sender;
			if(plugin.spawning.contains(p)){
				p.sendMessage(ChatColor.RED + "You are already teleporting!");
				return true;
			}
			p.sendMessage("§8[§2§lOmega§r§8] §7Teleporting to spawn... Do not move.");
			plugin.spawning.add(p);
			Location loc = p.getLocation();
			double x = loc.getX();
			double y = loc.getY();
			double z = loc.getZ();
			plugin.getConfig().set("spawning." + p.getName() + ".X", x);
			plugin.getConfig().set("spawning." + p.getName() + ".Y", y);
			plugin.getConfig().set("spawning." + p.getName() + ".Z", z);
			plugin.saveConfig();
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				  public void run() {
					  if(!(Math.abs(p.getLocation().getX())-Math.abs(plugin.getConfig().getDouble("spawning." + p.getName() + ".X")) < 0.75)){
						  plugin.spawning.remove(p);
						  p.sendMessage("§8[§2§lOmega§r§8] §7Your spawn request has been canceled because you moved.");
						  p.playSound(p.getLocation(), Sound.FIZZ, 1, 1);
						  plugin.getConfig().set("spawning." + p.getName(), null);
						  plugin.saveConfig();
						  return;
					  }
					  if(!(Math.abs(p.getLocation().getY())-Math.abs(plugin.getConfig().getDouble("spawning." + p.getName() + ".Y")) < 0.75)){
						  plugin.spawning.remove(p);
						  p.sendMessage("§8[§2§lOmega§r§8] §7Your spawn request has been canceled because you moved.");
						  p.playSound(p.getLocation(), Sound.FIZZ, 1, 1);
						  plugin.getConfig().set("spawning." + p.getName(), null);
						  plugin.saveConfig();
						  return;
					  }
					  if(!(Math.abs(p.getLocation().getZ())-Math.abs(plugin.getConfig().getDouble("spawning." + p.getName() + ".Z")) < 0.75)){
						  plugin.spawning.remove(p);
						  p.sendMessage("§8[§2§lOmega§r§8] §7Your spawn request has been canceled because you moved.");
						  p.playSound(p.getLocation(), Sound.FIZZ, 1, 1);
						  plugin.getConfig().set("spawning." + p.getName(), null);
						  plugin.saveConfig();
						  return;
					  }
					  Location configloc = new Location(Bukkit.getWorld(plugin.getConfig().getString("spawnlocation.world")),
							  plugin.getConfig().getDouble("spawnlocation.X")
							  , plugin.getConfig().getDouble("spawnlocation.Y")
							  , plugin.getConfig().getDouble("spawnlocation.Z")
							  , (float)plugin.getConfig().getDouble("spawnlocation.YAW"), (float)plugin.getConfig().getDouble("spawnlocation.PITCH"));
					  p.teleport(configloc);
					  p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
					  plugin.getConfig().set("spawning." + p.getName(), null);
					  plugin.saveConfig();
					  plugin.spawning.remove(p);
		  }
				}, 100L);
		}
		return false;
	}

}
