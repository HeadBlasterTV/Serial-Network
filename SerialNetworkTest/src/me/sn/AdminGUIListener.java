package me.sn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class AdminGUIListener implements Listener{

	@EventHandler
	public void cancel(InventoryClickEvent e) {
		
		if(e.getInventory().getName().equalsIgnoreCase(AdminGUI.getPrefix())) {
			
			if(e.getSlot() == e.getRawSlot()) {
				
				e.setCancelled(true);
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onC(InventoryClickEvent e) {
		
		ItemStack er = new ItemStack(Material.TNT);
		ItemMeta m = er.getItemMeta();
		m.setDisplayName("§4Clear Inventory");
		er.setItemMeta(m);
		
		if(e.getInventory().contains(er)) {
			
			if(e.getSlot() == e.getRawSlot()) {
				
				e.setCancelled(true);
			
		}
		}
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		if(e.getInventory().getName().equalsIgnoreCase(AdminGUI.getPrefix())) {
			
			if(e.getCurrentItem().getType() == Material.SKULL_ITEM) {
				
				e.getWhoClicked().closeInventory();
				e.getWhoClicked().openInventory(getSettingInv((Bukkit.getPlayer(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())))));
				
			}
			
		}
 		
	}
	
	@EventHandler
	public void onClicked(InventoryClickEvent e) {
		
		if(e.getCurrentItem().hasItemMeta()) {
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Ban Player")) {
				
				Player targ = Bukkit.getPlayer(ChatColor.stripColor(e.getInventory().getName()));
				Player p = (Player) e.getWhoClicked();
				p.performCommand(AdminGUI.getInstance().getConfig().getString("ban-command").replace("%player%", targ.getName()).replace("%ban-message%", AdminGUI.getInstance().getConfig().getString("ban-message")));
				e.getWhoClicked().sendMessage(AdminGUI.getPrefix() + " Succesfully banned " + targ.getName());
				
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cKick Player")) {
				
				Player targ = Bukkit.getPlayer(ChatColor.stripColor(e.getInventory().getName()));
				Player p = (Player) e.getWhoClicked();
				p.performCommand(ChatColor.translateAlternateColorCodes('&',AdminGUI.getInstance().getConfig().getString("kick-command").replace("%player%", targ.getName()).replace("%kick-message%", AdminGUI.getInstance().getConfig().getString("kick-message"))));
				e.getWhoClicked().sendMessage(AdminGUI.getPrefix() + " Succesfully kicked " + targ.getName());
				
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Mute Player")) {
				
				Player targ = Bukkit.getPlayer(ChatColor.stripColor(e.getInventory().getName()));
				Player p = (Player) e.getWhoClicked();
				p.performCommand(ChatColor.translateAlternateColorCodes('&',AdminGUI.getInstance().getConfig().getString("mute-command").replace("%player%", targ.getName()).replace("%reason%", AdminGUI.getInstance().getConfig().getString("mute-reason"))));
				e.getWhoClicked().sendMessage(AdminGUI.getPrefix() + " Succesfully muted " + targ.getName());
				
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§dTp to Player")) {
				
				Player targ = Bukkit.getPlayer(ChatColor.stripColor(e.getInventory().getName()));
				Player p = (Player) e.getWhoClicked();
				p.performCommand(ChatColor.translateAlternateColorCodes('&',AdminGUI.getInstance().getConfig().getString("tp-command").replace("%player%", p.getName()).replace("%target%", targ.getName())));
				
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Tp Player to you")) {
				
				Player targ = Bukkit.getPlayer(ChatColor.stripColor(e.getInventory().getName()));
				Player p = (Player) e.getWhoClicked();
				p.performCommand(ChatColor.translateAlternateColorCodes('&',AdminGUI.getInstance().getConfig().getString("tphere-command").replace("%player%", targ.getName())));
				
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Clear Inventory")) {
				
				Bukkit.getPlayer(ChatColor.stripColor(e.getInventory().getName())).getInventory().clear();;
				
			}  else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Close Inventory")) {
				
				e.getWhoClicked().closeInventory();
				
			} 
			 
		}
		
	}
	
	public static Inventory getInventory(Player sender) {
		
		int size = 0;
		
		if(Bukkit.getOnlinePlayers().size() < 9) size = 1;
		if(Bukkit.getOnlinePlayers().size() <= 9) size = 1;
		if(Bukkit.getOnlinePlayers().size() >= 9*2) size = 2;
		if(Bukkit.getOnlinePlayers().size() >= 9*3) size = 3;
		if(Bukkit.getOnlinePlayers().size() >= 9*4) size = 4;
		if(Bukkit.getOnlinePlayers().size() >= 9*5) size = 5;
		if(Bukkit.getOnlinePlayers().size() >= 9*6) size = 6;
		
		Inventory inv = Bukkit.createInventory(null, size*9,AdminGUI.getPrefix());
		
		//int slot = 0;
		
		for(Player p : Bukkit.getOnlinePlayers()) {
		 
			if(p != sender) {
			if(!p.hasPermission("admingui.bypass")) {
			
			ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(byte)3);
			SkullMeta sm = (SkullMeta) skull.getItemMeta();
			
			sm.setDisplayName("§6" + p.getName());
			sm.setOwner(p.getName());
			skull.setItemMeta(sm);
			
			inv.addItem(skull);
			}
			}
			//slot++;
			
		}
		
		return inv;
	}
	
	public static Inventory getSettingInv(Player p) {
		
		Inventory si = Bukkit.createInventory(null, 9, "§6" + p.getName());
		
		ItemStack ph = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte) 15);
		ItemMeta phme = ph.getItemMeta();
		
		phme.setDisplayName("§0§kggg");
		ph.setItemMeta(phme);
		
		int slot = 0;
		
		for(int i = 0;i < si.getSize();i++) {
			
			si.setItem(slot, ph);
			slot++;
			
		}
		
		/**/
		ItemStack ban = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta bme = ban.getItemMeta();
		
		bme.setDisplayName("§4Ban Player");
		ban.setItemMeta(bme);
		/**/
		ItemStack kick = new ItemStack(Material.FEATHER);
		ItemMeta kme = kick.getItemMeta();
		
		kme.setDisplayName("§cKick Player");
		kick.setItemMeta(kme);
		/**/
		ItemStack mute = new ItemStack(Material.BARRIER);
		ItemMeta mme = mute.getItemMeta();
		
		mme.setDisplayName("§6Mute Player");
		mute.setItemMeta(mme);
		/**/
		ItemStack tpto = new ItemStack(Material.ENDER_PEARL);
		ItemMeta tme = tpto.getItemMeta();
		
		tme.setDisplayName("§dTp to Player");
		tpto.setItemMeta(tme);
		/**/
		ItemStack tphere = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta tpme = tphere.getItemMeta();
		
		tpme.setDisplayName("§5Tp Player to you");
		tphere.setItemMeta(tpme);
		/**/
		ItemStack clear = new ItemStack(Material.TNT);
		ItemMeta cme = clear.getItemMeta();
		
		cme.setDisplayName("§4Clear Inventory");
		clear.setItemMeta(cme);
		
		ItemStack close = new ItemStack(Material.WOOL,1,(byte)14);
		ItemMeta clme = close.getItemMeta();
		
		clme.setDisplayName("§6Close Inventory");
		close.setItemMeta(clme);
		
		si.setItem(0, ban);
		si.setItem(1, kick);
		si.setItem(3, mute);
		si.setItem(5, tpto);
		si.setItem(6, tphere);
		si.setItem(7, clear);
		si.setItem(8, close);
		
		return si;
	}
	
}
