package xyz.vectlabs.ultrahoppers.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.vectlabs.ultrahoppers.UltraHoppers;
import xyz.vectlabs.ultrahoppers.Util;
import xyz.vectlabs.ultrahoppers.commands.handler.SubCommand;
import xyz.vectlabs.ultrahoppers.core.UHTier;

import java.util.List;

public class CreateTierCommand implements SubCommand {
    String format;
    public CreateTierCommand(){
        this.format = "/uh createTier <name> [displayName] <weight> <filter-capacity> <max-item-per-cycle>";
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //             0          1          2            3                   4
        //createTier <name> [displayName] <weight> <filter-capacity> <max-item-per-cycle>

        if (!(sender instanceof Player)){
            sender.sendMessage(Util.color("&cThis command can only be executed by a player."));
            return true;
        }
        Player p = (Player) sender;
        if (args.length<4||args.length>5){
            sender.sendMessage(Util.color("&cCorrect format is: "+format));
            return true;
        }
        if (p.getInventory().getItemInMainHand()==null||p.getInventory().getItemInMainHand().getType()== Material.AIR){
            sender.sendMessage(Util.color("&cPlease hold the cost item in your main hand."));
            return true;
        }
        try {
            ItemStack costItem = p.getInventory().getItemInMainHand();
            String name = args[0];
            int ptr = 1;
            String displayName = name;
            if (args.length > 4) {
                displayName = args[ptr++];
            }
            int weight = Integer.parseInt(args[ptr++]);
            int filterCapacity = Integer.parseInt(args[ptr++]);
            int maxItemPerCycle = Integer.parseInt(args[ptr++]);

            if (UltraHoppers.plugin.getConfigManager().getDefinedUHTiers().containsKey(name)) {
                sender.sendMessage(Util.color("&cA Tier with the same name already exists!"));
                return true;
            }
            UHTier tier = new UHTier(name, displayName, weight, costItem, filterCapacity, maxItemPerCycle);
            UHTier.save(tier);
            sender.sendMessage(Util.color("&aSuccessfully created a new tier."));





        }catch (Exception e){
            if (e instanceof NumberFormatException){
                sender.sendMessage(Util.color("&cPlease make sure the weight, filter-capacity and maxItemPerCycle are valid numbers."));
            }
            sender.sendMessage(Util.color("&cFailed to create a tier. Check console for error logs."));
        }
        return true;
    }

    @Override
    public List<String> suggestTabCompletes(CommandSender sender, Command cmd, String label, String[] args) {
       return null;
    }

    @Override
    public String getName() {
        return "createTier";
    }

    @Override
    public boolean isProtected() {
        return true;
    }

    @Override
    public String getPermission() {
        return "ultrahoppers.command.createTier";
    }



}
