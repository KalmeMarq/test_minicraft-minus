package minicraft.entity.furniture;

import minicraft.core.CrashHandler;
import minicraft.core.Game;
import minicraft.entity.Direction;
import minicraft.entity.ItemHolder;
import minicraft.entity.mob.Player;
import minicraft.gfx.Sprite;
import minicraft.item.Inventory;
import minicraft.item.Item;
import minicraft.item.Items;
import minicraft.screen.ContainerDisplay;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Chest extends Furniture implements ItemHolder {
	private Inventory inventory; // Inventory of the chest

	public Chest() { this("Chest"); }

	/**
	 * Creates a chest with a custom name.
	 * @param name Name of chest.
	 */
	public Chest(String name) {
		super(name, new Sprite(10, 26, 2, 2, 2), 3, 3); // Name of the chest

		inventory = new Inventory(); // Initialize the inventory.
	}

	/** This is what occurs when the player uses the "Menu" command near this */
	public boolean use(Player player) {
		Game.setDisplay(new ContainerDisplay(player, this));
		return true;
	}

	public void populateInvRandom(String lootTable, int depth) {
		try (InputStream stream = Game.class.getResourceAsStream("/resources/chestloot/" + lootTable  + ".json")) {
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String chestJson = reader.lines().collect(Collectors.joining("\n"));

                JSONObject json = new JSONObject(chestJson);

                JSONArray loot = json.getJSONArray("loot");

                for (Object object : loot) {
                    JSONObject obj = (JSONObject) object;
                    Item item = Items.get(obj.getString("item"));
                    int amount = obj.has("amount") ? obj.getInt("amount") : 1;
                    int chance = obj.getInt("chance");

                    inventory.tryAdd(chance, item, amount);
                }

                if (inventory.invSize() == 0) {
                    JSONArray fallback = json.getJSONArray("fallback");

                    for (Object object : fallback) {
                        JSONObject obj = (JSONObject) object;
                        Item item = Items.get(obj.getString("item"));
                        int amount = obj.has("amount") ? obj.getInt("amount") : 1;

                        inventory.add(item, amount);
                    }
                }
            }
		} catch (Exception e) {
			CrashHandler.errorHandle(e, new CrashHandler.ErrorInfo("Loot table", CrashHandler.ErrorInfo.ErrorType.REPORT, "Couldn't read loot table \"" + lootTable + ".json" + "\""));
		}
	}

	@Override
	public boolean interact(Player player, @Nullable Item item, Direction attackDir) {
		if (inventory.invSize() == 0)
			return super.interact(player, item, attackDir);
		return false;
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public void die() {
		if (level != null) {
			List<Item> items = inventory.getItems();
			level.dropItem(x, y, items.toArray(new Item[items.size()]));
		}
		super.die();
	}
}
