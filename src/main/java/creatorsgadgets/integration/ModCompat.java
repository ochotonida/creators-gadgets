package creatorsgadgets.integration;

import creatorsgadgets.integration.curios.CuriosIntegration;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;

public class ModCompat {

    public static final String CURIOS = "curios";

    public static boolean isEquipped(LivingEntity entity, Item item) {
        return ModList.get().isLoaded(CURIOS) && CuriosIntegration.isEquipped(entity, item);
    }
}
