package com.spectrasonic.animationsCore;

import co.aikar.taskchain.TaskChain;
import com.spectrasonic.animationsCore.Utils.FrameUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Game {
    private final Main instance;
    private static final long TICK_IN_MILLIS = 50L; // 1 tick = 50ms

    public Game(Main instance) {
        this.instance = Objects.requireNonNull(instance, "Core instance cannot be null");
    }

    private static Duration ticksToDuration(int ticks) {
        return Duration.ofMillis(ticks * TICK_IN_MILLIS);
    }

    public void createAnimation(boolean numeric, int frameDelay, int from, int until) {
        var chain = Main.newChain();

        List<Character> animationFrames = FrameUtils.generateFrames(from, until, numeric);

        Title.Times times = Title.Times.times(
                Duration.ZERO,           // Fade in
                ticksToDuration(20),     // Stay - más limpio y legible
                Duration.ZERO            // Fade out
        );

        animationFrames.forEach(frame ->
                chain.delay(frameDelay)
                        .sync(() ->
                                Bukkit.getOnlinePlayers().forEach(player ->
                                        player.showTitle(Title.title(
                                                Component.text(frame.toString()),
                                                Component.empty(),
                                                times
                                        ))
                                )
                        )
        );

        chain.sync(TaskChain::abort).execute();
    }
}