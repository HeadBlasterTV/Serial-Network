package me.sn;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminGUI extends JavaPlugin{

	@Override
	public void onEnable() {
		
		this.getCommand("admin").setExecutor(new AdminCommand());
		Bukkit.getPluginManager().registerEvents(new AdminGUIListener(), this);
		loadConfig();
		
	}
	
	public void loadConfig() {
		
		if(!new File("plugins/" + this.getDescription().getName() + "/config.yml").exists()) {
			
			getConfig().options().copyDefaults(true);
			saveConfig();
			
		}
		
	}
	
	public static AdminGUI getInstance() {
		
		return AdminGUI.getPlugin(AdminGUI.class);
		
	}
	
	public static String getPrefix() {
		
		return ChatColor.translateAlternateColorCodes('&',getInstance().getConfig().getString("prefix"));
		
	}
	
}
