package org.kayteam.securecommands.util;

import org.kayteam.securecommands.SecureCommands;

public class Debug {

    public static void info(String message){
        SecureCommands.getInstance().getLogger().info(message);
    }
}
