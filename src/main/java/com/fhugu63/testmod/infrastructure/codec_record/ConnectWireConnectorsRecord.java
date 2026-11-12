package com.fhugu63.testmod.infrastructure.codec_record;

import com.fhugu63.testmod.TestMod;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ConnectWireConnectorsRecord(EnergyNetworkRecord energyNetwork, BlockPos firstBlockPos, BlockPos secondBlockPos) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ConnectWireConnectorsRecord> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "connect_wire_connectors"));

    public static final StreamCodec<ByteBuf, ConnectWireConnectorsRecord> STREAM_CODEC = StreamCodec.composite(
            EnergyNetworkRecord.STREAM_CODEC,
            ConnectWireConnectorsRecord::energyNetwork,

            BlockPos.STREAM_CODEC,
            ConnectWireConnectorsRecord::firstBlockPos,

            BlockPos.STREAM_CODEC,
            ConnectWireConnectorsRecord::secondBlockPos,

            ConnectWireConnectorsRecord::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
