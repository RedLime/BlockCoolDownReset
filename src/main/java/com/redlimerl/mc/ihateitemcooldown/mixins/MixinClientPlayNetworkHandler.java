package com.redlimerl.mc.ihateitemcooldown.mixins;

import com.redlimerl.mc.ihateitemcooldown.BreakCoolDownReset;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.PlaySoundIdS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {
    @Shadow private MinecraftClient client;

    @Inject(method = "onPlaySoundId", at = @At("TAIL"))
    public void onPlaySoundIdMixin(PlaySoundIdS2CPacket packet, CallbackInfo ci) {
        if (packet.getSoundId().toString().equals("cdreset:reset_cooldown")) {
            if (client.interactionManager != null)
                ((AccessorClientPlayerInteractionManager) client.interactionManager).setBlockBreakCd(BreakCoolDownReset.COOL_DOWN);
        }
    }
}
