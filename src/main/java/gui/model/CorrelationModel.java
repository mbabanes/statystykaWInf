package gui.model;

import entity.plants.Plant;
import entity.provinces.Province;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.MessageFormat;

public class CorrelationModel
{
    private final String PEARSONS_COEFFICIENT = "Współczynnik korelacji Pearsona: {0}.";

    private ObservableList<Province> provinces;

    public CorrelationModel(ObservableList<Province> provinces)
    {
        this.provinces = provinces;
    }

    public ObservableList<Province> getProvinces()
    {
        return provinces;
    }

    public ObservableList<String> getPlantsLabels()
    {
        ObservableList<String> plants = FXCollections.observableArrayList();
        for (Plant plant : provinces.get(0).getListOfPlant())
        {
            plants.add(plant.getName());
        }

        return plants;
    }

    public ObservableList<String> getPlantsLabelsForCorrelation(int idPlant)
    {
        ObservableList<String> plants = getPlantsLabels();
        plants.remove(idPlant);
        return plants;
    }


    public double makeCorrelation(int idPlant1, int idPlant2)
    {
        double sumPlants1 = 0.0;
        double sumPlants2 = 0.0;

        double sumPlants1To2 = 0.0;
        double sumPlants2To2 = 0.0;

        double sumPlants1ByPlants2 = 0.0;

        final int N = provinces.size();

        for (int i = 0; i < N; i++)
        {
            double plant1 = provinces.get(i).getListOfPlant().get(idPlant1).getQuantity();
            double plant2 = provinces.get(i).getListOfPlant().get(idPlant2).getQuantity();

            sumPlants1 += plant1;
            sumPlants2 += plant2;

            sumPlants1ByPlants2 += plant1 * plant2;

            sumPlants1To2 += Math.pow(plant1, 2);
            sumPlants2To2 += Math.pow(plant2, 2);
        }

        double r = (N * sumPlants1ByPlants2 - sumPlants1 * sumPlants2) /
                (Math.sqrt((N * sumPlants1To2 - Math.pow(sumPlants1, 2)) *
                        (N * sumPlants2To2 - Math.pow(sumPlants2, 2))));
        return r;
    }

    public String interpret(double r, String plant1Label, String plant2Label)
    {
        if (r < 0)
        {
            return MessageFormat.format(PEARSONS_COEFFICIENT + "\nKorelacja ujemna. Jeśli {1} rośnie, wówczas {2} maleje.", r, plant1Label, plant2Label);
        } else if (r > 0)
        {
            return MessageFormat.format(PEARSONS_COEFFICIENT + "\nKorelacja dodatnia. Jeśli {1} rośnie, wówczas {2} rośnie.", r, plant1Label, plant2Label);
        } else
        {
            return MessageFormat.format(PEARSONS_COEFFICIENT + "\nBrak korelacji. Brak związku między {1} a {2}.", r, plant1Label, plant2Label);
        }
    }
}
