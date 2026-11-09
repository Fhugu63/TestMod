package com.fhugu63.testmod;

import com.fhugu63.testmod.content.energy.wire.CopperWireItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.fhugu63.testmod.TestMod.MODID;

public class AllItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> GREEN_GUMMY_BEAR = ITEMS.registerSimpleItem(
            "green_gummy_bear",
            new Item.Properties()
                    .food(
                        new FoodProperties.Builder()
                        .alwaysEdible()
                        .nutrition(1)
                        .saturationModifier(2f)
                        .build()
                    )
    );

    public static final DeferredItem<CopperWireItem> COPPER_WIRE_ITEM = ITEMS.register(
            "copper_wire_item",
            () -> new CopperWireItem(new Item.Properties())
    );

    public static final DeferredItem<BlockItem> WIRE_CONNECTOR_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            AllBlocks.WIRE_CONNECTOR
    );

    public static final DeferredItem<BlockItem> CREATIVE_ACCUMULATOR_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            AllBlocks.CREATIVE_ACCUMULATOR_BLOCK
    );

    public static final DeferredItem<BlockItem> LAMP_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            AllBlocks.LAMP_BLOCK
    );

    public static final DeferredItem<BlockItem> BASIC_ELECTRIC_MOTOR_BLOCK_ITEM = ITEMS.registerSimpleBlockItem(
            AllBlocks.BASIC_ELECTRIC_MOTOR
    );
}
