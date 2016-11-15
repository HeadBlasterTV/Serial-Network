package me.sn;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if(command.getName().equalsIgnoreCase("admin")) {
				
				if(p.hasPermission("admingui.gui")) {
					
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 1);
					p.openInventory(AdminGUIListener.getInventory(p));
					
				} else p.sendMessage(AdminGUI.getPrefix() + " You don't have the permission to execute that command!");
				
			}
			
		}
		
		return true;
	}
	
}
