package pl.inder00.tools.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.inder00.tools.basic.User;
import pl.inder00.tools.basic.utils.UserUtil;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player p = event.getEntity();
        User u = UserUtil.get(p.getUniqueId());
        u.setDeathLocation(p.getLocation());
        event.setDeathMessage(null);
    }

}
