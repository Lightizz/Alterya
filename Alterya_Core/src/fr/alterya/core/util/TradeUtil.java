package fr.alterya.core.util;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.alterya.core.MainCore;
import fr.alterya.core.Trade;

public class TradeUtil
{
	@SuppressWarnings("unused")
	private MainCore plugin;
	 private ArrayList<Trade> tradeList = new ArrayList<>();

	  public TradeUtil(MainCore instance) { this.plugin = instance; }

	  public ArrayList<Trade> getAllTrades() { return this.tradeList; }

	  public void removeTrade(Trade trade) {
	    ArrayList<Trade> trades = new ArrayList<>();
	    for (Trade t : this.tradeList) {
	      if (!t.getRequester().getName().equalsIgnoreCase(trade.getRequester().getName()) || !t.getAccepter().getName().equalsIgnoreCase(trade.getAccepter().getName())) {
	        trades.add(t);
	      }
	    } 
	    this.tradeList = trades;
	  }
	  
	  public void addTrade(Trade trade) {
	    removeTrade(trade);
	    this.tradeList.add(trade);
	  }
	  
	  public Trade getTradeFromRequester(Player requester) {
	    Trade trade = null;
	    for (Trade t : this.tradeList) {
	      if (t.getRequester().getName().equalsIgnoreCase(requester.getName())) {
	        trade = t;
	      }
	    } 
	    return trade;
	  }
	  
	  public Trade getTradeFromAccepter(Player accepter) {
	    Trade trade = null;
	    for (Trade t : this.tradeList) {
	      if (t.getAccepter().getName().equalsIgnoreCase(accepter.getName())) {
	        trade = t;
	      }
	    } 
	    return trade;
	  }
	  
	  public Inventory setItemsLeft(Inventory inventory, ItemStack[] itemsArray) {
	    Inventory inv = inventory;
	    ArrayList<ItemStack> items = new ArrayList<>(); byte b; int i; ItemStack[] arrayOfItemStack;
	    for (i = (arrayOfItemStack = itemsArray).length, b = 0; b < i; ) { ItemStack item = arrayOfItemStack[b];
	      items.add(item); b++; }
	    
	    int slot = 0;
	    while (items.size() > 0) {
	      if (slot != 3 && slot != 12 && slot != 21) {
	        inv.setItem(slot, items.get(0));
	        items.remove(items.get(0));
	        slot++; continue;
	      } 
	      inv.setItem(slot, items.get(0));
	      items.remove(items.get(0));
	      slot += 6;
	    } 
	    
	    return inv;
	  }
	  
	  public Inventory setItemsRight(Inventory inventory, ItemStack[] itemsArray) {
	    Inventory inv = inventory;
	    ArrayList<ItemStack> items = new ArrayList<>(); byte b; int i; ItemStack[] arrayOfItemStack;
	    for (i = (arrayOfItemStack = itemsArray).length, b = 0; b < i; ) { ItemStack item = arrayOfItemStack[b];
	      items.add(item); b++; }
	    
	    int slot = 5;
	    while (items.size() > 0) {
	      if (slot != 8 && slot != 17 && slot != 26) {
	        inv.setItem(slot, items.get(0));
	        items.remove(items.get(0));
	        slot++; continue;
	      } 
	      inv.setItem(slot, items.get(0));
	      items.remove(items.get(0));
	      slot += 6;
	    } 
	    
	    return inv;
	  }
	  
	  public ItemStack[] getItemsRequester(Player p) {
	    ArrayList<ItemStack> items = new ArrayList<>();
	    if (getTradeFromRequester(p) != null) {
	      Trade trade = getTradeFromRequester(p);
	      if (trade.hasTradeWindowOpen(p)) {
	        Inventory inv = p.getOpenInventory().getTopInventory();
	        for (int slot = 0; slot < 4; slot++) {
	          if (inv.getItem(slot) != null) {
	            items.add(inv.getItem(slot));
	          }
	        } 
	        for (int slot = 9; slot < 13; slot++) {
	          if (inv.getItem(slot) != null) {
	            items.add(inv.getItem(slot));
	          }
	        } 
	        for (int slot = 18; slot < 22; slot++) {
	          if (inv.getItem(slot) != null) {
	            items.add(inv.getItem(slot));
	          }
	        } 
	      } 
	    } 
	    return items.toArray(new ItemStack[items.size()]);
	  }
	  
	  public ItemStack[] getItemsAccepter(Player p) {
	    ArrayList<ItemStack> items = new ArrayList<>();
	    if (getTradeFromAccepter(p) != null) {
	      Trade trade = getTradeFromAccepter(p);
	      if (trade.hasTradeWindowOpen(p)) {
	        Inventory inv = p.getOpenInventory().getTopInventory();
	        for (int slot = 0; slot < 4; slot++) {
	          if (inv.getItem(slot) != null) {
	            items.add(inv.getItem(slot));
	          }
	        } 
	        for (int slot = 9; slot < 13; slot++) {
	          if (inv.getItem(slot) != null) {
	            items.add(inv.getItem(slot));
	          }
	        } 
	        for (int slot = 18; slot < 22; slot++) {
	          if (inv.getItem(slot) != null) {
	            items.add(inv.getItem(slot));
	          }
	        } 
	      } 
	    } 
	    return items.toArray(new ItemStack[items.size()]);
	  }
}
