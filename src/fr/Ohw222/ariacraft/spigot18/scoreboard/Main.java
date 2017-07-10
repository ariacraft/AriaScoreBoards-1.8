package fr.Ohw222.ariacraft.spigot18.scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public Map<Player, ScoreboardSign> boards = new HashMap<>();
	
	@Override
	public void onEnable() {
		
		getServer().getPluginManager().registerEvents((Listener) this, this);
		saveDefaultConfig();
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		
		for(Entry<Player, ScoreboardSign> sign : boards.entrySet()) {
			sign.getValue().setLine(2, "§3Joueurs en ligne :§f"+Bukkit.getOnlinePlayers().size()+"/"+Bukkit.getMaxPlayers());
		}
		
		ScoreboardSign scoreboard = new ScoreboardSign(player, "§aAria§2§lCraft");
		scoreboard.create();
		scoreboard.setLine(0, "§7 §m======================§a ");
		scoreboard.setLine(1, "§f  §a  ");
		scoreboard.setLine(2, "§r  §b  "+this.getConfig().getString("config.type-serveur")+" §c§l"+this.getConfig().getString("config.numero")+"");
		scoreboard.setLine(3, "§r  §b  §lJoueurs : §r§f"+Bukkit.getOnlinePlayers().size()+"§7/§b"+Bukkit.getMaxPlayers());
		scoreboard.setLine(4, "§f  §e  ");
		scoreboard.setLine(5, "§7 §m======================§f ");
		scoreboard.setLine(6, "§4  §0  ");
		scoreboard.setLine(7, "§r  §b  §lVous : §f§l"+player.getDisplayName());
		scoreboard.setLine(8, "§e  §1  ");
		scoreboard.setLine(9, "§7 §m======================§8");
		scoreboard.setLine(10, "§e  §8  §a ");
		scoreboard.setLine(11, "§r  §b  §lSite : §r§f"+this.getConfig().getString("config.adresse-site"));
		scoreboard.setLine(12, "§r  §3  -> §b§o"+this.getConfig().getString("config.ip-srv"));
		scoreboard.setLine(13, "§e  §4 ");
		scoreboard.setLine(14, "§7 §m======================§e ");
		boards.put(player, scoreboard);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		
		for(Entry<Player, ScoreboardSign> sign : boards.entrySet()) {
			sign.getValue().setLine(2, "§3Joueurs en ligne :§f"+(Bukkit.getOnlinePlayers().size()-1)+"/"+Bukkit.getMaxPlayers());
		}
		
		if(boards.containsKey(player)) {
			
			boards.get(player).destroy();
			
		}
		
		
	}

}
