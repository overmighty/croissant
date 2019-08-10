package com.github.overmighty.croissant.util;

import java.lang.reflect.Parameter;
import java.util.regex.Pattern;

/**
 * Provides miscellaneous utility methods.
 */
public class CroissantUtil {

    private static final Pattern PLAYER_UUID_PATTERN = Pattern.compile(
        "\\p{XDigit}{8}-\\p{XDigit}{4}-[34]\\p{XDigit}{3}-[89ab]\\p{XDigit}{3}-\\p{XDigit}{12}"
    );

    /**
     * Returns the given method parameter's type if it is a regular parameter,
     * or its component type if the parameter represents variable arguments
     * (varargs).
     *
     * @param param the parameter to get the type of
     * @return the parameter's type, or its component type if the parameter
     *         represents variable arguments
     */
    public static Class<?> getParameterType(Parameter param) {
        if (param.isVarArgs()) {
            return param.getType().getComponentType();
        } else {
            return param.getType();
        }
    }

    /**
     * Returns a {@link Pattern} to check if a string is a valid Minecraft
     * player UUID.
     *
     * @return a {@link Pattern} for Minecraft player UUIDs
     */
    public static Pattern getPlayerUUIDPattern() {
        return PLAYER_UUID_PATTERN;
    }

    /**
     * Returns the index of the Minecraft inventory slot at which the given
     * inventory row and column cross.
     *
     * @param row    the row's index (starting from 0 with the topmost row)
     * @param column the column's index (starting from 0 with the leftmost row)
     * @return the inventory slot at which the given row and column cross
     */
    @SuppressWarnings("unused")
    public static int slotAt(int row, int column) {
        return (row * 9) + column;
    }

}
