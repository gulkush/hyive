package com.softkoki.hyive;

/**
 * Created by gulkush on 8/14/2016.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class FlipsideTextView extends TextView {
    public FlipsideTextView(Context context) {
        this(context, null);
    }

    public FlipsideTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlipsideTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(MyApp.tf_flipside);
    }

}
