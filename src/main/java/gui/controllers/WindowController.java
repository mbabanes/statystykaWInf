package gui.controllers;

import entity.provinces.Province;
import gui.model.DataSetModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class WindowController
{
    @FXML
    private TableView<Province> plantsTable;

    @FXML
    private TableColumn<Province, String> provvinceCol;

    @FXML
    private TableColumn<Province, Number> wheatCol;

    @FXML
    private TableColumn<Province, Number> ryeCol;

    @FXML
    private TableColumn<Province, Number> barleyCol;

    @FXML
    private TableColumn<Province, Number> oatsCol;

    @FXML
    private TableColumn<Province, Number> potatoesCol;

    @FXML
    private TableColumn<Province, Number> sugarBeetsCol;

    private DataSetModel dataSetModel;

    @FXML
    public void initialize()
    {
        dataSetModel = new DataSetModel();

        try
        {
            dataSetModel.loadData();
            plantsTable.setItems(dataSetModel.getDataList());
            this.provvinceCol.setCellValueFactory(param -> param.getValue().nameProperty());
            this.wheatCol.setCellValueFactory(value -> value.getValue().wheatProperty().get().quantityProperty());
            this.ryeCol.setCellValueFactory(value -> value.getValue().ryeProperty().get().quantityProperty());
            this.barleyCol.setCellValueFactory(value -> value.getValue().barleyProperty().get().quantityProperty());
            this.oatsCol.setCellValueFactory(value -> value.getValue().oatProperty().get().quantityProperty());
            this.potatoesCol.setCellValueFactory(value -> value.getValue().potatoesProperty().get().quantityProperty());
            this.sugarBeetsCol.setCellValueFactory(value -> value.getValue().sugarBeetsProperty().get().quantityProperty());

        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }
    }
}
