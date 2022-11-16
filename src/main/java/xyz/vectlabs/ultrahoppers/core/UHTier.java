package xyz.vectlabs.ultrahoppers.core;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import xyz.vectlabs.ultrahoppers.UltraHoppers;

public class UHTier {
    
    public static void save(UHTier tier){
        YamlConfiguration config = UltraHoppers.plugin.getConfigManager().getConfig();
        String tierName = tier.getName();
        config.set("hopper-tiers." + tierName +".display-name", tier.getDisplayName());
        config.set("hopper-tiers." + tierName + ".weight", tier.getWeight());
        config.set("hopper-tiers." + tierName + ".cost", tier.getCost());
        config.set("hopper-tiers." + tierName + ".filter-capacity", tier.getFilterCapacity());
        config.set("hopper-tiers." + tierName + ".max-item-per-cycle", tier.getMaxItemPerCycle());
        UltraHoppers.plugin.getConfigManager().saveConfigs();
        UltraHoppers.plugin.getConfigManager().init();
    }

    private final String name;
    private final String displayName;
    private final ItemStack cost;
    private final int weight;
    private final int filterCapacity;
    private final int maxItemPerCycle;

    public UHTier(String name, String displayName, int weight, ItemStack cost, int filterCapacity, int maxItemPerCycle) {
        this.name = name;
        this.cost = cost;
        this.filterCapacity = filterCapacity;
        this.maxItemPerCycle = maxItemPerCycle;
        this.weight = weight;
        this.displayName = displayName==null?this.name: ChatColor.translateAlternateColorCodes('&',displayName);
    }

    public int getWeight() {
        return weight;
    }

    public String getDisplayName(){
        return this.displayName;
    }

    public String getName() {
        return name;
    }

    public ItemStack getCost() {
        return cost;
    }

    public int getFilterCapacity() {
        return filterCapacity;
    }

    public int getMaxItemPerCycle() {
        return maxItemPerCycle;
    }
}
