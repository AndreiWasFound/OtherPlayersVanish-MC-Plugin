package andreiwasfound.otherplayersvanish;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {

    private Main plugin;
    public Events(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!(plugin.getvanishedplayers().contains(e.getPlayer()))) {
            plugin.addvanishedplayers(e.getPlayer());
        }
    }
}
