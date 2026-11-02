package com.fhugu63.testmod.content.energy.motor;

import com.fhugu63.testmod.AllBlockEntitys;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BasicElectricalMotorBlock extends DirectionalKineticBlock implements IBE<BasicElectricalMotorBlockEntity> {
    //public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public BasicElectricalMotorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(FACING)
                .getAxis();
    }

    @Override
    public Class<BasicElectricalMotorBlockEntity> getBlockEntityClass() {
        return BasicElectricalMotorBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends BasicElectricalMotorBlockEntity> getBlockEntityType() {
        return AllBlockEntitys.BASIC_ELECTRIC_MOTOR_BE.get();
    }
}
