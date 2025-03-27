package creatorsgadgets;

import com.tterrag.registrate.providers.ProviderType;
import creatorsgadgets.data.ModRecipeProvider;
import creatorsgadgets.data.ModTagProvider;
import creatorsgadgets.data.ModTooltipProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;

public class CreatorsGadgetsData {

    public static void gatherData(GatherDataEvent event) {
        ModTagProvider.addGenerators();
        CreatorsGadgets.REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> new ModTooltipProvider(provider::add).start());

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        generator.addProvider(event.includeClient(), new ModRecipeProvider(packOutput));
    }
}