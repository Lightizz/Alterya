package fr.alterya.core.util;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class StructureBuilder
{
	static Location l1 = new Location(Bukkit.getWorld("world"), 0, 0, 0);
	static Location l2 = new Location(Bukkit.getWorld("world"), 0, 0, 0);
	static Location l3 = new Location(Bukkit.getWorld("world"), 0, 0, 0);
	private static List<Location> kothLocList = Arrays.asList(l1, l2, l3);
	
	public static Location kothLocCorner$1 = new Location(Bukkit.getWorld("world"), 0, 0, 0);
	
	public static void removeKOTHStructure() {
		for(Location l : kothLocList) {
			l.getBlock().setType(Material.AIR);
		}
	}
	
	public static void buildKOTHStructure(int rPosX, int rPosY, int rPosZ) {

		//System.out.println(rPosX + " " + rPosY + " " + rPosZ);
		
//		VIEWS AND INFOS
//		   ||		||
//		   \/		\/
		
//		   | Front view |
		/*
		 * 
		 * Infos : 
		 * - 7b of high long
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
		
//				 |Base location|		
		kothLocCorner$1 = new Location(Bukkit.getWorld("world"), rPosX, rPosY, rPosZ);
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
		
		Location kothLocSFS1 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 1, kothLocCorner$1.getY() + 1, kothLocCorner$1.getZ() + 1);
		kothLocSFS1.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocSFS$2 = new Location(kothLocSFS1.getWorld(), kothLocSFS1.getX(), kothLocSFS1.getY(), kothLocSFS1.getZ() + 1);
		kothLocSFS$2.getBlock().setType(Material.WOOD_STEP);
		Location kothLocSFS$3 = new Location(kothLocSFS1.getWorld(), kothLocSFS1.getX(), kothLocSFS1.getY(), kothLocSFS1.getZ() + 2);
		kothLocSFS$3.getBlock().setType(Material.WOOD_STEP);
		Location kothLocSFS$4 = new Location(kothLocSFS1.getWorld(), kothLocSFS1.getX(), kothLocSFS1.getY(), kothLocSFS1.getZ() + 3);
		kothLocSFS$4.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocSFS$5 = new Location(kothLocCorner$1.getWorld(), kothLocSFS1.getX(), kothLocSFS1.getY(), kothLocSFS1.getZ() + 4);
		kothLocSFS$5.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocSFS_4 = new Location(kothLocSFS1.getWorld(), kothLocSFS1.getX() + 1, kothLocSFS1.getY(), kothLocSFS1.getZ());
		kothLocSFS_4.getBlock().setType(Material.WOOD_STEP);
		Location kothLocSFS_3 = new Location(kothLocSFS1.getWorld(), kothLocSFS1.getX() + 2, kothLocSFS1.getY(), kothLocSFS1.getZ());
		kothLocSFS_3.getBlock().setType(Material.WOOD_STEP);
		Location kothLocSFS_2 = new Location(kothLocSFS1.getWorld(), kothLocSFS1.getX() + 3, kothLocSFS1.getY(), kothLocSFS1.getZ());
		kothLocSFS_2.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocSFS_1 = new Location(kothLocCorner$1.getWorld(), kothLocSFS1.getX() + 4, kothLocSFS1.getY(), kothLocSFS1.getZ());
		kothLocSFS_1.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocSFS2_ = new Location(kothLocSFS_1.getWorld(), kothLocSFS_1.getX(), kothLocSFS_1.getY(), kothLocSFS_1.getZ() + 1);
		kothLocSFS2_.getBlock().setType(Material.WOOD_STEP);
		Location kothLocSFS3_ = new Location(kothLocSFS_1.getWorld(), kothLocSFS_1.getX(), kothLocSFS_1.getY(), kothLocSFS_1.getZ() + 2);
		kothLocSFS3_.getBlock().setType(Material.WOOD_STEP);
		Location kothLocSFS4_ = new Location(kothLocSFS_1.getWorld(), kothLocSFS_1.getX(), kothLocSFS_1.getY(), kothLocSFS_1.getZ() + 3);
		kothLocSFS4_.getBlock().setType(Material.WOOD_STEP);
	
		Location kothLocSFS5_ = new Location(kothLocCorner$1.getWorld(), kothLocSFS_1.getX(), kothLocSFS_1.getY(), kothLocSFS_1.getZ() + 4);
		kothLocSFS5_.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocSFS4 = new Location(kothLocSFS1.getWorld(), kothLocSFS5_.getX() - 3, kothLocSFS5_.getY(), kothLocSFS5_.getZ());
		kothLocSFS4.getBlock().setType(Material.WOOD_STEP);
		Location kothLocSFS3 = new Location(kothLocSFS1.getWorld(), kothLocSFS5_.getX() - 2, kothLocSFS5_.getY(), kothLocSFS5_.getZ());
		kothLocSFS3.getBlock().setType(Material.WOOD_STEP);
		Location kothLocSFS2 = new Location(kothLocSFS1.getWorld(), kothLocSFS5_.getX() - 1, kothLocSFS5_.getY(), kothLocSFS5_.getZ());
		kothLocSFS2.getBlock().setType(Material.WOOD_STEP);
		
//		######################################################
//		--##-- 		 Building iron blocks ring			--##--
//		######################################################
		
		Location kothLocI1 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 1, kothLocCorner$1.getY(), kothLocCorner$1.getZ() + 1);
		kothLocI1.getBlock().setType(Material.IRON_BLOCK);
		
		Location kothLocI2 = new Location(kothLocCorner$1.getWorld(), kothLocI1.getX(), kothLocI1.getY(), kothLocI1.getZ() + 1);
		kothLocI2.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI3 = new Location(kothLocCorner$1.getWorld(), kothLocI1.getX(), kothLocI1.getY(), kothLocI1.getZ() + 2);
		kothLocI3.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI4 = new Location(kothLocCorner$1.getWorld(), kothLocI1.getX(), kothLocI1.getY(), kothLocI1.getZ() + 3);
		kothLocI4.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI5 = new Location(kothLocCorner$1.getWorld(), kothLocI1.getX(), kothLocI1.getY(), kothLocI1.getZ() + 4);
		kothLocI5.getBlock().setType(Material.IRON_BLOCK);
		
		Location kothLocI6 = new Location(kothLocCorner$1.getWorld(), kothLocI1.getX() + 1, kothLocI1.getY(), kothLocI1.getZ());
		kothLocI6.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI7 = new Location(kothLocCorner$1.getWorld(), kothLocI1.getX() + 2, kothLocI1.getY(), kothLocI1.getZ());
		kothLocI7.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI8 = new Location(kothLocCorner$1.getWorld(), kothLocI1.getX() + 3, kothLocI1.getY(), kothLocI1.getZ());
		kothLocI8.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI9 = new Location(kothLocCorner$1.getWorld(), kothLocI1.getX() + 4, kothLocI1.getY(), kothLocI1.getZ());
		kothLocI9.getBlock().setType(Material.IRON_BLOCK);
		
		Location kothLocI10 = new Location(kothLocCorner$1.getWorld(), kothLocI9.getX(), kothLocI9.getY(), kothLocI9.getZ() + 1);
		kothLocI10.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI11 = new Location(kothLocCorner$1.getWorld(), kothLocI9.getX(), kothLocI9.getY(), kothLocI9.getZ() + 2);
		kothLocI11.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI12 = new Location(kothLocCorner$1.getWorld(), kothLocI9.getX(), kothLocI9.getY(), kothLocI9.getZ() + 3);
		kothLocI12.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI13 = new Location(kothLocCorner$1.getWorld(), kothLocI9.getX(), kothLocI9.getY(), kothLocI9.getZ() + 4);
		kothLocI13.getBlock().setType(Material.IRON_BLOCK);
		
		Location kothLocI14 = new Location(kothLocCorner$1.getWorld(), kothLocI13.getX() - 1, kothLocI13.getY(), kothLocI13.getZ());
		kothLocI14.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI15 = new Location(kothLocCorner$1.getWorld(), kothLocI13.getX() - 2, kothLocI13.getY(), kothLocI13.getZ());
		kothLocI15.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI16 = new Location(kothLocCorner$1.getWorld(), kothLocI13.getX() - 3, kothLocI13.getY(), kothLocI13.getZ());
		kothLocI16.getBlock().setType(Material.IRON_BLOCK);
		
		Location kothLocI17 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 2, kothLocCorner$1.getY() - 1, kothLocCorner$1.getZ() + 2);
		kothLocI17.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI18 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 2, kothLocCorner$1.getY() - 1, kothLocCorner$1.getZ() + 3);
		kothLocI18.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI19 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 2, kothLocCorner$1.getY() - 1, kothLocCorner$1.getZ() + 4);
		kothLocI19.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI20 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 3, kothLocCorner$1.getY() - 1, kothLocCorner$1.getZ() + 2);
		kothLocI20.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI21 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 4, kothLocI13.getY() - 1, kothLocCorner$1.getZ() + 2);
		kothLocI21.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI22 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 3, kothLocI13.getY() - 1, kothLocCorner$1.getZ() + 3);
		kothLocI22.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI23 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 4, kothLocI13.getY() - 1, kothLocCorner$1.getZ() + 4);
		kothLocI23.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI24 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 4, kothLocI13.getY() - 1, kothLocCorner$1.getZ() + 3);
		kothLocI24.getBlock().setType(Material.IRON_BLOCK);
		Location kothLocI25 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 3, kothLocI13.getY() - 1, kothLocCorner$1.getZ() + 4);
		kothLocI25.getBlock().setType(Material.IRON_BLOCK);
		
//		######################################################
//		--##-- 		 Building water + beacon			--##--
//		######################################################
		
		Location kothLocI26 = new Location(kothLocCorner$1.getWorld(), kothLocI17.getX(), kothLocI17.getY() + 1, kothLocI17.getZ());
		kothLocI26.getBlock().setType(Material.WATER);
		Location kothLocI27 = new Location(kothLocCorner$1.getWorld(), kothLocI18.getX(), kothLocI18.getY() + 1, kothLocI18.getZ());
		kothLocI27.getBlock().setType(Material.WATER);
		Location kothLocI28 = new Location(kothLocCorner$1.getWorld(), kothLocI19.getX(), kothLocI19.getY() + 1, kothLocI19.getZ());
		kothLocI28.getBlock().setType(Material.WATER);
		Location kothLocI29 = new Location(kothLocCorner$1.getWorld(), kothLocI20.getX(), kothLocI20.getY() + 1, kothLocI20.getZ());
		kothLocI29.getBlock().setType(Material.WATER);
		Location kothLocI30 = new Location(kothLocCorner$1.getWorld(), kothLocI21.getX(), kothLocI21.getY() + 1, kothLocI21.getZ());
		kothLocI30.getBlock().setType(Material.WATER);
		Location kothLocI31 = new Location(kothLocCorner$1.getWorld(), kothLocI24.getX(), kothLocI24.getY() + 1, kothLocI24.getZ());
		kothLocI31.getBlock().setType(Material.WATER);
		Location kothLocI32 = new Location(kothLocCorner$1.getWorld(), kothLocI23.getX(), kothLocI23.getY() + 1, kothLocI23.getZ());
		kothLocI32.getBlock().setType(Material.WATER);
		Location kothLocI33 = new Location(kothLocCorner$1.getWorld(), kothLocI22.getX(), kothLocI22.getY() + 1, kothLocI22.getZ());
		kothLocI33.getBlock().setType(Material.BEACON);
		Location kothLocI34 = new Location(kothLocCorner$1.getWorld(), kothLocI25.getX(), kothLocI25.getY() + 1, kothLocI25.getZ());
		kothLocI34.getBlock().setType(Material.WATER);
		
		Location kothLocI35 = new Location(kothLocCorner$1.getWorld(), kothLocI17.getX(), kothLocI17.getY() + 2, kothLocI17.getZ());
		kothLocI35.getBlock().setType(Material.GLASS);
		Location kothLocI36 = new Location(kothLocCorner$1.getWorld(), kothLocI18.getX(), kothLocI18.getY() + 2, kothLocI18.getZ());
		kothLocI36.getBlock().setType(Material.GLASS);
		Location kothLocI37 = new Location(kothLocCorner$1.getWorld(), kothLocI19.getX(), kothLocI19.getY() + 2, kothLocI19.getZ());
		kothLocI37.getBlock().setType(Material.GLASS);
		Location kothLocI38 = new Location(kothLocCorner$1.getWorld(), kothLocI20.getX(), kothLocI20.getY() + 2, kothLocI20.getZ());
		kothLocI38.getBlock().setType(Material.GLASS);
		Location kothLocI39 = new Location(kothLocCorner$1.getWorld(), kothLocI21.getX(), kothLocI21.getY() + 2, kothLocI21.getZ());
		kothLocI39.getBlock().setType(Material.GLASS);
		Location kothLocI40 = new Location(kothLocCorner$1.getWorld(), kothLocI22.getX(), kothLocI22.getY() + 2, kothLocI22.getZ());
		kothLocI40.getBlock().setType(Material.GLASS);
		Location kothLocI41 = new Location(kothLocCorner$1.getWorld(), kothLocI23.getX(), kothLocI23.getY() + 2, kothLocI23.getZ());
		kothLocI41.getBlock().setType(Material.GLASS);
		Location kothLocI42 = new Location(kothLocCorner$1.getWorld(), kothLocI24.getX(), kothLocI24.getY() + 2, kothLocI24.getZ());
		kothLocI42.getBlock().setType(Material.GLASS);
		Location kothLocI43 = new Location(kothLocCorner$1.getWorld(), kothLocI25.getX(), kothLocI25.getY() + 2, kothLocI25.getZ());
		kothLocI43.getBlock().setType(Material.GLASS);
		
//		######################################################
//		--##-- 		 Building cooble pillars			--##--
//		######################################################
		
		Location kothLocPillar1 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX(), kothLocCorner$1.getY() + 1, kothLocCorner$1.getZ());
		kothLocPillar1.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar2 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX(), kothLocCorner$1.getY() + 2, kothLocCorner$1.getZ());
		kothLocPillar2.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar3 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX(), kothLocCorner$1.getY() + 3, kothLocCorner$1.getZ());
		kothLocPillar3.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar4 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX(), kothLocCorner$1.getY() + 4, kothLocCorner$1.getZ());
		kothLocPillar4.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar5 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX(), kothLocCorner$1.getY() + 5, kothLocCorner$1.getZ());
		kothLocPillar5.getBlock().setType(Material.COBBLE_WALL);
		
		Location kothLocPillar6 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX(), kothLocCorner$1.getY() + 1, kothLocCorner$1.getZ() + 1);
		kothLocPillar6.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocT5 = new Location(kothLocPillar6.getWorld(), kothLocPillar6.getX(), kothLocPillar6.getY() + 1, kothLocPillar6.getZ());
		kothLocT5.getBlock().setType(Material.TORCH);
		Location kothLocPillar7 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX() + 1, kothLocCorner$1.getY() + 1, kothLocCorner$1.getZ());
		kothLocPillar7.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocT6 = new Location(kothLocPillar7.getWorld(), kothLocPillar7.getX(), kothLocPillar7.getY() + 1, kothLocPillar7.getZ());
		kothLocT6.getBlock().setType(Material.TORCH);
		
		Location kothLocPillar8 = new Location(kothLocCorner$1.getWorld(), kothLoc_1.getX(), kothLocCorner$1.getY() + 1, kothLoc_1.getZ());
		kothLocPillar8.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar9 = new Location(kothLocCorner$1.getWorld(), kothLoc_1.getX(), kothLocCorner$1.getY() + 2, kothLoc_1.getZ());
		kothLocPillar9.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar10 = new Location(kothLocCorner$1.getWorld(), kothLoc_1.getX(), kothLocCorner$1.getY() + 3, kothLoc_1.getZ());
		kothLocPillar10.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar11 = new Location(kothLocCorner$1.getWorld(), kothLoc_1.getX(), kothLocCorner$1.getY() + 4, kothLoc_1.getZ());
		kothLocPillar11.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar12 = new Location(kothLocCorner$1.getWorld(), kothLoc_1.getX(), kothLocCorner$1.getY() + 5, kothLoc_1.getZ());
		kothLocPillar12.getBlock().setType(Material.COBBLE_WALL);
		
		Location kothLocPillar13 = new Location(kothLocCorner$1.getWorld(), kothLoc_1.getX(), kothLocCorner$1.getY() + 1, kothLoc_1.getZ() - 1);
		kothLocPillar13.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocT3 = new Location(kothLocPillar13.getWorld(), kothLocPillar13.getX(), kothLocPillar13.getY() + 1, kothLocPillar13.getZ());
		kothLocT3.getBlock().setType(Material.TORCH);
		Location kothLocPillar14 = new Location(kothLocCorner$1.getWorld(), kothLoc_1.getX() - 1, kothLocCorner$1.getY() + 1, kothLoc_1.getZ());
		kothLocPillar14.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocT4 = new Location(kothLocPillar14.getWorld(), kothLocPillar14.getX(), kothLocPillar14.getY() + 1, kothLocPillar14.getZ());
		kothLocT4.getBlock().setType(Material.TORCH);
		
		Location kothLocPillar15 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$7.getX(), kothLocCorner$1.getY() + 1, kothLocCorner$7.getZ());
		kothLocPillar15.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar16 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$7.getX(), kothLocCorner$1.getY() + 2, kothLocCorner$7.getZ());
		kothLocPillar16.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar17 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$7.getX(), kothLocCorner$1.getY() + 3, kothLocCorner$7.getZ());
		kothLocPillar17.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar18 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$7.getX(), kothLocCorner$1.getY() + 4, kothLocCorner$7.getZ());
		kothLocPillar18.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar19 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$7.getX(), kothLocCorner$1.getY() + 5, kothLocCorner$7.getZ());
		kothLocPillar19.getBlock().setType(Material.COBBLE_WALL);
		
		Location kothLocPillar20 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$7.getX() + 1, kothLocCorner$1.getY() + 1, kothLocCorner$7.getZ());
		kothLocPillar20.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocT1 = new Location(kothLocPillar20.getWorld(), kothLocPillar20.getX(), kothLocPillar20.getY() + 1, kothLocPillar20.getZ());
		kothLocT1.getBlock().setType(Material.TORCH);
		Location kothLocPillar21 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$7.getX(), kothLocCorner$1.getY() + 1, kothLocCorner$7.getZ() - 1);
		kothLocPillar21.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocT2 = new Location(kothLocPillar21.getWorld(), kothLocPillar21.getX(), kothLocPillar21.getY() + 1, kothLocPillar21.getZ());
		kothLocT2.getBlock().setType(Material.TORCH);
		
		Location kothLocPillar22 = new Location(kothLocCorner$1.getWorld(), kothLocCorner7_.getX(), kothLocCorner$1.getY() + 1, kothLocCorner7_.getZ());
		kothLocPillar22.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar23 = new Location(kothLocCorner$1.getWorld(), kothLocCorner7_.getX(), kothLocCorner$1.getY() + 2, kothLocCorner7_.getZ());
		kothLocPillar23.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar24 = new Location(kothLocCorner$1.getWorld(), kothLocCorner7_.getX(), kothLocCorner$1.getY() + 3, kothLocCorner7_.getZ());
		kothLocPillar24.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar25 = new Location(kothLocCorner$1.getWorld(), kothLocCorner7_.getX(), kothLocCorner$1.getY() + 4, kothLocCorner7_.getZ());
		kothLocPillar25.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocPillar26 = new Location(kothLocCorner$1.getWorld(), kothLocCorner7_.getX(), kothLocCorner$1.getY() + 5, kothLocCorner7_.getZ());
		kothLocPillar26.getBlock().setType(Material.COBBLE_WALL);
		
		Location kothLocPillar27 = new Location(kothLocCorner$1.getWorld(), kothLocCorner7_.getX(), kothLocCorner$1.getY() + 1, kothLocCorner7_.getZ() + 1);
		kothLocPillar27.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocT7 = new Location(kothLocPillar20.getWorld(), kothLocPillar27.getX(), kothLocPillar27.getY() + 1, kothLocPillar27.getZ());
		kothLocT7.getBlock().setType(Material.TORCH);
		Location kothLocPillar28 = new Location(kothLocCorner$1.getWorld(), kothLocCorner7_.getX() - 1, kothLocCorner$1.getY() + 1, kothLocCorner7_.getZ());
		kothLocPillar28.getBlock().setType(Material.COBBLE_WALL);
		Location kothLocT8 = new Location(kothLocPillar20.getWorld(), kothLocPillar28.getX(), kothLocPillar28.getY() + 1, kothLocPillar28.getZ());
		kothLocT8.getBlock().setType(Material.TORCH);
	
//		######################################################
//		--##-- 		 Building wooden top (step)			--##--
//		######################################################
		
		Location kothLocCornerT$1 = new Location(Bukkit.getWorld("world"), rPosX, rPosY + 6, rPosZ);
		kothLocCornerT$1.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocT$2 = new Location(kothLocCorner$1.getWorld(), kothLocCorner$1.getX(), kothLocCorner$1.getY() + 6, kothLocCorner$1.getZ() + 1);
		kothLocT$2.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT$3 = new Location(kothLoc$2.getWorld(), kothLoc$2.getX(), kothLoc$2.getY() + 6, kothLoc$2.getZ() + 1);
		kothLocT$3.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT$4 = new Location(kothLoc$3.getWorld(), kothLoc$3.getX(), kothLoc$3.getY() + 6, kothLoc$3.getZ() + 1);
		kothLocT$4.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT$5 = new Location(kothLoc$4.getWorld(), kothLoc$4.getX(), kothLoc$4.getY() + 6, kothLoc$4.getZ() + 1);
		kothLocT$5.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT$6 = new Location(kothLoc$5.getWorld(), kothLoc$5.getX(), kothLoc$5.getY() + 6, kothLoc$5.getZ() + 1);
		kothLocT$6.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocCornerT$7 = new Location(kothLoc$6.getWorld(), kothLoc$6.getX(), kothLoc$6.getY() + 6, kothLoc$6.getZ() + 1);
		kothLocCornerT$7.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocT_6 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 5, kothLocCorner$7.getY() + 6, kothLocCorner$7.getZ());
		kothLocT_6.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT_5 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 4, kothLocCorner$7.getY() + 6, kothLocCorner$7.getZ());
		kothLocT_5.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT_4 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 3, kothLocCorner$7.getY() + 6, kothLocCorner$7.getZ());
		kothLocT_4.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT_3 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 2, kothLocCorner$7.getY() + 6, kothLocCorner$7.getZ());
		kothLocT_3.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT_2 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 1, kothLocCorner$7.getY() + 6, kothLocCorner$7.getZ());
		kothLocT_2.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocT_1 = new Location(kothLocCorner$7.getWorld(), kothLocCorner$7.getX() + 6, kothLocCorner$7.getY() + 6, kothLocCorner$7.getZ());
		kothLocT_1.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocT2_ = new Location(kothLoc_1.getWorld(), kothLoc_1.getX(), kothLoc_1.getY() + 6, kothLoc_1.getZ() - 1);
		kothLocT2_.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT3_ = new Location(kothLoc2_.getWorld(), kothLoc2_.getX(), kothLoc2_.getY() + 6, kothLoc2_.getZ() - 1);
		kothLocT3_.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT4_ = new Location(kothLoc3_.getWorld(), kothLoc3_.getX(), kothLoc3_.getY() + 6, kothLoc3_.getZ() - 1);
		kothLocT4_.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT5_ = new Location(kothLoc4_.getWorld(), kothLoc4_.getX(), kothLoc4_.getY() + 6, kothLoc4_.getZ() - 1);
		kothLocT5_.getBlock().setType(Material.WOOD_STEP);
		Location kothLocT6_ = new Location(kothLoc5_.getWorld(), kothLoc5_.getX(), kothLoc5_.getY() + 6, kothLoc5_.getZ() - 1);
		kothLocT6_.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocCornerT7_ = new Location(kothLoc6_.getWorld(), kothLoc6_.getX(), kothLoc6_.getY() + 6, kothLoc6_.getZ() - 1);
		kothLocCornerT7_.getBlock().setType(Material.WOOD_STEP);
		
		Location kothLocTOP6 = new Location(kothLocCorner7_.getWorld(), kothLocCorner7_.getX() - 5, kothLocCorner7_.getY() + 6, kothLocCorner7_.getZ());
		kothLocTOP6.getBlock().setType(Material.WOOD_STEP);
		Location kothLocTOP5 = new Location(kothLocCorner7_.getWorld(), kothLocCorner7_.getX() - 4, kothLocCorner7_.getY() + 6, kothLocCorner7_.getZ());
		kothLocTOP5.getBlock().setType(Material.WOOD_STEP);
		Location kothLocTOP4 = new Location(kothLocCorner7_.getWorld(), kothLocCorner7_.getX() - 3, kothLocCorner7_.getY() + 6, kothLocCorner7_.getZ());
		kothLocTOP4.getBlock().setType(Material.WOOD_STEP);
		Location kothLocTOP3 = new Location(kothLocCorner7_.getWorld(), kothLocCorner7_.getX() - 2, kothLocCorner7_.getY() + 6, kothLocCorner7_.getZ());
		kothLocTOP3.getBlock().setType(Material.WOOD_STEP);
		Location kothLocTOP2 = new Location(kothLocCorner7_.getWorld(), kothLocCorner7_.getX() - 1, kothLocCorner7_.getY() + 6, kothLocCorner7_.getZ());
		kothLocTOP2.getBlock().setType(Material.WOOD_STEP);
		
		kothLocList = Arrays.asList
				(
						kothLoc$2, 
						kothLoc$3,
						kothLoc$4,
						kothLoc$5, 
						kothLoc$6, 
						kothLoc2, 
						kothLoc2_,
						kothLoc3,
						kothLoc3_,
						kothLoc4,
						kothLoc4_,
						kothLoc5,
						kothLoc5_,
						kothLoc6,
						kothLoc6_,
						kothLoc_1,
						kothLoc_2,
						kothLoc_3,
						kothLoc_4,
						kothLoc_5,
						kothLoc_6,
						kothLocCorner$1,
						kothLocCorner$7,
						kothLocCorner7_,
						kothLocCornerT$1,
						kothLocCornerT$7,
						kothLocCornerT7_,
						kothLocI1,
						kothLocI2,
						kothLocI3,
						kothLocI4,
						kothLocI5,
						kothLocI6,
						kothLocI7,
						kothLocI8,
						kothLocI9,
						kothLocI10,
						kothLocI11,
						kothLocI12,
						kothLocI13,
						kothLocI14,
						kothLocI15,
						kothLocI16,
						kothLocI17,
						kothLocI18,
						kothLocI19,
						kothLocI20,
						kothLocI21,
						kothLocI22,
						kothLocI23,
						kothLocI24,
						kothLocI25,
						kothLocI26,
						kothLocI27,
						kothLocI28,
						kothLocI30,
						kothLocI31,
						kothLocI32,
						kothLocI33,
						kothLocI34,
						kothLocI35,
						kothLocI36,
						kothLocI37,
						kothLocI38,
						kothLocI39,
						kothLocI40,
						kothLocI41,
						kothLocI42,
						kothLocI43,
						kothLocPillar1,
						kothLocPillar2,
						kothLocPillar3,
						kothLocPillar4,
						kothLocPillar5,
						kothLocPillar6,
						kothLocPillar7,
						kothLocPillar8,
						kothLocPillar9,
						kothLocPillar10,
						kothLocPillar11,
						kothLocPillar12,
						kothLocPillar13,
						kothLocPillar14,
						kothLocPillar15,
						kothLocPillar16,
						kothLocPillar17,
						kothLocPillar18,
						kothLocPillar19,
						kothLocPillar20,
						kothLocPillar21,
						kothLocPillar22,
						kothLocPillar23,
						kothLocPillar24,
						kothLocPillar25,
						kothLocPillar26,
						kothLocPillar27,
						kothLocPillar28,
						kothLocSFS$2,
						kothLocSFS$3,
						kothLocSFS$4,
						kothLocSFS$5,
						kothLocSFS1,
						kothLocSFS2,
						kothLocSFS2_,
						kothLocSFS3,
						kothLocSFS3_,
						kothLocSFS4,
						kothLocSFS4_,
						kothLocSFS5_,
						kothLocSFS_1,
						kothLocSFS_2,
						kothLocSFS_3,
						kothLocSFS_4,
						kothLocT$2,
						kothLocT$3,
						kothLocT$4,
						kothLocT$5,
						kothLocT$6,
						kothLocT1,
						kothLocT2,
						kothLocT2_,
						kothLocT3,
						kothLocT3_,
						kothLocT4,
						kothLocT4_,
						kothLocT5,
						kothLocT5_,
						kothLocT6,
						kothLocT6_,
						kothLocT7,
						kothLocT8,
						kothLocT_1,
						kothLocT_2,
						kothLocT_3,
						kothLocT_4,
						kothLocT_5,
						kothLocT_6,
						kothLocTOP2,
						kothLocTOP3,
						kothLocTOP4,
						kothLocTOP5,
						kothLocTOP6
				);
	}
}
