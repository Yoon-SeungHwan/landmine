package me.blog.hwani6736.socar.minsweeper;

import android.graphics.Point;

import me.blog.hwani6736.socar.minsweeper.component.plane.PlaneObject;

/**
 * Created by NarZa on 2018. 8. 29..
 */
public class GameManager {
    private boolean isInitialized;
    private Point[] landMines;
    private PlaneObject planeObject;

    GameManager(int xSize, int ySize, int landMineSize) {

        landMines = LandMineGenerator.generateLandMines(xSize, ySize, landMineSize);
        planeObject = new PlaneObject(xSize, ySize);

        isInitialized = true;
    }

    public PlaneObject run() {
        if (!isInitialized)
            throw new RuntimeException("Data Not Initialized Exception");

        planeObject.markLandMine(landMines);

        planeObject.print();

        return planeObject;
    }

}
