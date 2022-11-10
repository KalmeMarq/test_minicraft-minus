package minicraft.item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;

import org.json.JSONObject;
import minicraft.core.Game;

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
                String fishingJson = reader.lines().collect(Collectors.joining("\n"));

                JSONObject json = new JSONObject(fishingJson);

                List<FishingLootItem> items = new ArrayList<>();
                List<FishingLootMessage> msgs = new ArrayList<>();

                if (json.has("loot")) {
                    JSONArray loot = json.getJSONArray("loot");

                    for (Object object : loot) {
                        JSONObject obj = (JSONObject) object;

                        if (obj.has("message")) {
                            String msg = obj.getString("message");
                            int chance = obj.getInt("chance");

                            msgs.add(new FishingLootMessage(msg, chance));
                        } else if (obj.has("item")) {
                            List<String> its = new ArrayList<>();
                            int chance = obj.getInt("chance");

                            if (obj.get("item") instanceof JSONArray) {
                                for (Object object1 : obj.getJSONArray("item")) {
                                    its.add((String) object1);
                                }
                            } else {
                                its.add(obj.getString("item"));
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
