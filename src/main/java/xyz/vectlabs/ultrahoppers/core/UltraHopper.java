package xyz.vectlabs.ultrahoppers.core;

import org.bukkit.Location;
import org.bukkit.block.Hopper;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import xyz.vectlabs.ultrahoppers.UltraHoppers;

import java.io.Serializable;
import java.util.UUID;

public class UltraHopper implements Serializable {

    private String tier;
    private int itemPerCycle;
    private UHFilter filter;
    private Location location;
    private UUID uuid;

    public UltraHopper(Hopper hopper,String tier, UHFilter filter){
        location = hopper.getLocation();
        this.tier = tier;
        this.filter = filter;
        this.uuid = UUID.randomUUID();
    }

    public boolean isValid(){
        //TODO: LOCATION CHECK
        return UltraHoppers.plugin.getConfigManager().getDefinedUHTiers().containsKey(tier);
    }

    public void pull(InventoryMoveItemEvent e){

    }

    public void push(InventoryMoveItemEvent e) {

    }

    public void save(){

    }


    private UHTier getNextTier(){
        int i = UltraHoppers.plugin.getConfigManager().getSortedUHTiers().indexOf(this.tier)+1;
        if (i >= UltraHoppers.plugin.getConfigManager().getSortedUHTiers().size())
            return null;
        return UltraHoppers.plugin.getConfigManager().getDefinedUHTiers()
                .get(
                        UltraHoppers.plugin.getConfigManager().getSortedUHTiers().get(i)
                );
    }

    private UHTier getPreviousTier(){
        int i = UltraHoppers.plugin.getConfigManager().getSortedUHTiers().indexOf(this.tier)-1;
        if (i < 0)
            return null;
        return UltraHoppers.plugin.getConfigManager().getDefinedUHTiers()
                .get(
                        UltraHoppers.plugin.getConfigManager().getSortedUHTiers().get(i)
                );
    }

    public String getTierName() {
        return tier;
    }

    public UHTier getTier(){
        return UltraHoppers.plugin.getConfigManager().getDefinedUHTiers().get(tier);
    }

    public void upgradeTier() {

    }

    public void downgradeTier(){

    }

    public int getItemPerCycle() {
        return itemPerCycle;
    }

    public void setItemPerCycle(int itemPerCycle) {
        this.itemPerCycle = itemPerCycle;
    }

    public UHFilter getFilter() {
        return filter;
    }

    public Location getLocation() {
        return location;
    }

    public UUID getUuid() {
        return uuid;
    }
}
