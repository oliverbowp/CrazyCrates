package com.badbones69.crazycrates.api.crates;

import com.badbones69.crazycrates.objects.Crate;
import com.ryderbelserion.stick.paper.utils.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrateManager {
    
    private final JavaPlugin instance;

    private final ArrayList<Crate> crates = new ArrayList<>();

    public CrateManager(JavaPlugin instance) {
        this.instance = instance;
    }

    public void loadCrates() {
        this.crates.clear();

        File cratesDir = new File(this.instance.getDataFolder(), "crates");

        if (!cratesDir.exists()) {
            if (!cratesDir.mkdirs()) {
                this.instance.getLogger().severe("Could not create crates directory! " + cratesDir.getAbsolutePath());
                return;
            }

            FileUtils.extract("/crates/", this.instance.getDataFolder().toPath(), false);
        }

        File[] crateList = cratesDir.listFiles((dir, name) -> name.endsWith(".yml"));

        if (crateList == null) {
            this.instance.getLogger().severe("Could not read crates directory! " + cratesDir.getAbsolutePath());
            return;
        }

        for (File file : crateList) {
            this.instance.getLogger().info("Loading crate: " + file.getName());

            CrateConfig crateConfig = new CrateConfig(file);

            crateConfig.load();

            Crate crate = new Crate(crateConfig);

            //TODO() Get locations of crate if it exists then add them.

            this.crates.add(crate);
        }
    }

    private void unloadCrates() {
        this.crates.clear();

        //TODO() unload crates, close inventories, remove holograms, clear hashmap.
    }

    public List<Crate> getCrates() {
        return Collections.unmodifiableList(crates);
    }
}