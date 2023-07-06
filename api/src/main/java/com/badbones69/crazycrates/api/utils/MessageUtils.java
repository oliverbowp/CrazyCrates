package com.badbones69.crazycrates.api.utils;

import com.ryderbelserion.stick.paper.utils.AdventureUtils;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

public class MessageUtils {

    public static void send(Audience audience, String component, boolean isEnabled, String prefix) {
        audience.sendMessage(isEnabled ? AdventureUtils.parse(prefix).append(AdventureUtils.parse(component)) : AdventureUtils.parse(component));
    }

    public static void hover(Audience audience, String message, String text, String value, ClickEvent.Action action) {
        Component textComponent = AdventureUtils.parse(message)
                .hoverEvent(HoverEvent.showText(AdventureUtils.parse(text)))
                .clickEvent(ClickEvent.clickEvent(action, value));

        audience.sendMessage(textComponent);
    }

    public static void hover(Audience audience, String message, String text, String button, String value, ClickEvent.Action action) {
        Component textComponent = AdventureUtils.parse(message)
                .append(AdventureUtils.parse(button).hoverEvent(HoverEvent.showText(AdventureUtils.parse(text))))
                .clickEvent(ClickEvent.clickEvent(action, value));

        audience.sendMessage(textComponent);
    }
}