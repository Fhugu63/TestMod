package com.fhugu63.testmod.Blocks;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.BlockEntitys.LampBlockEntity;
import com.fhugu63.testmod.Energy.EnergyBlockEntity;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.client.renderer.entity.LeashKnotRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class LampBlock extends Block implements EntityBlock {
    public LampBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new LampBlockEntity(blockPos, blockState);
    }

    private static <E extends BlockEntity, A extends BlockEntity> @Nullable BlockEntityTicker<A> createTickerHelper(
            BlockEntityType<A> type, BlockEntityType<E> checkedType, BlockEntityTicker<? super E> ticker
    ) {
        return checkedType == type ? (BlockEntityTicker<A>) ticker : null;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        // You can return different tickers here, depending on whatever factors you want. A common use case would be
        // to return different tickers on the client or server, only tick one side to begin with,
        // or only return a ticker for some blockstates (e.g. when using a "my machine is working" blockstate property).
        return createTickerHelper(type, AllBlockEntitys.LAMP_BLOCK_ENTITY.get(), LampBlockEntity::tick);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.box(
                3, 0, 3,
                13, 12, 13
        );
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        EnergyBlockEntity energyBlockEntity = (EnergyBlockEntity) level.getBlockEntity(pos);

        if (!energyBlockEntity.energyNetworkNull()) return super.getLightEmission(state, level, pos);

        if (energyBlockEntity.gettedEnergy == 100) return 15;

        return super.getLightEmission(state, level, pos);
    }

    @Override
    public boolean hasDynamicLightEmission(BlockState state) {
        return true;
    }
}
