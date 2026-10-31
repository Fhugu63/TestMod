package com.fhugu63.testmod;

import com.fhugu63.testmod.BlockEntitys.WireConnectorBlockEntity;
import com.fhugu63.testmod.Blocks.CreativeAccumulatorBlock;
import com.fhugu63.testmod.Blocks.LampBlock;
import com.fhugu63.testmod.Blocks.WireConnectorBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TestMod.MODID);

    public static final DeferredBlock<WireConnectorBlock> WIRE_CONNECTOR = BLOCKS.register(
            "wire_connector",
            () -> new WireConnectorBlock(BlockBehaviour.Properties.of())
    );

    public static final DeferredBlock<CreativeAccumulatorBlock> CREATIVE_ACCUMULATOR_BLOCK = BLOCKS.register(
            "creative_accumulator_block",
            () -> new CreativeAccumulatorBlock(BlockBehaviour.Properties.of())
    );

    public static final DeferredBlock<LampBlock> LAMP_BLOCK = BLOCKS.register(
            "lamp_block",
            () -> new LampBlock(BlockBehaviour.Properties.of())
    );
}
