package com.badbones69.crazycrates.api.crates;

import com.ryderbelserion.stick.paper.utils.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public class CrateManager {

    private final JavaPlugin plugin;

    public CrateManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadCrates() {
        File cratesDir = new File(this.plugin.getDataFolder(), "crates");

        if (!cratesDir.exists()) {
            if (!cratesDir.mkdirs()) {
                this.plugin.getLogger().severe("Could not create crates directory! " + cratesDir.getAbsolutePath());
                return;
            }

            FileUtils.extract("/crates/", this.plugin.getDataFolder().toPath(), false);
        }

        File[] crateList = cratesDir.listFiles((dir, name) -> name.endsWith(".yml"));

        if (crateList == null) {
            this.plugin.getLogger().severe("Could not read crates directory! " + cratesDir.getAbsolutePath());
            return;
        }

        for (File file : crateList) {
            this.plugin.getLogger().info("Loading crate: " + file.getName());

            CrateConfig crateConfig = new CrateConfig(file);

            crateConfig.load();

            // TODO() Create new "Crate" object
            // TODO() Get locations of crate if it exists then add them.

            // TODO() Add crate to crates map.
        }
    }

    private void unloadCrates() {
        // TODO() unload crates, close inventories, remove holograms, clear hashmap.
    }
}