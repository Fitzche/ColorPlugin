package fr.fitzche.uhc_color;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import PlayerColorboard.Colorboard;
import fr.fitzche.uhc_color.Gui.PlayerGui;
import fr.fitzche.uhc_color.Gui.chooseColorGui;

public class Color implements CommandExecutor {

	@Deprecated
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (args.length==0) {
			Player player = (Player) sender;
			PlayerGui gui = new PlayerGui(Main.players, player);
			gui.open();
			return true;
		}
		if (Main.getPlayer(args[0]) == null) {
			
			System.out.println("joueur null");
			return true;
		} 
		
		Colorboard board;
		
		Player player = (Player) sender;
		if (Main.getPlayerBoard(player)==null) {
			board = new Colorboard(player);
			Main.colors.add(board);
		} else {
			board = Main.getPlayerBoard(player);
		}
		
		chooseColorGui gui = new chooseColorGui(board, Main.getPlayer(args[0]));
		player.openInventory(gui.inv);
		
		
		return false;
	}

}
