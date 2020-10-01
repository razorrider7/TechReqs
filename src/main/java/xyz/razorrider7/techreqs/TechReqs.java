package xyz.razorrider7.techreqs;

import eu.asangarin.tt.api.APIManager;
import eu.asangarin.tt.api.TechFormat;
import org.bukkit.plugin.java.JavaPlugin;

public class TechReqs extends JavaPlugin {

    @Override
    public void onLoad() {
        APIManager.get().addRequirement("papimath", PAPIMathRequirement.class, new TechFormat("{display}"));
        APIManager.get().addRequirement("item", ItemRequirement.class, new TechFormat("{display} x {amount}"));
        APIManager.get().addRequirement("papistring", PAPIStringRequirement.class, new TechFormat("{display}"));
    }
}
