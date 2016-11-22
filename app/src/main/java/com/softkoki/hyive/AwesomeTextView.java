package com.softkoki.hyive;

/**
 * Created by gulkush on 8/14/2016.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class AwesomeTextView extends TextView {
    public AwesomeTextView(Context context) {
        this(context, null);
    }

    public AwesomeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AwesomeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(MyApp.tf_awesome);
    }

}
