package gui.controllers.utill;

import entity.plants.Plant;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.NumberFormat;

public class QuantityStringPropertyConverter
{
    private Plant plant;

    public QuantityStringPropertyConverter(){};


    private StringProperty format()
    {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        StringProperty valueInFormat = new SimpleStringProperty();
        valueInFormat.setValue(numberFormat.format(plant.getQuantity() ) );

        return valueInFormat;
    }


    public StringProperty convertPlantToStringProperty(Plant value)
    {
        this.plant = value;

        return format();
    }
}
