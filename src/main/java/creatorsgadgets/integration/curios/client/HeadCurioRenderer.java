package creatorsgadgets.integration.curios.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

@OnlyIn(Dist.CLIENT)
public class HeadCurioRenderer implements ICurioRenderer {

    private final HumanoidModel<LivingEntity> model;

    public HeadCurioRenderer(ModelPart part) {
        this.model = new HumanoidModel<>(part);
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        model.setupAnim(slotContext.entity(), limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        model.prepareMobModel(slotContext.entity(), limbSwing, limbSwingAmount, partialTicks);
        ICurioRenderer.followHeadRotations(slotContext.entity(), model.head);

        matrixStack.pushPose();
        matrixStack.translate(model.head.x / 16.0, model.head.y / 16.0, model.head.z / 16.0);
        matrixStack.mulPose(Axis.ZP.rotation(model.head.zRot));
        matrixStack.mulPose(Axis.YP.rotation(model.head.yRot));
        matrixStack.mulPose(Axis.XP.rotation(model.head.xRot));

        matrixStack.translate(0, -0.25, 0);
        matrixStack.mulPose(Axis.ZP.rotationDegrees(180.0f));
        matrixStack.scale(0.625f, 0.625f, 0.625f);

        Minecraft mc = Minecraft.getInstance();
        mc.getItemRenderer().renderStatic(stack, ItemDisplayContext.HEAD, light, OverlayTexture.NO_OVERLAY, matrixStack, renderTypeBuffer, mc.level, 0);
        matrixStack.popPose();
    }
}

