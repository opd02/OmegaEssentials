package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class gmcommand implements CommandExecutor{
	main plugin;
	public gmcommand(main passedplugin){
		this.plugin = passedplugin;
	}
	public void othergamemodeSwitcher(CommandSender sender, String mode, CommandSender admin){
		Player p = (Player) sender;
		switch(mode){
		case "c": 
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6creative§7.");
			admin.sendMessage("§8[§2§lOmega§r§8] §7You have changed §f" + p.getName() + "'s§7 gamemode to §6creative§7.");
			break;
		case "s":
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6survival§7.");
			admin.sendMessage("§8[§2§lOmega§r§8] §7You have changed§f " + p.getName() + "'s§7 gamemode to §6survival§7.");
			break;
		case "sp":
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6spectator§7.");
			admin.sendMessage("§8[§2§lOmega§r§8] §7You have changed§f " + p.getName() + "'s §7gamemode to §6spectator§7.");
			break;
		case "creative": 
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6creative§7.");
			admin.sendMessage("§8[§2§lOmega§r§8] §7You have changed §f" + p.getName() + "'s§7 gamemode to §6creative§7.");
			break;
		case "survival":
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6survival§7.");
			admin.sendMessage("§8[§2§lOmega§r§8] §7You have changed §f" + p.getName() + "'s §7gamemode to §6survival§7.");
			break;
		case "spectator":
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6spectator§7.");
			admin.sendMessage("§8[§2§lOmega§r§8] §7You have changed §f" + p.getName() + "'s§7 gamemode to §6spectator§7.");
			break;
		default:
			admin.sendMessage(ChatColor.RED + "'" + mode + "'" + " is not a valid gamemode!");
			break;
		}
	}
	public void selfgamemodeSwitcher(CommandSender sender, String mode){
			if(!(sender instanceof Player)){
				sender.sendMessage("Console, please specify a player.");
				return;	
			}
			Player p = (Player) sender;
			switch(mode){
			case "c": 
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6creative§7.");
				break;
			case "s":
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6survival§7.");
				break;
			case "sp":
				p.setGameMode(GameMode.SPECTATOR);
				p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6spectator§7.");
				break;
			case "creative": 
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6creative§7.");
				break;
			case "survival":
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6survival§7.");
				break;
			case "spectator":
				p.setGameMode(GameMode.SPECTATOR);
				p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6spectator§7.");
				break;
			default:
				p.sendMessage(ChatColor.RED + "'" + mode + "'" + " is not a valid gamemode!");
				break;
			}
		}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("gm")){
			if(!sender.hasPermission("essentials.gm")){
				sender.sendMessage(ChatColor.RED + "You do not have permission to use that command!");
				return true;
			}
			if(args.length==0){
				sender.sendMessage(ChatColor.RED + "Incorrecet usage: /gm (gamemode) <player>");
				return true;
			}
			try{
				Player target = Bukkit.getPlayer(args[1]);
				if(target==null){
					sender.sendMessage(ChatColor.RED + args[1] + " is not an online player!");
					return true;
				}
				othergamemodeSwitcher(target, args[0], sender);
				return true;
			}catch(Exception e){
				selfgamemodeSwitcher(sender, args[0]);
				return true;	
			}
		}
		if(cmd.getName().equalsIgnoreCase("gmc")){
			Player p = (Player) sender;
			if(!(p.hasPermission("essentials.gm"))){
				p.sendMessage(ChatColor.RED + "You do not have permission to use the command!");
				return true;
			}
			p.setGameMode(GameMode.CREATIVE);
			p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6creative§7.");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("gms")){
			Player p = (Player) sender;
			if(!(p.hasPermission("essentials.gm"))){
				p.sendMessage(ChatColor.RED + "You do not have permission to use the command!");
				return true;
			}
			p.setGameMode(GameMode.SURVIVAL);
			p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6survival§7.");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("gmsp")){
			Player p = (Player) sender;
			if(!(p.hasPermission("essentials.gm"))){
				p.sendMessage(ChatColor.RED + "You do not have permission to use the command!");
				return true;
			}
			p.setGameMode(GameMode.SPECTATOR);
			p.sendMessage("§8[§2§lOmega§r§8] §7Your gamemode has been set to §6spectator§7.");
			return true;
		}
		return false;
	}
}