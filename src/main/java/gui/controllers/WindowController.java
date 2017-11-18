package gui.controllers;

import entity.provinces.Province;
import gui.controllers.utill.Dialogs;
import gui.controllers.utill.QuantityStringPropertyConverter;
import gui.controllers.utill.QuantityValueValidator;
import gui.loader.DataPresenterLoader;
import gui.model.DataSetModel;
import gui.model.StatisticsModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.stage.Stage;

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

    private DataPresenterLoader dataPresenterLoader;

    @FXML
    public void initialize()
    {
        dataSetModel = new DataSetModel();

        try
        {
            dataSetModel.loadData();
            prepareTableView();
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

    @FXML
    public void editPlantValuesCommit(TableColumn.CellEditEvent<Province, String> cell)
    {
        String newValue = cell.getNewValue();
        QuantityValueValidator validator = new QuantityValueValidator(newValue);
        try
        {
            Number value = validator.validate();
            int idPlant = appointIdPlant(cell.getTablePosition().getColumn());
            Province province = cell.getRowValue();

            this.dataSetModel.updateDataList(province, value.longValue(), idPlant);

            refreshStatistics(province.getId());
        } catch (ParseException e) {
            Dialogs.errorDialog("Błąd walidacji, należy podawać liczby całkowite dodatnie.");
        }

        this.plantsTable.refresh();
    }


    private void refreshStatistics(int idProvince)
    {
        this.wykresController.aktualizujWykres(idProvince);
//        this.wykresController.zmienKolorWojewodztwa(idProvince);
        this.dataPresenterLoader.getStatisticsController().refresh();

    }


    private void prepareTableView()
    {
        plantsTable.setItems(dataSetModel.getDataList());
        putValuesToEachColumn();
        ustawienieParametrowEdycji();
    }

    private void putValuesToEachColumn()
    {
        QuantityStringPropertyConverter converter = new QuantityStringPropertyConverter();

        this.provinceCol.setCellValueFactory(value -> value.getValue().nameProperty());
        this.areaColumn.setCellValueFactory(value -> {
            StringProperty areaValue = new SimpleStringProperty();
            areaValue.setValue(NumberFormat.getNumberInstance().format(value.getValue().getArea()));
            return areaValue;
        });
        this.wheatCol.setCellValueFactory(value -> {
            return converter.convert(value.getValue().getWheat());
        });
        this.ryeCol.setCellValueFactory(value -> {
            return converter.convert(value.getValue().getRye());
        });
        this.barleyCol.setCellValueFactory(value -> {
            return converter.convert(value.getValue().getBarley());
        });
        this.oatsCol.setCellValueFactory(value -> {
            return converter.convert(value.getValue().getOat());
        });
        this.potatoesCol.setCellValueFactory(value -> {
            return converter.convert(value.getValue().getPotatoes());
        });
        this.sugarBeetsCol.setCellValueFactory(value -> {
            return converter.convert(value.getValue().getSugarBeets());
        });
    }

    private void initStageWykres()
    {
        dataPresenterLoader = new DataPresenterLoader(dataSetModel.getDataList());
        dataPresenterLoader.loadWykresWindow();
        stageWykres = dataPresenterLoader.getStage();
        wykresController = dataPresenterLoader.getWykresController();

        dataPresenterLoader.loadStatisticWindow();
    }



    private void ustawienieParametrowEdycji()
    {
        provinceCol.setEditable(true);
        areaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        wheatCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ryeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        barleyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        oatsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        potatoesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        sugarBeetsCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private int appointIdPlant(int currentColumn)
    {
        final int columnOffset = 2;
        return currentColumn - columnOffset;
    }

    @FXML
    public void edycjaPowierzchniCommit(TableColumn.CellEditEvent<Province, String> cell) {
        String newValue = cell.getNewValue();

        newValue = newValue.replace(',','.');

        float dv = 0;
        try {
            dv = Float.parseFloat(newValue);
        } catch (Exception e) {
            return;
        }

        if(dv>50)
            dv = 50;

        if(dv<0)
            dv = 0;

        Province province = cell.getRowValue();

        province.setArea(dv);

        this.wykresController.zmienKolorWojewodztwa(province.getId());


        this.plantsTable.refresh();
    }
}
