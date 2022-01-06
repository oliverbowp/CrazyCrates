package me.badbones69.crazycrates.multisupport;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import me.badbones69.crazycrates.api.CrazyManager;
import me.badbones69.crazycrates.api.enums.CrateType;
import me.badbones69.crazycrates.api.objects.Crate;

import java.text.NumberFormat;

public class MVdWPlaceholderAPISupport {
    
    private static CrazyManager cc = CrazyManager.getInstance();
    
    public static void registerPlaceholders() {
        for (final Crate crate : cc.getCrates()) {
            if (crate.getCrateType() != CrateType.MENU) {
                PlaceholderAPI.registerPlaceholder(CrazyManager.getJavaPlugin(), "crazycrates_" + crate.getName(), e -> NumberFormat.getNumberInstance().format(cc.getVirtualKeys(e.getPlayer(), crate)));
                PlaceholderAPI.registerPlaceholder(CrazyManager.getJavaPlugin(), "crazycrates_" + crate.getName() + "_physical", e -> NumberFormat.getNumberInstance().format(cc.getPhysicalKeys(e.getPlayer(), crate)));
                PlaceholderAPI.registerPlaceholder(CrazyManager.getJavaPlugin(), "crazycrates_" + crate.getName() + "_total", e -> NumberFormat.getNumberInstance().format(cc.getTotalKeys(e.getPlayer(), crate)));
            }
        }
    }
}