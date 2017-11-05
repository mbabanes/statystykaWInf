package loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LoadData
{
    private File dataFile;

    public LoadData(File dataFile) throws IOException
    {
        Pattern patternForCSVFilePath = Pattern.compile(".*\\.csv");
        if (!patternForCSVFilePath.matcher(dataFile.getPath()).matches())
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
        ArrayList<String> lines = new ArrayList<>(18);

        try(Scanner csvFile = new Scanner(new FileInputStream(dataFile)))
        {
            while(csvFile.hasNext())
            {
                lines.add(csvFile.nextLine());
            }
        }

        return lines;
    }


    protected File getDataFile()
    {
        return dataFile;
    }
}
