package creatorsgadgets.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {

    public static ServerConfig server;

    public static void registerServer(ModLoadingContext context) {
        Pair<ServerConfig, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        server = serverSpecPair.getLeft();
        context.registerConfig(net.minecraftforge.fml.config.ModConfig.Type.SERVER, serverSpecPair.getRight());
    }
}
