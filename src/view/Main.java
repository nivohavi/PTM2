package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SimulatorModel;
import view_model.ViewModel;

import java.io.IOException;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage _primaryStage) {
        primaryStage = _primaryStage;

        // ***create models and view-model connections***
        // models
        SimulatorModel simModel = new SimulatorModel();
        // view model

        ViewModel viewModel = new ViewModel(simModel);
        //simModel.addObserver(viewModel);

        FXMLLoader fxl=new FXMLLoader();
        try {
            BorderPane root = fxl.load(getClass().getResource("Window.fxml").openStream());

            WindowController wc=fxl.getController(); // View
            wc.setViewModel(viewModel);

            Scene scene = new Scene(root,400,400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);

    }
}