package committee.nova.flotage.plugin.jade;

import committee.nova.flotage.block.RackBlock;
import committee.nova.flotage.tile.RackBlockEntity;
import mcp.mobius.waila.api.*;

@WailaPlugin
public class FloJade implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(new RackComponentProvider(), RackBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerComponentProvider(new RackComponentProvider(), TooltipPosition.BODY, RackBlock.class);
    }
}
