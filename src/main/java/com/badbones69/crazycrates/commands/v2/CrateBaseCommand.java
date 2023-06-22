package com.badbones69.crazycrates.commands.v2;

import com.badbones69.crazycrates.CrazyCrates;

public class CrateBaseCommand {

    private final CrazyCrates plugin = CrazyCrates.getPlugin(CrazyCrates.class);

    private void generateHelp(int page, int maxPage) {
        int start = maxPage * (page - 1);
    }
}