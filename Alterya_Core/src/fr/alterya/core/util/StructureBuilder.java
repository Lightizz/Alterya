package fr.alterya.core.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class StructureBuilder
{
	public static void buildKOTHStructure(int rPosX, int rPosY, int rPosZ) {

		//System.out.println(randomPosX + " " + randomPosY + " " + randomPosZ);
		
//		VIEWS AND INFOS
//		   ||		||
//		   \/		\/
		
//		   | Front view |
		/*
		 * 
		 * Infos : 
		 * - 8b of high long
		 * 
		 */
		
//		   | Top view |
		/*
		 *
		 *  7/7_ 6_ 5_ 4_ 3_ 2_ 1_/_1
		 *    6 			     _2
		 *    5 			     _3			Infos :	
		 *    4 			     _4			- <---- 7b ---->
		 *    3				     _5			-   <-- 5b -->
		 *    2				     _6
		 *  1/$1 $2 $3 $4 $5 $6 $7/_7
		 *  
		 *     x	|  
		 *  -z • z  |  • = y
		 *    -x	|  
		 */
		
//		######################################################
//		--##-- 		 Building base wooden ring			--##--
//		######################################################
		
		Location kothLocCorner$1 = new Location(Bukkit.getWorld("world"), rPosX, rPosY, rPosZ);
		kothLocCorner$1.getBlock().setType(Material.WOOD);
		
		Location kothLoc$2 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX(), kothLocCorner$1.getY(), kothLocCorner$1.getZ() + 1);
		kothLoc$2.getBlock().setType(Material.WOOD);
		Location kothLoc$3 = new Location(kothLoc$2.getWorld(), kothLoc$2.getX(), kothLoc$2.getY(), kothLoc$2.getZ() + 1);
		kothLoc$3.getBlock().setType(Material.WOOD);
		Location kothLoc$4 = new Location(kothLoc$3.getWorld(), kothLoc$3.getX(), kothLoc$3.getY(), kothLoc$3.getZ() + 1);
		kothLoc$4.getBlock().setType(Material.WOOD);
		Location kothLoc$5 = new Location(kothLoc$4.getWorld(), kothLoc$4.getX(), kothLoc$4.getY(), kothLoc$4.getZ() + 1);
		kothLoc$5.getBlock().setType(Material.WOOD);
		Location kothLoc$6 = new Location(kothLoc$5.getWorld(), kothLoc$5.getX(), kothLoc$5.getY(), kothLoc$5.getZ() + 1);
		kothLoc$6.getBlock().setType(Material.WOOD);
		
		Location kothLocCorner$7 = new Location(kothLoc$6.getWorld(), kothLoc$6.getX(), kothLoc$6.getY(), kothLoc$6.getZ() + 1);
		kothLocCorner$7.getBlock().setType(Material.WOOD);
		
		Location kothLoc_6 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 5, kothLocCorner$7.getY(), kothLocCorner$7.getZ());
		kothLoc_6.getBlock().setType(Material.WOOD);
		Location kothLoc_5 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 4, kothLocCorner$7.getY(), kothLocCorner$7.getZ());
		kothLoc_5.getBlock().setType(Material.WOOD);
		Location kothLoc_4 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 3, kothLocCorner$7.getY(), kothLocCorner$7.getZ());
		kothLoc_4.getBlock().setType(Material.WOOD);
		Location kothLoc_3 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 2, kothLocCorner$7.getY(), kothLocCorner$7.getZ());
		kothLoc_3.getBlock().setType(Material.WOOD);
		Location kothLoc_2 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 1, kothLocCorner$7.getY(), kothLocCorner$7.getZ());
		kothLoc_2.getBlock().setType(Material.WOOD);
		
		Location kothLoc_1 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 6, kothLocCorner$7.getY(), kothLocCorner$7.getZ());
		kothLoc_1.getBlock().setType(Material.WOOD);
		
		Location kothLoc2_ = new Location(kothLoc_1.getWorld(), kothLoc_1.getX(), kothLoc_1.getY(), kothLoc_1.getZ() - 1);
		kothLoc2_.getBlock().setType(Material.WOOD);
		Location kothLoc3_ = new Location(kothLoc2_.getWorld(), kothLoc2_.getX(), kothLoc2_.getY(), kothLoc2_.getZ() - 1);
		kothLoc3_.getBlock().setType(Material.WOOD);
		Location kothLoc4_ = new Location(kothLoc3_.getWorld(), kothLoc3_.getX(), kothLoc3_.getY(), kothLoc3_.getZ() - 1);
		kothLoc4_.getBlock().setType(Material.WOOD);
		Location kothLoc5_ = new Location(kothLoc4_.getWorld(), kothLoc4_.getX(), kothLoc4_.getY(), kothLoc4_.getZ() - 1);
		kothLoc5_.getBlock().setType(Material.WOOD);
		Location kothLoc6_ = new Location(kothLoc5_.getWorld(), kothLoc5_.getX(), kothLoc5_.getY(), kothLoc5_.getZ() - 1);
		kothLoc6_.getBlock().setType(Material.WOOD);
		
		Location kothLocCorner7_ = new Location(kothLoc6_.getWorld(), kothLoc6_.getX(), kothLoc6_.getY(), kothLoc6_.getZ() - 1);
		kothLocCorner7_.getBlock().setType(Material.WOOD);
		
		Location kothLoc6 = new Location(kothLocCorner7_.getWorld(), kothLocCorner7_.getX() - 5, kothLocCorner7_.getY(), kothLocCorner7_.getZ());
		kothLoc6.getBlock().setType(Material.WOOD);
		Location kothLoc5 = new Location(kothLocCorner7_.getWorld(), kothLocCorner7_.getX() - 4, kothLocCorner7_.getY(), kothLocCorner7_.getZ());
		kothLoc5.getBlock().setType(Material.WOOD);
		Location kothLoc4 = new Location(kothLocCorner7_.getWorld(), kothLocCorner7_.getX() - 3, kothLocCorner7_.getY(), kothLocCorner7_.getZ());
		kothLoc4.getBlock().setType(Material.WOOD);
		Location kothLoc3 = new Location(kothLocCorner7_.getWorld(), kothLocCorner7_.getX() - 2, kothLocCorner7_.getY(), kothLocCorner7_.getZ());
		kothLoc3.getBlock().setType(Material.WOOD);
		Location kothLoc2 = new Location(kothLocCorner7_.getWorld(), kothLocCorner7_.getX() - 1, kothLocCorner7_.getY(), kothLocCorner7_.getZ());
		kothLoc2.getBlock().setType(Material.WOOD);
	
//		######################################################
//		--##-- 		 Building 1st stairs floor			--##--
//		######################################################
	}
}
