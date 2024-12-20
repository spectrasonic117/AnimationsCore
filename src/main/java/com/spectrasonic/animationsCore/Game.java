package com.spectrasonic.animationsCore;

import co.aikar.taskchain.TaskChain;
import com.spectrasonic.animationsCore.Utils.FrameUtils;

import org.bukkit.Bukkit;

import java.util.List;
import java.util.Objects;

public class Game {

    public Game(Main instance) {
        Main instance1 = Objects.requireNonNull(instance, "Core instance cannot be null");
    }

    /**
     * Creates an animation effect using title displays.
     *
     * @param numeric Use numeric or character-based frames
     * @param frameDelay Delay between frames in server ticks
     * @param from Starting frame index
     * @param until Ending frame index
     */
    public void createAnimation(boolean numeric, int frameDelay, int from, int until) {
        var chain = Main.newChain();

        List<Character> animationFrames = FrameUtils.generateFrames(from, until, numeric);

        animationFrames.forEach(frame ->
                chain.delay(frameDelay)
                        .sync(() ->
                                Bukkit.getOnlinePlayers().forEach(player ->
                                        player.sendTitle(frame.toString(), "", 0, 20, 0)
                                )
                        )
        );

        chain.sync(TaskChain::abort).execute();
    }
}