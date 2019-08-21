package fr.alterya.core.money;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.alterya.core.Main;
import fr.alterya.core.util.FileWriter;

public class MoneyManager
{			
	Plugin plugin;
	private FileWriter fw;
	
	public static double defaultMoney = 50.00;
	
	public MoneyManager(String uuid) {
		fw = new FileWriter("plugins/AccountsData", uuid.toString() + ".yml");
	}
	
	@SuppressWarnings("unused")
	public void setMoney(String uuid, double moneyToSet) {
		if(moneyBankExist(uuid) == false) {
			this.createMoneyBank(uuid, defaultMoney);
		}
		double oldMoney = fw.getDouble(uuid);
		double newMoney = moneyToSet;
		oldMoney = newMoney;
		fw.setValue(uuid, newMoney);
		fw.saveConfig();
	}
	
	public void addMoney(String uuid, double moneyToAdd) {
		if(moneyBankExist(uuid) == false) {
			this.createMoneyBank(uuid, defaultMoney);
		}
		double oldMoney = fw.getDouble(uuid);
		double newMoney = oldMoney + moneyToAdd;
		fw.setValue(uuid, newMoney);
		fw.saveConfig();
	}
	
	public void substractMoney(String uuid, double moneyToSubstract) {
		if(moneyBankExist(uuid) == false) {
			this.createMoneyBank(uuid, defaultMoney);
		}
		double oldMoney = fw.getDouble(uuid);
		double newMoney = oldMoney - moneyToSubstract;
		fw.setValue(uuid, newMoney);
		fw.saveConfig();
	}
	
	public void purgeMoney(String uuid) {
		if(moneyBankExist(uuid) == false) {
			this.createMoneyBank(uuid, defaultMoney);
		}
		fw.setValue(uuid, defaultMoney);
		fw.saveConfig();
	}
	
	public void createMoneyBank(String uuid, double defaultMoney0) {
		defaultMoney = defaultMoney0;
		fw.setValue(uuid, defaultMoney0);
		fw.saveConfig();
	}
	
	public boolean moneyBankExist(String uuid) {
		fw.saveConfig();
		return fw.getString(uuid) != null;
	}
	
	public String[] getMoneyTop(Player player){
		if(this.getMoneyBanks() != null) {
			String[] accountList = (String[]) this.getMoneyBanks().toArray();
			return accountList;
		}
		player.sendMessage(Main.prefix + "§4Un problème est survenu, veuillez contacter un Administrateur ou un Développeur !");
		return null;
	}
	
	public Set<String> getMoneyBanks(){
		fw.saveConfig();
		return fw.getKeys(false);
	}
	
	public double getMoney(String uuid) {
		if(moneyBankExist(uuid) == false) {
			this.createMoneyBank(uuid, 50.00);
		}
		fw.saveConfig();
		return fw.getDouble(uuid);
	}
}
