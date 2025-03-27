package creatorsgadgets.data;

import com.simibubi.create.AllItems;
import creatorsgadgets.registry.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TOOLBOX_RADAR)
                .pattern(" T ")
                .pattern("PLP")
                .pattern(" E ")
                .define('T', AllItems.TRANSMITTER)
                .define('P', AllItems.BRASS_SHEET)
                .define('L', Tags.Items.LEATHER)
                .define('E', AllItems.ELECTRON_TUBE)
                .unlockedBy("has_electron_tube", hasItem(AllItems.ELECTRON_TUBE))
                .save(consumer, RecipeBuilder.getDefaultRecipeId(ModItems.TOOLBOX_RADAR));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.TOOLBOX_RESONATOR)
                .pattern(" N ")
                .pattern("ARA")
                .pattern(" M ")
                .define('N', Items.NETHERITE_INGOT)
                .define('A', Items.AMETHYST_SHARD)
                .define('R', ModItems.TOOLBOX_RADAR)
                .define('M', AllItems.PRECISION_MECHANISM)
                .unlockedBy("has_netherite", hasItem(Items.NETHERITE_INGOT))
                .save(consumer, RecipeBuilder.getDefaultRecipeId(ModItems.TOOLBOX_RESONATOR));
    }

    private static InventoryChangeTrigger.TriggerInstance hasItem(ItemLike item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }
}
