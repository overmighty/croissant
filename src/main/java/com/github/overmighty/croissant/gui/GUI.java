package com.github.overmighty.croissant.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * An interactive GUI, based on a fake chest inventory.
 * <p>
 * By default, players will not be able to change the contents of any slot. This
 * behavior can be disabled for some slots or ranges of slots using
 * {@link GUI#unhandleSlot(int)} and {@link GUI#unhandleSlots(int, int)}
 * respectively. It can also be re-enabled using {@link GUI#handleSlot(int)} and
 * {@link GUI#handleSlots(int, int)}.
 *
 * @see ScrollableGUI
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class GUI implements InventoryHolder, Listener {

    private final Inventory inventory;
    private final Set<Integer> unhandledSlots = new HashSet<>();
    private final Map<Integer, Consumer<InventoryClickEvent>> clickHandlers = new HashMap<>();

    /**
     * Constructs a new {@code GUI}.
     *
     * @param title the GUI's title
     * @param rows  the amount of rows the fake chest inventory used for the GUI
     *              should have
     */
    public GUI(String title, int rows) {
        this.inventory = Bukkit.createInventory(this, rows * 9, title);
    }

    /**
     * Returns the fake chest inventory used as frontend for the GUI.
     *
     * @return the GUI's inventory
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Returns the set of slots of the GUI's inventory that are not handled.
     * Players are not restricted from changing those slots' contents.
     *
     * @return the GUI's unhandled slots
     */
    public Set<Integer> getUnhandledSlots() {
        return unhandledSlots;
    }

    /**
     * Returns the GUI's click handlers, mapped by the index of the inventory
     * slot to which they are bound.
     *
     * @return the GUI's click handlers
     */
    public Map<Integer, Consumer<InventoryClickEvent>> getClickHandlers() {
        return clickHandlers;
    }

    /**
     * Opens the GUI to the given player. The GUI can be opened to multiple
     * players at the same time.
     *
     * @param player the player to open the GUI to
     */
    public void openTo(Player player) {
        player.openInventory(this.inventory);
    }

    /**
     * Makes the {@link GUIHandler} stop handling {@link InventoryClickEvent}s
     * and {@link org.bukkit.event.inventory.InventoryDragEvent}s that have to
     * do with the specified slot. Useful if you want players to be able to take
     * items from a slot, for example.
     *
     * @param slot the slot to stop handling
     */
    public void unhandleSlot(int slot) {
        this.unhandledSlots.add(slot);
    }

    /**
     * Makes the {@link GUIHandler} stop handling {@link InventoryClickEvent}s
     * and {@link org.bukkit.event.inventory.InventoryDragEvent}s that have to
     * do with slots included in the specified range. Useful if you want players
     * to be able to take items from some slots, for example.
     *
     * @param start the start index of the range of slots to stop handling,
     *              inclusive
     * @param end   the end index of the range of slots to stop handling,
     *              also inclusive
     */
    public void unhandleSlots(int start, int end) {
        for (int i = start; i <= end; i++) {
            this.unhandledSlots.add(i);
        }
    }

    /**
     * Makes the {@link GUIHandler} start handling {@link InventoryClickEvent}s
     * and {@link org.bukkit.event.inventory.InventoryDragEvent}s that have to
     * do with the specified slot again.
     *
     * @param slot the slot to start handling again
     */
    public void handleSlot(int slot) {
        this.unhandledSlots.remove(slot);
    }

    /**
     * Makes the {@link GUIHandler} start handling {@link InventoryClickEvent}s
     * and {@link org.bukkit.event.inventory.InventoryDragEvent}s that have to
     * do with slots included in the specified range again.
     *
     * @param start the start index of the range of slots to start handling
     *              again, inclusive
     * @param end   the end index of the range of slots to start handling again,
     *              also inclusive
     */
    public void handleSlots(int start, int end) {
        for (int i = start; i <= end; i++) {
            this.unhandledSlots.remove(i);
        }
    }

    /**
     * Sets a button on the GUI in the given inventory slot.
     *
     * @param slot    the slot in which to set the button
     * @param item    the item stack to use to display the button
     * @param handler the click handler for the button
     */
    public void setButton(int slot, ItemStack item, Consumer<InventoryClickEvent> handler) {
        this.inventory.setItem(slot, item);
        this.clickHandlers.put(slot, handler);
    }

    /**
     * Sets a button in the first empty inventory slot of the GUI, if it has
     * one.
     *
     * @param item    the item stack to use to display the button
     * @param handler the click handler for the button
     */
    public void addButton(ItemStack item, Consumer<InventoryClickEvent> handler) {
        int firstEmptySlot = this.inventory.firstEmpty();

        // If the inventory used for the GUI has an empty inventory slot
        if (firstEmptySlot > -1) {
            this.setButton(firstEmptySlot, item, handler);
        }
    }

    /**
     * Removes the item stack in the given inventory slot and the click handler
     * for the same slot, from the GUI.
     *
     * @param slot the slot of the button to remove
     */
    public void removeButton(int slot) {
        this.inventory.clear(slot);
        this.clickHandlers.remove(slot);
    }

}
