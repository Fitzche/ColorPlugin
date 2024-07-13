package fr.fitzche.uhc_color;

import java.util.ArrayList;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import PlayerColorboard.Colorboard;

public class Main extends JavaPlugin implements Listener{
	
	public static ArrayList<Player> players;
	public static Server serv;
	public static ArrayList<Colorboard> colors;
	public static JavaPlugin plug;
	
	@Override
	public void onEnable() {
		Main.plug = this;
		super.onEnable();
		getCommand("color").setExecutor(new Color());
		this.getServer().getPluginManager().registerEvents(this, this);
		Main.serv = this.getServer();
		Main.players = new ArrayList<Player>();
		for (Player ply:this.getServer().getOnlinePlayers()) {
			Main.players.add(ply);
		}
		
		colors = new ArrayList<Colorboard>();
		for (Player ply:Main.players) {
			colors.add(new Colorboard(ply));
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static JavaPlugin getPlugin() {
		return Main.plug;
	}
	
	public static Player getPlayer(String name) {
		for (Player ply: Main.players) {
			System.out.println(ply.getName() + "/"+ name);
			if (ply.getName().equals(name)) {
				System.out.println(ply.getName() + "/"+ name);
				return ply;
			}
		}
		
		
		System.out.println("joueur non trouvé");
		return null;
	}
	
	public static Colorboard getPlayerBoard(Player p) {
		for (Colorboard b:Main.colors) {
			if (b.player==p) {
				
				return b;
			}
		}
		
		Colorboard col = new Colorboard(p);
		Main.colors.add(col);
		
		return col;
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		System.out.println("added");
		Main.players.add(e.getPlayer());
		
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Main.players.remove(Main.getPlayer(e.getPlayer().getName()));
	}
	
}
