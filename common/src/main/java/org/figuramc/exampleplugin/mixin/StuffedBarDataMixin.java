package org.figuramc.exampleplugin.mixin;

import net.willsbr.overstuffed.client.ClientStuffedBarData;
import org.figuramc.exampleplugin.EventsAPIAccess;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.avatar.AvatarManager;
import org.figuramc.figura.lua.api.event.LuaEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value= ClientStuffedBarData.class)
public abstract class StuffedBarDataMixin {

    //@Shadow public abstract FoodData getFoodData();

    @Inject(method = "set",at =@At("RETURN"), remap = false)
    private static void set(int input, int soft, int firm, int hard, CallbackInfo ci)
    {
            //System.out.println("getPlayerStuffedBar");
        Avatar localPlayer = AvatarManager.getAvatarForPlayer(FiguraMod.getLocalPlayerUUID());
        if (localPlayer == null || localPlayer.luaRuntime == null) {
            return;
        }
        LuaEvent event = ((EventsAPIAccess) localPlayer.luaRuntime.events).Overstuffed$getStuffedValueEvent(); // get the event

// then to call/update the event, its not really like fabric or forge
// THIS IS WHAT UPDATES THE VALUE
        int total=soft+firm+hard;
        localPlayer.run(event, localPlayer.tick, input,soft,firm,hard,total);


    }
}

