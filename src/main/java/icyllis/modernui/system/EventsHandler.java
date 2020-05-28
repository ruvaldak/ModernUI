/*
 * Modern UI.
 * Copyright (C) 2019 BloCamLimb. All rights reserved.
 *
 * Modern UI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Modern UI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modern UI. If not, see <https://www.gnu.org/licenses/>.
 */

package icyllis.modernui.system;

import icyllis.modernui.graphics.BlurHandler;
import icyllis.modernui.graphics.font.TrueTypeRenderer;
import icyllis.modernui.gui.master.GlobalModuleManager;
import icyllis.modernui.gui.master.LayoutEditingGui;
import icyllis.modernui.gui.test.ContainerTest;
import icyllis.modernui.gui.test.ModuleTest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nonnull;

/**
 * Listens global common events, and dispatch to child event handlers
 */
@Mod.EventBusSubscriber
public class EventsHandler {

    @SubscribeEvent
    public static void rightClickItem(@Nonnull PlayerInteractEvent.RightClickItem event) {
        if (ConfigManager.COMMON.isEnableDeveloperMode()) {
            if (event.getItemStack().getItem().equals(Items.DIAMOND)) {
                if (event.getSide().isServer()) {
                    NetworkHooks.openGui((ServerPlayerEntity) event.getPlayer(), new ContainerTest.Provider());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onContainerClosed(PlayerContainerEvent.Close event) {

    }

    /**
     * Listens global client events
     */
    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(Dist.CLIENT)
    public static class ClientEventHandler {

        @SubscribeEvent
        public static void onRenderTick(@Nonnull TickEvent.RenderTickEvent event) {
            if (event.phase == TickEvent.Phase.START) {
                GlobalModuleManager.INSTANCE.renderTick(event.renderTickTime);
            } else {
                BlurHandler.INSTANCE.renderTick();
            }
        }

        @SubscribeEvent
        public static void onClientTick(@Nonnull TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.START) {
                GlobalModuleManager.INSTANCE.clientTick();
            }
        }

        @SubscribeEvent
        public static void onGuiOpen(@Nonnull GuiOpenEvent event) {
            TrueTypeRenderer.INSTANCE.init();
            GlobalModuleManager.INSTANCE.onGuiOpen(event.getGui(), event::setCanceled);
            BlurHandler.INSTANCE.blur(event.getGui());
        }

        @SubscribeEvent
        public static void onRenderAttackIndicator(@Nonnull RenderGameOverlayEvent.Pre event) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS && GlobalModuleManager.INSTANCE.getModernScreen() != null) {
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onGuiInit(GuiScreenEvent.InitGuiEvent event) {

        }

        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event) {
            if (ConfigManager.COMMON.isEnableDeveloperMode() && event.getAction() == GLFW.GLFW_PRESS) {
                if (Screen.hasControlDown() && Screen.hasShiftDown()) {
                    if (event.getKey() == GLFW.GLFW_KEY_T && GlobalModuleManager.INSTANCE.getModernScreen() != null) {
                        LayoutEditingGui.INSTANCE.iterateWorking();
                    }
                    if (event.getKey() == GLFW.GLFW_KEY_P) {
                        if (GlobalModuleManager.INSTANCE.getModernScreen() == null) {
                            if (Minecraft.getInstance().currentScreen != null) {
                                ModernUI.LOGGER.debug(ModernUI.MARKER, "Opened gui class name : {}", Minecraft.getInstance().currentScreen.getClass().getName());
                            }
                        }
                    }
                }
            }
        }

        @SubscribeEvent
        public static void onScreenEndDraw(GuiScreenEvent.DrawScreenEvent.Post event) {
            LayoutEditingGui.INSTANCE.draw();
        }

        @SubscribeEvent
        public static void onScreenStartMouseClicked(@Nonnull GuiScreenEvent.MouseClickedEvent.Pre event) {
            event.setCanceled(LayoutEditingGui.INSTANCE.mouseClicked(event.getButton()));
        }

        @SubscribeEvent
        public static void onScreenStartMouseReleased(@Nonnull GuiScreenEvent.MouseReleasedEvent.Pre event) {
            LayoutEditingGui.INSTANCE.mouseReleased();
        }

        @SubscribeEvent
        public static void onScreenStartMouseDragged(@Nonnull GuiScreenEvent.MouseDragEvent.Pre event) {
            event.setCanceled(LayoutEditingGui.INSTANCE.mouseDragged(event.getDragX(), event.getDragY()));
        }
    }

    /**
     * Listens Modern UI events on both sides
     */
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventHandler {

        @SubscribeEvent
        public static void setupCommon(FMLCommonSetupEvent event) {
            NetworkManager.INSTANCE.registerMessages();
            RegistryLibrary.INSTANCE = null;
        }

        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void setupClient(FMLClientSetupEvent event) {
            SettingsManager.INSTANCE.buildAllSettings();
            GlobalModuleManager.INSTANCE.registerContainerScreen(RegistryLibrary.TEST_CONTAINER, c -> ModuleTest::new);
        }

        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void registerSounds(@Nonnull RegistryEvent.Register<SoundEvent> event) {
            RegistryLibrary.INSTANCE.registerSounds(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerContainers(@Nonnull RegistryEvent.Register<ContainerType<?>> event) {
            RegistryLibrary.INSTANCE.registerContainers(event.getRegistry());
        }

        @SubscribeEvent
        public static void onConfigChange(@Nonnull ModConfig.ModConfigEvent event) {
            ConfigManager.reload(event.getConfig().getSpec());
        }

        @SubscribeEvent
        public static void onLoadComplete(FMLLoadCompleteEvent event) {

        }
    }
}
