package com.github.overmighty.croissant.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Provides chainable methods that allow for easy construction of
 * {@link ItemStack}s.
 */
@SuppressWarnings("unused")
public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    /**
     * Constructs a new {@code ItemBuilder} with an item stack containing 1
     * item.
     *
     * @param material the material to use for the item stack
     */
    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    /**
     * Returns the built item stack, after updating its meta data.
     *
     * @return the built item stack
     */
    public ItemStack getItemStack() {
        this.item.setItemMeta(meta);
        return this.item;
    }

    /**
     * Lets you manipulate the item stack's meta data with a {@link Consumer}.
     *
     * @param consumer the consumer to do an operation on the meta data
     * @return this {@code ItemBuilder}, for chaining
     */
    public ItemBuilder manipulateMeta(Consumer<ItemMeta> consumer) {
        consumer.accept(this.meta);
        return this;
    }

    /**
     * Sets the amount of items in the stack.
     *
     * @param amount the amount of items in the stack
     * @return this {@code ItemBuilder}, for chaining
     */
    public ItemBuilder setAmount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    /**
     * Sets the durability of the items in the stack.
     *
     * @param durability the durability of the items in the stack
     * @return this {@code ItemBuilder}, for chaining
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder setDurability(short durability) {
        this.item.setDurability(durability);
        return this;
    }

    /**
     * Sets the display name of the items in the stack. The display name will be
     * prefixed with {@link ChatColor#RESET} so that it is not italic by
     * default.
     *
     * @param displayName the display name of the items in the stack
     * @return this {@code ItemBuilder}, for chaining
     */
    public ItemBuilder setDisplayName(String displayName) {
        this.meta.setDisplayName(ChatColor.RESET + displayName);
        return this;
    }


    /**
     * Sets the lore of the items in the stack.
     *
     * @param lore each line of the lore of the items in the stack
     * @return this {@code ItemBuilder}, for chaining
     */
    public ItemBuilder setLore(String... lore) {
        this.meta.setLore(Arrays.asList(lore));
        return this;
    }

    /**
     * Adds an enchantment to the items in the stack. This method calls
     * {@link ItemStack#addEnchantment(Enchantment, int)}.
     *
     * @param enchantment the enchantment to add
     * @param level       the enchantment's level
     * @return this {@code ItemBuilder}, for chaining
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.item.addEnchantment(enchantment, level);
        return this;
    }

    /**
     * Adds an enchantment to the items in the stack. This method calls
     * {@link ItemStack#addUnsafeEnchantment(Enchantment, int)}.
     *
     * @param enchantment the enchantment to add
     * @param level       the enchantment's level
     * @return this {@code ItemBuilder}, for chaining
     */
    public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level) {
        this.item.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    /**
     * Removes an enchantment from the items in the stack.
     *
     * @param enchantment the enchantment to remove
     * @return this {@code ItemBuilder}, for chaining
     */
    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        this.item.removeEnchantment(enchantment);
        return this;
    }

    /**
     * Adds item flags to the items in the stack.
     *
     * @param flags the flags to add
     * @return this {@code ItemBuilder}, for chaining
     */
    public ItemBuilder addItemFlags(ItemFlag... flags) {
        this.meta.addItemFlags(flags);
        return this;
    }

    /**
     * Removes item flags from the items in the stack.
     *
     * @param flags the flags to remove
     * @return this {@code ItemBuilder}, for chaining
     */
    public ItemBuilder removeItemFlags(ItemFlag... flags) {
        this.meta.removeItemFlags(flags);
        return this;
    }

}
