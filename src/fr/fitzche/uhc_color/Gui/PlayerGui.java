package fr.fitzche.uhc_color.Gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import PlayerColorboard.Colorboard;
import fr.fitzche.uhc_color.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent.Action;

public class PlayerGui implements Listener{
	Inventory inv;
	Player ply;
	
	public PlayerGui(ArrayList<Player> players, Player ply) {
		
		Main.serv.getPluginManager().registerEvents(this, Main.getPlugin());

		
		
		this.ply = ply;
		this.inv = Bukkit.createInventory(null, 36,  "C"+ChatColor.YELLOW+"o"+ChatColor.DARK_BLUE+"l"+ChatColor.AQUA+"o"+ChatColor.GREEN+"r");
		int x = 0;
		for (Player player: players) {
			
			ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta meta = (SkullMeta) head.getItemMeta();
			
			meta.setOwner(player.getName());
			meta.setDisplayName(player.getName());
			ArrayList<String> l = new ArrayList<String>();
			l.add(ChatColor.WHITE+"De cette " +Main.getPlayerBoard(ply).getColorOfTeam(Main.getPlayerBoard(ply).getTeamOfPlayer(player)) + " COULEUR");
			meta.setLore(l);
			head.setItemMeta(meta);
			
			this.inv.setItem(x, head);
			x++;
		}
		
	}
	
	public void refresh(ArrayList<Player> players) {
		
		this.inv = Bukkit.createInventory(null, 36, "L"+ChatColor.RED+"G"+ChatColor.BLUE+ "C"+ChatColor.YELLOW+"o"+ChatColor.DARK_BLUE+"l"+ChatColor.AQUA+"o"+ChatColor.GREEN+"r");
		int x = 0;
		for (Player player: players) {
			
			ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			SkullMeta meta = (SkullMeta) head.getItemMeta();
			
			meta.setOwner(player.getName());
			meta.setDisplayName(player.getName());
			ArrayList<String> l = new ArrayList<String>();
			l.add("De cette " +Main.getPlayerBoard(ply).getColorOfTeam(Main.getPlayerBoard(ply).getTeamOfPlayer(player)) + " COULEUR");
			meta.setLore(l);
			head.setItemMeta(meta);
			
			this.inv.setItem(x, head);
			x++;
		}
	}
	
	public void open() {
		this.ply.closeInventory();
		this.ply.openInventory(this.inv);
	}
	
	@Deprecated
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		e.setCancelled(true);
		for (ItemStack item:this.inv.getContents()) {
			
			//System.out.println("test2");
			if (item != null) {
				if (e.getCurrentItem().getItemMeta().getDisplayName() == item.getItemMeta().getDisplayName()) {
				e.getWhoClicked().closeInventory();
				System.out.println("KLML");
					
					
				Player player = (Player) e.getWhoClicked();
				
				
				Colorboard board;
				if (Main.getPlayerBoard(player)==null) {
					board = new Colorboard(player);
					Main.colors.add(board);
				} else {
					board = Main.getPlayerBoard(player);
				}
				chooseColorGui gui = new chooseColorGui(board, Main.getPlayer(item.getItemMeta().getDisplayName()));
				player.openInventory(gui.inv);
				

			}
			}
		}
	}
}
