package me.opd02.essentials;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import commands.cicommand;
import commands.echestcommand;
import commands.editwarpcommand;
import commands.feedcommand;
import commands.flycommand;
import commands.gmcommand;
import commands.ignorecommand;
import commands.listcommand;
import commands.msgcommand;
import commands.nearcommand;
import commands.spawncommand;
import commands.tpacommand;
import commands.warpcommand;

public class main extends JavaPlugin implements Listener {
	public HashMap<Player, Player> conversations = new HashMap<Player, Player>();
	public ArrayList<Player> spawning = new ArrayList<Player>();
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(new Events(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		this.getCommand("gm").setExecutor(new gmcommand(this));
		this.getCommand("gmc").setExecutor(new gmcommand(this));
		this.getCommand("gms").setExecutor(new gmcommand(this));
		this.getCommand("gmsp").setExecutor(new gmcommand(this));
		this.getCommand("fly").setExecutor(new flycommand(this));
		this.getCommand("echest").setExecutor(new echestcommand(this));
		this.getCommand("msg").setExecutor(new msgcommand(this));
		this.getCommand("r").setExecutor(new msgcommand(this));
		this.getCommand("ci").setExecutor(new cicommand(this));
		this.getCommand("feed").setExecutor(new feedcommand(this));
		this.getCommand("near").setExecutor(new nearcommand(this));
		this.getCommand("list").setExecutor(new listcommand(this));
		this.getCommand("ignore").setExecutor(new ignorecommand(this));
		this.getCommand("tpa").setExecutor(new tpacommand(this));
		this.getCommand("tpaccept").setExecutor(new tpacommand(this));
		this.getCommand("tpahere").setExecutor(new tpacommand(this));
		this.getCommand("tpdeny").setExecutor(new tpacommand(this));
		this.getCommand("spawn").setExecutor(new spawncommand(this));
		this.getCommand("setspawn").setExecutor(new spawncommand(this));
		this.getCommand("warp").setExecutor(new warpcommand(this));
		this.getCommand("setwarp").setExecutor(new editwarpcommand(this));
		this.getCommand("delwarp").setExecutor(new editwarpcommand(this));
		//this.getCommand("kit").setExecutor(new kitcommand(this));
		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
	}
}
