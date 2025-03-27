package creatorsgadgets.item;

import creatorsgadgets.integration.ModCompat;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EquipableItem extends Item {

    private final EquipmentSlot slot;

    public EquipableItem(Properties properties, EquipmentSlot slot) {
        super(properties);
        this.slot = slot;
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return slot;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        EquipmentSlot slot = Mob.getEquipmentSlotForItem(heldItem);
        if (player.getItemBySlot(slot).isEmpty()) {
            player.setItemSlot(slot, heldItem.copy());
            if (!player.isCreative()) {
                heldItem.setCount(0);
            }
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, heldItem);
        } else {
            return new InteractionResultHolder<>(InteractionResult.FAIL, heldItem);
        }
    }

    public boolean isEquipped(LivingEntity entity) {
        if (entity == null) {
            return false;
        }
        for (ItemStack slotItem : entity.getArmorSlots()) {
            if (slotItem.is(this)) {
                return true;
            }
        }
        return ModCompat.isEquipped(entity, this);
    }
}
