package me.blog.hwani6736.socar.minsweeper.component.plane;

import android.graphics.Point;
import android.util.Log;

import java.util.Locale;

/**
 * 플레이 필드
 * Created by NarZa on 2018. 8. 30..
 */
public class PlaneObject {
    private final String TAG = PlaneObject.class.getSimpleName();
    public static final int LAND_TYPE_LANDMINE = -1;

    private int maxSizeX;
    private int maxSizeY;
    private int[][] plane;

    public PlaneObject(int sizeX, int sizeY) {
        this.maxSizeX = sizeX;
        this.maxSizeY = sizeY;
    }

    public void markLandMine(Point[] landMinePoints) {

        plane = new int[maxSizeX][maxSizeY];

        if (landMinePoints != null) {
            for (Point point : landMinePoints) {
                if (isIndexBound(point.x, point.y))
                    markPointRound(point.x, point.y);
            }
        }
    }

    private void markPointRound(int x, int y) {

        int prevX = x - 1, nextX = x + 1;

        if (x >= 0 && x < maxSizeX && y >= 0 && y < maxSizeY)
            plane[x][y] = LAND_TYPE_LANDMINE;

        // Mark x Line
        markVertical(x, y);

        // Mark x - 1 Line
        if (prevX >= 0)
            markVertical(prevX, y);

        // Mark x + 1 Line
        if (nextX < maxSizeX)
            markVertical(nextX, y);
    }

    private void markVertical(int x, int y) {

        int prevY = y - 1, nextY = y + 1;

        if (isNormalGround(x, y))
            plane[x][y]++;

        if (prevY >= 0 && isNormalGround(x, prevY))
            plane[x][prevY]++;

        if (nextY < maxSizeY && isNormalGround(x, nextY))
            plane[x][nextY]++;
    }

    /**
     * 지뢰가 있는 땅인지 검사
     * @param x
     * @param y
     * @return True 지뢰가 없는 땅, False 지뢰가 있는 땅
     */
    private boolean isNormalGround(int x, int y) {
        return plane[x][y] != LAND_TYPE_LANDMINE;
    }

    // ArrayIndex 유효성 검사
    private boolean isIndexBound(int x, int y) {
        return x >= 0 && x < maxSizeX
                && y >= 0 && y < maxSizeY;
    }

    public int getLandType(int x, int y) {

        return isIndexBound(x, y)
                ? plane[x][y]
                : 0;
    }

    public void print() {
        StringBuilder stringBuilder = new StringBuilder("================================\n");
        for (int idxY = 0; idxY < maxSizeY; idxY++) {
            for (int idxX = 0; idxX < maxSizeX; idxX++)
                stringBuilder.append(String.format(Locale.KOREAN, "%2d\t",plane[idxX][idxY]));
            stringBuilder.append("\n");
        }
        Log.e(TAG, stringBuilder.toString());
    }

    public int getMaxSizeX() {
        return maxSizeX;
    }

    public int getMaxSizeY() {
        return maxSizeY;
    }
}