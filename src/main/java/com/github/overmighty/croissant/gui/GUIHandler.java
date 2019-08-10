package com.github.overmighty.croissant.gui;

import com.github.overmighty.croissant.Croissant;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

import java.util.function.Consumer;

/**
 * Handles Bukkit events that involve {@link GUI}s.
 */
public class GUIHandler implements Listener {

    /**
     * Registers the Bukkit event listeners that allow {@link GUI}s to function.
     */
    @SuppressWarnings({ "WeakerAccess", "unused" })
    public static void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new GUIHandler(), Croissant.getPlugin());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();

        if (event.isCancelled() || inventory == null || !(inventory.getHolder() instanceof GUI)) {
            return;
        }

        GUI gui = (GUI) inventory.getHolder();

        if (gui.getUnhandledSlots().contains(event.getSlot())) {
            return;
        }

        event.setCancelled(true); // Do not let players alter the contents of a handled slot
        Consumer<InventoryClickEvent> clickHandler = gui.getClickHandlers().get(event.getSlot());

        if (clickHandler != null) {
            clickHandler.accept(event);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.isCancelled() || !(event.getInventory().getHolder() instanceof GUI)) {
            return;
        }

        GUI gui = (GUI) event.getInventory().getHolder();

        // If the event does not only involve unhandled slots of the GUI
        if (!gui.getUnhandledSlots().containsAll(event.getInventorySlots())) {
            // Do not let players alter the contents of handled slots of the GUI
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof GUI)) {
            return;
        }

        GUI gui = (GUI) event.getInventory().getHolder();

        if (gui.getScrollableGUI() != null) {
            gui.getScrollableGUI().getIndexes().remove(event.getPlayer().getUniqueId());
        }
    }

}
