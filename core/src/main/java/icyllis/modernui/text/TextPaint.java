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

package icyllis.modernui.text;

import icyllis.modernui.graphics.font.FontMetricsInt;
import icyllis.modernui.graphics.font.GlyphManagerBase;
import icyllis.modernui.graphics.font.FontPaint;

import javax.annotation.Nonnull;

/**
 * This class holds data used during text measuring and drawing.
 */
public class TextPaint extends FontPaint {

    public TextPaint() {
    }

    /**
     * Copy the data from paint into this TextPaint
     */
    public void set(@Nonnull TextPaint paint) {
        super.set(paint);
    }

    /**
     * Create a copy of this paint to a paint for internal layout engine.
     *
     * @return a internal paint
     */
    public FontPaint copyToInternal() {
        return new FontPaint(this);
    }

    /**
     * Calculate font metrics in pixels
     *
     * @param fm a FontMetrics to store the result
     */
    public void getFontMetrics(FontMetricsInt fm) {
        GlyphManagerBase.getInstance().getFontMetrics(mFontCollection, mFontStyle, mFontSize, fm);
    }
}
