package creatorsgadgets;

import creatorsgadgets.integration.curios.CuriosIntegrationClient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class CreatorsGadgetsClient {

    public CreatorsGadgetsClient(IEventBus modEventBus) {
        modEventBus.addListener(this::clientSetup);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        CuriosIntegrationClient.setup();
    }
}
