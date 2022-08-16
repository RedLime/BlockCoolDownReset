package com.redlimerl.mc.ihateitemcooldown;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class BreakCoolDownReset implements ClientModInitializer {

    public static int COOL_DOWN = 0;

    @Override
    public void onInitializeClient() {
        File configFile = FabricLoader.getInstance().getConfigDir().resolve("breakcooldown.txt").toFile();

        try {
            if (configFile.exists()) {
                COOL_DOWN = Integer.parseInt(FileUtils.readFileToString(configFile, StandardCharsets.UTF_8));
            } else {
                FileUtils.writeStringToFile(configFile, String.valueOf(COOL_DOWN), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
