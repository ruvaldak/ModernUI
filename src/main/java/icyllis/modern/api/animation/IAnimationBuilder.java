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

package icyllis.modern.api.animation;

public interface IAnimationBuilder {

    /**
     * Set the init value
     * @param init init
     * @return builder
     */
    IAnimationBuilder setInit(float init);

    /**
     * Set the target value
     * @param target target
     * @return builder
     */
    IAnimationBuilder setTarget(float target);

    /**
     * Set the change related to init value
     * @param translate translate
     * @return builder
     */
    IAnimationBuilder setTranslate(float translate);

    IAnimationBuilder setDelay(float delay);

    IAnimationBuilder setTiming(float timing);

    IAnimationBuilder setMotion(MotionType type);
}
