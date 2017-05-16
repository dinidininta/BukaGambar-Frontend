package com.example.bukagambarfrontend;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by User-pc on 16/05/2017.
 */

public class PrefixEditText extends AppCompatEditText {
    float mOriginalRightPadding = -1;

    public PrefixEditText(Context context) {
        super(context);
    }

    public PrefixEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PrefixEditText(Context context, AttributeSet attrs,
                          int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        calculatePrefix();
    }

    private void calculatePrefix() {
        if (mOriginalRightPadding == -1) {
            String prefix = (String) getTag();
            float[] widths = new float[prefix.length()];
            getPaint().getTextWidths(prefix, widths);
            float textWidth = 0;
            for (float w : widths) {
                textWidth += w;
            }
            mOriginalRightPadding = getCompoundPaddingStart();
            setPadding((int) (textWidth + mOriginalRightPadding),
                    getPaddingEnd(), getPaddingTop(),
                    getPaddingBottom());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String prefix = (String) getTag();
        canvas.drawText(prefix, mOriginalRightPadding,
                getLineBounds(0, null), getPaint());
    }
}