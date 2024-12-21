package com.spectrasonic.animationsCore;

import co.aikar.commands.PaperCommandManager;
import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import com.spectrasonic.animationsCore.Utils.MessageUtils;

import java.util.Objects;

public final class Main extends JavaPlugin {
    private static @Getter Main instance;
    private @Getter Game game;
    private @Getter PaperCommandManager commandManager;
    private @Getter TaskChainFactory taskChainFactory;


    @Override
    public void onEnable() {
        instance = this;

        // Initialize components with null-safe methods
        initializeGame();
        initializeTaskChain();
        initializeCommands();

        MessageUtils.sendStartupMessage(this);
    }

    private void initializeGame() {
        this.game = new Game(this);
    }

    private void initializeTaskChain() {
        this.taskChainFactory = BukkitTaskChainFactory.create(this);
    }

    private void initializeCommands() {
        this.commandManager = new PaperCommandManager(this);
        this.commandManager.registerCommand(new AnimationCommand(this));
    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
        if (this.commandManager != null) {
            this.commandManager.unregisterCommands();
        }
    }

    public static <T> TaskChain<T> newChain() {
        return Objects.requireNonNull(getInstance().getTaskChainFactory()).newChain();
    }

    public static <T> TaskChain<T> newSharedChain(String name) {
        return Objects.requireNonNull(getInstance().getTaskChainFactory()).newSharedChain(name);
    }
}