package gui.model.entityFx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Statistic
{
    private StringProperty nameOfStat = new SimpleStringProperty();
    private StringProperty barleyValue = new SimpleStringProperty();
    private StringProperty oatValue = new SimpleStringProperty();
    private StringProperty potatoesValue = new SimpleStringProperty();
    private StringProperty ryeValue = new SimpleStringProperty();
    private StringProperty sugarBeetsValue = new SimpleStringProperty();
    private StringProperty wheatValue = new SimpleStringProperty();


    public String getNameOfStat()
    {
        return nameOfStat.get();
    }

    public StringProperty nameOfStatProperty()
    {
        return nameOfStat;
    }

    public void setNameOfStat(String nameOfStat)
    {
        this.nameOfStat.set(nameOfStat);
    }

    public String getBarleyValue()
    {
        return barleyValue.get();
    }

    public StringProperty barleyValueProperty()
    {
        return barleyValue;
    }

    public void setBarleyValue(String barleyValue)
    {
        this.barleyValue.set(barleyValue);
    }

    public String getOatValue()
    {
        return oatValue.get();
    }

    public StringProperty oatValueProperty()
    {
        return oatValue;
    }

    public void setOatValue(String oatValue)
    {
        this.oatValue.set(oatValue);
    }

    public String getPotatoesValue()
    {
        return potatoesValue.get();
    }

    public StringProperty potatoesValueProperty()
    {
        return potatoesValue;
    }

    public void setPotatoesValue(String potatoesValue)
    {
        this.potatoesValue.set(potatoesValue);
    }

    public String getRyeValue()
    {
        return ryeValue.get();
    }

    public StringProperty ryeValueProperty()
    {
        return ryeValue;
    }

    public void setRyeValue(String ryeValue)
    {
        this.ryeValue.set(ryeValue);
    }

    public String getSugarBeetsValue()
    {
        return sugarBeetsValue.get();
    }

    public StringProperty sugarBeetsValueProperty()
    {
        return sugarBeetsValue;
    }

    public void setSugarBeetsValue(String sugarBeetsValue)
    {
        this.sugarBeetsValue.set(sugarBeetsValue);
    }

    public String getWheatValue()
    {
        return wheatValue.get();
    }

    public StringProperty wheatValueProperty()
    {
        return wheatValue;
    }

    public void setWheatValue(String wheatValue)
    {
        this.wheatValue.set(wheatValue);
    }
}
