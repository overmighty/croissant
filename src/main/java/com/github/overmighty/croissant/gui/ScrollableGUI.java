package com.github.overmighty.croissant.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * A scrollable GUI that can contain multiple pages.
 *
 * @see GUI
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class ScrollableGUI {

    private final List<GUI> pages;
    private final Map<UUID, Integer> indexes = new HashMap<>();

    /**
     * Constructs a new {@code ScrollableGUI}.
     *
     * @param title     the title to set to each page of the GUI; {@code {page}}
     *                  will be replaced with each page's number
     * @param rows      the amount of rows each page's inventory should have
     * @param pageCount the amount of pages the GUI should have
     */
    public ScrollableGUI(String title, int rows, int pageCount) {
        this.pages = new ArrayList<>(pageCount);

        for (int i = 0; i < pageCount; i++) {
            this.pages.add(new GUI(title.replace("{page}", Integer.toString(i + 1)), rows));
        }
    }

    /**
     * Returns the GUI's pages.
     *
     * @return the GUI's pages
     */
    public List<GUI> getPages() {
        return pages;
    }

    /**
     * Returns a map that pairs the UUID of each player currently interacting
     * with the GUI, with the index of the page of the GUI that the player is
     * currently viewing.
     *
     * @return the index of the page of the GUI that each player is viewing
     */
    public Map<UUID, Integer> getIndexes() {
        return indexes;
    }

    /**
     * Opens the first page of the GUI to the given player.
     *
     * @param player the player to open the first page of the GUI to
     */
    public void openTo(Player player) {
        this.indexes.put(player.getUniqueId(), 0);
        this.pages.get(0).openTo(player);
    }

    /**
     * Sets a button on each page of the GUI.
     *
     * @param slot    the slot of each page in which to set the button
     * @param item    the item stack to use to display the button
     * @param handler the click handler for the button
     */
    public void setStickyButton(int slot, ItemStack item, Consumer<InventoryClickEvent> handler) {
        for (GUI page : this.pages) {
            page.setButton(slot, item, handler);
        }
    }

    private void takePlayerToPage(Player player, int pageIndex) {
        this.indexes.replace(player.getUniqueId(), pageIndex);
        this.pages.get(pageIndex).openTo(player);
    }

    /**
     * Sets a navigation button on the GUI, to allow players to scroll between
     * the GUI's pages.
     *
     * @param slot the slot of each page in which to set the button
     * @param item the item stack to use to display the button
     * @param type the type of navigation button to set
     */
    public void setNavigationButton(int slot, ItemStack item, NavigationButtonType type) {
        if (type == NavigationButtonType.FIRST_PAGE) {
            for (int i = 2; i < this.pages.size(); i++) {
                this.pages.get(i).setButton(slot, item, event ->
                    this.takePlayerToPage((Player) event.getWhoClicked(), 0)
                );
            }
        } else if (type == NavigationButtonType.PREVIOUS_PAGE) {
            for (int i = 1; i < this.pages.size(); i++) {
                this.pages.get(i).setButton(slot, item, event -> {
                    int newPageIndex = this.indexes.get(event.getWhoClicked().getUniqueId()) - 1;
                    this.takePlayerToPage((Player) event.getWhoClicked(), newPageIndex);
                });
            }
        } else if (type == NavigationButtonType.NEXT_PAGE) {
            for (int i = 0; i < this.pages.size() - 1; i++) {
                this.pages.get(i).setButton(slot, item, event -> {
                    int newPageIndex = this.indexes.get(event.getWhoClicked().getUniqueId()) + 1;
                    this.takePlayerToPage((Player) event.getWhoClicked(), newPageIndex);
                });
            }
        } else if (type == NavigationButtonType.LAST_PAGE) {
            for (int i = 0; i < this.pages.size() - 2; i++) {
                this.pages.get(i).setButton(slot, item, event ->
                    this.takePlayerToPage((Player) event.getWhoClicked(), this.pages.size() - 1)
                );
            }
        }
    }

}
