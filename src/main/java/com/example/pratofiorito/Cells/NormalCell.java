package com.example.pratofiorito.Cells;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class NormalCell extends BaseCell {

    private int nearbyBombs;

    public NormalCell(String txt,int x, int y, BaseCell [][] cellGrid, int nearbyBombs) {
        super(txt,x,y,false);
        this.nearbyBombs = nearbyBombs;
    }


    private int rec_clicks(BaseCell[][] cellGrid , BaseCell last){
        int act = 0;
        for(int i = -1 ; i <= 1 ; i++){
            for(int j = -1 ; j <= 1 ; j++){
                try{
                    if(cellGrid[getX()+i][getY()+j] != last){

                        act += cellGrid[getX()+i][getY()+j].action(cellGrid);
                    }

                }catch (IndexOutOfBoundsException ignored){}
            }
        }
        return act;
    }


    @Override
    public int action(BaseCell[][] cellGrid) {

        if(isActive()) return 0;

        if(!isToSetBandiera()) setBandiera();

        getRectangle().setFill(Color.LIGHTGRAY);
        getTxt().setVisible(true);

        setActive(true);

        if(nearbyBombs == 0){
            return 1+rec_clicks(cellGrid, cellGrid[getX()][getY()]);
        }
        return 1;
    }

}
