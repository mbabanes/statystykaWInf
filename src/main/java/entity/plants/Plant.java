package entity.plants;

//        pszenica;żyto;jęczmień;owies;ziemniaki;burakiCukrowe;
//        wheat, rye, barley, oats, potatoes, sugar beets;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Plant
{
    private StringProperty name = new SimpleStringProperty();
    private LongProperty quantity = new SimpleLongProperty();

    protected String imagePath;


    public static Plant createPlant(String name)
    {
        switch(name.toLowerCase())
        {
            case "pszenica":
            {
                return new Wheat();
            }

            case "żyto":
            {
                return new Rye();
            }

            case "jęczmień":
            {
                return new Barley();
            }

            case "owies":
            {
                return new Oat();
            }

            case "ziemniaki":
            {
                return new Potatoes();
            }

            case "buraki cukrowe":
            {
                return new SugarBeets();
            }
        }
        return null;
    }

    public String getName()
    {
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public long getQuantity()
    {
        return quantity.get();
    }

    public LongProperty quantityProperty()
    {
        return quantity;
    }

    public void setQuantity(long quantity)
    {
        this.quantity.set(quantity);
    }

    @Override
    public String toString()
    {
        return "Plant{" +
                "name=" + name +
                ", quantity=" + quantity +
                '}';
    }

    public String getImagePath()
    {
        return imagePath;
    }

}
