package dev.industrialreborn.content;

import dev.industrialreborn.IndustrialReborn;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public final class ModBlocks {
    public static final Block GENERATOR = register("generator",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Block BATTERY_BOX = register("battery_box",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final Block MACERATOR = register("macerator",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    private ModBlocks() {}

    private static Block register(String path, Block block) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(IndustrialReborn.MOD_ID, path);
        Registry.register(BuiltInRegistries.BLOCK, id, block);
        Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
        return block;
    }

    public static void register() {
        IndustrialReborn.LOGGER.info("Registering Industrial Reborn blocks");
    }
}
