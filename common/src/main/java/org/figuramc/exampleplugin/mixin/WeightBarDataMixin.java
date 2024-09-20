package org.figuramc.exampleplugin.mixin;

import net.willsbr.overstuffed.OverStuffed;
import net.willsbr.overstuffed.client.ClientWeightBarData;
import net.willsbr.overstuffed.config.OverstuffedConfig;
import org.figuramc.exampleplugin.EventsAPIAccess;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.lua.api.event.LuaEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value= ClientWeightBarData.class)
public abstract class WeightBarDataMixin {

    //@Shadow public abstract FoodData getFoodData();

    @Inject(method = "setCurrentWeight",at =@At("RETURN"), remap = false)
    private static void setCurrentWeight(int newWeight, CallbackInfo ci)
    {
            //System.out.println("getPlayerStuffedBar");
        Avatar localPlayer = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());

        if (localPlayer == null || localPlayer.luaRuntime == null) {
            return;
        }

        LuaEvent event = ((EventsAPIAccess) localPlayer.luaRuntime.events).Overstuffed$getWeightValueEvent(); //get the event
// then to call/update the event, its not really like fabric or forge
// THIS IS WHAT UPDATES THE VALU
        int maxWeight= OverstuffedConfig.getMaxWeight();
        int minWeight=OverstuffedConfig.getMinWeight();
        int lastStage=ClientWeightBarData.getLastWeightStage();

        localPlayer.run(event, localPlayer.tick,newWeight,minWeight,maxWeight,lastStage);
        //current weight, max, min, last Weight Stage


    }
}

