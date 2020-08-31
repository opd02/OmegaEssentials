package commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.opd02.essentials.main;

public class msgcommand implements CommandExecutor{
	
	main plugin;
	public msgcommand(main passedplugin){
		this.plugin = passedplugin;
	}
	public void setReplyTarget(Player messager, Player reciever){
        plugin.conversations.put(messager, reciever);
        plugin.conversations.put(reciever, messager);
    }
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equalsIgnoreCase("r")){
			if(!(sender instanceof Player)){
				sender.sendMessage("That command is for players only!");
				return true;
			}
			Player p = (Player) sender;
			if(args.length==0){
				p.sendMessage(ChatColor.RED + "Usage: /r (message)");
				return true;
			}
			if(!(plugin.conversations.containsValue(p))){
				p.sendMessage(ChatColor.RED + "You are not in a conversation right now!");
				return true;
			}
			Player rec = plugin.conversations.get(p);
			ArrayList<String> ignorelist = (ArrayList<String>) plugin.getConfig().getStringList("ignore." + rec.getName());
			if(ignorelist.contains(p.getName())){
				p.sendMessage(ChatColor.RED + "You cannot message that player! They have you blocked!");
				return true;
			}
			try{
				@SuppressWarnings("unused")
				String gotya = args[0];
				String message = "";
                for(int i = 0; i < args.length; i++){
                    message += " " + args[i];
                }
                p.sendMessage("§2§lYou >> " + rec.getName() + " §r§a" + message);
                rec.sendMessage("§2§l" + p.getName() + " >> You §r§a" + message);
                rec.playSound(rec.getLocation(), Sound.ITEM_PICKUP, (float)0.75, (float)0.5);
			}catch(Exception e){
				p.sendMessage(ChatColor.RED + "Usage: /r (message)");
				return true;
			}
			
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("msg")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "Only players may message other players!");
				return true;
			}
			Player p = (Player) sender;
			if(args.length==0){
				p.sendMessage(ChatColor.RED + "Usage: /msg (player) (message)");
				return true;
			}
			if(Bukkit.getPlayer(args[0])==null){
				p.sendMessage(ChatColor.RED + args[0] + " is not an online user!");
				return true;
			}
			Player rec = Bukkit.getPlayer(args[0]);
			ArrayList<String> ignorelist = (ArrayList<String>) plugin.getConfig().getStringList("ignore." + rec.getName());
			if(ignorelist.contains(p.getName())){
				p.sendMessage(ChatColor.RED + "You cannot message that player! They have you blocked!");
				return true;
			}
			if(p.getName()==rec.getName()){
				p.sendMessage(ChatColor.RED + "You cannot send a message to yourself!");
				return true;
			}
			try{
				@SuppressWarnings("unused")
				String gotya = args[1];
				String message = "";
                for(int i = 1; i < args.length; i++){
                    message += " " + args[i];
                }
                p.sendMessage("§2§lYou >> " + rec.getName() + " §r§a" + message);
                rec.sendMessage("§2§l" + p.getName() + " >> You §r§a" + message);
                rec.playSound(rec.getLocation(), Sound.ITEM_PICKUP, (float)0.75, (float)0.5);
				setReplyTarget(p, rec);
			}catch(Exception e){
				p.sendMessage(ChatColor.RED + "Usage: /msg (player) (message)");
				return true;
			}
			
		}
		return false;
	}

}
