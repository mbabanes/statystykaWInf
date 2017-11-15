package gui.model;

import entity.provinces.Province;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import loader.DataLoader;
import loader.DataParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataSetModel
{
    ObservableList<Province> dataList = FXCollections.observableArrayList();



    public void loadData() throws IOException
    {
        File dataFile = new File(this.getClass().getResource("/Dane.csv").getFile());

        DataLoader dataLoader = new DataLoader(dataFile);

        DataParser dataParser = new DataParser(dataLoader.getLoadedData());

        ArrayList<Province> provinces = dataParser.parse();

        provinces.forEach(province -> {
            dataList.add(province);
        });
    }

    public ObservableList<Province> getDataList()
    {
        return dataList;
    }

    public void setDataList(ObservableList<Province> dataList)
    {
        this.dataList = dataList;
    }

    public void editDataList(Province province, long newValue, int idPlant)
    {
        province.getListOfPlant().get(idPlant).setQuantity(newValue);
    }
}
