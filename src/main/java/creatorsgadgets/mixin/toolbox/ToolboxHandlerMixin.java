package creatorsgadgets.mixin.toolbox;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.simibubi.create.content.equipment.toolbox.ToolboxHandler;
import creatorsgadgets.config.ModConfig;
import creatorsgadgets.registry.ModItems;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ToolboxHandler.class)
public class ToolboxHandlerMixin {

    @ModifyReturnValue(method = "getMaxRange", at = @At("RETURN"), remap = false)
    private static double modifyMaxRange(double original, Player player) {
        return original + (ModItems.TOOLBOX_RESONATOR.get().isEquipped(player) ? ModConfig.server.toolboxResonatorRangeBonus.get() : 0);
    }
}
