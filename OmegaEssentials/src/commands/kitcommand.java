package commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.opd02.essentials.main;

public class kitcommand implements CommandExecutor {
	main plugin;
	public kitcommand(main passedplugin){
		this.plugin = passedplugin;
	}
	public ItemStack mkitem(Material m, int amount, String name, Enchantment a, Enchantment b, Enchantment c, Enchantment d, Enchantment e, int aa, int bb, int cc, int dd, int ee){
		ItemStack item = new ItemStack(m, amount);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(name.replace("&", String.valueOf(ChatColor.COLOR_CHAR)));
		if(aa!=0){
			itemm.addEnchant(a, aa, true);
		}
		if(bb!=0){
			itemm.addEnchant(b, bb, true);
		}
		if(cc!=0){
			itemm.addEnchant(c, cc, true);
		}
		if(dd!=0){
			itemm.addEnchant(d, dd, true);
		}
		if(ee!=0){
			itemm.addEnchant(e, ee, true);
		}
		item.setItemMeta(itemm);
		return item;
	}
	public void snowball(Player p){
		p.getInventory().addItem(mkitem(Material.DIAMOND_SWORD, 1, "&r&fSnowball Sword", Enchantment.DAMAGE_ALL, Enchantment.DURABILITY, null, null, null, 2, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_PICKAXE, 1, "&r&fSnowball Pickaxe", Enchantment.DIG_SPEED, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_SPADE, 1, "&r&fSnowball Shovel", Enchantment.DIG_SPEED, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_AXE, 1, "&r&fSnowball Axe", Enchantment.DIG_SPEED, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.BOW, 1, "&r&fSnowball Bow", Enchantment.ARROW_DAMAGE, Enchantment.DURABILITY, Enchantment.ARROW_INFINITE, null, null, 1, 1, 1, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_HELMET, 1, "&r&fSnowball Helmet", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_CHESTPLATE, 1, "&r&fSnowball Chestplate", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_LEGGINGS, 1, "&r&fSnowball Swords", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_BOOTS, 1, "&r&fSnowball Boots", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.ARROW, 1, "&r&fSnowball Arrow", Enchantment.ARROW_INFINITE, null, null, null, null, 1, 0, 0, 0, 0));
		p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 64));
		p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1, (byte)1));
	}
	public void icicle(Player p){
		p.getInventory().addItem(mkitem(Material.DIAMOND_SWORD, 1, "&r&bIcicle Sword", Enchantment.DAMAGE_ALL, Enchantment.DURABILITY, Enchantment.FIRE_ASPECT, null, null, 3, 2, 1, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_PICKAXE, 1, "&r&bIcicle Pickaxe", Enchantment.DIG_SPEED, Enchantment.DURABILITY, null, null, null, 2, 2, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_SPADE, 1, "&r&bIcicle Shovel", Enchantment.DIG_SPEED, Enchantment.DURABILITY, null, null, null, 2, 2, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_AXE, 1, "&r&bIcicle Axe", Enchantment.DIG_SPEED, Enchantment.DURABILITY, null, null, null, 2, 2, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.BOW, 1, "&r&bIcicle Bow", Enchantment.ARROW_DAMAGE, Enchantment.DURABILITY, Enchantment.ARROW_INFINITE, null, null, 3, 2, 1, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_HELMET, 1, "&r&bIcicle Helmet", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 2, 2, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_CHESTPLATE, 1, "&r&bIcicle Chestplate", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 2, 2, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_LEGGINGS, 1, "&r&bIcicle Swords", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 2, 2, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_BOOTS, 1, "&r&bIcicle Boots", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 2, 2, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.ARROW, 1, "&r&bIcicle Arrow", Enchantment.ARROW_INFINITE, null, null, null, null, 1, 0, 0, 0, 0));
		p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 64));
		p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2, (byte)1));
	}
	public void member(Player p){
		p.getInventory().addItem(mkitem(Material.DIAMOND_SWORD, 1, "&r&8Member Sword", Enchantment.DAMAGE_ALL, Enchantment.DURABILITY, null, null, null, 2, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_PICKAXE, 1, "&r&8Member Pickaxe", Enchantment.DIG_SPEED, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_SPADE, 1, "&r&8Member Shovel", Enchantment.DIG_SPEED, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_AXE, 1, "&r&8Member Axe", Enchantment.DIG_SPEED, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.BOW, 1, "&r&8Member Bow", Enchantment.ARROW_DAMAGE, Enchantment.DURABILITY, Enchantment.ARROW_INFINITE, null, null, 1, 1, 1, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_HELMET, 1, "&r&8Member Helmet", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_CHESTPLATE, 1, "&r&8Member Chestplate", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_LEGGINGS, 1, "&r&8Member Swords", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.DIAMOND_BOOTS, 1, "&r&8Member Boots", Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.DURABILITY, null, null, null, 1, 1, 0, 0, 0));
		p.getInventory().addItem(mkitem(Material.ARROW, 1, "&r&8Member Arrow", Enchantment.ARROW_INFINITE, null, null, null, null, 1, 0, 0, 0, 0));
		p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 64));
		p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1, (byte)1));
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]){
		if(cmd.getName().equalsIgnoreCase("kit")){
			Player p = (Player) sender;
			icicle(p);
		}
		return true;
	}
}
