package de.your.package;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemCreator {

	public static ItemStack createItem(Material material, byte itemdata, int itemamount, String itemname, List<String> itemlore, short itemdamage) {
		@SuppressWarnings("deprecation")
		ItemStack item = new ItemStack(material, itemamount, itemdamage, itemdata);
		ItemMeta meta = item.getItemMeta();
		
		if(itemname != null) meta.setDisplayName(itemname);
		if(itemlore != null) meta.setLore(itemlore);
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack createLeatherArmor(Material material, byte itemdata, int itemamount, Color color, String itemname, List<String> itemlore, short itemdamage) {
		@SuppressWarnings("deprecation")
		ItemStack item = new ItemStack(material, itemamount, itemdamage, itemdata);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		
		if(itemname != null) meta.setDisplayName(itemname);
		if(itemlore != null) meta.setLore(itemlore);
		if(color != null) meta.setColor(color);
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static void clearPlayer(Player player) {
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
	}
	
	public static void removeItem(Inventory inv, ItemStack item, int amount) {
		List<ItemStack> list = new ArrayList<>();
		int devine = amount;
		for(ItemStack it : inv.getContents()) {
			if(it.getType().equals(item.getType())) {
				if(it.getAmount()-devine > 0) {
					int x = it.getAmount();
					//
					for(int a=devine; a>0; a--) {
						it.setAmount(x--);
						devine--;
					}
					
				} else {
					inv.remove(it);
				}
			} else {
				list.add(it);
			}
		}
		
		return;
	}
	
	
	
}
