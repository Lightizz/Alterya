package fr.alterya.factions.zcore.persist;

import fr.alterya.factions.zcore.MPlugin;

public class SaveTask implements Runnable
{
	static private boolean running = false;

	MPlugin p;
	public SaveTask(MPlugin p)
	{
		this.p = p;
	}
	
	public void run()
	{
		if ( ! p.getAutoSave() || running) return;
		running = true;
		p.preAutoSave();
		EM.saveAllToDisc();
		p.postAutoSave();
		running = false;
	}
}
