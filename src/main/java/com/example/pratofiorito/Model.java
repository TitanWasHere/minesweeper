package com.example.pratofiorito;

import com.example.pratofiorito.Cells.BaseCell;

public class Model {
    private int bombs; // n
    private int notClickedCells;

    private BaseCell[][] celle;

    public Model(){
        this (40, 20, 20);
    }

    public Model(int bombs, int n , int m){
        this.bombs = bombs;
        this.celle = new BaseCell[n][m];

        this.notClickedCells = n*m;
    }

    public BaseCell[][] getCelle() {
        return celle;
    }

    public int getBombs() {
        return bombs;
    }

    public int getNotClickedCells() {
        return notClickedCells;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public void setCelle(BaseCell[][] celle) {
        this.celle = celle;
    }

    public void setNotClickedCells(int notClickedCells) {
        this.notClickedCells = notClickedCells;
    }
}
