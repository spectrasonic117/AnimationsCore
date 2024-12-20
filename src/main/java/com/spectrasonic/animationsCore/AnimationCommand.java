package com.spectrasonic.animationsCore;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.command.CommandSender;

import java.time.Duration;

@CommandAlias("animation|animations")
@CommandPermission("animation.cmd")
@RequiredArgsConstructor
public class AnimationCommand extends BaseCommand {
    private final Main instance;

    @Default
    @Description("Create an animation with specified parameters")
    public void animation(
            CommandSender sender,
            boolean numeric,
            int frames,
            int from,
            int until
    ) {
        instance.getGame().createAnimation(numeric, frames, from, until);

        sender.sendMessage(Component.text()
                .append(Component.text("[AnimationsCore] ", NamedTextColor.LIGHT_PURPLE))
                .append(Component.text("Created animation: ", NamedTextColor.WHITE))
                .append(Component.text("Frames: " + frames, NamedTextColor.GREEN))
                .append(Component.text(" Range: " + from + "-" + until, NamedTextColor.YELLOW))
                .build());
    }
}