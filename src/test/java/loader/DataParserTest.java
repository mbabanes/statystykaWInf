package loader;

import entity.provinces.Province;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class DataParserTest
{
    private DataLoader dataLoader;
    private File dataFile;
    private ArrayList<String> loadedData;

    @Before
    public void setUp() throws Exception
    {
        dataFile = new File(this.getClass().getResource("/Dane.csv").getFile());
        dataLoader = new DataLoader(dataFile);
        loadedData = dataLoader.getLoadedData();
    }

    @Test
    public void whenPutDataThenIsSet() throws Exception
    {
        DataParser dataParser = new DataParser(loadedData);
        int expected = loadedData.size();
        int actual = dataParser.getDataFromCSV().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenParseDataThenGetArrayListOfRecords() throws Exception
    {
        DataParser dataParser = new DataParser(loadedData);
        ArrayList<Province> list = dataParser.parse();

        list.forEach((province -> {
            System.out.println(province);
        }));

        int actual = list.size();
        int expected = 16;

        Assert.assertEquals(expected, actual);
    }

}
