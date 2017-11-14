package gui.controllers.utill;

import entity.plants.Plant;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.NumberFormat;

public class QuantityInFormat
{
    Plant plant;

    public QuantityInFormat(Plant plant)
    {
        this.plant = plant;
    }


    private StringProperty format()
    {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        StringProperty valueInFormat = new SimpleStringProperty();
        valueInFormat.setValue(numberFormat.format(plant.getQuantity() ) );

        return valueInFormat;
    }

    public StringProperty parse()
    {
        return format();
    }
}
