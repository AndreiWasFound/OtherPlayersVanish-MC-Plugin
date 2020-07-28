package andreiwasfound.otherplayersvanish;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

    private Main plugin;
    public Command(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("vanishotherplayers")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Console cannot use this command");
                return true;
            }
            Player player = (Player) sender;
            if (sender.hasPermission("otherplayersvanish.use")) {
                if (plugin.hasvanishedplayers()) {
                    plugin.removevanishedplayers(player);
                    sender.sendMessage(ChatColor.RED + "Other players are now visible!");
                } else if (!(plugin.hasvanishedplayers())) {
                    plugin.addvanishedplayers(player);
                    sender.sendMessage(ChatColor.GREEN + "Other players are now vanished!");
                    return true;
                }
            }
        }
        return false;
    }
}
