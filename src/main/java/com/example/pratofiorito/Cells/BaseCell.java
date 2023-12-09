package com.example.pratofiorito.Cells;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

abstract public class BaseCell extends StackPane {

    private int x;
    private int y;

    private Color color; // Switch dei colori per quando viene o meno cliccata
    private Rectangle rectangle;
    private Text txt;
    private boolean active;
    private boolean bomb;

    private Text bandiera = null;
    private boolean toSetBandiera;

    BaseCell(String txt, int x , int y, boolean bomb){
        this.active = false;
        this.bomb = bomb;
        this.txt = new Text(txt);
        this.txt.setVisible(false);

        this.rectangle = new Rectangle(25,25);
        this.rectangle.setFill(Color.DARKGRAY);

        this.x = x;
        this.y = y;

        bandiera = new Text("O");
        bandiera.setVisible(false);
        this.toSetBandiera = true;

        this.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        this.getChildren().setAll(this.rectangle, this.txt, this.bandiera);

    }

    public void printAllBombs(BaseCell[][] cellGrid){
        for (BaseCell[] baseCells : cellGrid)
            for (BaseCell base : baseCells)
                if (base.isBomb())
                    base.getTxt().setVisible(true);


        
    }

    public void setBandiera(){
        if(toSetBandiera) {
            toSetBandiera = false;
            this.bandiera.setVisible(true);
        }else{
            toSetBandiera = true;
            this.bandiera.setVisible(false);
        }

    }
    public boolean isToSetBandiera(){
        return toSetBandiera;
    }

    abstract public int action(BaseCell[][] cellGrid);

    public boolean isBomb() {
        return bomb;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTxt(String str) {
        this.txt.setText(str);
    }

    public Text getTxt() {
        return txt;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
