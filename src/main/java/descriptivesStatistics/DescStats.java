package descriptivesStatistics;

import entity.provinces.Province;
import javafx.collections.ObservableList;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
import java.util.List;

public class DescStats
{
    private ObservableList<Province> provinces;
    private List<DescriptiveStatistics> descriptiveStatisticsList = new ArrayList<>();

    public DescStats(ObservableList<Province> provinces)
    {
        this.provinces = provinces;
        prepare();
    }



    public List<Number> means()
    {
        List<Number> meansOfPlants = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats ->{
            meansOfPlants.add(stats.getMean());
        });

        return meansOfPlants;
    }

    public List<Number> geometricMeans()
    {
        List<Number> geoMeans = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats ->
        {
            geoMeans.add(stats.getGeometricMean());
        });

        return geoMeans;
    }

    public List<Number> harmonicMeans()
    {
        List<Number> harmonicMeans = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats ->
        {
            harmonicMeans.add(countHarmonicMean(stats.getValues()));
        });

        return harmonicMeans;
    }


    public List<Number> quadraticMeans()
    {
        List<Number> quadraticMeans = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats ->
        {
            quadraticMeans.add(stats.getQuadraticMean());
        });

        return quadraticMeans;
    }

    public List<Number> medians()
    {
        List<Number> medians = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats ->
        {
            medians.add(stats.getPercentile(50));
        });

        return medians;
    }

    protected double countHarmonicMean(double[] data)
    {
        double sum = 0;

        for (int i = 0; i < data.length; i++)
        {
            sum += 1/data[i];
        }

        return (data.length)/sum;
    }

    private void prepare()
    {
        DescriptiveStatistics wheatStats = new DescriptiveStatistics();
        DescriptiveStatistics barleyStats = new DescriptiveStatistics();
        DescriptiveStatistics oatStats = new DescriptiveStatistics();
        DescriptiveStatistics potatoesStats = new DescriptiveStatistics();
        DescriptiveStatistics ryeStats = new DescriptiveStatistics();
        DescriptiveStatistics sugarBeetsStats = new DescriptiveStatistics();

        provinces.forEach(province -> {
            wheatStats.addValue(province.getWheat().getQuantity());
            barleyStats.addValue(province.getBarley().getQuantity());
            oatStats.addValue(province.getOat().getQuantity());
            potatoesStats.addValue(province.getPotatoes().getQuantity());
            ryeStats.addValue(province.getRye().getQuantity());
            sugarBeetsStats.addValue(province.getSugarBeets().getQuantity());
        });

        descriptiveStatisticsList.add(wheatStats);
        descriptiveStatisticsList.add(barleyStats);
        descriptiveStatisticsList.add(oatStats);
        descriptiveStatisticsList.add(potatoesStats);
        descriptiveStatisticsList.add(ryeStats);
        descriptiveStatisticsList.add(sugarBeetsStats);
    }
}
