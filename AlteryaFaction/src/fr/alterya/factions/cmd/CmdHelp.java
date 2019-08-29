package fr.alterya.factions.cmd;

import java.util.ArrayList;

import fr.alterya.factions.P;
import fr.alterya.factions.integration.Econ;

import fr.alterya.factions.Conf;
import fr.alterya.factions.struct.Permission;


public class CmdHelp extends FCommand
{
	
	public CmdHelp()
	{
		super();
		this.aliases.add("help");
		this.aliases.add("h");
		this.aliases.add("?");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("page", "1");
		
		this.permission = Permission.HELP.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}	
	
	@Override
	public void perform()
	{
		if (helpPages == null) updateHelp();
		
		int page = this.argAsInt(0, 1);
		
		sendMessage(p.txt.titleize("Aide factions ("+page+"/"+helpPages.size()+")"));
		
		page -= 1;
		
		if (page < 0 || page >= helpPages.size())
		{
			msg("<b>Cette page n'existe pas.");
			return;
		}
		sendMessage(helpPages.get(page));
	}
	
	//----------------------------------------------//
	// Build the help pages
	//----------------------------------------------//
	
	public ArrayList<ArrayList<String>> helpPages;
	
	public void updateHelp()
	{
		helpPages = new ArrayList<ArrayList<String>>();
		ArrayList<String> pageLines;

		pageLines = new ArrayList<String>();
		//pageLines.add( p.cmdBase.cmdHelp.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdList.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdShow.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdPower.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdJoin.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdLeave.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdHome.getUseageTemplate(true) );
		pageLines.add( p.txt.parse("<i>Apprenez à créer une faction à la page suivante.") );
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add( p.cmdBase.cmdCreate.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdDescription.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdTag.getUseageTemplate(true) );
		pageLines.add( p.txt.parse("<i>Vous voudrez peut-être le fermer et utiliser des invitations:" ));
		pageLines.add( p.cmdBase.cmdOpen.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdInvite.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdDeinvite.getUseageTemplate(true) );
		pageLines.add( p.txt.parse("<i>Et n'oubliez pas de définir votre maison de faction :" ));
		pageLines.add( p.cmdBase.cmdSethome.getUseageTemplate(true) );
		helpPages.add(pageLines);
		
		if (Econ.isSetup() && Conf.econEnabled && Conf.bankEnabled)
		{
			pageLines = new ArrayList<String>();
			pageLines.add( p.txt.parse("<i>Votre faction a une banque qui est utilisée pour payer certains" ));
			pageLines.add( p.txt.parse("<i>choses, alors il aura besoin d'avoir l'argent déposé dans elle." ));
			pageLines.add( p.txt.parse("<i>Pour en savoir plus, utilisez la commande d'argent." ));
			pageLines.add( "" );
			pageLines.add( p.cmdBase.cmdMoney.getUseageTemplate(true) );
			pageLines.add( "" );
			pageLines.add( "" );
			pageLines.add( "" );
			helpPages.add(pageLines);
		}
		
		pageLines = new ArrayList<String>();
		pageLines.add( p.cmdBase.cmdClaim.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdAutoClaim.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdUnclaim.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdUnclaimall.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdKick.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdPromote.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdDemote.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdOfficer.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdLeader.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdTitle.getUseageTemplate(true) );
		pageLines.add( p.txt.parse("<i>Les titres de joueurs sont juste pour le plaisir. Aucune règle ne leur est connectée." ));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add( p.cmdBase.cmdMap.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdSeeChunks.getUseageTemplate(true) );
		pageLines.add(p.txt.parse("<i>Les terres revendiquées avec la propriété définie sont en outre protégées"));
		pageLines.add(p.txt.parse("<i>que seuls les propriétaires, l’administrateur de la faction et éventuellement le"));
		pageLines.add(p.txt.parse("<i>les modérateurs des factions ont un accès complet."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add( p.cmdBase.cmdDisband.getUseageTemplate(true) );
		pageLines.add("");
		pageLines.add( p.cmdBase.cmdRelationAlly.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdRelationTruce.getUseageTemplate(true) ); 
		pageLines.add( p.cmdBase.cmdRelationNeutral.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdRelationEnemy.getUseageTemplate(true) );
		pageLines.add(p.txt.parse("<i>Définissez la relation que vous souhaitez avoir avec une autre faction."));
		pageLines.add(p.txt.parse("<i>Votre relation par défaut avec les autres factions sera neutre."));
		pageLines.add(p.txt.parse("<i>Si les DEUX factions choisissent \"allié\", vous serez des alliés."));
		pageLines.add(p.txt.parse("<i>Si UNE faction choisit \"ennemi\", vous serez des ennemis."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add(p.txt.parse("<i>Vous ne pouvez jamais blesser des membres ou des alliés."));
		pageLines.add(p.txt.parse("<i>Vous ne pouvez pas blesser les neutres sur leur propre territoire."));
		pageLines.add(p.txt.parse("<i>Vous pouvez toujours blesser les ennemis et les joueurs sans faction."));
		pageLines.add("");
		pageLines.add(p.txt.parse("<i>Les dégâts causés par les ennemis sont réduits sur votre propre territoire."));
		pageLines.add(p.txt.parse("<i>Quand tu meurs tu perds le pouvoir. Il est restauré au fil du temps."));
		pageLines.add(p.txt.parse("<i>Le pouvoir d'une faction est la somme de tous les membres."));
		pageLines.add(p.txt.parse("<i>TLe pouvoir d'une faction détermine la quantité de terres qu'elle peut contenir."));
		pageLines.add(p.txt.parse("<i>Vous pouvez réclamer des terres à des factions avec trop peu de pouvoir."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add(p.txt.parse("<i>Seuls les membres des factions peuvent construire et détruire eux-mêmes."));
		pageLines.add(p.txt.parse("<i>territoire. L'utilisation des éléments suivants est également limitée :"));
		pageLines.add(p.txt.parse("<i>Porte, coffre, four, distributeur, diode."));
		pageLines.add("");
		pageLines.add(p.txt.parse("<i>Assurez-vous de placer des plaques de pression devant les portes pour votre"));
		pageLines.add(p.txt.parse("<i>visiteurs invités. Sinon, ils ne peuvent pas passer. Vous pouvez"));
		pageLines.add(p.txt.parse("<i>utilisez également cette option pour créer des zones réservées aux membres."));
		pageLines.add(p.txt.parse("<i>Les distributeurs étant protégés, vous pouvez créer des pièges sans"));
		pageLines.add(p.txt.parse("<i>se soucier de ces flèches se faire voler."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add("Enfin quelques commandes pour les administrateurs du serveur :");
		pageLines.add( p.cmdBase.cmdBypass.getUseageTemplate(true) );
		pageLines.add(p.txt.parse("<c>/f claim safezone <i>réclamer des terres pour la Safe Zone"));
		pageLines.add(p.txt.parse("<c>/f claim warzone <i>réclamer des terres pour la War Zone"));
		pageLines.add(p.txt.parse("<c>/f autoclaim [safezone|warzone] <i>deviner"));
		pageLines.add(p.txt.parse("<i>Note: " + p.cmdBase.cmdUnclaim.getUseageTemplate(false) + P.p.txt.parse("<i>") + " travaille également sur les zones de sécurité / zones de guerre."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add(p.txt.parse("<i>Plus de commandes pour les administrateurs de serveur :"));
		pageLines.add( p.cmdBase.cmdPowerBoost.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdLock.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdReload.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdSaveAll.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdVersion.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdConfig.getUseageTemplate(true) );
		helpPages.add(pageLines);
	}
}

