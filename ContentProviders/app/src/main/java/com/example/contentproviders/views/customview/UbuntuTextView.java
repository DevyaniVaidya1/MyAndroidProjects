package com.example.contentproviders.views.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.contentproviders.constants.Constants;

public class UbuntuTextView extends AppCompatTextView {

    public UbuntuTextView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), Constants.FONT_PATH));
    }

    public UbuntuTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UbuntuTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
}
