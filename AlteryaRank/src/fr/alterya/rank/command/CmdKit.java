package fr.alterya.rank.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.rank.Main;
import fr.alterya.rank.Rank;
import fr.alterya.rank.RankList;
/*
Author : Lightiz
*/
public class CmdKit extends BukkitRunnable implements CommandExecutor
{
	public int timer = 0;
	
	Player player0;
	
	Main main;
	
	Rank rank;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args) {
		Player player = (Player) sender;
		player = player0;
		if(command.getName().equalsIgnoreCase("kit")) {
			if(rank.getPlayerRank(player) == RankList.MEMOIRE) {
				if(this.timer != 0) {
					int timeRemaiting = 172800 - timer;
					player.sendMessage(Main.prefix + "Il vous reste " + timeRemaiting + "sec avant de pouvoir re-utiliser le /kit !");
				}else if(this.timer == 0) {
					giveMemoireKit(player);
					player.sendMessage(Main.prefix + "Voici votre kit du grade Mémoire !");
					this.runTaskTimer(main, 20, 20);
					return true;
				}
			}else if(rank.getPlayerRank(player) == RankList.SAGE) {
				if(this.timer != 0) {
					int timeRemaiting = 259200 - timer;
					player.sendMessage(Main.prefix + "Il vous reste " + timeRemaiting + "sec avant de pouvoir re-utiliser le /kit !");
				}else if(this.timer == 0) {
					giveSageKit(player);
					player.sendMessage(Main.prefix + "Voici votre kit du grade Sage !");
					this.runTaskTimer(main, 20, 20);
					return true;
				}
			}else if(rank.getPlayerRank(player) == RankList.SOUVENIR) {
				if(this.timer != 0) {
					int timeRemaiting = 172800 - timer;
					player.sendMessage(Main.prefix + "Il vous reste " + timeRemaiting + "sec avant de pouvoir re-utiliser le /kit !");
				}else if(this.timer == 0) {
					giveSouvenirKit(player);
					player.sendMessage(Main.prefix + "Voici votre kit du grade Souvenir !");
					this.runTaskTimer(main, 20, 20);
					return true;
				}	
			}else {
				player.sendMessage(Main.prefix + "Vous n'avez aucun grade permettant le /kit, vous ne pouvez pas reçevoir de kit !");
				return true;
			}
		}
		return false;
	}
	
	public void giveSageKit(Player player) {
		player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
		player.updateInventory();
	}
	
	public void giveMemoireKit(Player player) {
		player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
		player.updateInventory();
	}

	public void giveSouvenirKit(Player player) {
		player.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
		player.updateInventory();
	}

	@Override
	public void run()
	{
		//Dev note : 1h = 3 600 sec ; 24h = 86 400 sec ; 48h = 172 800 sec ; 72h = 259 200 sec
		if(rank.getPlayerRank(player0) == RankList.MEMOIRE && timer >= 172800) {
			cancel();
			timer = 0;
		}else if(rank.getPlayerRank(player0) == RankList.SAGE && timer >= 259200) {
			cancel();
			timer = 0;
		}else if(rank.getPlayerRank(player0) == RankList.SOUVENIR && timer >= 172800) {
			cancel();
			timer = 0;
		}
		timer ++;
	}
}
