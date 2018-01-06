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
        descriptiveStatisticsList.forEach(stats -> {
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

    public List<Number> q1Percentiles()
    {
        return percentiles(25);
    }

    public List<Number> q3Percentiles()
    {

        return percentiles(75);
    }


    public List<Number> standardDeviations()
    {
        List<Number> standardDeviations = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats ->
        {
            standardDeviations.add(stats.getStandardDeviation());
        });

        return standardDeviations;
    }

    public List<Number> variances()
    {
        List<Number> variances = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats ->
        {
            variances.add(stats.getVariance());
        });

        return variances;
    }

    public List<Number> intervals()
    {
        List<Number> intervals = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats ->
        {
            intervals.add(stats.getMax() - stats.getMin());
        });

        return intervals;
    }

    public List<Number> iqrs()
    {
        return countIQRs();
    }

    public List<Number> averageAbsoluteDeviations()
    {
        return countAverageAbsoluteDeviations();
    }

    public List<Number> quarterDeviations()
    {
        return countQuarterDeviations();
    }

    public List<Number> coefficientOfVariations()
    {
        return countCoefficientVariations();
    }


    public List<Number> gini()
    {
        return countGini();
    }


    public List<Number> kurtosis()
    {
        List<Number> kurtosis = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats -> {
            kurtosis.add(countKurt(stats));
        });

        return kurtosis;
    }


    public List<Number> biases()
    {
        List<Number> biases = new ArrayList<>();

        descriptiveStatisticsList.forEach(stats -> {
            biases.add(coutnBiases(stats));
        });

        return biases;

    }


    public List<Number> thirdCentralMoments()
    {
        List<Number> thirdCentralMoments = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats -> {
            thirdCentralMoments.add(countThirdCentralMoment(stats));
        });

        return thirdCentralMoments;
    }


    public List<Number> asymmetricCoefficients()
    {
        List<Number> asymmetricCoefficients = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats -> {
            asymmetricCoefficients.add(countAsymmetricCoefficient(stats));
        });

        return asymmetricCoefficients;
    }


    private List<Number> percentiles(double p)
    {
        List<Number> q = new ArrayList<>();
        descriptiveStatisticsList.forEach(stats -> {
            q.add(stats.getPercentile(p));
        });

        return q;
    }

    private List<Number> countIQRs()
    {
        List<Number> iqrs = new ArrayList<>();
        List<Number> q1 = q1Percentiles();
        List<Number> q3 = q3Percentiles();

        for (int i = 0; i < 6; i++)
        {
            iqrs.add(q3.get(i).longValue() - q1.get(i).longValue());
        }

        return iqrs;
    }

    private List<Number> countAverageAbsoluteDeviations()
    {
        List<Number> means = means();
        List<Number> averageAbsoluteDeviations = new ArrayList<>();

        for (int i = 0; i < 6; i++)
        {
            double abd = averageAbsoluteDeviations(descriptiveStatisticsList.get(i).getValues(), means.get(i).doubleValue());
            averageAbsoluteDeviations.add(abd);
        }
        return averageAbsoluteDeviations;
    }

    private List<Number> countQuarterDeviations()
    {
        List<Number> iqrs = iqrs();
        List<Number> quarterDeviations = new ArrayList<>();

        iqrs.forEach(iqr -> {
            quarterDeviations.add((0.5 * iqr.longValue()));
        });

        return quarterDeviations;
    }


    protected double countHarmonicMean(double[] data)
    {
        double sum = 0;

        for (int i = 0; i < data.length; i++)
        {
            sum += 1 / data[i];
        }

        return (data.length) / sum;
    }


    private List<Number> countCoefficientVariations()
    {
        List<Number> standardDeviations = standardDeviations();
        List<Number> means = means();

        List<Number> cov = new ArrayList<>();
        for (int i = 0; i < 6; i++)
        {
            double std = standardDeviations.get(i).doubleValue();
            double mean = means.get(i).doubleValue();
            cov.add(std / mean);
        }

        return cov;
    }


    private double averageAbsoluteDeviations(double[] data, double mean)
    {
        double sum = 0;
        for (int i = 0; i < data.length; i++)
        {
            sum += Math.abs(data[i] - mean);
        }

        return sum / data.length;
    }


    private List<Number> countGini()
    {
        List<Number> ginis = new ArrayList<>();

        descriptiveStatisticsList.forEach(descriptiveStatistics ->
        {
            ginis.add(countGiniFor(descriptiveStatistics));
        });

        return ginis;
    }

    private double countGiniFor(DescriptiveStatistics barleyStats)
    {
        double[] data = barleyStats.getValues();
        final int N = data.length;

        double sum = 0.0;
        for (int i = 0; i < data.length; i++)
        {
            sum += (2 * (i + 1) - N - 1) * data[i];
        }

        double mean = barleyStats.getMean();
        double mian = N * N * mean;

        double gini = sum / mian;
        return gini;
    }

    private double countKurt(DescriptiveStatistics data)
    {
        double[] x = data.getValues();
        double mean = data.getMean();
        double sum = 0.0;


        for (int i = 0; i < x.length; i++)
        {
            sum += Math.pow((x[i] - mean), 4);
        }

        final double N = x.length;
        double dividedByN = sum / N;

        double standardDeviation = data.getStandardDeviation();
        double dividedByStandardDeviation = dividedByN / Math.pow(standardDeviation, 4);
        return dividedByStandardDeviation - 3;
    }


    private double coutnBiases(DescriptiveStatistics stats)
    {
        double mean = stats.getMean();
        double median = stats.getPercentile(50);
        double standardDeviation = stats.getStandardDeviation();
        return 3 * (mean - median) / standardDeviation;
    }

    private double countThirdCentralMoment(DescriptiveStatistics stats)
    {
        double[] x = stats.getValues();
        double mean = stats.getMean();
        double sum = 0.0;

        for (int i = 0; i < x.length; i++)
        {
            sum += Math.pow((x[i] - mean), 3);
        }

        final int N = x.length;

        return sum / N;
    }


    private double countAsymmetricCoefficient(DescriptiveStatistics stats)
    {
        double standardDeviation = stats.getStandardDeviation();
        double m3 = countThirdCentralMoment(stats);
        return m3 / Math.pow(standardDeviation, 4);
    }

    private void prepare()
    {
        DescriptiveStatistics barleyStats = new DescriptiveStatistics();
        DescriptiveStatistics oatStats = new DescriptiveStatistics();
        DescriptiveStatistics potatoesStats = new DescriptiveStatistics();
        DescriptiveStatistics ryeStats = new DescriptiveStatistics();
        DescriptiveStatistics sugarBeetsStats = new DescriptiveStatistics();
        DescriptiveStatistics wheatStats = new DescriptiveStatistics();

        provinces.forEach(province -> {
            barleyStats.addValue(province.getBarley().getQuantity());
            oatStats.addValue(province.getOat().getQuantity());
            potatoesStats.addValue(province.getPotatoes().getQuantity());
            ryeStats.addValue(province.getRye().getQuantity());
            sugarBeetsStats.addValue(province.getSugarBeets().getQuantity());
            wheatStats.addValue(province.getWheat().getQuantity());
        });

        descriptiveStatisticsList.add(barleyStats);
        descriptiveStatisticsList.add(oatStats);
        descriptiveStatisticsList.add(potatoesStats);
        descriptiveStatisticsList.add(ryeStats);
        descriptiveStatisticsList.add(sugarBeetsStats);
        descriptiveStatisticsList.add(wheatStats);
    }
}
