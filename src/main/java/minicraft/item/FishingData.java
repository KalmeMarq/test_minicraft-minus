package minicraft.item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import minicraft.core.Game;
import minicraft.util.JsonUtil;

public class FishingData {
    public static final FishingLoot fishData = getData("fish");
    public static final FishingLoot toolData = getData("tool");
    public static final FishingLoot junkData = getData("junk");
    public static final FishingLoot rareData = getData("rare");

    public static FishingLoot getData(String name) {
        FishingLoot data = null;

        try (InputStream stream = Game.class.getResourceAsStream("/resources/fishing/" + name  + "_loot.json")) {
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                JsonObject json = JsonUtil.deserialize(reader, JsonObject.class, false);

                List<FishingLootItem> items = new ArrayList<>();
                List<FishingLootMessage> msgs = new ArrayList<>();

                if (JsonUtil.hasArray(json, "loot")) {
                    JsonArray loot = JsonUtil.getArray(json, "loot");

                    for (JsonElement object : loot) {
                        JsonObject obj = object.getAsJsonObject();
                        int chance = JsonUtil.getInt(obj, "chance");

                        if (JsonUtil.hasString(obj, "message")) {
                            String msg = JsonUtil.getString(obj, "message");
                            msgs.add(new FishingLootMessage(msg, chance));
                        } else if (JsonUtil.hasElement(obj, "item")) {
                            List<String> its = new ArrayList<>();

                            if (JsonUtil.hasArray(obj, "item")) {
                                for (JsonElement object1 : JsonUtil.getArray(obj, "item")) {
                                    its.add(object1.getAsString());
                                }
                            } else if (JsonUtil.hasString(obj, "item")) {
                                its.add(JsonUtil.getString(obj, "item"));
                            }

                            if (its.size() > 0) {
                                items.add(new FishingLootItem(its, chance));
                            }
                        }
                    }
                }

                data = new FishingLoot(items, msgs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (data == null) {
            return new FishingLoot(new ArrayList<>(), new ArrayList<>());
        } else {
            return data;
        }
    }

    public static class FishingLoot {
        private List<FishingLootItem> items;
        private List<FishingLootMessage> secretMessages;

        FishingLoot(List<FishingLootItem> items, List<FishingLootMessage> secretMessages) {
            this.items = items;
            this.secretMessages = secretMessages;
        }

        public List<FishingLootItem> getItems() {
          return items;
        }

        public List<FishingLootMessage> getSecretMessages() {
          return secretMessages;
        }
    }

    public static class FishingLootItem {
        private List<String> items;
        private int chance;

        FishingLootItem(List<String> items, int chance) {
            this.items = items;
            this.chance = chance;
        }

        public List<String> getItems() {
          return items;
        }

        public int getChance() {
          return chance;
        }
    }

    public static class FishingLootMessage {
        private String message;
        private int chance;

        FishingLootMessage(String message, int chance) {
            this.message = message;
            this.chance = chance;
        }

        public int getChance() {
          return chance;
        }

        public String getMessage() {
          return message;
        }
    }
}
