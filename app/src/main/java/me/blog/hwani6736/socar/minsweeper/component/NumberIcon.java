package me.blog.hwani6736.socar.minsweeper.component;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.blog.hwani6736.socar.R;

/**
 * Created by NarZa on 2018. 8. 31..
 */
public class NumberIcon extends RelativeLayout {
    public NumberIcon(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_ground_number, this, true);
        setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
    }

    public void setNumber(int number) {
        ((TextView)findViewById(R.id.tv_count)).setText(String.valueOf(number));
    }
}
