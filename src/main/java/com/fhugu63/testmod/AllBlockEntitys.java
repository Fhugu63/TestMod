package com.fhugu63.testmod;

import com.fhugu63.testmod.content.energy.accumulator.CreativeAccumulatorBlockEntity;
import com.fhugu63.testmod.content.energy.kinetic_generator.KineticGeneratorBlockEntity;
import com.fhugu63.testmod.content.energy.lamp.LampBlockEntity;
import com.fhugu63.testmod.content.energy.conductor.WireConnectorBlockEntity;
import com.fhugu63.testmod.content.energy.motor.BasicElectricalMotorBlockEntity;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class AllBlockEntitys {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TestMod.MODID);

    public static final Supplier<BlockEntityType<WireConnectorBlockEntity>> WIRE_CONNECTOR_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
            "wire_connector_be",
            // The block entity type, created using a builder.
            () -> BlockEntityType.Builder.of(
                            // The supplier to use for constructing the block entity instances.
                            WireConnectorBlockEntity::new,
                            // A vararg of blocks that can have this block entity.
                            // This assumes the existence of the referenced blocks as DeferredBlock<Block>s.
                            AllBlocks.WIRE_CONNECTOR.get()
                    )
                    // Build using null; vanilla does some datafixer shenanigans with the parameter that we don't need.
                    .build(null)
    );

    public static final Supplier<BlockEntityType<CreativeAccumulatorBlockEntity>> CREATIVE_ACCUMULATOR_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
            "creative_accumulator_be",
            // The block entity type, created using a builder.
            () -> BlockEntityType.Builder.of(
                            // The supplier to use for constructing the block entity instances.
                            CreativeAccumulatorBlockEntity::new,
                            // A vararg of blocks that can have this block entity.
                            // This assumes the existence of the referenced blocks as DeferredBlock<Block>s.
                            AllBlocks.CREATIVE_ACCUMULATOR_BLOCK.get()
                    )
                    // Build using null; vanilla does some datafixer shenanigans with the parameter that we don't need.
                    .build(null)
    );

    public static final Supplier<BlockEntityType<LampBlockEntity>> LAMP_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
            "lamp_be",
            // The block entity type, created using a builder.
            () -> BlockEntityType.Builder.of(
                            // The supplier to use for constructing the block entity instances.
                            LampBlockEntity::new,
                            // A vararg of blocks that can have this block entity.
                            // This assumes the existence of the referenced blocks as DeferredBlock<Block>s.
                            AllBlocks.LAMP_BLOCK.get()
                    )
                    // Build using null; vanilla does some datafixer shenanigans with the parameter that we don't need.
                    .build(null)
    );

    public static final Supplier<BlockEntityType<BasicElectricalMotorBlockEntity>> BASIC_ELECTRIC_MOTOR_BE = BLOCK_ENTITY_TYPES.register(
            "basic_electric_motor_be",
            // The block entity type, created using a builder.
            () -> BlockEntityType.Builder.of(
                            // The supplier to use for constructing the block entity instances.
                            BasicElectricalMotorBlockEntity::new,
                            // A vararg of blocks that can have this block entity.
                            // This assumes the existence of the referenced blocks as DeferredBlock<Block>s.
                            AllBlocks.BASIC_ELECTRIC_MOTOR.get()
                    )
                    // Build using null; vanilla does some datafixer shenanigans with the parameter that we don't need.
                    .build(null)
    );

    public static final Supplier<BlockEntityType<KineticGeneratorBlockEntity>> KINETIC_GENERATOR_BE = BLOCK_ENTITY_TYPES.register(
            "kinetic_generator_be",
            // The block entity type, created using a builder.
            () -> BlockEntityType.Builder.of(
                            // The supplier to use for constructing the block entity instances.
                            KineticGeneratorBlockEntity::new,
                            // A vararg of blocks that can have this block entity.
                            // This assumes the existence of the referenced blocks as DeferredBlock<Block>s.
                            AllBlocks.KINETIC_GENERATOR.get()
                    )
                    // Build using null; vanilla does some datafixer shenanigans with the parameter that we don't need.
                    .build(null)
    );
}
