package entity.provinces;

import entity.plants.Plant;
import javafx.beans.property.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

//        pszenica;żyto;jęczmień;owies;ziemniaki;burakiCukrowe;
//        wheat, rye, barley, oat, potatoes, sugar beets;

public abstract class Province
{
//    private String name;
    private List<Plant> listOfPlant;
    private ObjectProperty<Plant> wheat = new SimpleObjectProperty<>();
    private ObjectProperty<Plant> rye = new SimpleObjectProperty<>();
    private ObjectProperty<Plant> barley = new SimpleObjectProperty<>();
    private ObjectProperty<Plant> oat = new SimpleObjectProperty<>();
    private ObjectProperty<Plant> potatoes = new SimpleObjectProperty<>();
    private ObjectProperty<Plant> sugarBeets = new SimpleObjectProperty<>();
    private Color kolorNaMapie;

    protected int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    private FloatProperty area = new SimpleFloatProperty();


    public void createListOfPlants()
    {
        listOfPlant = new ArrayList<>();
        listOfPlant.add(wheat.get());
        listOfPlant.add(rye.get());
        listOfPlant.add(barley.get());
        listOfPlant.add(oat.get());
        listOfPlant.add(potatoes.get());
        listOfPlant.add(sugarBeets.get());
    }


    private StringProperty name = new SimpleStringProperty();


    public static Province createProvince(String name)
    {
        switch(name.toUpperCase())
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
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public List<Plant> getListOfPlant()
    {
        return listOfPlant;
    }

    public void setListOfPlant(List<Plant> listOfPlant)
    {
        this.listOfPlant = listOfPlant;
    }

    public Plant getWheat()
    {
        return wheat.get();
    }

    public ObjectProperty<Plant> wheatProperty()
    {
        return wheat;
    }

    public void setWheat(Plant wheat)
    {
        this.wheat.set(wheat);
    }

    public Plant getRye()
    {
        return rye.get();
    }

    public ObjectProperty<Plant> ryeProperty()
    {
        return rye;
    }

    public void setRye(Plant rye)
    {
        this.rye.set(rye);
    }

    public Plant getBarley()
    {
        return barley.get();
    }

    public ObjectProperty<Plant> barleyProperty()
    {
        return barley;
    }

    public void setBarley(Plant barley)
    {
        this.barley.set(barley);
    }

    public Plant getOat()
    {
        return oat.get();
    }

    public ObjectProperty<Plant> oatProperty()
    {
        return oat;
    }

    public void setOat(Plant oat)
    {
        this.oat.set(oat);
    }

    public Plant getPotatoes()
    {
        return potatoes.get();
    }

    public ObjectProperty<Plant> potatoesProperty()
    {
        return potatoes;
    }

    public void setPotatoes(Plant potatoes)
    {
        this.potatoes.set(potatoes);
    }

    public Plant getSugarBeets()
    {
        return sugarBeets.get();
    }

    public ObjectProperty<Plant> sugarBeetsProperty()
    {
        return sugarBeets;
    }

    public void setSugarBeets(Plant sugarBeets)
    {
        this.sugarBeets.set(sugarBeets);
    }

    public Color getKolorNaMapie() {
        return kolorNaMapie;
    }

    public void setKolorNaMapie(Color kolorNaMapie) {
        this.kolorNaMapie = kolorNaMapie;
    }

    public float getArea()
    {
        return area.get();
    }

    public FloatProperty areaProperty()
    {
        return area;
    }

    public void setArea(float area)
    {
        this.area.set(area);
    }

    @Override
    public String toString()
    {
        return "Province{" +
                "listOfPlant=" + listOfPlant +
                ", wheat=" + wheat +
                ", rye=" + rye +
                ", barley=" + barley +
                ", oat=" + oat +
                ", potatoes=" + potatoes +
                ", sugarBeets=" + sugarBeets +
                ", kolorNaMapie=" + kolorNaMapie +
                ", id=" + id +
                ", area=" + area +
                ", name=" + name +
                '}';
    }
}
