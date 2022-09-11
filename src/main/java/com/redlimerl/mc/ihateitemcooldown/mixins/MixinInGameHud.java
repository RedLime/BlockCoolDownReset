package com.redlimerl.mc.ihateitemcooldown.mixins;

import com.redlimerl.mc.ihateitemcooldown.BreakCoolDownReset;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "setOverlayMessage", at = @At("HEAD"))
    public void onTitleMixin(Text message, boolean tinted, CallbackInfo ci) {
        if (message.getString().isEmpty()) {
            if (client.interactionManager != null)
                ((AccessorClientPlayerInteractionManager) client.interactionManager).setBlockBreakCd(BreakCoolDownReset.COOL_DOWN);
        }
    }
}
