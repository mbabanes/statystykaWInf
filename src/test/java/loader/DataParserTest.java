package loader;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class DataParserTest
{
    @Test
    public void whenPutDataThenIsSet() throws Exception
    {
        File dataFile = new File(this.getClass().getResource("/Dane.csv").getFile());
        LoadData loadData = new LoadData(dataFile);

        ArrayList<String> loadedData = loadData.getLoadedData();

        DataParser dataParser = new DataParser(loadedData);
        int expected = loadedData.size();
        int actual = dataParser.getData().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenParseDataThenGetArrayListOfRecords() throws Exception
    {
        File dataFile = new File(this.getClass().getResource("/Dane.csv").getFile());
        LoadData loadData = new LoadData(dataFile);

        ArrayList<String> loadedData = loadData.getLoadedData();
        DataParser dataParser = new DataParser(loadedData);
//        ArrayList<String> actual = dataParser.parse();
    }

}
