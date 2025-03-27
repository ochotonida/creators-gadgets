package creatorsgadgets.integration.curios;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosIntegration {

    public static void setup() {
        MinecraftForge.EVENT_BUS.addGenericListener(ItemStack.class, CuriosIntegration::attachCapabilities);
    }

    public static void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
        event.addCapability(CuriosCapability.ID_ITEM, CuriosApi.createCurioProvider(new ICurio() {

            @Override
            public ItemStack getStack() {
                return stack;
            }

            @Override
            public boolean canEquipFromUse(SlotContext slotContext) {
                return true;
            }
        }));
    }

    public static boolean isEquipped(LivingEntity entity, Item item) {
        return CuriosApi.getCuriosInventory(entity).map(inventory -> inventory.isEquipped(item)).orElse(false);
    }
}
