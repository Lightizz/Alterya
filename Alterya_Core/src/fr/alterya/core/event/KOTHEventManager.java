package fr.alterya.core.event;

import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.MainCore;
import fr.alterya.core.command.CmdFurnace;

public class KOTHEventManager extends BukkitRunnable
{
	private MainCore m;
	
	private KOTHEvent kothE = new KOTHEvent(m, this);
	
	public int timerKOTHEvent = 0;
	//10hrs = 36 000 sec
	private final int timerLimitKOTHEvent = 36000;
	
	public KOTHEventManager(MainCore main) {
		m = main;
	}

	public void restartTimerTotem() {
		runTaskTimer(CmdFurnace.mainCore, 0, 20);
	}
	
	@Override
	public void run()
	{
		if(timerKOTHEvent >= timerLimitKOTHEvent) {
			kothE.startEvent();
			kothE.run();
			cancel();
			timerKOTHEvent = 0;
		}
		timerKOTHEvent ++;
	}
}
