package gui.controllers;

import entity.plants.Plant;
import entity.provinces.Province;
import gui.controllers.utill.Dialogs;
import gui.controllers.utill.QuantityInFormat;
import gui.controllers.utill.ValidateQuantityValue;
import gui.model.DataSetModel;
import gui.wykresy.Wykres;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

public class WindowController
{
    @FXML
    private TableView<Province> plantsTable;

    @FXML
    private TableColumn<Province, String> provinceCol;

    @FXML
    private TableColumn<Province, String> areaColumn;

    @FXML
    private TableColumn<Province, String> wheatCol;

    @FXML
    private TableColumn<Province, String> ryeCol;

    @FXML
    private TableColumn<Province, String> barleyCol;

    @FXML
    private TableColumn<Province, String> oatsCol;

    @FXML
    private TableColumn<Province, String> potatoesCol;

    @FXML
    private TableColumn<Province, String> sugarBeetsCol;

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
            this.provinceCol.setCellValueFactory(value -> value.getValue().nameProperty());
            this.areaColumn.setCellValueFactory(value -> {
                StringProperty areaValue = new SimpleStringProperty();
                areaValue.setValue(NumberFormat.getNumberInstance().format(value.getValue().getArea()));
                return areaValue;
            });
            this.wheatCol.setCellValueFactory(value -> {
                QuantityInFormat quantityInFormat = new QuantityInFormat(value.getValue().getWheat());
                return quantityInFormat.parse();
            });
            this.ryeCol.setCellValueFactory(value -> {
                QuantityInFormat quantityInFormat = new QuantityInFormat(value.getValue().getRye());
                return quantityInFormat.parse();
            });
            this.barleyCol.setCellValueFactory(value -> {
                QuantityInFormat quantityInFormat = new QuantityInFormat(value.getValue().getBarley());
                return quantityInFormat.parse();
            });
            this.oatsCol.setCellValueFactory(value -> {
                QuantityInFormat quantityInFormat = new QuantityInFormat(value.getValue().getOat());
                return quantityInFormat.parse();
            });
            this.potatoesCol.setCellValueFactory(value -> {
                QuantityInFormat quantityInFormat = new QuantityInFormat(value.getValue().getPotatoes());
                return quantityInFormat.parse();
            });
            this.sugarBeetsCol.setCellValueFactory(value -> {
                QuantityInFormat quantityInFormat = new QuantityInFormat(value.getValue().getSugarBeets());
                return quantityInFormat.parse();
            });

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
        stageWykres.setScene(new Scene(pane));
    }

    @FXML
    public void editPlantValuesCommit(TableColumn.CellEditEvent<Province, String> cell)
    {
        String newValue = cell.getNewValue();
        ValidateQuantityValue validator = new ValidateQuantityValue(newValue);
        try
        {
            Number value = validator.parse();
            final int columnOffset = 2;
            int idPlant = cell.getTablePosition().getColumn() - columnOffset;
            Province province = cell.getRowValue();

            this.dataSetModel.editDataList(province, value.longValue(), idPlant);
        }
        catch (ParseException e)
        {
            Dialogs.errorDialog("Błąd walidacji, należy podawać liczby całkowite dodatnie.");
        }

        this.plantsTable.refresh();
    }

    private void ustawienieParametrowEdycji()
    {
        wheatCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ryeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        barleyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        oatsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        potatoesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        sugarBeetsCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }
}
