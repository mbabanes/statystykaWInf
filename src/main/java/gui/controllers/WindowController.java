package gui.controllers;

import entity.plants.Plant;
import entity.provinces.Province;
import gui.model.DataSetModel;
import gui.wykresy.Wykres;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

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
    private TableColumn<Province, Number> barleyCol;

    @FXML
    private TableColumn<Province, Number> oatsCol;

    @FXML
    private TableColumn<Province, Number> potatoesCol;

    @FXML
    private TableColumn<Province, Number> sugarBeetsCol;

    private WykresController wykresController;

    private Stage stageWykres;

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
            this.barleyCol.setCellValueFactory(value -> value.getValue().barleyProperty().get().quantityProperty());
            this.oatsCol.setCellValueFactory(value -> value.getValue().oatProperty().get().quantityProperty());
            this.potatoesCol.setCellValueFactory(value -> value.getValue().potatoesProperty().get().quantityProperty());
            this.sugarBeetsCol.setCellValueFactory(value -> value.getValue().sugarBeetsProperty().get().quantityProperty());
            provinceCol.setEditable(true);


            ustawienieParametrowEdycji();
            initStageWykres();

        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    public void wykresOknoAction()
    {
        stageWykres.show();
    }

    private void initStageWykres()
    {
        stageWykres = new Stage();
        stageWykres.setTitle("Wykresik");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/fxml/wykres.fxml"));
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        wykresController = loader.getController();

        ObservableList<Province> woj = plantsTable.getItems();
        wykresController.setWojewodztwa(woj);
        wykresController.setStage(stageWykres);
        wykresController.dajWykres();

        if(pane!=null)
            stageWykres.setScene(new Scene(pane));
    }

    @FXML
    public void cellEditCommit(TableColumn.CellEditEvent<Province,Number> cell)
    {
        if( cell.getNewValue()==null)
            return;

        if(cell.getTableView().getColumns().indexOf(cell.getTableColumn())>1) {
            cell.getTableView().getItems().get(cell.getTablePosition().getRow()).getListOfPlant().get(cell.getTableView().getColumns().indexOf(cell.getTableColumn()) - 2).setQuantity((long) cell.getNewValue());
            wykresController.aktualizujWykres(cell.getTablePosition().getRow());
        }
        if(cell.getTableView().getColumns().indexOf(cell.getTableColumn())==1) {
            cell.getTableView().getItems().get(cell.getTablePosition().getRow()).areaProperty().setValue(cell.getNewValue());
            wykresController.zmienKolorWojewodztwa(cell.getTablePosition().getRow());

        }
    }

    private void ustawienieParametrowEdycji()
    {
        provinceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        areaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        wheatCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        ryeCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        barleyCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        oatsCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        potatoesCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        sugarBeetsCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
    }
}
