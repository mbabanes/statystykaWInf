package loader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataLoaderTest
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    File dataFile;
    DataLoader dataLoader;

    @Before
    public void setUp() throws IOException
    {
        dataFile = new File(this.getClass().getResource("/Dane.csv").getPath());
        dataLoader = new DataLoader(dataFile);
    }

    @Test
    public void whenOpenNoCSVFileThenCastIOException() throws IOException
    {
        File wrongDataFile = new File("c:/File.txt");
        exception.expect(IOException.class);
        DataLoader dataLoader = new DataLoader(wrongDataFile);
    }


    @Test
    public void whenFilePathIsCorrectThenPathIsSet() throws IOException
    {
        Assert.assertEquals(dataFile.getPath(), dataLoader.getDataFile().getPath());
    }


    @Test
    public void whenGetLoadedDataThenReturnListOfStrings() throws IOException
    {
        ArrayList<String> actual = dataLoader.getLoadedData();
        Assert.assertTrue(actual.size() > 0 );
    }
}
