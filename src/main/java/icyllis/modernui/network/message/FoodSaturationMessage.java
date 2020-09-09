/*
 * Modern UI.
 * Copyright (C) 2019-2020 BloCamLimb. All rights reserved.
 *
 * Modern UI is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Modern UI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Modern UI. If not, see <https://www.gnu.org/licenses/>.
 */

package icyllis.modernui.network.message;

import icyllis.modernui.system.mixin.AccessorFoodStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.FoodStats;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nonnull;

/**
 * This message send to self, no tracking players
 */
public class FoodSaturationMessage implements IMessage {

    private float foodSaturationLevel;
    private float foodExhaustionLevel;

    public FoodSaturationMessage() {

    }

    public FoodSaturationMessage(float foodSaturationLevel, float foodExhaustionLevel) {
        this.foodSaturationLevel = foodSaturationLevel;
        this.foodExhaustionLevel = foodExhaustionLevel;
    }

    @Override
    public void encode(@Nonnull PacketBuffer buffer) {
        buffer.writeFloat(foodSaturationLevel);
        buffer.writeFloat(foodExhaustionLevel);
    }

    @Override
    public void decode(@Nonnull PacketBuffer buffer) {
        foodSaturationLevel = buffer.readFloat();
        foodExhaustionLevel = buffer.readFloat();
    }

    @Override
    public void handle(@Nonnull NetworkEvent.Context context) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player != null) {
            FoodStats foodStats = player.getFoodStats();
            foodStats.setFoodSaturationLevel(foodSaturationLevel);
            ((AccessorFoodStats) foodStats).setExhaustionLevel(foodExhaustionLevel);
        }
    }
}
