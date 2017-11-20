package gui.model;

import descriptivesStatistics.DescStats;
import entity.provinces.Province;
import gui.controllers.utill.QuantityStringPropertyConverter;
import gui.model.entityFx.Statistic;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.NumberFormat;
import java.util.List;

public class StatisticsModel
{
    private ObservableList<Province> provincesObservableList;
    private ObservableList<Statistic> descriptiveStatisticObservableList = FXCollections.observableArrayList();


    public StatisticsModel(ObservableList<Province> provincesObservableList)
    {
        this.provincesObservableList = provincesObservableList;
    }


    public void refresh()
    {
        descriptiveStatisticObservableList.clear();
        countStatistics();
    }

    public void countStatistics()
    {
        DescStats descStats = new DescStats(provincesObservableList);
        putStats("Średnia arytmetyczna", descStats.means());
        putStats("Średnia geometryczna", descStats.geometricMeans());
        putStats("Średnia harmoniczna", descStats.harmonicMeans());
        putStats("Średnia kwadratowa", descStats.quadraticMeans());
        putStats("Mediana", descStats.medians());
        putStats("Kwantyl Q1", descStats.q1Percentiles());
        putStats("Kwantyl Q3", descStats.q3Percentiles());
        putStats("Odchylenie standardowe", descStats.standardDeviations());
        putStats("Wariancja", descStats.variances());
        putStats("Rozstęp", descStats.gaps());
        putStats("Rozstęp ćwiartkowy (IQR)", descStats.iqrs());
        putStats("Średnie odchylenie bezwzględne", descStats.averageAbsoluteDeviations());
        putStats("Odchylenie ćwiartkowe", descStats.quarterDeviations());
        putStats("Współczynnik zmienności", descStats.coefficientOfVariations());
    }


    public ObservableList<Statistic> getDescriptiveStatisticObservableList()
    {
        return descriptiveStatisticObservableList;
    }

    private void putStats(String nameOfStat, List<Number> values)
    {
        Statistic stats = new Statistic();
        stats.setNameOfStat(nameOfStat);
        stats.setBarleyValue(formattedValue(values.get(0).doubleValue()));
        stats.setOatValue(formattedValue(values.get(1).doubleValue()));
        stats.setPotatoesValue(formattedValue(values.get(2).doubleValue()));
        stats.setRyeValue(formattedValue(values.get(3).doubleValue()));
        stats.setSugarBeetsValue(formattedValue(values.get(4).doubleValue()));
        stats.setWheatValue(formattedValue(values.get(5).doubleValue()));

        descriptiveStatisticObservableList.add(stats);
    }

    private String formattedValue(double value)
    {
        return NumberFormat.getNumberInstance().format(value);
    }
}
