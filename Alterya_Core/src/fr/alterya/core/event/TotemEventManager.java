package fr.alterya.core.event;

import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.MainCore;
import fr.alterya.core.command.CmdFurnace;

public class TotemEventManager extends BukkitRunnable
{
	private MainCore m;
	
	private TotemEvent totemE = new TotemEvent(m, this);
	
	public int timerTotemEvent = 0;
	//6hrs = 21 600 sec
	private final int timerLimitTotemEvent = 21600;
	
	public TotemEventManager(MainCore main) {
		m = main;
	}

	public void restartTimerTotem() {
		runTaskTimer(CmdFurnace.mainCore, 0, 20);
	}
	
	@Override
	public void run()
	{
		if(timerTotemEvent >= timerLimitTotemEvent) {
			totemE.startEvent();
			totemE.run();
			cancel();
			timerTotemEvent = 0;
		}
		timerTotemEvent ++;
	}
}
