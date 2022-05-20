package me.dreamvoid.forgemodchecker.bungee;

import me.dreamvoid.forgemodchecker.Config;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Events implements Listener {
    // talk is cheap, show me the code.
    @EventHandler
    public void onPlayerLogin(ServerConnectedEvent e){
        BungeePlugin.INSTANCE.getProxy().getScheduler().schedule(BungeePlugin.INSTANCE, new Runnable() {
            @Override
            public void run() {
        /*e.getPlayer().sendPluginMessage(BungeePlugin.INSTANCE, "FML", new byte[] {0, 0, 0, 0, 0, 2});
        e.getPlayer().sendPluginMessage(BungeePlugin.INSTANCE, "FML|HS", new byte[] {-2, 0});
        e.getPlayer().sendPluginMessage(BungeePlugin.INSTANCE, "FML|HS", new byte[] {0, 2, 0, 0, 0, 0});
        e.getPlayer().sendPluginMessage(BungeePlugin.INSTANCE, "FML|HS", new byte[] {2, 0, 0, 0, 0});*/
                BungeePlugin.modlist.put(e.getPlayer(), e.getPlayer().getModList());
                System.out.println(e.getPlayer().getModList());
                if(Config.Settings_LogPlayerMods){
                    BungeePlugin.INSTANCE.getLogger().info("Player " + e.getPlayer().getName() + "'s mods: " + Arrays.toString(BungeePlugin.modlist.get(e.getPlayer()).keySet().toArray()));
                }
            }
        }, 2, TimeUnit.SECONDS);
    }
}
