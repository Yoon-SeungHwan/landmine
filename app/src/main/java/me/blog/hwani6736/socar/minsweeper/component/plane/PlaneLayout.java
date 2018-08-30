package me.blog.hwani6736.socar.minsweeper.component.plane;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import me.blog.hwani6736.socar.R;
import me.blog.hwani6736.socar.minsweeper.component.LandMineIcon;
import me.blog.hwani6736.socar.minsweeper.component.NumberIcon;

/**
 * 전체 필드를 UI로 표현
 * Created by NarZa on 2018. 8. 31..
 */
public class PlaneLayout extends ScrollView {
    private PlaneObject planeObject;
    private LinearLayout layoutFrame;
    private Context context;

    public PlaneLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        layoutFrame = new LinearLayout(context);
        layoutFrame.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layoutFrame.setOrientation(LinearLayout.VERTICAL);

        super.addView(layoutFrame);
    }

    public void bind(PlaneObject planeObject) {
        layoutFrame.removeAllViews();

        this.planeObject = planeObject;
        if (planeObject != null)
            drawViews();
    }

    private void drawViews() {

        int maxX = planeObject.getMaxSizeX();
        int maxY = planeObject.getMaxSizeY();
        int lineHeight = getResources().getDimensionPixelSize(R.dimen.landmine_cell_height);

        for (int idxY = 0; idxY < maxY; idxY++) {

            LinearLayout line = new LinearLayout(context);
            line.setOrientation(LinearLayout.HORIZONTAL);
            line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, lineHeight));

            for (int idxX = 0; idxX < maxX; idxX++) {
                int number = planeObject.getLandType(idxX, idxY);

                if (number == PlaneObject.LAND_TYPE_LANDMINE) {
                    LandMineIcon icon = new LandMineIcon(context);
                    line.addView(icon);
                } else {
                    NumberIcon icon = new NumberIcon(context);
                    icon.setNumber(number);
                    line.addView(icon);
                }
            }

            addView(line);
        }
    }

    @Override
    public void addView(View child) {

        if (layoutFrame != null)
            layoutFrame.addView(child);
    }
}
