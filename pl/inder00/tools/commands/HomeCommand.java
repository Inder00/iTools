package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.tools.Tools;
import pl.inder00.tools.basic.Teleport;
import pl.inder00.tools.basic.User;
import pl.inder00.tools.basic.utils.UserUtil;
import pl.inder00.tools.files.Config;

public class HomeCommand implements CommandExecutor {

    Tools plugin;

    public HomeCommand(Tools plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("home").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Ta komenda jest obslugiwana przez gre!");
            return false;
        }

        Player p = (Player) sender;
        Config cfg = Config.getInst();

        if(p.hasPermission("tools.home")){
            User u = UserUtil.get(p.getUniqueId());

            if(u.getHome() == null){
                p.sendMessage(cfg.home$notset);
                return false;
            }

            new Teleport(p,u.getHome(),cfg.teleport$delay).setIgnoreHighestBlock(true);
            p.sendMessage(cfg.home$cooldown);
            return true;
        } else {
            p.sendTitle("",cfg.no$permissions);
            p.sendMessage(cfg.no$permissions);
            return false;
        }
    }

}
