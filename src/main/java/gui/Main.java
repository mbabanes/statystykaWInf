package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Scene mainWindow = new Scene(FXMLUtils.fxmlLoader("/fxml/window.fxml"));
        primaryStage.setScene(mainWindow);
//        primaryStage.setTitle(FXMLUtils.getResourceBundle().getString("title.application"));

        primaryStage.show();
    }
}
