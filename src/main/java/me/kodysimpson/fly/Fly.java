package me.kodysimpson.fly;

import me.kodysimpson.fly.commands.FlyCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Fly extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("fly").setExecutor(new FlyCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
