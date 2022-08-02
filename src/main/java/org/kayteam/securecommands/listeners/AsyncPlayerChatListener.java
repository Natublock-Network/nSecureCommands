package org.kayteam.securecommands.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.kayteam.securecommands.SecureCommands;
public class AsyncPlayerChatListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if(SecureCommands.getCommandManager().getCacheCommands().containsKey(player)){
            event.setCancelled(true);
            String password = event.getMessage();
            if(SecureCommands.getCommandManager().getUsers().get(player.getName()).equals(password)){
                String command = SecureCommands.getCommandManager().getCacheCommands().get(player);
                Bukkit.getScheduler().callSyncMethod(SecureCommands.getInstance(), () -> Bukkit.dispatchCommand(player, command));
            }else if(password.equalsIgnoreCase(SecureCommands.getCommandManager().getCancelMessage())){
                SecureCommands.getCommandManager().getCacheCommands().remove(player);
                SecureCommands.getMessages().sendMessage(player, "commandCanceled");
            }else{
                SecureCommands.getMessages().sendMessage(player, "incorrectPassword");
            }
        }
    }
}
