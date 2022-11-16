package xyz.vectlabs.ultrahoppers;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.vectlabs.ultrahoppers.core.config.ConfigManager;
import xyz.vectlabs.ultrahoppers.spigui.SpiGUI;

import java.io.File;


public class UltraHoppers extends JavaPlugin {

    public static UltraHoppers plugin;
    private ConfigManager configManager;
    private SpiGUI guiManager;
    @Override
    public void onEnable() {
        plugin = this;
        configManager = new ConfigManager(
                new File(this.getDataFolder()+"/config.yml"),
                new File(this.getDataFolder()+"/language.yml")
        );
        configManager.init();
        this.guiManager = new SpiGUI(this);

    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public SpiGUI getGuiManager() {
        return guiManager;
    }

    @Override
    public void onDisable() {


    }


}
