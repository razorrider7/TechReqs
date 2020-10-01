package xyz.razorrider7.techreqs;

import eu.asangarin.aria.utils.LineConfig;
import eu.asangarin.tt.api.FormatManager;
import eu.asangarin.tt.api.TechRequirement;
import eu.asangarin.tt.data.TechEntry;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PAPIMathRequirement implements TechRequirement {
    private String placeholder;
    private double match;
    private String operator;
    private String display;

    @Override
    public boolean met(TechEntry e, Player p) {
        // Checks if the player has unlocked the specific entry.
        // If yes, this requirement is met!
        double placeholderDouble = Double.parseDouble(PlaceholderAPI.setPlaceholders(p, placeholder));
        switch(operator) {
            case "==":
                if (placeholderDouble == match) {
                    return true;
                }
            case ">":
                if (placeholderDouble > match) {
                    return true;
                }
            case ">=":
                if (placeholderDouble >= match) {
                    return true;
                }
            case "<":
                if (placeholderDouble < match) {
                    return true;
                }
            case "<=":
                if (placeholderDouble <= match) {
                    return true;
                }
            case "!=":
                if (placeholderDouble != match) {
                    return true;
                }
        }
        return false;
    }

    @Override
    public String display(TechEntry e, Player p, boolean isUnlocked) {
        // Uses the format manager to properly display this requirement.
        // The string returned is the String that will replace the placeholder in the lore.
        return FormatManager.get().getFormat("papimath").getFormat(isUnlocked).replace("{display}", display);
    }

    @Override
    public void fulfill(TechEntry e, Player p) {
    }

    @Override
    public boolean setup(TechEntry e, LineConfig c) {
        // Loads data from the LineConfig
        placeholder = c.getString("placeholder");
        operator = c.getString("operator");
        match = c.getDouble("match");
        display = c.getString("display");
        if (PlaceholderAPI.containsPlaceholders(placeholder)) {
            return operator.equals("==") || operator.equals(">") || operator.equals(">=") || operator.equals("<") || operator.equals("<=") || operator.equals("!=");
        }
        return false;
    }
}
