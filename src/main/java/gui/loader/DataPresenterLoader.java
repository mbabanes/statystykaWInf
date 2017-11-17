package gui.loader;

import entity.provinces.Province;
import gui.controllers.StatisticsController;
import gui.controllers.WykresController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DataPresenterLoader
{
    private WykresController wykresController;
    private StatisticsController statisticsController;
    private Stage stage;
    private ObservableList<Province> provinces;

    private TabPane tabPane;

    public DataPresenterLoader(ObservableList<Province> provinces)
    {
        this.provinces = provinces;
    }


    public void loadWykresWindow()
    {
        stage = new Stage();
        stage.setTitle("Wykresik");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/wykres.fxml"));
        AnchorPane pane = null;

        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tabPane = (TabPane) pane.getChildren().get(0);
        wykresController = loader.getController();

        ObservableList<Province> woj = provinces;
        wykresController.setWojewodztwa(woj);
        wykresController.setStage(stage);
        wykresController.dajWykres();
        stage.setScene(new Scene(pane));
    }


    public void loadStatisticWindow()
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/statistic.fxml"));
        try
        {
            statisticsController = new StatisticsController(provinces);
            loader.setController(statisticsController);
            Pane pane = loader.load();
//            statisticsController.initialize();
            this.tabPane.getTabs().get(1).setContent(pane);
        }
        catch (IOException e)
        {
            System.out.println("Blad ladowania statisitcWindow");
        }
    }

    public WykresController getWykresController()
    {
        return wykresController;
    }

    public void setWykresController(WykresController wykresController)
    {
        this.wykresController = wykresController;
    }

    public Stage getStage()
    {
        return stage;
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public StatisticsController getStatisticsController()
    {
        return statisticsController;
    }
}
