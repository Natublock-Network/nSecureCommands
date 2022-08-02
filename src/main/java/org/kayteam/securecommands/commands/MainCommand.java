package org.kayteam.securecommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.kayteam.securecommands.SecureCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0){
            switch (args[0].toLowerCase(Locale.ROOT)){
                case "reload":{
                    SecureCommands.getSettings().reloadYamlFile();
                    SecureCommands.getMessages().reloadYamlFile();
                    SecureCommands.getCommandManager().load();
                    SecureCommands.getMessages().sendMessage(sender, "pluginReloaded");
                    break;
                }
                case "addplayer":{
                    if(!(sender instanceof Player)){
                        if(args.length > 2){
                            String playerName = args[1];
                            String password = args[2];
                            if(!SecureCommands.getCommandManager().getUsers().containsKey(playerName)){
                                SecureCommands.getSettings().set("users." + playerName, password);
                                SecureCommands.getCommandManager().getUsers().put(playerName, password);
                                SecureCommands.getSettings().saveYamlFile();
                                SecureCommands.getMessages().sendMessage(sender, "playerAddedCorrectly");
                            }else{
                                SecureCommands.getMessages().sendMessage(sender, "alreadyPlayerAdded");
                            }
                        }else{
                            SecureCommands.getMessages().sendMessage(sender, "commandUsage", new String[][]{{"%usage%", "/seccmds addplayer <name> <password>"}});
                        }
                    }else{
                        SecureCommands.getMessages().sendMessage(sender, "onlyConsole");
                    }
                    break;
                }
                case "removeplayer":{
                    if(!(sender instanceof Player)){
                        if(args.length > 1){
                            String playerName = args[1];
                            if(SecureCommands.getCommandManager().getUsers().containsKey(playerName)){
                                SecureCommands.getSettings().set("users." + playerName, "");
                                SecureCommands.getCommandManager().getUsers().remove(playerName);
                                SecureCommands.getSettings().saveYamlFile();
                                SecureCommands.getMessages().sendMessage(sender, "playerRemovedCorrectly");
                            }else{
                                SecureCommands.getMessages().sendMessage(sender, "playerNotAdded");
                            }
                        }else{
                            SecureCommands.getMessages().sendMessage(sender, "commandUsage", new String[][]{{"%usage%", "/seccmds removeplayer <name>"}});
                        }
                    }else{
                        SecureCommands.getMessages().sendMessage(sender, "onlyConsole");
                    }
                    break;
                }
                case "addcommand":{
                    if(!(sender instanceof Player)){
                        if(args.length > 1){
                            String cmd = args[1];
                            if(!SecureCommands.getCommandManager().getCommands().contains(cmd)){
                                List<String> commands = SecureCommands.getSettings().getStringList("users");
                                commands.add(cmd);
                                SecureCommands.getCommandManager().getCommands().add(cmd);
                                SecureCommands.getSettings().set("commands", commands);
                                SecureCommands.getSettings().saveYamlFile();
                                SecureCommands.getMessages().sendMessage(sender, "commandAddedCorrectly");
                            }else{
                                SecureCommands.getMessages().sendMessage(sender, "commandAlreadyAdded");
                            }
                        }else{
                            SecureCommands.getMessages().sendMessage(sender, "commandUsage", new String[][]{{"%usage%", "/seccmds addcommand <command>"}});
                        }
                    }else{
                        SecureCommands.getMessages().sendMessage(sender, "onlyConsole");
                    }
                    break;
                }
                case "removecommand":{
                    if(!(sender instanceof Player)){
                        if(args.length > 1){
                            String cmd = args[1];
                            if(SecureCommands.getCommandManager().getCommands().contains(cmd)){
                                List<String> commands = SecureCommands.getSettings().getStringList("users");
                                commands.remove(cmd);
                                SecureCommands.getCommandManager().getCommands().remove(cmd);
                                SecureCommands.getSettings().set("commands", commands);
                                SecureCommands.getSettings().saveYamlFile();
                                SecureCommands.getMessages().sendMessage(sender, "commandRemovedCorrectly");
                            }else{
                                SecureCommands.getMessages().sendMessage(sender, "commandNotAdded");
                            }
                        }else{
                            SecureCommands.getMessages().sendMessage(sender, "commandUsage", new String[][]{{"%usage%", "/seccmds removecommand <command>"}});
                        }
                    }else{
                        SecureCommands.getMessages().sendMessage(sender, "onlyConsole");
                    }
                    break;
                }
                case "help":{
                    SecureCommands.getMessages().sendMessage(sender, "help");
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> tabs = new ArrayList<>();
        if(args.length == 1){
            tabs.add("reload");
            tabs.add("help");
        }
        return tabs;
    }
}
