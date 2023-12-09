package com.example.pratofiorito;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class View {
    private Stage stage;

    private Dialog<String> firstWindow;
    private Label nameLabel;
    private Label orizzontalCellsLabel;
    private Label verticalCellsLabel;
    private Label bombsLabel;

    private TextField nameField;
    private TextField orizzontalCellsField;
    private TextField verticalCellsField;
    private TextField bombsField;

    private GridPane gridWindow;
    private ButtonType buttonOk;

    private BorderPane root;

    private VBox titleBox;
    private Text title;
    private Text nBombs;
    private Text winOrLose;
    private Text name;

    private GridPane grid;
    private VBox gameBox;
    private Button restartBtn;

    public View(Stage stage){
        reStartFirst("", "");

        name = new Text("Benvenuto "+nameField.getText()+"!");
        title = new Text("Campo minato");
        nBombs = new Text("N° bombe: "+bombsField.getText());
        winOrLose = new Text("");

        titleBox = new VBox(name,title, nBombs, winOrLose);
        VBox.setMargin(name, new Insets(10,0,0,0));
        titleBox.setAlignment(Pos.CENTER);

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        restartBtn = new Button("Restart");
        restartBtn.setAlignment(Pos.CENTER);

        gameBox = new VBox(titleBox,grid, restartBtn);
        gameBox.setSpacing(10);
        VBox.setMargin(grid, new Insets(5,15,0,15));
        VBox.setMargin(restartBtn, new Insets(0,0,10,0));
        gameBox.setAlignment(Pos.CENTER);

        this.root = new BorderPane();
        root.setCenter(gameBox);


        this.stage = stage;
        /*Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Campo minato");
        stage.show();*/
    }
    public void initStage(){
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Campo minato");
        stage.show();
    }

    private void reStartFirst(String name, String error){
        firstWindow = new Dialog<String>();
        firstWindow.setTitle("Benvenuto a Campo minato!");
        firstWindow.setHeaderText(error);

        gridWindow = new GridPane();
        nameLabel = new Label("Nome: ");
        nameField = new TextField(name);
        orizzontalCellsLabel = new Label("Celle orizzontali: ");
        orizzontalCellsField = new TextField("15");
        verticalCellsLabel = new Label("Celle verticali: ");
        verticalCellsField = new TextField("20");
        bombsLabel = new Label("Bombe: ");
        bombsField = new TextField("30");

        buttonOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        gridWindow.add(nameLabel, 1,1);
        gridWindow.add(nameField, 2,1);
        gridWindow.add(orizzontalCellsLabel, 1, 2);
        gridWindow.add(orizzontalCellsField, 2, 2);
        gridWindow.add(verticalCellsLabel,1,3);
        gridWindow.add(verticalCellsField,2,3);
        gridWindow.add(bombsLabel, 1, 4);
        gridWindow.add(bombsField,2, 4);

        firstWindow.getDialogPane().setContent(gridWindow);
        firstWindow.getDialogPane().getButtonTypes().add(buttonOk);

        firstWindow.showAndWait();

        int nBombs = Integer.parseInt(bombsField.getText());
        int nOrizzontalCells = Integer.parseInt(orizzontalCellsField.getText());
        int nVerticalCells = Integer.parseInt(verticalCellsField.getText());
        int nmCells = (nOrizzontalCells * nVerticalCells)-1;

        boolean ok = true;
        String err = "";
        if(nBombs < 1 || nBombs > nmCells){
            ok = false;
            err = "Inserisci un numero di bombe compreso tra 1 e il totale delle celle -1 ";
        }
        if(ok && (nOrizzontalCells > 53 || nOrizzontalCells < 4 )){
            ok = false;
            err = "Inserisci un numero di celle orizzontali compreso tra 4 e 53";
        }
        if(ok && (nVerticalCells > 24 || nVerticalCells < 1)){
            ok = false;
            err = "Inserisci un numero di celle verticali compreso tra 1 e 24";
        }


        if (!ok) reStartFirst(nameField.getText(), err);

    }

    public int getN(){
        return Integer.parseInt(this.orizzontalCellsField.getText());
    }
    public int getM(){
        return Integer.parseInt(this.verticalCellsField.getText());
    }
    public int getNumberBombs(){
        return Integer.parseInt(this.bombsField.getText());
    }

    public GridPane getGrid() {
        return grid;
    }
    public BorderPane getRoot() {
        return root;
    }
    public Button getRestartBtn() {
        return restartBtn;
    }
    public Text getTitle() {
        return title;
    }

    public void setWinOrLose(String str) {
        this.winOrLose.setText(str);
    }

    public void reset(){
        grid.getChildren().clear();
    }
}

