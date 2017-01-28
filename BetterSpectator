package your.package.path;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class BetterSpectator {

	private static Scoreboard board;
	private static Team team;
	private static PotionEffect effect;
	private static HashMap<Player, Boolean> specs;
	
	public BetterSpectator(HashMap<Player, Boolean> spectatorMap) {
		board = Bukkit.getScoreboardManager().getMainScoreboard();
		if(board.getTeam("spectator") == null) {
			team = board.getTeam("spectator");
		} else {
			team = board.registerNewTeam("spectator");
		}
		specs = spectatorMap;
		effect = new PotionEffect(PotionEffectType.INVISIBILITY, 9999999, 1);
		//OPTIONS
		team.setCanSeeFriendlyInvisibles(true);
		team.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
	}
	
	public static void setSpactator(Player player) {
		for(Player online : Bukkit.getOnlinePlayers()) {
			online.hidePlayer(player);
			getTeam().addEntry(player.getName());
			player.addPotionEffect(effect, true);
		}
	}
	
	public static void unsetSpactator(Player player) {
		for(Player online : Bukkit.getOnlinePlayers()) {
			online.showPlayer(player);
			getTeam().removeEntry(player.getName());
			if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
				player.removePotionEffect(PotionEffectType.INVISIBILITY);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void clearSpectator() {
		for(OfflinePlayer online : getTeam().getPlayers()) {
			getTeam().removePlayer(online);
		}
		for(Player online : Bukkit.getOnlinePlayers()) {
			for(Player other : Bukkit.getOnlinePlayers()) {
				if(online != other) online.showPlayer(other);
				getTeam().removeEntry(online.getName());
				if(online.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
					online.removePotionEffect(PotionEffectType.INVISIBILITY);
				}
			}
		}
	}
	
	private static Scoreboard getBoard() {
		return board;
	}
	
	private static HashMap<Player, Boolean> getSpecs() {
		return specs;
	}
	
	private static Team getTeam() {
		return team;
	}
	
}
