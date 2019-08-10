package com.github.overmighty.croissant.gui;

/**
 * A type of navigation button that can be added to a {@link ScrollableGUI}.
 */
public enum NavigationButtonType {

    /**
     * A button that takes the player to the first page of the scrollable GUI
     * they are interacting with. Not displayed on the GUI's first two pages.
     */
    FIRST_PAGE,
    /**
     * A button that takes the player to the previous page of the scrollable
     * GUI they are interacting with. Not displayed on the GUI's first page.
     */
    PREVIOUS_PAGE,
    /**
     * A button that takes the player to the next page of the scrollable GUI
     * they are interacting with. Not displayed on the GUI's last page.
     */
    NEXT_PAGE,
    /**
     * A button that takes the player to the last page of the scrollable GUI
     * they are interacting with. Not displayed on the GUI's last two pages.
     */
    LAST_PAGE

}
