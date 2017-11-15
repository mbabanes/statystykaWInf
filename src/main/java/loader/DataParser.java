package loader;

import entity.plants.Plant;
import entity.provinces.Province;

import java.util.ArrayList;
import java.util.List;

public class DataParser
{
    private static final String SEPARATOR = ";";
    private static final int PROVINCE_NAME = 0;
    private static final int FIRST_LINE = 0;
    private static final int WHEAT_COL = 2;
    private static final int RYE_COL = 3;
    private static final int BARLEY_COL = 4;
    private static final int OAT_COL = 5;
    private static final int POTATOES_COL = 6;
    private static final int SUGAR_BEETS_COL = 7;
    private static final int PROVINCE_AREA = 1;

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
        return  createProvince(dataLine, i);
    }



    private Province createProvince(String[] dataLine, int i)
    {
        Province province = Province.createProvince(dataLine[PROVINCE_NAME]);
        province.setName(dataLine[PROVINCE_NAME]);
        province.setArea(Float.parseFloat(dataLine[PROVINCE_AREA]));
        province.setId(i - 1);
        String[] plantNames = takeLine(FIRST_LINE);

        province.setWheat(createPlant(plantNames[WHEAT_COL], dataLine[WHEAT_COL]));
        province.setRye(createPlant(plantNames[RYE_COL], dataLine[RYE_COL]));
        province.setBarley(createPlant(plantNames[BARLEY_COL], dataLine[BARLEY_COL]));
        province.setOat(createPlant(plantNames[OAT_COL], dataLine[OAT_COL]));
        province.setPotatoes(createPlant(plantNames[POTATOES_COL], dataLine[POTATOES_COL]));
        province.setSugarBeets(createPlant(plantNames[SUGAR_BEETS_COL], dataLine[SUGAR_BEETS_COL]));
        province.createListOfPlants();

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
