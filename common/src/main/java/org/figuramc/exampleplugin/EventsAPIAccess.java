package org.figuramc.exampleplugin;

import org.figuramc.figura.lua.api.event.LuaEvent;

public interface EventsAPIAccess {
    public LuaEvent Overstuffed$getStuffedValueEvent();
    public LuaEvent Overstuffed$getWeightValueEvent();
}
