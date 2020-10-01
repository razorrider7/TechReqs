package xyz.razorrider7.techreqs;

import eu.asangarin.tt.api.FormatManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import eu.asangarin.aria.utils.LineConfig;
import eu.asangarin.tt.api.TechRequirement;
import eu.asangarin.tt.data.TechEntry;

public class PAPIStringRequirement implements TechRequirement {
    private String placeholder;
    private String operator;
    private String match;
    private boolean matchCase;
    private String display;

    @Override
    public boolean met(TechEntry e, Player p) {
        // Checks if the player has unlocked the specific entry.
        // If yes, this requirement is met!
        if (matchCase) {
            if (operator.equals("equals") || operator.equals("==")) {
                return PlaceholderAPI.setPlaceholders(p, placeholder).equals(match);
            } else if (operator.equals("not") || operator.equals("!=")) {
                return !PlaceholderAPI.setPlaceholders(p, placeholder).equals(match);
            }
        } else if (operator.equals("equals") || operator.equals("==")) {
            return PlaceholderAPI.setPlaceholders(p, placeholder).equalsIgnoreCase(match);
        } else if (operator.equals("not") || operator.equals("!=")) {
            return !PlaceholderAPI.setPlaceholders(p, placeholder).equalsIgnoreCase(match);
        }
        return false;
    }

    @Override
    public String display(TechEntry e, Player p, boolean isUnlocked) {
        // Uses the format manager to properly display this requirement.
        // The string returned is the String that will replace the placeholder in the lore.
        return FormatManager.get().getFormat("papistring").getFormat(isUnlocked).replace("{display}", display);
    }

    @Override
    public void fulfill(TechEntry e, Player p) {
    }

    @Override
    public boolean setup(TechEntry e, LineConfig c) {
        // Loads data from the LineConfig
        placeholder = c.getString("placeholder");
        operator = c.getString("operator");
        match = c.getString("match");
        matchCase = c.getBoolean("case", false);
        display = c.getString("display");
        if (PlaceholderAPI.containsPlaceholders(placeholder)) {
            return true;
        }
            // If you 'return false;' the reward
        // will not be added to the tech entry
        return false;
    }
}
