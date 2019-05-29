package lab.pkg19;
    
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;

public class Lab19 extends Application {

    Label response;

    Double winSizeX = 800.0;
    Double winSizeY = 800.0;

    Double borderWidth = 800.0;
    Double borderHeight = 800.0;

    Random rand = new Random();

    public static void main(String[] args)
    {
        launch(args);
    }

    void  smoothButtonMove(Button btn,double x1, double y1, double x2, double y2, double time)
    {
        double dt = 0.1;
        double n = time / dt;
        double dx = (x2-x1)/n;
        double dy = (y2-y1)/n;
        double x=x1, y= y1;

        for(int i=0; i<n; i++)
        {
            x+= dx;
            y+=dy;
            btn.setLayoutX(x);
            btn.setLayoutY(y);
            try {
                Thread.sleep(16);
            } catch (InterruptedException e)
            {
            }
        }
    }

    public void start(Stage myStage)
    {
        myStage.setTitle("Работа с кнопками и событиями в JavaFX");
        Pane rootNode = new Pane();

        Scene myScene = new Scene(rootNode,winSizeX, winSizeY);

        myStage.setScene(myScene);

        response = new Label("Какую оценку по ООП ты хочешь?");
        response.setLayoutX(330);
        response.setLayoutY(380);

        Button btnAlpha = new Button("неуд.");
        btnAlpha.setLayoutX(400);
        btnAlpha.setLayoutY(400);
        Button btnBeta = new Button("отлично");
        btnAlpha.setLayoutX(400);
        btnAlpha.setLayoutY(400);

        btnAlpha.setOnAction((ActionEvent event) -> {
            response.setText("Да уж, быстро сдался <НЕУД.>");
        });

        btnBeta.setOnAction((ActionEvent event) -> {
            response.setText("Молодец, старался! <ОТЛИЧНО>");
        });

        btnBeta.setOnMouseEntered((MouseEvent event) -> {
            double x =  rand.nextDouble()*(winSizeX - borderWidth);
            double y = rand.nextDouble()*(winSizeY - borderHeight);
            
            System.out.println();
            System.out.println(x);
            System.out.println(y);
            
            borderWidth = btnBeta.getWidth();
            borderHeight = btnBeta.getHeight();
            
            Thread t = new Thread(() -> {
                smoothButtonMove(btnBeta,btnBeta.getLayoutX(), btnBeta.getLayoutY(), x, y, 1);
            });
            t.start();
        });

        rootNode.getChildren().addAll(btnAlpha, btnBeta, response);
        myStage.show();
    }
}