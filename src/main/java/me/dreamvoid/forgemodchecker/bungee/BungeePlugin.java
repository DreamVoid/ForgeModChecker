package me.dreamvoid.forgemodchecker.bungee;

import me.dreamvoid.forgemodchecker.Config;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class BungeePlugin extends Plugin {
    public static BungeePlugin INSTANCE;
    Configuration configuration;
    static HashMap<ProxiedPlayer, Map<String,String>> modlist = new HashMap<>();

    @Override
    public void onLoad() {
        INSTANCE = this;
        try {
            if (!getDataFolder().exists())
                getDataFolder().mkdir();
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                try (InputStream in = getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                }
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            getLogger().warning("An error occurred while load configuration, reason: " + e);
        }
    }

    @Override
    public void onEnable() {
        Config.Settings_LogPlayerMods = configuration.getBoolean("settings.log-player-mods");
        getProxy().getPluginManager().registerListener(this, new Events());
    }
}
