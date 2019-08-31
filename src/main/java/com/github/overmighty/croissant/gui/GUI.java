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
 * behavior can be disabled for some slots by adding their index to the
 * {@link Set} returned by {@link GUI#getIgnoredSlots()}. It can also be
 * re-enabled by removing the slots' index from the same {@link Set}.
 *
 * @see ScrollableGUI
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class GUI implements InventoryHolder, Listener {

    private final Inventory inventory;
    private final Set<Integer> ignoredSlots = new HashSet<>();
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
     * Returns the set of slots of the GUI's inventory that are ignored by the
     * {@link GUIHandler}. Players are not restricted from changing the contents
     * of these inventory slots.
     *
     * @return the GUI's ignored slots
     */
    public Set<Integer> getIgnoredSlots() {
        return ignoredSlots;
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
