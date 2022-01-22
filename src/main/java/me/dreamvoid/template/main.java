package me.dreamvoid.template;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class main extends JavaPlugin implements Listener {
    public static YamlConfiguration config;

    @Override // 加载插件
    public void onLoad() {

    }

    @Override // 启用插件
    public void onEnable() {
        LoadConfig();
        Bukkit.getPluginManager().registerEvents(this,this); // 注册事件监听
        getCommand("some-command").setExecutor(this); // 注册插件命令
    }

    @Override // 禁用插件
    public void onDisable() {

    }

    private void LoadConfig() {
        // 加载配置文件
        File configure = new File(getDataFolder(), "config.yml");
        if(!(configure.exists())){ saveDefaultConfig(); }
        config = YamlConfiguration.loadConfiguration(configure);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }
}
