package entity.provinces;

import entity.plants.Plant;

import java.util.List;

public abstract class Province
{
    private String name;
    private List<Plant> listOfPlant;


    public static Province createProvince(String name)
    {
        switch(name)
        {
            case "DOLNOŚLĄSKIE":
            {
                return new Dolnoslaskie();
            }

            case "KUJAWSKO-POMORSKIE":
            {
                return new KujawskoPomorskie();
            }

            case "LUBELSKIE":
            {
                return new Lubelskie();
            }

            case "LUBUSKIE":
            {
                return new Lubuskie();
            }

            case "ŁÓDZKIE":
            {
                return new Lodzkie();
            }

            case "MAŁOPOLSKIE":
            {
                return new Malopolskie();
            }

            case "MAZOWIECKIE":
            {
                return new Mazwowieckie();
            }

            case "OPOLSKIE":
            {
                return new Opolskie();
            }

            case "PODKARPACKIE":
            {
                return new Podkarpackie();
            }

            case "PODLASKIE":
            {
                return new Podlaskie();
            }

            case "POMORSKIE":
            {
                return new Pomorskie();
            }

            case "ŚLĄSKIE":
            {
                return new Slaskie();
            }

            case "ŚWIĘTOKRZYSKIE":
            {
                return new Swietokrzyskie();
            }

            case "WARMIŃSKO-MAZURSKIE":
            {
                return new WarminskoMazurskie();
            }

            case "WIELKOPOLSKIE":
            {
                return new Wielkopolskie();
            }

            case "ZACHODNIOPOMORSKIE":
            {
                return new Zachodniopomorskie();
            }
        }
        return null;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Plant> getListOfPlant()
    {
        return listOfPlant;
    }

    public void setListOfPlant(List<Plant> listOfPlant)
    {
        this.listOfPlant = listOfPlant;
    }
}
