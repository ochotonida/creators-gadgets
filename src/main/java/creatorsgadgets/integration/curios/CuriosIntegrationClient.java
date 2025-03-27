package creatorsgadgets.integration.curios;

import com.simibubi.create.compat.curios.GogglesCurioRenderer;
import creatorsgadgets.integration.curios.client.HeadCurioRenderer;
import creatorsgadgets.registry.ModItems;
import net.minecraft.client.Minecraft;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.function.Supplier;

public class CuriosIntegrationClient {

    public static void setup() {
        Supplier<ICurioRenderer> renderer = () -> new HeadCurioRenderer(Minecraft.getInstance().getEntityModels().bakeLayer(GogglesCurioRenderer.LAYER));
        CuriosRendererRegistry.register(ModItems.TOOLBOX_RADAR.get(), renderer);
        CuriosRendererRegistry.register(ModItems.TOOLBOX_RESONATOR.get(), renderer);
    }
}
