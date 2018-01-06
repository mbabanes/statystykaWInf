package gui.controllers;

import gui.model.CorrelationModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.text.NumberFormat;


public class CorrelationController
{
    @FXML
    private VBox plantsChooserVBox;

    @FXML
    private Button countButton;

    @FXML
    private ComboBox<String> plant1ComboBox;

    @FXML
    private ComboBox<String> plant2ComboBox;

    @FXML
    private Label resultLabel;


    private CorrelationModel correlationModel;

    public CorrelationController(CorrelationModel correlationModel)
    {
        this.correlationModel = correlationModel;
    }

    public void initialize()
    {
        preparePlantsComboBox();
        prepareCountButton();
    }


    @FXML
    public void choosePlant1(ActionEvent event)
    {
        try
        {
            int idPlantOfPlant1ComboBox = this.plant1ComboBox.getSelectionModel().getSelectedIndex();
            this.plant2ComboBox.setItems(correlationModel.getPlantsLabelsForCorrelation(idPlantOfPlant1ComboBox));
        } catch (ArrayIndexOutOfBoundsException e)
        {}
    }

    @FXML
    public void doCorrelation()
    {
        double r = correlationModel.makeCorrelation(plant1ComboBox.getSelectionModel().getSelectedIndex(), plant2ComboBox.getSelectionModel().getSelectedIndex());

        String message = correlationModel.interpret(r, plant1ComboBox.getValue(), plant2ComboBox.getValue());


        resultLabel.setText(message);

    }



    private void preparePlantsComboBox()
    {
        this.plant1ComboBox.setItems(correlationModel.getPlantsLabels());
        this.plant2ComboBox.setDisable(true);
        this.plant2ComboBox.disableProperty().bind(plant1ComboBox.valueProperty().isNull());
    }

    private void prepareCountButton()
    {
        countButton.disableProperty().bind(plant1ComboBox.valueProperty().isNull().or(plant2ComboBox.valueProperty().isNull()));
    }

}
