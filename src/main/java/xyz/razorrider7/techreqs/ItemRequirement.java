package xyz.razorrider7.techreqs;

import eu.asangarin.aria.utils.LineConfig;
import eu.asangarin.tt.api.FormatManager;
import eu.asangarin.tt.api.TechRequirement;
import eu.asangarin.tt.data.TechEntry;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.logging.Level;

public class ItemRequirement implements TechRequirement {
    private Material material;
    private int amount;
    private boolean take;
    private ItemStack is;

    @Override
    public boolean met(TechEntry e, Player p) {
        // Checks if the player has unlocked the specific entry.
        // If yes, this requirement is met!
        return p.getInventory().containsAtLeast(is, amount);
    }

    @Override
    public String display(TechEntry e, Player p, boolean isUnlocked) {
        // Uses the format manager to properly display this requirement.
        // The string returned is the String that will replace the placeholder in the lore.
        if (is.getItemMeta().getDisplayName().equals("")) {
            String display = WordUtils.capitalizeFully(material.name().replace('_', ' '));

            return FormatManager.get().getFormat("item").getFormat(isUnlocked).replace("{display}", display).replace("{amount}", Integer.toString(amount));
        }
        return FormatManager.get().getFormat("item").getFormat(isUnlocked).replace("{display}", is.getItemMeta().getDisplayName()).replace("{amount}", Integer.toString(amount));
    }

    @Override
    public void fulfill(TechEntry e, Player p) {
        if (take) {
            int removed = 0;
            for (int slot:p.getInventory().all(material).keySet()) {
                if (removed == amount) {
                    return;
                }
                ItemStack is = p.getInventory().getItem(slot);
                if (is.isSimilar(this.is)) {
                    if (is.getAmount() > amount - removed) {
                        is.setAmount(is.getAmount() - (amount - removed));
                        p.getInventory().setItem(slot, is);
                        return;
                    } else {
                        removed = is.getAmount();
                        p.getInventory().setItem(slot, null);
                    }
                }
            }
        }
    }

    @Override
    public boolean setup(TechEntry e, LineConfig c) {
        // Loads data from the LineConfig
        amount = c.getInt("amount", 1);
        material = Material.getMaterial(c.getString("material", "STONE"));
        is = new ItemStack(material, c.getInt("amount", 1));
        if (c.getString("name", null) != null) {
            ItemMeta meta = is.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', c.getString("name")));
            is.setItemMeta(meta);
        }
        take = c.getBoolean("take", true);
        return true;
    }
}
