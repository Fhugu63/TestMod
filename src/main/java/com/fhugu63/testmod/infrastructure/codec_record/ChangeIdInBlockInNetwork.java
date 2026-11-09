package com.fhugu63.testmod.infrastructure.codec_record;

import com.fhugu63.testmod.TestMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public record ChangeIdInBlockInNetwork(Long id, Long newId) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ChangeIdInBlockInNetwork> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "change_ids"));

    //public static final Codec<EnergyNetwork> energyNetworkCodec = RecordCodecBuilder.f
    public static final StreamCodec<ByteBuf, ChangeIdInBlockInNetwork> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_LONG,
            ChangeIdInBlockInNetwork::id,
            ByteBufCodecs.VAR_LONG,
            ChangeIdInBlockInNetwork::newId,

            ChangeIdInBlockInNetwork::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
