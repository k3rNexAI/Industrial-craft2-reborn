package dev.industrialreborn.content;

import dev.industrialreborn.IndustrialReborn;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class ModItems {
    public static final Item COPPER_WIRE = register("copper_wire", new Item(new Item.Properties()));
    public static final Item RUBBER = register("rubber", new Item(new Item.Properties()));
    public static final Item REFINED_COPPER = register("refined_copper", new Item(new Item.Properties()));
    public static final Item ENERGY_CRYSTAL = register("energy_crystal", new Item(new Item.Properties()));

    private ModItems() {}

    private static Item register(String path, Item item) {
        return Registry.register(BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(IndustrialReborn.MOD_ID, path), item);
    }

    public static void register() {
        IndustrialReborn.LOGGER.info("Registering Industrial Reborn items");
    }
}
