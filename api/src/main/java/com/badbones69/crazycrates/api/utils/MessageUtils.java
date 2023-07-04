package com.badbones69.crazycrates.api.utils;

import com.ryderbelserion.stick.paper.utils.AdventureUtils;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

public class MessageUtils {

    public static void send(Audience audience, String component, boolean isEnabled, String prefix) {
        audience.sendMessage(isEnabled ? AdventureUtils.parse(prefix, false).append(AdventureUtils.parse(component, false)) : AdventureUtils.parse(component, false));
    }

    public static void hover(Audience audience, String message, String text, String value, ClickEvent.Action action) {
        Component textComponent = AdventureUtils.parse(message, false)
                .hoverEvent(HoverEvent.showText(AdventureUtils.parse(text, false)))
                .clickEvent(ClickEvent.clickEvent(action, value));

        audience.sendMessage(textComponent);
    }

    public static void hover(Audience audience, String message, String text, String button, String value, ClickEvent.Action action) {
        Component textComponent = AdventureUtils.parse(message, false)
                .append(AdventureUtils.parse(button, false).hoverEvent(HoverEvent.showText(AdventureUtils.parse(text, false))))
                .clickEvent(ClickEvent.clickEvent(action, value));

        audience.sendMessage(textComponent);
    }
}