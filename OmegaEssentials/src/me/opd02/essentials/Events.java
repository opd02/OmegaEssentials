package me.opd02.essentials;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
	main plugin;
	public Events(main passedplugin){
		this.plugin = passedplugin;
	}
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent e){
		if(e.getMessage().equalsIgnoreCase("/pl") || e.getMessage().equalsIgnoreCase("/plugins") || e.getMessage().equalsIgnoreCase("/plugin")){
			if(e.getPlayer().hasPermission("essentials.plsee")){
				return;
			}
			Player p = e.getPlayer();
			p.sendMessage("§8§m---------------------------------------------");
			p.sendMessage("");
			p.sendMessage("§8[§2§lOmega§r§8] §7This server was developed by §c§lopd02§r§7.");
			p.sendMessage("");
			p.sendMessage("§8§m---------------------------------------------");
			e.setCancelled(true);
		}
		if(e.getMessage().equalsIgnoreCase("/time day") || e.getMessage().equalsIgnoreCase("/day")){
			if(e.getPlayer().hasPermission("essentials.time")){
				e.getPlayer().getWorld().setTime(1000);
				e.getPlayer().sendMessage("§8[§2§lOmega§r§8] §7You have changed the time to §9day§7.");
				e.setCancelled(true);
				return;
			}
			return;
		}
		if(e.getMessage().equalsIgnoreCase("/time night") || e.getMessage().equalsIgnoreCase("/night")){
			if(e.getPlayer().hasPermission("essentials.time")){
				e.getPlayer().getWorld().setTime(14000);
				e.getPlayer().sendMessage("§8[§2§lOmega§r§8] §7You have changed the time to §9night§7.");
				e.setCancelled(true);
				return;
			}
			return;
		}
	}
	@EventHandler
	public void onPlayerLogOut(PlayerQuitEvent e){
		Player p = e.getPlayer();
		e.setQuitMessage("§7[§c§l-§r§7] " + p.getName());
		try{
			plugin.getConfig().set("tprequests." + p.getName(), null);
		}catch(Exception ex){
		}
		if(plugin.spawning.contains(p)){
			plugin.spawning.remove(p);
		}
		if(p.isFlying()==true){
			plugin.getConfig().set("logWhileflying." + p.getName(), "true");
			plugin.saveConfig();
		}
		if(plugin.conversations.containsKey(p)){
			plugin.conversations.remove(p);
		}
	}
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		e.setJoinMessage("§7[§a§l+§r§7] " + p.getName());
		if(plugin.getConfig().get("logWhileflying." + p.getName())=="true"){
			p.setAllowFlight(true);
			p.setFlying(true);
			plugin.getConfig().set("logWhileflying." + p.getName(), "false");
			plugin.saveConfig();
		}
	}
}