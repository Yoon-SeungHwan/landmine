package me.blog.hwani6736.socar.minsweeper.component;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import me.blog.hwani6736.socar.R;

/**
 * Created by NarZa on 2018. 8. 31..
 */
public class LandMineIcon extends RelativeLayout {
    public LandMineIcon(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_mine, this, true);
        setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
    }
}
