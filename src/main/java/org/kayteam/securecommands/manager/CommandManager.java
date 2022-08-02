package org.kayteam.securecommands.manager;

import org.bukkit.entity.Player;
import org.kayteam.securecommands.SecureCommands;
import org.kayteam.securecommands.util.Debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandManager {

    private List<String> commands = new ArrayList<>();
    private HashMap<String, String> users = new HashMap<>();
    private HashMap<Player, String> cacheCommands = new HashMap<>();
    private String cancelMessage = "cancel";

    public CommandManager() {
        load();
    }

    public void load(){
        loadCommands();
        loadUsers();
        loadCancelMessage();
    }

    public void loadCommands(){
        Debug.info("Loading commands...");
        commands.clear();
        if(SecureCommands.getSettings().contains("commands") && SecureCommands.getSettings().isStringList("commands")){
            commands.addAll(SecureCommands.getSettings().getStringList("commands"));
        }
        Debug.info("Commands loaded");
    }

    public void loadUsers(){
        Debug.info("Loading users...");
        users.clear();
        if(SecureCommands.getSettings().contains("commands")){
            try{
                for(String user : SecureCommands.getSettings().getConfigurationSection("users").getKeys(false)){
                    String password = SecureCommands.getSettings().getString("users."+user);
                    users.put(user, password);
                }
            }catch (Exception ignored){}
        }
        Debug.info("Users loaded");
    }

    public void loadCancelMessage(){
        Debug.info("Loading cancel message..");
        if(SecureCommands.getSettings().contains("cancelMessage") && SecureCommands.getSettings().isString("cancelMessage")){
            cancelMessage = SecureCommands.getSettings().getString("cancelMessage");
        }
        Debug.info("Cancel message loaded");
    }

    public List<String> getCommands() {
        return commands;
    }

    public HashMap<String, String> getUsers() {
        return users;
    }

    public HashMap<Player, String> getCacheCommands() {
        return cacheCommands;
    }

    public String getCancelMessage() {
        return cancelMessage;
    }
}
