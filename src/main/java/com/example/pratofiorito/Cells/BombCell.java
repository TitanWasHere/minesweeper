package com.example.pratofiorito.Cells;

import javafx.scene.paint.Color;

public class BombCell extends BaseCell{

    public BombCell(String txt, int x, int y) {
        super(txt, x, y, true);
    }


    @Override
    public int action(BaseCell[][] cellGrid) {
        if(isActive()) return -2;
        if(!isToSetBandiera()) setBandiera();
        this.getRectangle().setFill(Color.LIGHTGRAY);
        this.getTxt().setVisible(true);
        this.getTxt().setFill(Color.RED);

        setActive(true);

        printAllBombs(cellGrid);

        return -1;
    }
}
