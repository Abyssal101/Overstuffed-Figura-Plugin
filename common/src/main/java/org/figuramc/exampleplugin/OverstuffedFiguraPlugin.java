package org.figuramc.exampleplugin;

import org.figuramc.exampleplugin.screens.ExampleScreen;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.entries.FiguraAPI;
import org.figuramc.figura.entries.annotations.FiguraAPIPlugin;
import org.figuramc.figura.lua.LuaWhitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Example API Plugin
 *  Annotation required for Forge to Locate and Load the Plugin
 *  Entrypoint in fabric.mod.json: figura_api
 *  Fabric requires entrypoints have an empty constructor, Figura will not use it
 */
@FiguraAPIPlugin
@LuaWhitelist
public class OverstuffedFiguraPlugin implements FiguraAPI {
    public static final String PLUGIN_ID = "overstuffedfiguraplugin";
    public static final Logger LOGGER = LoggerFactory.getLogger(PLUGIN_ID);
    private Avatar avatar;

    public OverstuffedFiguraPlugin() {
    }
    public OverstuffedFiguraPlugin(Avatar avatar) {
        this.avatar = avatar;
    }

    /**
     * You can do common things on init here
     */
    public static void init() {
        LOGGER.info("Hello multi-loader world!");
    }

    @Override
    public FiguraAPI build(Avatar avatar) {
        return new OverstuffedFiguraPlugin(avatar);
    }

    @Override
    public String getName() {
        return PLUGIN_ID;
    }

    /**
     * You must whitelist your classes for your Plugin to work correctly! This cannot be null
     */
    @Override
    public Collection<Class<?>> getWhitelistedClasses() {
        List<Class<?>> classesToRegister = new ArrayList<>();
        for (Class<?> aClass : EXAMPLE_PLUGIN_CLASSES) {
            if (aClass.isAnnotationPresent(LuaWhitelist.class)) {
                classesToRegister.add(aClass);
            }
        }
        return classesToRegister;
    }

    /**
     * This can be empty, but not null
     */
    @Override
    public Collection<Class<?>> getDocsClasses() {
        return List.of();
    }

    public static final Class<?>[] EXAMPLE_PLUGIN_CLASSES = new Class[] {
            OverstuffedFiguraPlugin.class,
    };

}
