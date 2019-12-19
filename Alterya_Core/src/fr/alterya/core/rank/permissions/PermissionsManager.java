package fr.alterya.core.rank.permissions;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import com.massivecraft.factions.P;

import fr.alterya.core.MainCore;

public class PermissionsManager implements Listener
{
	MainCore main;
	
	P mainF;
	
	public HashMap<UUID, PermissionAttachment> playersPermissions = new HashMap<>();
	
	public PermissionsManager(MainCore main) {
		this.main = main;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		this.setupPermissions(player);
	}
	
	public void setupPermissions(Player player) {
		PermissionAttachment permissionAttachment = player.addAttachment(main);
		this.playersPermissions.put(player.getUniqueId(), permissionAttachment);
		permissionSetter(player.getUniqueId(), this.main.rank.config.getInt(player.getUniqueId().toString()));
	}

	//Set permissions
	public void permissionSetter(UUID uuid, int rankId)
	{
		PermissionAttachment permissionAttachment = this.playersPermissions.get(uuid);
		if(rankId == 0){
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(rankId).getRankName() + ".permissions"), true);
		}else if(rankId == 1) {
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(rankId).getRankName() + ".permissions"), true);
		}else if(rankId == 2) {
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(rankId).getRankName() + ".permissions"), true);
		}else if(rankId == 3) {
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(rankId).getRankName() + ".permissions"), true);
		}else if(rankId == 4) {
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(rankId).getRankName() + ".permissions"), true);
		}else if(rankId == 5) {
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(rankId).getRankName() + ".permissions"), true);
		}else if(rankId == 6) {
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(rankId).getRankName() + ".permissions"), true);
		}else if(rankId == 7) {
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(rankId).getRankName() + ".permissions"), true);
		}else if(rankId == 8) {
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(rankId).getRankName() + ".permissions"), true);
		}else if(rankId == 9) {
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(rankId).getRankName() + ".permissions"), true);
		}else if(rankId == 10) {
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(rankId).getRankName() + ".permissions"), true);
			permissionAttachment.setPermission(this.main.getConfig().getString("Groups." + "Responsable" + ".permissions"), true);
		}
	}
}
