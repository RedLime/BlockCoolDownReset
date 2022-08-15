package com.redlimerl.mc.ihateitemcooldown.mixins;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientPlayerInteractionManager.class)
public interface AccessorClientPlayerInteractionManager {

    @Accessor("blockBreakingCooldown")
    void setBlockBreakCd(int cd);
}
