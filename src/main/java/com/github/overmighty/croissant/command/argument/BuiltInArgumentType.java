package com.github.overmighty.croissant.command.argument;

import com.github.overmighty.croissant.util.CroissantUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * A built-in {@link ArgumentType}.
 */
public enum BuiltInArgumentType {

    /**
     * Represents a {@link Player}.
     * <p>
     * Accepts exact usernames and UUIDs of online players, case-insensitively.
     * <p>
     * Suggests matching usernames of online players for argument completion.
     */
    PLAYER(
        Player.class,
        new ArgumentType((ArgumentResolver<Player>) value -> {
            if (CroissantUtil.getPlayerUUIDPattern().matcher(value).matches()) {
                return Bukkit.getPlayer(UUID.fromString(value));
            }

            return Bukkit.getPlayerExact(value);
        }, (value, sender) -> {
            List<String> completions = new ArrayList<>();

            for (Player player : Bukkit.getOnlinePlayers()) {
                boolean shouldBeSuggested = StringUtil.startsWithIgnoreCase(player.getName(), value)
                    && (!(sender instanceof Player) || ((Player) sender).canSee(player));

                if (shouldBeSuggested) {
                    completions.add(player.getName());
                }
            }

            completions.sort(String.CASE_INSENSITIVE_ORDER);
            return completions;
        })
    ),
    /**
     * Represents an {@link OfflinePlayer}.
     * <p>
     * Accepts exact usernames of online players and UUIDs of players, whether
     * online or offline, case-insensitively.
     * <p>
     * Uses {@link BuiltInArgumentType#PLAYER}'s {@link ArgumentCompleter}.
     */
    OFFLINE_PLAYER(
        OfflinePlayer.class,
        new ArgumentType((ArgumentResolver<OfflinePlayer>) value -> {
            if (CroissantUtil.getPlayerUUIDPattern().matcher(value).matches()) {
                return Bukkit.getOfflinePlayer(UUID.fromString(value));
            }

            return Bukkit.getPlayerExact(value);
        }, PLAYER.getArgumentType().getCompleter())
    ),
    /**
     * Represents a {@link World}.
     * <p>
     * Accepts names of server worlds.
     * <p>
     * Suggests matching world names for argument completion.
     */
    WORLD(
        World.class,
        new ArgumentType(
            (ArgumentResolver<World>) Bukkit::getWorld,
            (value, sender) -> {
                List<String> completions = new ArrayList<>();

                for (World world : Bukkit.getWorlds()) {
                    if (StringUtil.startsWithIgnoreCase(world.getName(), value)) {
                        completions.add(world.getName());
                    }
                }

                return completions;
            }
        )
    ),
    /**
     * Represents a {@link String}.
     * <p>
     * Returns the raw argument as it was provided by the command sender.
     * <p>
     * Uses {@link BuiltInArgumentType#PLAYER}'s {@link ArgumentCompleter}.
     */
    STRING(
        String.class,
        new ArgumentType(
            (ArgumentResolver<String>) value -> value,
            PLAYER.getArgumentType().getCompleter()
        )
    ),
    /**
     * Represents a {@link Boolean}.
     * <p>
     * Accepts {@code true} and {@code false}, case-insensitively.
     * <p>
     * Suggests those same values for argument completion, if they match.
     */
    BOOLEAN(
        Boolean.class,
        new ArgumentType((ArgumentResolver<Boolean>) value -> {
            if (value.equalsIgnoreCase("true")) {
                return true;
            }

            if (value.equalsIgnoreCase("false")) {
                return false;
            }

            return null;
        }, (value, sender) -> {
            value = value.toLowerCase();

            if (value.equals("")) {
                return Arrays.asList("true", "false");
            }

            if ("true".startsWith(value)) {
                return Collections.singletonList("true");
            }

            if ("false".startsWith(value)) {
                return Collections.singletonList("false");
            }

            return Collections.emptyList();
        })
    ),
    /**
     * Represents a {@link Byte}.
     * <p>
     * Accepts any value that {@link Byte#parseByte(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    BYTE(
        Byte.class,
        new ArgumentType((ArgumentResolver<Byte>) value -> {
            try {
                return Byte.parseByte(value);
            } catch (NumberFormatException e) {
                return null;
            }
        })
    ),
    /**
     * Represents a {@link Short}.
     * <p>
     * Accepts any value that {@link Short#parseShort(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    SHORT(
        Short.class,
        new ArgumentType((ArgumentResolver<Short>) value -> {
            try {
                return Short.parseShort(value);
            } catch (NumberFormatException e) {
                return null;
            }
        })
    ),
    /**
     * Represents an {@link Integer}.
     * <p>
     * Accepts any value that {@link Integer#parseInt(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    INTEGER(
        Integer.class,
        new ArgumentType((ArgumentResolver<Integer>) value -> {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return null;
            }
        })
    ),
    /**
     * Represents a {@link Long}.
     * <p>
     * Accepts any value that {@link Long#parseLong(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    LONG(
        Long.class,
        new ArgumentType((ArgumentResolver<Long>) value -> {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                return null;
            }
        })
    ),
    /**
     * Represents a {@link Float}.
     * <p>
     * Accepts any value that {@link Float#parseFloat(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    FLOAT(
        Float.class,
        new ArgumentType((ArgumentResolver<Float>) value -> {
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException e) {
                return null;
            }
        })
    ),
    /**
     * Represents a {@link Double}.
     * <p>
     * Accepts any value that {@link Double#parseDouble(String)} accepts.
     * <p>
     * Does not suggest completions for arguments.
     */
    DOUBLE(
        Double.class,
        new ArgumentType((ArgumentResolver<Double>) value -> {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return null;
            }
        })
    );

    private final Class<?> parameterType;
    private final ArgumentType argumentType;

    BuiltInArgumentType(Class<?> parameterType, ArgumentType argumentType) {
        this.parameterType = parameterType;
        this.argumentType = argumentType;
    }

    /**
     * Returns the type of command executor method parameters to which the
     * {@link ArgumentType} should be bound.
     *
     * @return the type of command executor method parameters
     */
    public Class<?> getParameterType() {
        return parameterType;
    }

    /**
     * Returns the actual {@link ArgumentType}.
     *
     * @return the {@link ArgumentType}
     */
    public ArgumentType getArgumentType() {
        return argumentType;
    }

}
