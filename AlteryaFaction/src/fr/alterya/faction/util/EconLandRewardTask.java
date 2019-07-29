package fr.alterya.faction.util;

import fr.alterya.faction.Conf;
import fr.alterya.faction.Factions;
import fr.alterya.faction.Main;

public class EconLandRewardTask implements Runnable {

	double rate;
	
	public EconLandRewardTask()
	{
		this.rate = Conf.econLandRewardTaskRunsEveryXMinutes;
	}

	@Override
	public void run()
	{
		Factions.i.econLandRewardRoutine();
		// maybe setting has been changed? if so, restart task at new rate
		if (this.rate != Conf.econLandRewardTaskRunsEveryXMinutes)
			Main.main.startEconLandRewardTask(true);
	}

}
