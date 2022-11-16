package xyz.vectlabs.ultrahoppers.core;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class UHFilter {
    private List<ItemStack> filterItems;
    //false - will not allow the filterItems to pass.
    //true = will allow only the filterItems to pass.
    private boolean invert;
    private int size;

    public UHFilter(int size) {
        this.size = size;
        this.filterItems = new ArrayList<>();
    }

    public boolean shouldPass(ItemStack item){
        return true;
    }

    public List<ItemStack> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<ItemStack> filterItems) {
        this.filterItems = filterItems;
    }

    public boolean isInvert() {
        return invert;
    }

    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
