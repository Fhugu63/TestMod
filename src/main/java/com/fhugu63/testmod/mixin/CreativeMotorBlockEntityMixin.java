package com.fhugu63.testmod.mixin;

import com.simibubi.create.content.kinetics.motor.CreativeMotorBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CreativeMotorBlockEntity.class)
public class CreativeMotorBlockEntityMixin {
    @Final
    @Shadow
    public static final int MAX_SPEED = 2147000000;
}
