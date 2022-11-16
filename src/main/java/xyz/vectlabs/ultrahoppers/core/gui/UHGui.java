package xyz.vectlabs.ultrahoppers.core.gui;

import org.bukkit.inventory.Inventory;
import xyz.vectlabs.ultrahoppers.UltraHoppers;
import xyz.vectlabs.ultrahoppers.core.UltraHopper;
import xyz.vectlabs.ultrahoppers.spigui.SGMenu;

import java.util.HashMap;
import java.util.UUID;

public class UHGui extends SGMenu {

    public static HashMap<UUID, UHGui> GUIS = new HashMap<>();

    public static UHGui getUHGui(UltraHopper hopper){
        UHGui gui = GUIS.get(hopper.getUuid());
        if (gui == null)gui = new UHGui(hopper);

        return gui;

    }

    private Inventory cachedInv;
    private UltraHopper ultraHopper;
    public UHGui(UltraHopper ultraHopper) {
        super(UltraHoppers.plugin, UltraHoppers.plugin.getGuiManager(), ultraHopper.getTier().getDisplayName(), 5, null);
        this.ultraHopper = ultraHopper;
        setIcons();
        this.cachedInv = getInventory();
        setOnClose( (menu, e) ->{
            this.ultraHopper.save();
        });
        GUIS.put(ultraHopper.getUuid(), this);
    }

    private void setIcons(){

    }

    public UltraHopper getUltraHopper() {
        return ultraHopper;
    }

    public Inventory getCachedInv() {
        return cachedInv;
    }
}
