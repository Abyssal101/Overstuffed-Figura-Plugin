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
    @LuaFieldDoc("events.stateChanged")
    public LuaEvent STATE_CHANGED; // gets translated to event.stateChanged in lua
    @Inject(method = "<init>", at = @At("RETURN"))
    void a(CallbackInfo ci) {
        STATE_CHANGED = new LuaEvent();
        events.put("STATE_CHANGED", STATE_CHANGED);
    }
    @Override
    public LuaEvent Overstuffed$getValueEvent() {
        return STATE_CHANGED;
    }
}
