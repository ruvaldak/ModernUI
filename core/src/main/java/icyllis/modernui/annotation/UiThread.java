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

package icyllis.modernui.annotation;

import java.lang.annotation.*;

/**
 * Denotes that the annotated method should only be called on the UI thread,
 * which is used for handling UI events and recording drawing contents for
 * the GUI on application-level, there will no OpenGL context on this thread.
 * Input events from the window for UI part should be posted to this thread.
 * <p>
 * Example:
 * <pre><code>
 *  &#64;UiThread
 *  public void onMouseClick(MotionEvent ev) {
 *      // something here
 *  }</code></pre>
 */
@Documented
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.CLASS)
public @interface UiThread {
}
