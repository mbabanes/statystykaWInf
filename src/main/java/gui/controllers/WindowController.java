package gui.controllers;

import entity.provinces.Province;
import gui.model.DataSetModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

import java.io.IOException;

public class WindowController
{
    @FXML
    private TableView<Province> plantsTable;

    @FXML
    private TableColumn<Province, String> provinceCol;

    @FXML
    private TableColumn<Province, Number> areaColumn;

    @FXML
    private TableColumn<Province, Number> wheatCol;

    @FXML
    private TableColumn<Province, Number> ryeCol;

    @FXML
    private TableColumn<Province, String> barleyCol;

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
            this.provinceCol.setCellValueFactory(param -> param.getValue().nameProperty());
            this.areaColumn.setCellValueFactory(param -> param.getValue().areaProperty());
            this.wheatCol.setCellValueFactory(value -> value.getValue().wheatProperty().get().quantityProperty());
            this.ryeCol.setCellValueFactory(value -> value.getValue().ryeProperty().get().quantityProperty());
            this.barleyCol.setCellValueFactory(value -> value.getValue().barleyProperty().get().quantityProperty().asString());
            this.oatsCol.setCellValueFactory(value -> value.getValue().oatProperty().get().quantityProperty());
            this.potatoesCol.setCellValueFactory(value -> value.getValue().potatoesProperty().get().quantityProperty());
            this.sugarBeetsCol.setCellValueFactory(value -> value.getValue().sugarBeetsProperty().get().quantityProperty());

            this.barleyCol.setCellFactory(TextFieldTableCell.forTableColumn());

        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    public void wykresOknoAction()
    {
        //barley - jeczmien

        Stage stage = new Stage();
        stage.setTitle("Wykresik");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/wykres.fxml"));
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WykresController wykres = loader.getController();

        ObservableList<Province> woj = plantsTable.getItems();
        wykres.setWojewodztwa(woj);
        wykres.setStage(stage);
        wykres.dajWykres();
        stage.setScene(new Scene(pane));
        stage.show();
    }


    public void onCommit(TableColumn.CellEditEvent<Province, String> provinceNumberCellEditEvent)
    {

        switch (provinceNumberCellEditEvent.getTablePosition().getColumn())
        {
            case 4:
            {
                this.plantsTable.getItems().get(provinceNumberCellEditEvent.getTablePosition().getRow()).getBarley().setQuantity(Long.parseLong(provinceNumberCellEditEvent.getNewValue()));
                break;
            }
        }
        System.out.println(provinceNumberCellEditEvent.getTablePosition().getRow());

    }

}
