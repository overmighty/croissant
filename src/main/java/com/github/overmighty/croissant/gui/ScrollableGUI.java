package com.github.overmighty.croissant.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * A scrollable GUI that can contain multiple pages.
 *
 * @see GUI
 */
@SuppressWarnings("unused")
public class ScrollableGUI {

    private final List<GUI> pages;

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
     * Opens the first page of the GUI to the given player.
     *
     * @param player the player to open the first page of the GUI to
     */
    public void openTo(Player player) {
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

    private ItemStack makeNavigationItem(ItemStack item, int pageIndex) {
        ItemStack navigationItem = item.clone();
        ItemMeta itemMeta = navigationItem.getItemMeta();
        itemMeta.setDisplayName(
            itemMeta.getDisplayName().replace("{page}", Integer.toString(pageIndex + 1))
        );
        navigationItem.setItemMeta(itemMeta);
        return navigationItem;
    }

    /**
     * Sets a navigation button on the GUI, to allow players to scroll between
     * the GUI's pages. For each page of the GUI, {@code {page}} in the item
     * stack's display name will be replaced with the number of the page that
     * the button will take the player to.
     *
     * @param slot the slot of each page in which to set the button
     * @param item the item stack to use to display the button
     * @param type the type of navigation button to set
     */
    public void setNavigationButton(int slot, ItemStack item, NavigationButtonType type) {
        if (type == NavigationButtonType.FIRST_PAGE) {
            for (int i = 2; i < this.pages.size(); i++) {
                ItemStack navigationItem = this.makeNavigationItem(item, 0);
                this.pages.get(i).setButton(slot, navigationItem, event ->
                    this.pages.get(0).openTo((Player) event.getWhoClicked())
                );
            }
        } else if (type == NavigationButtonType.PREVIOUS_PAGE) {
            for (int i = 1; i < this.pages.size(); i++) {
                int previousPageIndex = i - 1;
                ItemStack navigationItem = this.makeNavigationItem(item, previousPageIndex);
                this.pages.get(i).setButton(slot, navigationItem, event ->
                    this.pages.get(previousPageIndex).openTo((Player) event.getWhoClicked())
                );
            }
        } else if (type == NavigationButtonType.NEXT_PAGE) {
            for (int i = 0; i < this.pages.size() - 1; i++) {
                int nextPageIndex = i + 1;
                ItemStack navigationItem = this.makeNavigationItem(item, nextPageIndex);
                this.pages.get(i).setButton(slot, navigationItem, event ->
                    this.pages.get(nextPageIndex).openTo((Player) event.getWhoClicked())
                );
            }
        } else if (type == NavigationButtonType.LAST_PAGE) {
            for (int i = 0; i < this.pages.size() - 2; i++) {
                ItemStack navigationItem = this.makeNavigationItem(item, this.pages.size() - 1);
                this.pages.get(i).setButton(slot, navigationItem, event ->
                    this.pages.get(this.pages.size() - 1).openTo((Player) event.getWhoClicked())
                );
            }
        }
    }

}
