package xyz.vectlabs.ultrahoppers.core.gui;

import org.bukkit.inventory.Inventory;
import xyz.vectlabs.ultrahoppers.UltraHoppers;
import xyz.vectlabs.ultrahoppers.core.UHFilter;
import xyz.vectlabs.ultrahoppers.core.UltraHopper;
import xyz.vectlabs.ultrahoppers.spigui.SGMenu;

import java.util.HashMap;
import java.util.UUID;

public class UHFilterGui extends SGMenu {

    private Inventory cachedInv;
    private UHFilter filter;

    public UHFilterGui(UHFilter filter) {
        super(UltraHoppers.plugin, UltraHoppers.plugin.getGuiManager(), "FILTER", 5, null);
        this.filter = filter;
        setIcons();
        this.cachedInv = getInventory();
    }

    private void setIcons(){

    }

    public UHFilter getUHFilter() {
        return filter;
    }

    public Inventory getCachedInv() {
        return cachedInv;
    }


}
