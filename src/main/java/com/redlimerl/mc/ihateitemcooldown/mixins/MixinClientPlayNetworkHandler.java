package com.redlimerl.mc.ihateitemcooldown.mixins;

import com.redlimerl.mc.ihateitemcooldown.BreakCoolDownReset;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {

    @Shadow private MinecraftClient client;

    @Redirect(method = "onTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;setOverlayMessage(Lnet/minecraft/text/Text;Z)V"))
    public void onTitleMixin(InGameHud instance, Text message, boolean tinted) {
        instance.setOverlayMessage(message, tinted);
        if (message.getString().isEmpty()) {
            if (client.interactionManager != null)
                ((AccessorClientPlayerInteractionManager) client.interactionManager).setBlockBreakCd(BreakCoolDownReset.COOL_DOWN);
        }
    }
}
