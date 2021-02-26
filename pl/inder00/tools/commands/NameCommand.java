package pl.inder00.tools.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.inder00.tools.Tools;
import pl.inder00.tools.files.Config;
import pl.inder00.tools.utils.Util;

public class NameCommand implements CommandExecutor {

    Tools plugin;

    public NameCommand(Tools plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("name").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Ta komenda jest obslugiwana przez gre!");
            return false;
        }

        Player p = (Player) sender;
        Config cfg = Config.getInst();
        if (p.hasPermission("tools.name")) {

            if (args.length == 0) {
                p.sendMessage(Util.fixColor("&cPoprawne uzycie &7/nick <pseudonim>"));
                return false;

            } else if (args.length == 1) {
                String a = args[0];
                p.setDisplayName(Util.fixColor(a+"&r"));
                p.sendMessage(Util.fixColor("&8» &7Ustawiles nowy pseudonim &6" + a));
                return false;

            } else {
                p.sendMessage(Util.fixColor("&cPoprawne uzycie &7/nick <pseudonim>"));
                return false;
            }
        } else {
            p.sendTitle("", cfg.no$permissions);
            p.sendMessage(cfg.no$permissions);
            return false;
        }
    }
}
