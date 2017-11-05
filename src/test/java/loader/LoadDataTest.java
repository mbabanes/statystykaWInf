package loader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoadDataTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    File dataFile;
    LoadData loadData;

    @Before
    public void setUp() throws IOException
    {
        dataFile = new File(this.getClass().getResource("/Dane.csv").getPath());
        loadData = new LoadData(dataFile);
    }

    @Test
    public void whenOpenNoCSVFileThenCastIOException() throws IOException
    {
        File wrongDataFile = new File("c:/File.txt");
        exception.expect(IOException.class);
        LoadData loadData = new LoadData(wrongDataFile);
    }


    @Test
    public void whenFilePathIsCorrectThenPathIsSet() throws IOException
    {
        Assert.assertEquals(dataFile.getPath(), loadData.getDataFile().getPath());
    }


    @Test
    public void whenGetLoadedDataThenReturnListOfStrings() throws IOException
    {
        ArrayList<String> actual = loadData.getLoadedData();
        Assert.assertTrue(actual.size() > 0 );
    }
}
