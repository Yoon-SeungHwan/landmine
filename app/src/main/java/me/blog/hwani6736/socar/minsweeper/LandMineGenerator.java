package me.blog.hwani6736.socar.minsweeper;

import android.graphics.Point;
import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

/**
 * Created by NarZa on 2018. 8. 30..
 */
class LandMineGenerator {
    private static final String TAG = LandMineGenerator.class.getSimpleName();

    static Point[] generateLandMines(int xSize, int ySize, int landMineSize) {

        Point[] landMinePoints = new Point[landMineSize];
        Random random = new Random();

        HashSet<String> tempCoordinates= new HashSet<>();

        for (int i=0; i<landMineSize; i++) {
            int x = random.nextInt(xSize);
            int y = random.nextInt(ySize);

            String key = String.valueOf(x) + "_" + String.valueOf(y);

            if (tempCoordinates.add(key))
                landMinePoints[i] = new Point(x, y);
            else
                i--;
        }

        return landMinePoints;
    }
}
