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

public class tpacommand implements CommandExecutor {
	main plugin;
	public tpacommand(main passedplugin){
		this.plugin = passedplugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("tpdeny")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "That command is for players only! Dumbass");
				return true;
			}
			Player p = (Player) sender;
			if(plugin.getConfig().getString("tprequests." + p.getName()) == null){
				p.sendMessage(ChatColor.RED + "You do not have any teleportation requests.");
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("tpa")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "That command is for players only! Dumbass");
				return true;
			}
			Player p = (Player) sender;
			if(args.length==0){
				p.sendMessage(ChatColor.RED + "Usage: /tpa (player)");
				return true;
			}
			try{
				Player target = Bukkit.getPlayer(args[0]);
				if(target==p){
					p.sendMessage(ChatColor.RED + "You cannot teleport to yourself!");
					return true;
				}
				String pname = p.getName();
				target.sendMessage("§8[§2§lOmega§r§8] §9" + p.getName() + "§7 has requested to teleport to you.");
				target.sendMessage("§8[§2§lO§r§8] §7Type §9/tpaccept §7to accept");
				target.sendMessage("§8[§2§lO§r§8] §7Type §9/tpdeny §7to deny");
				target.sendMessage("§8[§2§lO§r§8] §7This request will time out in §9120 seconds§7.");
				p.sendMessage("§8[§2§lOmega§r§8] §7Teleportation request has been sent to §9" + target.getName() + "§7.");
				target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, (float)0.5, 1);
				
				plugin.getConfig().set("tprequests." + target.getName(), p.getName());
				plugin.saveConfig();
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				    @Override
				    public void run() {
				    	try{
				    		if(plugin.getConfig().getString("tprequests." + target.getName()).equals(pname)){
					        	plugin.getConfig().set("tprequests." + target.getName(), null);
					        	plugin.saveConfig();
					        }
				    	}catch(Exception ex){
				    	}
				    }
				}, 2400L);
			}catch(Exception e){
				p.sendMessage(ChatColor.RED + "'" + args[0] + "' is not an online player!");
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("tpaccept")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "Ethan, this command is for players you fuck");
				return true;
			}
			Player p = (Player) sender;
			if(plugin.getConfig().getString("tprequests." + p.getName())==null){
				p.sendMessage("§8[§2§lOmega§r§8] §cYou do not have any teleport requests.");
				return true;
			}
			String configname = plugin.getConfig().getString("tprequests." + p.getName());
			if(configname.substring(0, 4).equals("here")){
				Player requester = (Player) Bukkit.getPlayer(configname.substring(4, configname.length()));
				p.sendMessage("§8[§2§lOmega§r§8] §7You have accepted §9" + configname.substring(4, configname.length()) + "'s §7teleport request, do not move! §9(5 seconds)");
				requester.sendMessage("§8[§2§lOmega§r§8] §9" + p.getName() + " §7has accepted your teleport request.");
				Location loc = p.getLocation();
				plugin.getConfig().set("dontmove." + p.getName() + ".X", loc.getBlockX());
				plugin.getConfig().set("dontmove." + p.getName() + ".Z", loc.getBlockZ());
				plugin.getConfig().set("tprequests." + p.getName(), null);
				plugin.saveConfig();
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				    @Override
				    public void run() {
				        if(p.getLocation().getBlockX()==plugin.getConfig().getInt("dontmove." + p.getName() + ".X") && p.getLocation().getBlockZ()==plugin.getConfig().getInt("dontmove." + p.getName() + ".Z")){
				        	p.teleport(requester.getLocation());
				        	plugin.getConfig().set("dontmove." + p.getName(), null);
				        	plugin.saveConfig();
				        }else{
				        	p.sendMessage("§8[§2§lOmega§r§8] §7Your request has been canceled becuase you moved!");
				        	p.playSound(p.getLocation(), Sound.FIZZ, 1, 1);
				        	plugin.getConfig().set("dontmove." + p.getName(), null);
				        	plugin.saveConfig();
				        	return;
				        }
				    }
				}, 100L);
			}else{
				Player requester = (Player) Bukkit.getPlayer(configname);
				p.sendMessage("§8[§2§lOmega§r§8] §7You have accepted §9" + configname + "'s §7teleport request.");
				requester.sendMessage("§8[§2§lOmega§r§8] §9" + p.getName() + " §7has accepted your teleport request, do not move! §9(5 seconds)");
				Location loc = requester.getLocation();
				plugin.getConfig().set("dontmove." + requester.getName() + ".X", loc.getBlockX());
				plugin.getConfig().set("dontmove." + requester.getName() + ".Z", loc.getBlockZ());
				plugin.getConfig().set("tprequests." + requester.getName(), null);
				plugin.saveConfig();
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				    @Override
				    public void run() {
				        if(requester.getLocation().getBlockX()==plugin.getConfig().getInt("dontmove." + requester.getName() + ".X") && requester.getLocation().getBlockZ()==plugin.getConfig().getInt("dontmove." + requester.getName() + ".Z")){
				        	requester.teleport(p.getLocation());
				        	plugin.getConfig().set("dontmove." + requester.getName(), null);
				        	plugin.saveConfig();
				        }else{
				        	requester.sendMessage("§8[§2§lOmega§r§8] §7Your request has been canceled becuase you moved!");
				        	requester.playSound(requester.getLocation(), Sound.FIZZ, 1, 1);
				        	plugin.getConfig().set("dontmove." + requester.getName(), null);
				        	plugin.saveConfig();
				        	return;
				        }
				    }
				}, 100L);
			}
		}
		if(cmd.getName().equalsIgnoreCase("tpahere")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "That command is for players only! Dumbass");
				return true;
			}
			Player p = (Player) sender;
			if(args.length==0){
				p.sendMessage(ChatColor.RED + "Usage: /tpahere (player)");
				return true;
			}
			try{
				Player target = Bukkit.getPlayer(args[0]);
				if(target==p){
					p.sendMessage(ChatColor.RED + "You cannot teleport to yourself!");
					return true;
				}
				String pname = p.getName();
				target.sendMessage("§8[§2§lOmega§r§8] §9" + p.getName() + "§7 has requested that you teleport to them.");
				target.sendMessage("§8[§2§lO§r§8] §7Type §9/tpaccept §7to accept");
				target.sendMessage("§8[§2§lO§r§8] §7Type §9/tpdeny §7to deny");
				target.sendMessage("§8[§2§lO§r§8] §7This request will time out in §9120 seconds§7.");
				p.sendMessage("§8[§2§lOmega§r§8] §7Teleportation request has been sent to §9" + target.getName() + "§7.");
				target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, (float)0.5, 1);
				
				plugin.getConfig().set("tprequests." + target.getName(), "here" + p.getName());
				plugin.saveConfig();
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				    @Override
				    public void run() {
				    	try{
				    		if(plugin.getConfig().getString("tprequests." + target.getName()).equals("here" + pname)){
					        	plugin.getConfig().set("tprequests." + target.getName(), null);
					        	plugin.saveConfig();
					        }
				    	}catch(Exception ex){	
				    	}
				    }
				}, 2400L);
			}catch(Exception e){
				p.sendMessage(ChatColor.RED + "'" + args[0] + "' is not an online player!");
				return true;
			}
		}
		return false;
	}
}