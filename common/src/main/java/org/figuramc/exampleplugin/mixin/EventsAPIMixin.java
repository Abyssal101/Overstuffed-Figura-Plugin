package org.figuramc.exampleplugin.mixin;

import org.figuramc.exampleplugin.EventsAPIAccess;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.event.EventsAPI;
import org.figuramc.figura.lua.api.event.LuaEvent;
import org.figuramc.figura.lua.docs.LuaFieldDoc;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(value = EventsAPI.class, remap = false)
public class EventsAPIMixin implements EventsAPIAccess {
    // this is a template
    @Shadow
    @Final
    private Map<String, LuaEvent> events;

    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.overstuffed_stuffed_bar")
    public LuaEvent OVERSTUFFED_STUFFED_BAR_UPDATE; // gets translated to overstuffed_stuffed_bar in lua

    @Unique
    @LuaWhitelist
    @LuaFieldDoc("events.overstuffed_weight_bar")
    public LuaEvent OVERSTUFFED_WEIGHT_BAR_UPDATE; // gets translated to overstuffed_weight_bar in lua

    @Inject(method = "<init>", at = @At("RETURN"))
    void a(CallbackInfo ci) {
        OVERSTUFFED_STUFFED_BAR_UPDATE = new LuaEvent();
        OVERSTUFFED_WEIGHT_BAR_UPDATE = new LuaEvent();

        events.put("OVERSTUFFED_STUFFED_BAR_UPDATE", OVERSTUFFED_STUFFED_BAR_UPDATE);
        events.put("OVERSTUFFED_WEIGHT_BAR_UPDATE", OVERSTUFFED_WEIGHT_BAR_UPDATE);

    }
    @Override
    public LuaEvent Overstuffed$getStuffedValueEvent() {
        return OVERSTUFFED_STUFFED_BAR_UPDATE;
    }
    @Override
    public LuaEvent Overstuffed$getWeightValueEvent() {
        return OVERSTUFFED_WEIGHT_BAR_UPDATE;
    }

}
