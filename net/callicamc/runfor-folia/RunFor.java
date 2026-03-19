package net.callicamc.runfor-folia;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class RunFor extends JavaPlugin implements CommandExecutor {

    private List<String> blacklist;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        blacklist = getConfig().getStringList("blacklisted-commands");

        if (getCommand("runfor") != null) {
            getCommand("runfor").setExecutor(this);
        }

        getLogger().info("RunFor enabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // check if sender is OP
        if (!sender.isOp()) {
            sender.sendMessage("§cYou must be OP");
            return true;
        }

        // check arguments
        if (args.length < 2) {
            sender.sendMessage("§cUsage: /runfor <player> <command>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cPlayer not found");
            return true;
        }

        // build command string and base command
        String fullCommand = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        String baseCmd = fullCommand.toLowerCase().trim().split(" ")[0].replace("/", "");
        if (baseCmd.contains(":")) baseCmd = baseCmd.split(":")[1];

        // check blacklist
        if (blacklist.contains(baseCmd)) {
            String msg = getConfig().getString("block-message", "&c[Security] Command /%cmd% is blacklisted.");
            sender.sendMessage(msg.replace("&", "§").replace("%cmd%", baseCmd));
            return true;
        }

        // run command safely on player's tick
        Bukkit.getScheduler().runTask(this, () -> {
            PermissiveWrapper wrapper = new PermissiveWrapper(target);
            Bukkit.dispatchCommand(wrapper, fullCommand);
        });

        sender.sendMessage("§aForced " + target.getName() + " to run /" + fullCommand);
        return true;
    }
}
