package descriptivesStatistics;

import entity.provinces.Province;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import loader.DataParser;
import org.junit.Before;
import org.junit.Test;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DescStatsTest
{
    private DescStats descStats;

    @Before
    public void setUp()
    {
        ArrayList<String> fakeData = new ArrayList<>();

        String line1 = "Kraina;Powierzchnia;Pszenica;Żyto;Jęczmień;Owies;Ziemniaki;Buraki Cukrowe;Powierzchnia woj";
        fakeData.add(line1);
        String line2 = "DOLNOŚLĄSKIE;16.46;15155214;684925;2523291;624157;7763762;10865265;1994674;";
        fakeData.add(line2);
        String line3 = "KUJAWSKO-POMORSKIE;15.77;9447539;1637747;3511034;346425;6142630;27028793;1797134;";
        fakeData.add(line3);
        String line4 = "LUBELSKIE;7.73;15474446;1125365;4569166;1812567;6637290;22479166;2512246;";
        fakeData.add(line4);

        DataParser dataParser = new DataParser(fakeData);

        ArrayList<Province> provinces = dataParser.parse();

        ObservableList<Province> provinceObservableList = FXCollections.observableArrayList();
        provinces.forEach(province -> {
            provinceObservableList.add(province);
        });

        descStats = new DescStats(provinceObservableList);
    }

    @Test
    public void whenInvokeCountMeanThenReturnMean()
    {
        List<Number> meansOfPlantsInCountry = descStats.means();
        assertEquals(6, meansOfPlantsInCountry.size());
        meansOfPlantsInCountry.forEach(mean ->
        {
            System.out.println(mean);
        });
    }

    @Test
    public void whenCountGeoMeanThenReturnListOfThatForEachPlant()
    {
        List<Number> geoMeansOfPlants = descStats.geometricMeans();
        assertEquals(6, geoMeansOfPlants.size());
        geoMeansOfPlants.forEach(geoMean ->
        {
            System.out.println(geoMean);
        });
    }


    @Test
    public void harmonicCountingTest()
    {
        double[] data = {2.0, 2.0, 5.0, 7.0};
        double harmonicMean = descStats.countHarmonicMean(data);
//        System.out.println(harmonicMeans);
        assertEquals(2.98d, harmonicMean, 0.01);
    }

    @Test
    public void whenHarmonicMeanThenReturnListOfThatForEachProvince()
    {
        List<Number> harmonicMeans = descStats.harmonicMeans();

        assertEquals(6, harmonicMeans.size());
    }

    @Test
    public void whenQuadraticMeanThenReturnListOfThatForEachProvince()
    {
        List<Number> quadraticMeans = descStats.quadraticMeans();

        assertEquals(6, quadraticMeans.size());
    }

    @Test
    public void whenDoMedianThenReturnListOfMediansForEachPlantForEachProvince()
    {
        List<Number> medians = descStats.medians();
        medians.forEach(median ->
        {
            System.out.println(NumberFormat.getIntegerInstance().format(median));
        });
    }

    @Test
    public void whenDoQ1AndQ3PercentilesThenReturnListOfThatForEachProvinceForEachPlant()
    {
        List<Number> q1 = descStats.q1Percentiles();
        assertEquals(6, q1.size());

        q1.forEach(q ->
        {
            System.out.println(NumberFormat.getIntegerInstance().format(q));
        });

        List<Number> q3 = descStats.q3Percentiles();

        assertEquals(6, q3.size());
    }

    @Test
    public void whenDoStandardDeviationsThenReturnListOfThatForEachPlant()
    {
        List<Number> standardDeviations = descStats.standardDeviations();
        assertEquals(6, standardDeviations.size());
    }

    @Test
    public void whenDoVariancesThenReturnListOfThatForEachPlant()
    {
        List<Number> variances = descStats.variances();
        assertEquals(6, variances.size());
    }

    @Test
    public void whenDoGapsThenReturnGapsForeachPlant()
    {
        List<Number> gaps = descStats.gaps();
        assertEquals(6, gaps.size());
    }

    @Test
    public void whenDoIqrsThenReturnIqrsForEachPlant()
    {
        List<Number> iqrs = descStats.iqrs();
        assertEquals(6, iqrs.size());
    }


    @Test
    public void whenDoAverageAbsoluteDeviationsThenReturnListOFThatForEachPlant()
    {
        List<Number> averageAbsoluteDeviations = descStats.averageAbsoluteDeviations();
        assertEquals(6, averageAbsoluteDeviations.size());
    }

    @Test
    public void whenDoQuarterDeviationsThenReturnThatForEachPlant()
    {
        List<Number> quarterDeviations = descStats.quarterDeviations();
        assertEquals(6, quarterDeviations.size());
    }
}