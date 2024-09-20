package org.figuramc.exampleplugin.forge;

import net.minecraftforge.fml.common.Mod;
import org.figuramc.exampleplugin.OverstuffedFiguraPlugin;
import org.figuramc.exampleplugin.mixin.WeightBarDataMixin;

/**
 * A mod class is needed for Forge to load the Plugin
 */
@Mod(OverstuffedFiguraPlugin.PLUGIN_ID)
public class ExampleModForge {
    public ExampleModForge() {
        OverstuffedFiguraPlugin.init();
    }
}
