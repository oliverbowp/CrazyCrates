package com.badbones69.crazycrates.cratetypes;

import com.badbones69.crazycrates.CrazyCrates;
import com.badbones69.crazycrates.Methods;
import com.badbones69.crazycrates.api.CrazyManager;
import com.badbones69.crazycrates.api.events.PlayerPrizeEvent;
import com.badbones69.crazycrates.api.objects.Crate;
import com.badbones69.crazycrates.api.objects.Prize;
import com.badbones69.crazycrates.enums.types.KeyType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public class Instant implements Listener {
    
    private static final CrazyCrates plugin = CrazyCrates.getPlugin();
    private static final CrazyManager crazyManager = plugin.getStarter().getCrazyManager();

    public static void openCrate(Player player, Crate crate, KeyType keyType, boolean checkHand) {
        int keys = switch (keyType) {
            case VIRTUAL_KEY -> crazyManager.getVirtualKeys(player, crate);
            case PHYSICAL_KEY -> crazyManager.getPhysicalKeys(player, crate);
            default -> 1;
        }; // If the key is free it is set to one.

        Location loc = player.getLocation();

        if (player.isSneaking() && keys > 1) {
            int keysUsed = 0;
            
            // give the player the prizes
            for (;keys > 0; keys--) {
                if (Methods.isInventoryFull(player)) break;
                if (keysUsed >= crate.getMaxMassOpen()) break;

                Prize prize = crate.pickPrize(player);
                crazyManager.givePrize(player, prize, crate);
                plugin.getServer().getPluginManager().callEvent(new PlayerPrizeEvent(player, crate, crate.getName(), prize));

                if (prize.useFireworks()) Methods.firework(loc.clone().add(.5, 1, .5));
                
                keysUsed++;
            }
            
            if (!crazyManager.takeKeys(keysUsed, player, crate, keyType, checkHand)) {
                Methods.failedToTakeKey(player, crate);
                crazyManager.removePlayerFromOpeningList(player);
                return;
            }

            crazyManager.removePlayerFromOpeningList(player);
        } else {
            if (!crazyManager.takeKeys(1, player, crate, keyType, checkHand)) {
                Methods.failedToTakeKey(player, crate);
                crazyManager.removePlayerFromOpeningList(player);
                return;
            }

            Prize prize = crate.pickPrize(player, loc.clone().add(.5, 1.3, .5));
            crazyManager.givePrize(player, prize, crate);
            plugin.getServer().getPluginManager().callEvent(new PlayerPrizeEvent(player, crate, crate.getName(), prize));

            if (prize.useFireworks()) Methods.firework(loc.clone().add(.5, 1, .5));

            crazyManager.removePlayerFromOpeningList(player);
        }
    }

    @EventHandler
    public void onHopperPickUp(InventoryPickupItemEvent e) {
        if (crazyManager.isDisplayReward(e.getItem())) e.setCancelled(true);
    }
}