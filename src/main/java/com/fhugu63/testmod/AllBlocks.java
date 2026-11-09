package com.fhugu63.testmod;

import com.fhugu63.testmod.content.energy.kinetic_generator.KineticGeneratorBlock;
import com.fhugu63.testmod.content.energy.motor.BasicElectricalMotorBlock;
import com.fhugu63.testmod.content.energy.accumulator.CreativeAccumulatorBlock;
import com.fhugu63.testmod.content.energy.lamp.LampBlock;
import com.fhugu63.testmod.content.energy.conductor.WireConnectorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TestMod.MODID);

    public static final DeferredBlock<WireConnectorBlock> WIRE_CONNECTOR = BLOCKS.register(
            "wire_connector",
            () -> new WireConnectorBlock(BlockBehaviour.Properties.of().noOcclusion())
    );

    public static final DeferredBlock<CreativeAccumulatorBlock> CREATIVE_ACCUMULATOR_BLOCK = BLOCKS.register(
            "creative_accumulator",
            () -> new CreativeAccumulatorBlock(BlockBehaviour.Properties.of())
    );

    public static final DeferredBlock<LampBlock> LAMP_BLOCK = BLOCKS.register(
            "lamp",
            () -> new LampBlock(BlockBehaviour.Properties.of().noOcclusion())
    );

    public static final DeferredBlock<BasicElectricalMotorBlock> BASIC_ELECTRIC_MOTOR = BLOCKS.register(
            "basic_electric_motor",
            () -> new BasicElectricalMotorBlock(BlockBehaviour.Properties.of())
    );

    public static final DeferredBlock<KineticGeneratorBlock> KINETIC_GENERATOR = BLOCKS.register(
            "kinetic_generator",
            () -> new KineticGeneratorBlock(BlockBehaviour.Properties.of())
    );
}
