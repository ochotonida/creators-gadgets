package creatorsgadgets;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import creatorsgadgets.config.ModConfig;
import creatorsgadgets.integration.ModCompat;
import creatorsgadgets.integration.curios.CuriosIntegration;
import creatorsgadgets.registry.ModItems;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreatorsGadgets.MOD_ID)
public class CreatorsGadgets {

    public static final String MOD_ID = "creatorsgadgets";

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID)
            .setTooltipModifierFactory(item ->
                    new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                            .andThen(TooltipModifier.mapNull(KineticStats.create(item)))
            );

    @SuppressWarnings("unused")
    public CreatorsGadgets(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        new CreatorsGadgetsClient(modEventBus);

        ModConfig.registerServer(context);

        REGISTRATE.registerEventListeners(modEventBus);

        ModItems.CREATIVE_MODE_TABS.register(modEventBus);
        ModItems.register();

        modEventBus.addListener(EventPriority.LOWEST, CreatorsGadgetsData::gatherData);

        if (ModList.get().isLoaded(ModCompat.CURIOS)) {
            CuriosIntegration.setup();
        }
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
