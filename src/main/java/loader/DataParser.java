package loader;

import entity.plants.Plant;
import entity.provinces.Province;

import java.util.ArrayList;

public class DataParser
{
    private static final String SEPARATOR = ";";
    private static final int PROVINCE_NAME = 0;
    private static final int FIRST_LINE = 0;
    private ArrayList<String> dataFromCSV;


    public DataParser(ArrayList<String> dataFromCSV)
    {
        this.dataFromCSV = dataFromCSV;
    }


    public ArrayList<String> getDataFromCSV()
    {
        return dataFromCSV;
    }

    public ArrayList<Province> parse()
    {
        return parseData();
    }

    private ArrayList<Province> parseData()
    {
        ArrayList<Province> data = new ArrayList<>();
        for (int i = 1; i < this.dataFromCSV.size(); i++)
        {
            data.add( createDataRow(i) );
        }

        return data;
    }

    private Province createDataRow(int i)
    {
        String[] dataLine = takeLine(i);
        Province province = createProvince(dataLine[PROVINCE_NAME]);

        ArrayList<Plant> listOfPlants = createListOfPlants(dataLine);

        province.setListOfPlant(listOfPlants);
        return province;
    }



    private Province createProvince(String name)
    {
        Province province = Province.createProvince(name);
        province.setName(name);
        return province;
    }

    private ArrayList<Plant> createListOfPlants(String[] dataLine)
    {
        ArrayList<Plant> listOfPlants = new ArrayList<>();

        String[] plantNames = takeLine(FIRST_LINE);
        for (int j = 1; j < dataLine.length; j++)
        {
            Plant plant = createPlant(plantNames[j], dataLine[j]);
            listOfPlants.add(plant);
        }
        return listOfPlants;
    }

    private String[] takeLine(int i)
    {
        return this.dataFromCSV.get(i).split(SEPARATOR);
    }

    private Plant createPlant(String plantName, String value)
    {
        Plant plant = Plant.createPlant(plantName);
        plant.setName(plantName);
        plant.setQuantity(Long.parseLong(value));
        return plant;
    }
}
