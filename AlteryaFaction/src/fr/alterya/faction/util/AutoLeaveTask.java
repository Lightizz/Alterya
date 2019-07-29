package fr.alterya.faction.util;

import fr.alterya.faction.Conf;
import fr.alterya.faction.Main;

public class AutoLeaveTask implements Runnable
{
	private static AutoLeaveProcessTask task;
	double rate;

	public AutoLeaveTask()
	{
		this.rate = Conf.autoLeaveRoutineRunsEveryXMinutes;
	}

	public synchronized void run()
	{
		if (task != null && ! task.isFinished())
			return;

		task = new AutoLeaveProcessTask();
		task.runTaskTimer(Main.main, 1, 1);

		// maybe setting has been changed? if so, restart this task at new rate
		if (this.rate != Conf.autoLeaveRoutineRunsEveryXMinutes)
			Main.main.startAutoLeaveTask(true);
	}
}
