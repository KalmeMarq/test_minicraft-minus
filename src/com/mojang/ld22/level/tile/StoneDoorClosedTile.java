package com.mojang.ld22.level.tile;

import com.mojang.ld22.Game;
import com.mojang.ld22.entity.Entity;
import com.mojang.ld22.entity.ItemEntity;
import com.mojang.ld22.entity.Mob;
import com.mojang.ld22.entity.Player;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.item.Item;
import com.mojang.ld22.item.ResourceItem;
import com.mojang.ld22.item.ToolItem;
import com.mojang.ld22.item.ToolType;
import com.mojang.ld22.item.resource.Resource;
import com.mojang.ld22.level.Level;
import com.mojang.ld22.sound.Sound;

public class StoneDoorClosedTile extends Tile {
	public StoneDoorClosedTile(int id) {
		super(id);
	}
	
	public void render(Screen screen, Level level, int x, int y) {
		int col0 = Color.get(333, 222, 111, 222);
		
		int col1 = Color.get(444, 333, 222, 333);
		
		int col2 = Color.get(333, 222, 111, 222);
		
	    int col3 = Color.get(222, 111, 000, 111);
	    
	    int col4 = Color.get(444, 333, 222, 333);
		
	    
		if (level.dirtColor == 322){
			
		if (Game.Time == 0){
		int col = col0;
		screen.render(x * 16 + 0, y * 16 + 0, 2 + 24 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 0, 3 + 24 * 32, col, 0);
		screen.render(x * 16 + 0, y * 16 + 8, 2 + 25 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 8, 3 + 25 * 32, col, 0);
		}
		} if (Game.Time == 1){
			int col = col1;
		screen.render(x * 16 + 0, y * 16 + 0, 2 + 24 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 0, 3 + 24 * 32, col, 0);
		screen.render(x * 16 + 0, y * 16 + 8, 2 + 25 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 8, 3 + 25 * 32, col, 0);
		}if (Game.Time == 2){
			int col = col2;
		screen.render(x * 16 + 0, y * 16 + 0, 2 + 24 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 0, 3 + 24 * 32, col, 0);
		screen.render(x * 16 + 0, y * 16 + 8, 2 + 25 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 8, 3 + 25 * 32, col, 0);
		}if (Game.Time == 3){
			int col = col3;
		screen.render(x * 16 + 0, y * 16 + 0, 2 + 24 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 0, 3 + 24 * 32, col, 0);
		screen.render(x * 16 + 0, y * 16 + 8, 2 + 25 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 8, 3 + 25 * 32, col, 0);
		}
		
		if (level.dirtColor == 222){
			int col = col4;
		screen.render(x * 16 + 0, y * 16 + 0, 2 + 24 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 0, 3 + 24 * 32, col, 0);
		screen.render(x * 16 + 0, y * 16 + 8, 2 + 25 * 32, col, 0);
		screen.render(x * 16 + 8, y * 16 + 8, 3 + 25 * 32, col, 0);
		}
	}
	public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		if (item instanceof ToolItem) {
			ToolItem tool = (ToolItem) item;
			if (tool.type == ToolType.pickaxe) {
				if (player.payStamina(4 - tool.level)) {
					level.setTile(xt, yt, Tile.sbrick, 0);
					level.add(new ItemEntity(new ResourceItem(Resource.sdoor), xt * 16 + random.nextInt(10) + 3, yt * 16 + random.nextInt(10) + 3));
					Sound.monsterHurt.play();
					return true;
				}
			}
			if (tool.type == ToolType.pick) {
				if (player.payStamina(4 - tool.level)) {
					level.setTile(xt, yt, Tile.sbrick, 0);
					level.add(new ItemEntity(new ResourceItem(Resource.sdoor), xt * 16 + random.nextInt(10) + 3, yt * 16 + random.nextInt(10) + 3));
					Sound.monsterHurt.play();
					return true;
				}
			}
		}
		return false;
	}
	public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
		level.setTile(x, y, Tile.sdo, 0);
	}
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false;
	}
}
	