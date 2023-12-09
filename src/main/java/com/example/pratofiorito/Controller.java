package com.example.pratofiorito;

import com.example.pratofiorito.Cells.BaseCell;
import com.example.pratofiorito.Cells.BombCell;
import com.example.pratofiorito.Cells.NormalCell;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.util.Random;

public class Controller {

    private final View view;
    private Model model;

    private boolean windowOpened;

    public Controller(Stage stage)
    {
        windowOpened = false;
        view = new View(stage);

        init(view.getNumberBombs(), view.getN(), view.getM());
    }

    void init(int nBombs, int n, int m){
        model = new Model(nBombs, n, m);
        view.getRestartBtn().setOnAction(actionEvent -> init(nBombs, n, m));
        view.setWinOrLose("");

        BaseCell[][] celle = model.getCelle();

        //Reset del GridPane
        view.reset();
        //Inizializzazione della griglia con all'interno le bombe
        Random rand = new Random();
        for(int i = 0 ; i < nBombs ; i++){
            int x, y;
            do{
                x = rand.nextInt(n);
                y = rand.nextInt(m);
            }while(celle[x][y] != null);
            celle[x][y] = new BombCell("X", x, y);
        }
        //Inizializzazione di tutte le celle normali
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                if (celle[i][j] == null){
                    int k = nearbyBombs(celle, i, j);
                    celle[i][j] = new NormalCell(""+(k == 0 ? " " : k), i, j, celle,k);
                }

                //celle[i][j].setOnMouseClicked(mouseEvent -> clickCell((BaseCell) mouseEvent.getSource()));
                celle [i][j].setOnMouseClicked(event -> {
                    if(event.getButton() == MouseButton.PRIMARY) clickCell((BaseCell) event.getSource());
                    else if(event.getButton() == MouseButton.SECONDARY) setFlag((BaseCell) event.getSource());
                });
                view.getGrid().add(celle[i][j], i, j);
            }
        if(!windowOpened){
            windowOpened = true;
            view.initStage();
        }

    }
    private void setFlag(BaseCell cella){
        if(cella.isActive()) return;

        cella.setBandiera();
    }
    private int nearbyBombs(BaseCell[][] cellGrid, int x, int y){
        int bombCounter = 0;
        for(int i = -1 ; i <= 1 ; i++)
            for(int j = -1 ; j <= 1 ; j++){
                try{
                    if(cellGrid[x+i][y+j] != null)
                        if(cellGrid[x+i][y+j].isBomb()) bombCounter++;

                }catch (IndexOutOfBoundsException ignored){}
            }
        return bombCounter;
    }
    public void clickCell(BaseCell cella){
        if(cella.isActive()) return;

        int act = cella.action(model.getCelle());
        if(act == -1){
            disableGrid();
            view.setWinOrLose("Hai perso :(");
        }else{
            // Questo vale solo se le celle intorno non vengono ricliccate
            model.setNotClickedCells(model.getNotClickedCells()-act);
            if(model.getNotClickedCells() - model.getBombs() == 0){
                disableGrid();
                cella.printAllBombs(model.getCelle());
                view.setWinOrLose("Hai vinto!");
            }
        }
    }

    private void disableGrid(){
        for(BaseCell[] BaseCells : model.getCelle())
            for(BaseCell base : BaseCells){
                base.setDisable(true);
                base.setOpacity(0.5);
            }
    }
}
