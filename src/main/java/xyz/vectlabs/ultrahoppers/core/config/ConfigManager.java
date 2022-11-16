package xyz.vectlabs.ultrahoppers.core.config;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.vectlabs.ultrahoppers.UltraHoppers;
import xyz.vectlabs.ultrahoppers.core.UHTier;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ConfigManager {

    public enum LanguagePath{

        NO_PERMISSION("&cYou do not have the permission to execute this command!");

        private String defaultValue;
        private LanguagePath(String defaultValue){
            this.defaultValue = defaultValue;
        }

        @Override
        public String toString() {
            return this.defaultValue;
        }
    }

    private YamlConfiguration config;
    private File configFile;
    private YamlConfiguration language;
    private File languageFile;
    private final HashMap<String, UHTier> definedUHTiers;
    private final List<String> sortedUHTiers;

    public ConfigManager(File configFile, File languageFile){
        this.configFile = configFile;
        config = new YamlConfiguration();
        this.languageFile = languageFile;
        language = new YamlConfiguration();
        definedUHTiers = new HashMap<>();
        sortedUHTiers = new ArrayList<>();
    }

    public void init(){
        try {
            if (!configFile.exists()) {
                System.out.println("New file has been created: " + configFile.getPath() + "\n");
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            } else {
                System.out.println(configFile.getPath() + " already exists, loading configurations...\n");
            }
            config.load(configFile);

            config.save(configFile);
            readUHTiers();

            if (!languageFile.exists()) {
                System.out.println("New file has been created: " + languageFile.getPath() + "\n");
                languageFile.getParentFile().mkdirs();
                languageFile.createNewFile();
            } else {
                System.out.println(languageFile.getPath() + " already exists, loading configurations...\n");
            }
            language.load(languageFile);
            for(LanguagePath path : LanguagePath.values()){
                language.addDefault(path.name(), path.toString());
            }
            language.save(languageFile);
        } catch (final Exception e) {
            e.printStackTrace();
            Bukkit.getServer().getPluginManager().disablePlugin(UltraHoppers.plugin);
            return;
        }


    }

    public void saveConfigs(){
        try {
            config.save(configFile);
            language.save(languageFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConfig(){
        return this.config;
    }

    public YamlConfiguration getLangConfig() {
        return this.language;
    }

    private void readUHTiers(){
        definedUHTiers.clear();
        sortedUHTiers.clear();
        for(String tierName : this.config.getConfigurationSection("hopper-tiers").getKeys(false)){
            UHTier tier = new UHTier(
                    tierName,
                    this.config.getString("hopper-tiers."+tierName+".display-name"),
                    this.config.getInt("hopper-tiers."+tierName+".weight"),
                    this.config.getItemStack("hopper-tiers."+tierName+".cost"),
                    this.config.getInt("hopper-tiers."+tierName+".filter-capacity"),
                    this.config.getInt("hopper-tiers."+tierName+".max-item-per-cycle")

            );
            definedUHTiers.put(tierName, tier);
            sortedUHTiers.add(tierName);
        }
        sortedUHTiers.sort(Comparator.comparingInt(ob -> definedUHTiers.get(ob).getWeight()));
    }


    public HashMap<String, UHTier> getDefinedUHTiers() {
        return definedUHTiers;
    }

    public List<String> getSortedUHTiers() {
        return sortedUHTiers;
    }
}
