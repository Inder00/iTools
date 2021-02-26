package pl.inder00.tools.utils;

import org.bukkit.ChatColor;

public class Util {

    public static String fixColor(String input){
        return ChatColor.translateAlternateColorCodes('&', input);
    }

}
