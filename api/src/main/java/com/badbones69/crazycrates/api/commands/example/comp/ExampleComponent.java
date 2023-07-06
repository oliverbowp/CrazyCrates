package com.badbones69.crazycrates.api.commands.example.comp;

import com.badbones69.crazycrates.api.commands.builder.ComponentBuilder;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.entity.Player;

public class ExampleComponent {

    public static void test(Player player) {
        ComponentBuilder builder = new ComponentBuilder();

        builder.setMessage("<red>An example message</red>");

        ComponentBuilder.PreciseComponentBuilder preciseBuilder = builder.getPreciseComponent();

        preciseBuilder
                .text(" <blue>Guten Tag</blue>")
                .hoverText("<green>Click Me</green>")
                .click("<green>You clicked me!</green>", ClickEvent.Action.COPY_TO_CLIPBOARD);

        player.sendMessage(builder.build());
    }
}