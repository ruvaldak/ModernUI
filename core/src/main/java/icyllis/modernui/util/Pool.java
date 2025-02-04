/*
 * Modern UI.
 * Copyright (C) 2019-2021 BloCamLimb. All rights reserved.
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

package icyllis.modernui.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Interface for managing a pool of objects.
 *
 * @param <T> The pooled type.
 */
public interface Pool<T> {

    /**
     * @return An instance from the pool if such, null otherwise.
     */
    @Nullable
    T acquire();

    /**
     * @param supplier The supplier that returns a new instance.
     * @return An instance from the pool if such, or from the supplier.
     */
    @Nonnull
    default T acquire(@Nonnull Supplier<T> supplier) {
        return Objects.requireNonNullElseGet(acquire(), supplier);
    }

    /**
     * Release an instance to the pool.
     *
     * @param instance The instance to release.
     * @return Whether the instance was put in the pool.
     * @throws IllegalStateException If the instance is already in the pool.
     */
    boolean release(@Nonnull T instance);
}
