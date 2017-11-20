package gui.controllers;

import entity.provinces.Province;
import gui.model.StatisticsModel;
import gui.model.entityFx.Statistic;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class StatisticsController
{
    @FXML
    private TableView<Statistic> statisticTable;

    @FXML
    private TableColumn<Statistic, String> titleCol;

    @FXML
    private TableColumn<Statistic, String> wheatCol;

    @FXML
    private TableColumn<Statistic, String> ryeCol;

    @FXML
    private TableColumn<Statistic, String> barleyCol;

    @FXML
    private TableColumn<Statistic, String> oatCol;

    @FXML
    private TableColumn<Statistic, String> potatoesCol;

    @FXML
    private TableColumn<Statistic, String> sugarBeetsCol;



    private StatisticsModel statisticsModel;

    public StatisticsController()
    {

    }

    public StatisticsController(ObservableList<Province> provinceObservableList)
    {
        this();
        this.statisticsModel = new StatisticsModel(provinceObservableList);

    }

    @FXML
    public void initialize()
    {
        statisticsModel.countStatistics();
        statisticTable.setItems(statisticsModel.getDescriptiveStatisticObservableList());
        this.titleCol.setCellValueFactory(value -> value.getValue().nameOfStatProperty());
        this.barleyCol.setCellValueFactory(value -> value.getValue().barleyValueProperty());
        this.oatCol.setCellValueFactory(value -> value.getValue().oatValueProperty());
        this.potatoesCol.setCellValueFactory(value -> value.getValue().potatoesValueProperty());
        this.ryeCol.setCellValueFactory(value -> value.getValue().ryeValueProperty());
        this.sugarBeetsCol.setCellValueFactory(value -> value.getValue().sugarBeetsValueProperty());
        this.wheatCol.setCellValueFactory(value -> value.getValue().wheatValueProperty());
    }




    public void refresh()
    {
        this.statisticsModel.refresh();
    }
}
