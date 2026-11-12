package com.fhugu63.testmod.content.energy.wire;

import com.fhugu63.testmod.content.energy.conductor.WireConnectorBlockEntity;
import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import com.fhugu63.testmod.infrastructure.codec_record.ChangeIdInBlockInNetwork;
import com.fhugu63.testmod.infrastructure.codec_record.ClickRecord;
import com.fhugu63.testmod.infrastructure.codec_record.EnergyNetworkRecord;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import com.fhugu63.testmod.infrastructure.energy_system.IEnergyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Objects;

public class CopperWireItem extends Item {
    EnergyBlockEntity firstConnect;
    EnergyBlockEntity secondConnect;

    public CopperWireItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide()) return InteractionResult.PASS;

        BlockPos clickedBlockPos = context.getClickedPos();
        Level level = context.getLevel();
        EnergyNetwork energyNetwork = null;

        if (level.getBlockEntity(clickedBlockPos) == null) return InteractionResult.PASS;
        if (level.getBlockEntity(clickedBlockPos).getClass() != WireConnectorBlockEntity.class) return InteractionResult.PASS;

        PacketDistributor.sendToServer(new ClickRecord(clickedBlockPos));

        return InteractionResult.PASS;
    }
}
