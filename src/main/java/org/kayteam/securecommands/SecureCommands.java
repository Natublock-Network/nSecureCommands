package org.kayteam.securecommands;

import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.securecommands.commands.MainCommand;
import org.kayteam.securecommands.listeners.AsyncPlayerChatListener;
import org.kayteam.securecommands.listeners.PlayerCommandPreprocessListener;
import org.kayteam.securecommands.manager.CommandManager;
import org.kayteam.storageapi.storage.Yaml;

import java.util.Objects;

public final class SecureCommands extends JavaPlugin {

    private static Yaml settings;
    private static Yaml messages;
    private static SecureCommands instance;
    private static CommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;
        settings = new Yaml(this, "settings");
        settings.registerYamlFile();
        messages = new Yaml(this, "messages");
        messages.registerYamlFile();
        commandManager = new CommandManager();
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
        Objects.requireNonNull(getCommand("securecommands")).setExecutor(new MainCommand());
        Objects.requireNonNull(getCommand("securecommands")).setTabCompleter(new MainCommand());
    }

    public static Yaml getSettings() {
        return settings;
    }

    public static Yaml getMessages() {
        return messages;
    }

    public static SecureCommands getInstance() {
        return instance;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }
}
