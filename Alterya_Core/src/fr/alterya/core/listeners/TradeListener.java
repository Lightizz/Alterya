package fr.alterya.core.listeners;

import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.MainCore;
import fr.alterya.core.Trade;

public class TradeListener implements Listener
{
	public MainCore m;
	
	public TradeListener(MainCore main) {
		m = main;
	}
	
	 @EventHandler
	  public void onInventoryDrag(InventoryDragEvent e) {
	    if (e.getWhoClicked() instanceof Player) {
	      Player p = (Player)e.getWhoClicked();
	      Trade trade = null;
	      if (m.tradeUtil.getTradeFromAccepter(p) != null) {
	        trade = m.tradeUtil.getTradeFromAccepter(p);
	      }
	      if (m.tradeUtil.getTradeFromRequester(p) != null) {
	        trade = m.tradeUtil.getTradeFromRequester(p);
	      }
	      if (trade != null && trade.hasTradeWindowOpen(p)) {
	        for (Iterator<Integer> iterator = e.getRawSlots().iterator(); iterator.hasNext(); ) { int rawSlot = ((Integer)iterator.next()).intValue();
	          if (m.canPlaceItem(p, rawSlot) && !trade.isCountdownInProgress()) {
	            final Trade finalTrade = trade;
	            (new BukkitRunnable()
	              {
	                public void run() {
	                  finalTrade.updateOpenTrade();
	                  m.tradeUtil.addTrade(finalTrade);
	                }
	              }).runTaskLater((Plugin)this, 1L); continue;
	          } 
	          e.setCancelled(true); }
	      
	      }
	    } 
	  }
	  
	  @EventHandler
	  public void onEntityDamage(EntityDamageEvent e) {
	    if (e.getEntity() instanceof Player) {
	      Player p = (Player)e.getEntity();
	      Trade trade = null;
	      if (m.tradeUtil.getTradeFromAccepter(p) != null) {
	        trade = m.tradeUtil.getTradeFromAccepter(p);
	      }
	      if (m.tradeUtil.getTradeFromRequester(p) != null) {
	        trade = m.tradeUtil.getTradeFromRequester(p);
	      }
	      if (trade != null) {
	        trade.cancelTrade(true);
	      }
	    } 
	  }
	  
	  @EventHandler
	  public void onInventoryClick(InventoryClickEvent e) {
	    if (e.getWhoClicked() instanceof Player) {
	      Player p = (Player)e.getWhoClicked();
	      Trade trade = null;
	      boolean isRequester = false;
	      if (m.tradeUtil.getTradeFromAccepter(p) != null) {
	        trade = m.tradeUtil.getTradeFromAccepter(p);
	        isRequester = false;
	      } 
	      if (m.tradeUtil.getTradeFromRequester(p) != null) {
	        trade = m.tradeUtil.getTradeFromRequester(p);
	        isRequester = true;
	      } 
	      if (trade != null && trade.hasTradeWindowOpen(p)) {
	        if (e.getClick() == ClickType.LEFT || e.getClick() == ClickType.RIGHT) {
	          if (m.canPlaceItem(p, e.getRawSlot()) && !trade.isCountdownInProgress()) {
	            final Trade finalTrade = trade;
	            (new BukkitRunnable()
	              {
	                public void run() {
	                  finalTrade.updateOpenTrade();
	                  m.tradeUtil.addTrade(finalTrade);
	                }
	              }).runTaskLater(m, 1L);
	          } else {
	            if (e.getRawSlot() == 27) {
	              if (isRequester) {
	                trade.setRequesterReady(true);
	                m.tradeUtil.addTrade(trade);
	              } else {
	                trade.setAccepterReady(true);
	                m.tradeUtil.addTrade(trade);
	              } 
	            } else if (e.getRawSlot() == 28) {
	              trade.cancelTrade(true);
	            } 
	            e.setCancelled(true);
	          } 
	        } else {
	          e.setCancelled(true);
	        } 
	      }
	    } 
	  }
	  
	  @EventHandler
	  public void onInventoryClose(InventoryCloseEvent e) {
	    if (e.getPlayer() instanceof Player) {
	      Player p = (Player)e.getPlayer();
	      Trade trade = null;
	      if (m.tradeUtil.getTradeFromAccepter(p) != null) {
	        trade = m.tradeUtil.getTradeFromAccepter(p);
	      }
	      if (m.tradeUtil.getTradeFromRequester(p) != null) {
	        trade = m.tradeUtil.getTradeFromRequester(p);
	      }
	      if (trade != null && trade.hasTradeWindowOpen(p) && !trade.isCancelled()) {
	        trade.cancelTrade(true);
	      }
	    } 
	  }
}
