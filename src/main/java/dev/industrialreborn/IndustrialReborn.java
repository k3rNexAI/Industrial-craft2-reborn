package dev.industrialreborn;

import dev.industrialreborn.content.ModBlocks;
import dev.industrialreborn.content.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class IndustrialReborn implements ModInitializer {
    public static final String MOD_ID = "industrial_reborn";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.register();
        ModBlocks.register();
        LOGGER.info("Industrial Reborn initialized. EU core ready for the next implementation stage.");
    }
}
