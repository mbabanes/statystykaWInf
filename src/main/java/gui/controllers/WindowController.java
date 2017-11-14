package gui.controllers;

import entity.provinces.Province;
import gui.controllers.utill.QuantityInFormat;
import gui.model.DataSetModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

public class WindowController
{
    private static final int WHEAT_COL = 2;
    private static final int RYE_COL = 3;
    private static final int BARLEY_COL = 4;
    private static final int OAT_COL = 5;
    private static final int POTATOES_COL = 6;
    private static final int SUGAR_BEETS_COL = 7;


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
        try
        {
            pane = loader.load();
        }
        catch (IOException e)
        {
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


    public void editPlantValuesCommit(TableColumn.CellEditEvent<Province, String> provinceNumberCellEditEvent)
    {
        try
        {
            final String SPACE = " ";
            final String NON_BREAKING_SPACE = "\u00A0";

            String newValueAsString = provinceNumberCellEditEvent.getNewValue().replaceAll(SPACE, NON_BREAKING_SPACE);
            Number newValue = parsePuttedText(newValueAsString);

            int currentColumn = provinceNumberCellEditEvent.getTablePosition().getColumn();
            int currentRow = provinceNumberCellEditEvent.getTablePosition().getRow();

            putNewValue(newValue, currentColumn, currentRow);
            this.plantsTable.refresh();
        }
        catch (ParseException e)
        {
            System.out.println("Blad Danych " + e.getMessage());
        }
    }

    private Number parsePuttedText(String newValueAsString) throws ParseException
    {
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        Number newValue = formatter.parse(newValueAsString);

        if (newValue.longValue() < 0)
            throw new ParseException("Liczba musi być wieksza od 0", 1);
        return newValue;
    }

    private void putNewValue(Number newValue, int currentColumn, int currentRow)
    {
        switch (currentColumn)
        {
            case WHEAT_COL:
            {
                this.plantsTable.getItems().get(currentRow).getWheat().setQuantity(newValue.longValue());
                break;
            }

            case RYE_COL:
            {
                this.plantsTable.getItems().get(currentRow).getRye().setQuantity(newValue.longValue());
                break;
            }

            case BARLEY_COL:
            {
                this.plantsTable.getItems().get(currentRow).getBarley().setQuantity(newValue.longValue());
                break;
            }

            case OAT_COL:
            {
                this.plantsTable.getItems().get(currentRow).getOat().setQuantity(newValue.longValue());
                break;
            }

            case POTATOES_COL:
            {
                this.plantsTable.getItems().get(currentRow).getPotatoes().setQuantity(newValue.longValue());
                break;
            }

            case SUGAR_BEETS_COL:
            {
                this.plantsTable.getItems().get(currentRow).getSugarBeets().setQuantity(newValue.longValue());
                break;
            }
        }
    }

}
