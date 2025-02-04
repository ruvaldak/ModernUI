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

package icyllis.modernui.graphics.drawable;

import icyllis.modernui.graphics.CanvasForge;

import javax.annotation.Nonnull;

/**
 * A drawing unit in a view, draw content in given bounds
 */
public abstract class Drawable {

    private int left;
    private int top;
    private int right;
    private int bottom;

    /**
     * Draw things in bounds.
     * Before you draw in the method, you have to call {@link CanvasForge#moveTo(Drawable)},
     * (0, 0) will be the top left of the bounds,
     * (width, height) will be the bottom right of the bounds.
     * See {@link #getWidth()}
     * See {@link #getHeight()}
     *
     * @param canvas the canvas to draw things
     */
    public abstract void draw(@Nonnull CanvasForge canvas);

    /**
     * Returns the drawable's intrinsic width. Actually a layout params.
     * <p>
     * Intrinsic width is the width at which the drawable would like to be laid
     * out, including any inherent padding. If the drawable has no intrinsic
     * width, such as a solid color, this method returns -1.
     *
     * @return the intrinsic width, or -1 if no intrinsic width
     */
    public int getIntrinsicWidth() {
        return -1;
    }

    /**
     * Returns the drawable's intrinsic height. Actually a layout params.
     * <p>
     * Intrinsic height is the height at which the drawable would like to be
     * laid out, including any inherent padding. If the drawable has no
     * intrinsic height, such as a solid color, this method returns -1.
     *
     * @return the intrinsic height, or -1 if no intrinsic height
     */
    public int getIntrinsicHeight() {
        return -1;
    }

    /**
     * Get actual width for drawing
     *
     * @return width
     */
    public final int getWidth() {
        return right - left;
    }

    /**
     * Get actual height for drawing
     *
     * @return height
     */
    public final int getHeight() {
        return bottom - top;
    }

    public final int getLeft() {
        return left;
    }

    public final int getTop() {
        return top;
    }

    public final int getRight() {
        return right;
    }

    public final int getBottom() {
        return bottom;
    }

    /**
     * Set the bounds of this drawable for drawing
     *
     * @param left   left bound
     * @param top    top bound
     * @param right  right bound
     * @param bottom bottom bound
     */
    public void setBounds(int left, int top, int right, int bottom) {
        if (this.left != left || this.top != top ||
                this.right != right || this.bottom != bottom) {

            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;

            onBoundsChanged();
        }
    }

    /**
     * Called when bounds changed, including position or size
     */
    protected void onBoundsChanged() {

    }
}
