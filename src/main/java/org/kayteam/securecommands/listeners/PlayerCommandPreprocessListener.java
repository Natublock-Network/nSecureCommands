package org.kayteam.securecommands.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.kayteam.securecommands.SecureCommands;

public class PlayerCommandPreprocessListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().replaceFirst("/", "");
        Player player = event.getPlayer();
        for(String cmd : SecureCommands.getCommandManager().getCommands()){
            if(command.startsWith(cmd)){
                event.setCancelled(true);
                if(SecureCommands.getCommandManager().getUsers().containsKey(player.getName())){
                    SecureCommands.getCommandManager().getCacheCommands().put(event.getPlayer(), command);
                    SecureCommands.getMessages().sendMessage(event.getPlayer(), "enterPassword");
                }else{
                    SecureCommands.getMessages().sendMessage(event.getPlayer(), "notAuthorized");
                }
                break;
            }
        }
    }
}
