package creatorsgadgets.config;

import creatorsgadgets.CreatorsGadgets;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;

public class ServerConfig {

    public final ForgeConfigSpec.IntValue toolboxResonatorRangeBonus;

    ServerConfig(ForgeConfigSpec.Builder builder) {
        toolboxResonatorRangeBonus = builder
                .comment("How much farther toolboxes can be accessed when wearing a Toolbox Resonator")
                .translation(translate("toolbox_resonator_range_bonus"))
                .defineInRange("toolbox_resonator_range_bonus", 10, 0, Integer.MAX_VALUE);
    }

    private String translate(String name) {
        String key = "%s.config.server.%s".formatted(CreatorsGadgets.MOD_ID, name);
        CreatorsGadgets.REGISTRATE.addRawLang(key, Arrays.stream(name.split("_"))
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
                .reduce((s1, s2) -> s1 + " " + s2)
                .orElseThrow()
        );
        return key;
    }
}
