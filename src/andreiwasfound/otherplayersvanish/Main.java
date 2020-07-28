package andreiwasfound.otherplayersvanish;

import andreiwasfound.otherplayersvanish.utilities.MetricsLite;
import andreiwasfound.otherplayersvanish.utilities.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    private List<Player> vanishedplayers = new ArrayList<Player>();

    @Override
    public void onEnable() {
        printToConsole("UpdateChecker is trying to register");
        updateChecker();
        printToConsole("UpdateChecker has been registered successfully");
        printToConsole("Commands are trying to register");
        registerCommands();
        printToConsole("Commands have been registered successfully");
        printToConsole("Events are trying to register");
        registerEvents();
        printToConsole("Events have been registered successfully");
        printToConsole("bStats is trying to register");
        int pluginId = 8328;
        MetricsLite metrics = new MetricsLite(this, pluginId);
        printToConsole("bStats has been registered successfully");
    }

    public void addvanishedplayers(Player player) {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(onlinePlayers);
        }
        vanishedplayers.add(player);
    }

    public void removevanishedplayers(Player player) {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            player.showPlayer(onlinePlayers);
        }
        vanishedplayers.remove(player);
    }

    public List<Player> getvanishedplayers() {
        return vanishedplayers;
    }

    public boolean hasvanishedplayers() {
        if (vanishedplayers.isEmpty())
            return false;
        return true;
    }

    @Override
    public void onDisable() {

    }

    public void registerCommands() {
        getCommand("vanishotherplayers").setExecutor(new Command(this));
    }

    private void registerEvents() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new Events(this), this);
    }

    public void printToConsole(String msg) {
        this.getServer().getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "OtherPlayersVanish" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET + " " + msg);
    }

    public void updateChecker() {
        new UpdateChecker(this, 80958).getLatestVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                printToConsole("OtherPlayersVanish is up to date!");
            } else {
                printToConsole("OtherPlayersVanish is outdated!");
                printToConsole("Newest version: " + version);
                printToConsole("Your version: " + configVersion);
                printToConsole("Please Update Here: " + configWebsite);
            }
        });
    }

    PluginDescriptionFile config = this.getDescription();
    String configVersion = config.getVersion();
    String configWebsite = config.getWebsite();
}
