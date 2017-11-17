package gui.model;

import entity.provinces.Province;
import gui.controllers.utill.QuantityStringPropertyConverter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class StatisticsModel
{
    ObservableList<Province> provincesObservableList;

    StringProperty text = new SimpleStringProperty();

    public StatisticsModel(ObservableList<Province> provincesObservableList)
    {
        this.provincesObservableList = provincesObservableList;
    }



//    public void setProvincesObservableList(ObservableList<Province> provincesObservableList)
//    {
//        this.provincesObservableList = provincesObservableList;
//    }


    public void wstaw()
    {
        QuantityStringPropertyConverter qsc = new QuantityStringPropertyConverter();
        text.bind(qsc.convert(provincesObservableList.get(0).getBarley()));
    }

    public String getText()
    {
        return text.get();
    }

    public StringProperty textProperty()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text.set(text);
    }
}
