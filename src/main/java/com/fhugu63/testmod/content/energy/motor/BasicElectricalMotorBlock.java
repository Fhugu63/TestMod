package com.fhugu63.testmod.content.energy.motor;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
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
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction preferred = getPreferredFacing(context);
        if ((context.getPlayer() != null && context.getPlayer()
                .isShiftKeyDown()) || preferred == null)
            return super.getStateForPlacement(context);
        return defaultBlockState().setValue(FACING, preferred);
    }

    @Override
    public Class<BasicElectricalMotorBlockEntity> getBlockEntityClass() {
        return BasicElectricalMotorBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends BasicElectricalMotorBlockEntity> getBlockEntityType() {
        return AllBlockEntitys.BASIC_ELECTRIC_MOTOR_BE.get();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == state.getValue(FACING);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        BasicElectricalMotorBlockEntity energyBlockEntity = (BasicElectricalMotorBlockEntity) level.getBlockEntity(pos);
        energyBlockEntity.onPlace();
    }
}
