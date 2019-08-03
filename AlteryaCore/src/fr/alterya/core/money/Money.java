package fr.alterya.core.money;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/*
Author and resp of the money: Lightiz
*/

public class Money
{
	public double money = 10.00;
	
	public double getPlayerMoney(String playerUUID) {
		return this.money;
	}
	
	public void setPlayerMoney(String playerUUID, double moneySet) {
		this.money = moneySet;
	}
	
	public void addPlayerMoney(String playerUUID, double moneyAdded) {
		this.money =+ moneyAdded;
	}
	
	@SuppressWarnings("unused")
	public void transfertPlayerMoney(String playerSenderUUID, String playerReceiverUUID, double moneyToTransfert) {
		Player sender = Bukkit.getServer().getPlayer(playerSenderUUID);
		Player receiver = Bukkit.getServer().getPlayer(playerReceiverUUID);
		
		double senderMoney = this.getPlayerMoney(playerSenderUUID);
		double receiverMoney = this.getPlayerMoney(playerReceiverUUID);
		
		 senderMoney =- moneyToTransfert;
		 receiverMoney =+ moneyToTransfert;
		 
		 sender.sendMessage("Vous avez envoyer " + moneyToTransfert + "$ à " + receiver.getName() + " !");
		 receiver.sendMessage("Vous avez reçu " + moneyToTransfert + "$ de " + sender.getName() + " !");
	}
}
