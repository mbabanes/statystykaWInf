package gui.loader;

import entity.provinces.Province;
import gui.controllers.CorrelationController;
import gui.controllers.StatisticsController;
import gui.controllers.WykresController;
import gui.model.CorrelationModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DataPresenterLoader
{
    private WykresController wykresController;
    private StatisticsController statisticsController;
    private CorrelationController correlationController;

    public static Stage stage;
    private ObservableList<Province> provinces;

    private TabPane tabPane;

    public DataPresenterLoader(ObservableList<Province> provinces)
    {
        this.provinces = provinces;
    }


    public void loadMapTab()
    {
        stage = new Stage();
        stage.setTitle("Wykresik");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/wykres.fxml"));
        Pane pane = null;

        try
        {
            pane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        tabPane = (TabPane) pane.lookup("#tabPane");
        wykresController = loader.getController();

        ObservableList<Province> woj = provinces;
        wykresController.setWojewodztwa(woj);
        wykresController.setStage(stage);
        wykresController.dajWykres();

        Scene sc = new Scene(pane);
        stage.setScene(sc);
    }


    public void loadStatisticTab()
    {
        statisticsController = new StatisticsController(provinces);
        this.tabPane.getTabs().get(1).setContent(loadFXML("/fxml/statistic.fxml", statisticsController));
    }


    public void loadCorrelationTab()
    {
        CorrelationModel correlationModel = new CorrelationModel(provinces);

        this.correlationController = new CorrelationController(correlationModel);
        this.tabPane.getTabs().get(2).setContent(loadFXML("/fxml/correlation.fxml", correlationController));
    }

    private Pane loadFXML(String fxml, Object controller)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxml));
        loader.setController(controller);
        try
        {
            VBox vbox = loader.load();
//            vbox.setFillWidth(true);
            return vbox;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public WykresController getWykresController()
    {
        return wykresController;
    }


    public Stage getStage()
    {
        return stage;
    }


    public StatisticsController getStatisticsController()
    {
        return statisticsController;
    }
}
