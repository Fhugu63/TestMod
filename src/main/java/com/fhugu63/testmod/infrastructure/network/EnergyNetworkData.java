package com.fhugu63.testmod.infrastructure.network;

import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record EnergyNetworkData(EnergyNetwork energyNetwork, BlockPos blockPos) implements CustomPacketPayload {
    /*public static final StreamCodec<ByteBuf, EnergyNetworkData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.,
            EnergyNetworkData::energyNetwork,
            ByteBufCodecs.VAR_INT,
            EnergyNetworkData::blockPos,
            EnergyNetworkData::new
    );*/

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return null;
    }
}
