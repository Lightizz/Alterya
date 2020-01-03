package fr.alterya.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Trade
{
  MainCore plugin;
  Player tradeRequester = null;
  Player tradeAccepter = null;
  
  Inventory requesterInventory = null;
  Inventory accepterInventory = null;
  
  ItemStack[] requesterTradeRequestItems = new ItemStack[0];
  ItemStack[] accepterTradeRequestItems = new ItemStack[0];
  
  boolean countdownInProgress = false;
  
  boolean tradeAccepted = false;
  boolean requesterReady = false;
  boolean accepterReady = false;
  boolean tradeReady = false;
  boolean cancelled = false;
  int countdown = -1;
  
  public Trade(MainCore plugin, Player tradeRequester, Player tradeAccepter) {
    this.plugin = plugin;
    this.tradeRequester = tradeRequester;
    this.tradeAccepter = tradeAccepter;
    tradeRequester.sendMessage(ChatColor.AQUA + "MainCore> " + ChatColor.GRAY + "Requête d'échange envoyée au joueur " + ChatColor.AQUA + tradeAccepter.getName());
    tradeAccepter.sendMessage(ChatColor.AQUA + "MainCore> " + ChatColor.GRAY + "Le joueur " + ChatColor.AQUA + tradeRequester.getName() + ChatColor.GRAY + " veut faire un échange avec vous. Vous avez 15 secondes pour accepter.");
    startTimeOutCounter();
  }
  
  public void openTrade() {
    if (!isCancelled()) {
      if (this.tradeRequester.isOnline() && this.tradeAccepter.isOnline() && this.tradeRequester.getLocation().distance(this.tradeAccepter.getLocation()) <= 10.0D) {
        String invName = "|Vous|               |Autre|";
        Inventory inv = Bukkit.createInventory(null, 36, invName);
        ItemStack divider = MainCore.getItem(Material.IRON_FENCE, 1, 0, "§r", new String[0]);
        ItemStack declineTrade = MainCore.getItem(Material.WOOL, 1, 14, ChatColor.RED + "Clickez pour annuler l'échange", new String[] { ChatColor.GRAY + "Cliquez sur ce bloc à tout moment", ChatColor.GRAY + "temps pendant l'échange pour annuler" });
        ItemStack acceptTrade = MainCore.getItem(Material.WOOL, 1, 5, ChatColor.GREEN + "Cliquez pour échange prêt", new String[] { ChatColor.GRAY + "Une fois que les deux échangeurs ont cliqué", ChatColor.GRAY + "ce bloc, le compte à rebours sera", ChatColor.GRAY + "commence" });
        ItemStack readyOrNot = MainCore.getItem(Material.INK_SACK, 1, 8, ChatColor.GRAY + "Pas prêt", new String[0]);
        inv.setItem(4, divider);
        inv.setItem(13, divider);
        inv.setItem(22, divider);
        inv.setItem(31, divider);
        inv.setItem(27, acceptTrade);
        inv.setItem(28, declineTrade);
        inv.setItem(30, readyOrNot);
        inv.setItem(35, readyOrNot);
        this.requesterInventory = Bukkit.createInventory(null, 36, invName);
        this.requesterInventory.setContents(inv.getContents());
        this.accepterInventory = Bukkit.createInventory(null, 36, invName);
        this.accepterInventory.setContents(inv.getContents());
        this.tradeRequester.openInventory(this.requesterInventory);
        this.tradeAccepter.openInventory(this.accepterInventory);
      } else {
        cancelTrade(true);
      } 
    }
  }
  
  public void closeTrade() {
    @SuppressWarnings("unused")
	ItemStack[] empty = new ItemStack[0];
    if (this.tradeRequester.isOnline() && this.tradeRequester.getOpenInventory() != null && 
      this.tradeRequester.getOpenInventory().getTopInventory() != null) {
      this.tradeRequester.closeInventory();
      this.tradeRequester.updateInventory();
    } 
    
    if (this.tradeAccepter.isOnline() && this.tradeAccepter.getOpenInventory() != null && 
      this.tradeAccepter.getOpenInventory().getTopInventory() != null) {
      this.tradeAccepter.closeInventory();
      this.tradeAccepter.updateInventory();
    } 
  }


  
  public void startTimeOutCounter() { (new BukkitRunnable() {
        int seconds = 0;
        
        public void run() {
          if (!Trade.this.isCancelled()) {
            if (this.seconds < 15) {
              if (!Trade.this.tradeRequester.isOnline() || !Trade.this.tradeAccepter.isOnline() || !Trade.this.tradeRequester.getWorld().getName().equalsIgnoreCase(Trade.this.tradeAccepter.getWorld().getName()) || Trade.this.tradeRequester.getLocation().distance(Trade.this.tradeAccepter.getLocation()) > 10.0D) {
                Trade.this.cancelTrade(true);
                cancel();
              } 
              if (Trade.this.isTradeAccepted()) {
                cancel();
              }
            } else {
              Trade.this.cancelTrade(true);
              cancel();
            } 
            this.seconds++;
          } else {
            cancel();
          } 
        }
      }).runTaskTimer((Plugin)this.plugin, 20L, 20L); }

  
  public void startReadyCounter() {
    this.countdownInProgress = true;
    this.countdown = this.plugin.getConfig().getInt("CountdownLength");
    (new BukkitRunnable() {
        int seconds = 0;
        
        public void run() {
          if (!Trade.this.isCancelled()) {
            Trade.this.updateOpenTrade();
            if (this.seconds < 5) {
              if (!Trade.this.tradeRequester.isOnline() || !Trade.this.tradeAccepter.isOnline() || !Trade.this.hasTradeWindowOpen(Trade.this.tradeRequester) || !Trade.this.hasTradeWindowOpen(Trade.this.tradeAccepter)) {
                Trade.this.cancelTrade(true);
                cancel();
              } 
            } else {
              Trade.this.cancelTrade(false);
              cancel();
            } 
            Trade.this.countdown--;
            this.seconds++;
          } else {
            cancel();
          } 
        }
      }).runTaskTimer((Plugin)this.plugin, 20L, 20L);
  }

  
  public boolean isCountdownInProgress() { return this.countdownInProgress; }

  
  public void giveItemsFromTrade() {
    ItemStack[] empty = new ItemStack[0];
    this.tradeRequester.getInventory().addItem(getAccepterTradeRequestItems());
    setAccepterTradeRequestItems(empty);
    this.tradeAccepter.getInventory().addItem(getRequesterTradeRequestItems());
    setRequesterTradeRequestItems(empty);
  }
  
  public void returnItems() {
    ItemStack[] empty = new ItemStack[0];
    this.tradeRequester.getInventory().addItem(getRequesterTradeRequestItems());
    setRequesterTradeRequestItems(empty);
    this.tradeAccepter.getInventory().addItem(getAccepterTradeRequestItems());
    setAccepterTradeRequestItems(empty);
  }
  
  public boolean hasTradeWindowOpen(Player p) {
    boolean open = false;
    if (p.getOpenInventory() != null) {
      Inventory inv = p.getOpenInventory().getTopInventory();
      if (inv.getSize() == 36 && inv.getTitle().contains("|Vous|               |Autre|")) {
        open = true;
      }
    } 
    return open;
  }
  
  public void updateOpenTrade() {
    if (!isCancelled()) {
      if (this.tradeRequester.isOnline() && this.tradeAccepter.isOnline() && this.tradeRequester.getLocation().distance(this.tradeAccepter.getLocation()) <= 10.0D) {
        setRequesterTradeRequestItems(plugin.tradeUtil.getItemsRequester(getRequester()));
        setAccepterTradeRequestItems(plugin.tradeUtil.getItemsAccepter(getAccepter()));
        String invName = "|Vous|               |Autre|";
        Inventory inv = Bukkit.createInventory(null, 36, invName);
        int dividerCountdown = 1;
        if (this.countdown > 0) {
          dividerCountdown = this.countdown;
        }
        ItemStack divider = MainCore.getItem(Material.IRON_FENCE, dividerCountdown, 0, "§r", new String[0]);
        ItemStack declineTrade = MainCore.getItem(Material.WOOL, 1, 14, ChatColor.RED + "Clickez pour annuler", new String[] { ChatColor.GRAY + "Cliquez sur ce bloc à tout moment", ChatColor.GRAY + "moment pendant l'échange pour annuler" });
        ItemStack acceptTrade = MainCore.getItem(Material.WOOL, 1, 5, ChatColor.GREEN + "Clickez pour être prêt", new String[] { ChatColor.GRAY + "Une fois que les deux échangeurs ont cliqué", ChatColor.GRAY + "ce bloc, le compte à rebours devrait", ChatColor.GRAY + "commence" });
        ItemStack ready = MainCore.getItem(Material.INK_SACK, 1, 10, ChatColor.GREEN + "Prêt", new String[0]);
        ItemStack notReady = MainCore.getItem(Material.INK_SACK, 1, 8, ChatColor.GRAY + "Pas prêt", new String[0]);
        inv.setItem(4, divider);
        inv.setItem(13, divider);
        inv.setItem(22, divider);
        inv.setItem(31, divider);
        inv.setItem(27, acceptTrade);
        inv.setItem(28, declineTrade);
        inv.setItem(30, notReady);
        inv.setItem(35, notReady);
        Inventory requesterInventory = Bukkit.createInventory(null, 36, invName);
        requesterInventory.setContents(inv.getContents());
        requesterInventory = this.plugin.tradeUtil.setItemsLeft(requesterInventory, getRequesterTradeRequestItems());
        requesterInventory = this.plugin.tradeUtil.setItemsRight(requesterInventory, getAccepterTradeRequestItems());
        Inventory accepterInventory = Bukkit.createInventory(null, 36, invName);
        accepterInventory.setContents(inv.getContents());
        accepterInventory = this.plugin.tradeUtil.setItemsLeft(accepterInventory, getAccepterTradeRequestItems());
        accepterInventory = this.plugin.tradeUtil.setItemsRight(accepterInventory, getRequesterTradeRequestItems());
        if (isRequesterReady()) {
          requesterInventory.setItem(30, ready);
          accepterInventory.setItem(35, ready);
        } 
        if (isAccepterReady()) {
          requesterInventory.setItem(35, ready);
          accepterInventory.setItem(30, ready);
        } 
        this.tradeRequester.getOpenInventory().getTopInventory().setContents(requesterInventory.getContents());
        this.tradeAccepter.getOpenInventory().getTopInventory().setContents(accepterInventory.getContents());
      } else {
        cancelTrade(true);
      } 
    }
  }

  
  public void setRequesterTradeRequestItems(ItemStack[] items) { this.requesterTradeRequestItems = items; }


  
  public ItemStack[] getRequesterTradeRequestItems() { return this.requesterTradeRequestItems; }


  
  public void setAccepterTradeRequestItems(ItemStack[] items) { this.accepterTradeRequestItems = items; }


  
  public ItemStack[] getAccepterTradeRequestItems() { return this.accepterTradeRequestItems; }

  
  public void setTradeAccepted(boolean bool) {
    this.tradeRequester.sendMessage(ChatColor.AQUA + "MainCore> " + ChatColor.GRAY + "L'échange a été accepté.");
    this.tradeAccepter.sendMessage(ChatColor.AQUA + "MainCore> " + ChatColor.GRAY + "L'échange a été accepté.");
    openTrade();
    this.tradeAccepted = bool;
  }

  
  public boolean isTradeAccepted() { return this.tradeAccepted; }

  
  public void setRequesterReady(boolean bool) {
    this.requesterReady = bool;
    updateOpenTrade();
    if (isRequesterReady() && isAccepterReady() && !isCountdownInProgress()) {
      startReadyCounter();
    }
  }

  
  public boolean isRequesterReady() { return this.requesterReady; }

  
  public void setAccepterReady(boolean bool) {
    this.accepterReady = bool;
    updateOpenTrade();
    if (isRequesterReady() && isAccepterReady() && !isCountdownInProgress()) {
      startReadyCounter();
    }
  }

  
  public boolean isAccepterReady() { return this.accepterReady; }


  
  public void setTradeReady(boolean bool) { this.tradeReady = bool; }


  
  public boolean isTradeReady() { return this.tradeReady; }

  
  public void cancelTrade(boolean cancelled) {
    this.cancelled = true;
    if (cancelled) {
      returnItems();
      closeTrade();
      if (this.tradeRequester.isOnline()) {
        this.tradeRequester.sendMessage(ChatColor.AQUA + "MainCore> " + ChatColor.GRAY + "L'échange a été annulé.");
      }
      if (this.tradeAccepter.isOnline()) {
        this.tradeAccepter.sendMessage(ChatColor.AQUA + "MainCore> " + ChatColor.GRAY + "L'échange a été annulé.");
      }
    } else {
      giveItemsFromTrade();
      closeTrade();
      if (this.tradeRequester.isOnline()) {
        this.tradeRequester.sendMessage(ChatColor.AQUA + "MainCore> " + ChatColor.GRAY + "Échange terminé.");
      }
      if (this.tradeAccepter.isOnline()) {
        this.tradeAccepter.sendMessage(ChatColor.AQUA + "MainCore> " + ChatColor.GRAY + "Échange terminé.");
      }
    } 
    this.countdownInProgress = false;
    this.plugin.tradeUtil.removeTrade(this);
  }

  
  public boolean isCancelled() { return this.cancelled; }


  
  public Player getRequester() { return this.tradeRequester; }


  
  public Player getAccepter() { return this.tradeAccepter; }
}
