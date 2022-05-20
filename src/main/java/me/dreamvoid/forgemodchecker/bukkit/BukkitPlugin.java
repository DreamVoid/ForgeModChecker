package me.dreamvoid.forgemodchecker.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class BukkitPlugin extends JavaPlugin implements Listener {
    static BukkitPlugin INSTANCE;
    public static YamlConfiguration config;

    @Override // 加载插件
    public void onLoad() {
        INSTANCE = this;
    }

    @Override // 启用插件
    public void onEnable() {
        //LoadConfig();
        Bukkit.getPluginManager().registerEvents(this,this); // 注册事件监听
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "FML|HS");
        //getCommand("some-command").setExecutor(this); // 注册插件命令
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

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                /*e.getPlayer().sendPluginMessage(BukkitPlugin.INSTANCE, "FML", new byte[] {0, 0, 0, 0, 0, 2});
                e.getPlayer().sendPluginMessage(BukkitPlugin.INSTANCE, "FML|HS", new byte[] {-2, 0});
                e.getPlayer().sendPluginMessage(BukkitPlugin.INSTANCE, "FML|HS", new byte[] {0, 2, 0, 0, 0, 0});
                e.getPlayer().sendPluginMessage(BukkitPlugin.INSTANCE, "FML|HS", new byte[] {2, 0, 0, 0, 0});*/
                sendFmlPacket(e.getPlayer(), (byte) -2, (byte) 0);
                sendFmlPacket(e.getPlayer(), (byte) 0, (byte) 2, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
                sendFmlPacket(e.getPlayer(), (byte) 2, (byte) 0, (byte) 0, (byte) 0, (byte) 0);
            }
        }.runTaskLater(BukkitPlugin.INSTANCE, 20L);
    }

    private void sendFmlPacket(Player player, byte... data)
    {
        player.sendPluginMessage(this, "FML|HS", data);
    }
}
