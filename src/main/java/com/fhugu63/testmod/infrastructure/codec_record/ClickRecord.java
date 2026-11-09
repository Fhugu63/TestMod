package com.fhugu63.testmod.infrastructure.codec_record;

import com.fhugu63.testmod.TestMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;

public record ClickRecord(BlockPos blockPos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ClickRecord> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "click_packet"));
    static Color color = new Color(1f, 1f, 1f);
    //public static final Codec<EnergyNetwork> energyNetworkCodec = RecordCodecBuilder.f
    public static final StreamCodec<ByteBuf, ClickRecord> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            ClickRecord::blockPos,

            ClickRecord::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
