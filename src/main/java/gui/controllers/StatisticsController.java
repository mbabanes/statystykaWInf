package gui.controllers;

import entity.provinces.Province;
import gui.model.StatisticsModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class StatisticsController
{
    @FXML
    private Label label;


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

        statisticsModel.wstaw();

//        label.textProperty().bind(statisticsModel.textProperty());
//        statisticsModel.textProperty().bind(label.textProperty());
        label.textProperty().bind(statisticsModel.textProperty());
    }



    public StatisticsModel getStatisticsModel()
    {
        return statisticsModel;
    }

    public void setStatisticsModel(StatisticsModel statisticsModel)
    {
        this.statisticsModel = statisticsModel;
    }

    public void refresh()
    {
        this.statisticsModel.wstaw();
    }
}
