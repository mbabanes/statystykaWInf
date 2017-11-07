package loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DataLoader
{
    private File dataFile;
    private ArrayList<String> data;

    public DataLoader(File dataFile) throws IOException
    {
        if ( isNoCSVFile(dataFile) )
        {
            throw new IOException("Zły plik");
        }
        else
        {
            this.dataFile = dataFile;
        }
    }


    public ArrayList<String> getLoadedData() throws IOException
    {
        return readDataFromFile();
    }


    private ArrayList<String> readDataFromFile() throws IOException
    {
        data = new ArrayList<>();

        readData();

        return data;
    }


    private boolean isNoCSVFile(File dataFile)
    {
        Pattern patternForCSVFilePath = Pattern.compile(".*\\.csv");
        return !patternForCSVFilePath.matcher(dataFile.getPath()).matches();
    }

    private void readData() throws IOException
    {
        try(Scanner csvFile = new Scanner(new FileInputStream(dataFile)))
        {
            while(csvFile.hasNext())
            {
                data.add(csvFile.nextLine());
            }
        }
    }

    protected File getDataFile()
    {
        return dataFile;
    }
}
