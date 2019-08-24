package fr.alterya.factions.util;

import fr.alterya.factions.P;

import fr.alterya.factions.Conf;
import fr.alterya.factions.Factions;

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
			P.p.startEconLandRewardTask(true);
	}

}
