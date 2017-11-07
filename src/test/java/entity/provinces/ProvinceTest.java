package entity.provinces;

import entity.plants.Plant;
import org.junit.Assert;
import org.junit.Test;

public class ProvinceTest
{
    @Test
    public void whenCreateProvinceThenReturnCorrectInstanceOfClass()
    {
        Province slaskie = Province.createProvince("ŚLĄSKIE");
        Assert.assertTrue( (slaskie instanceof Slaskie));

        Province dolnoSlaskie = Province.createProvince("DOLNOŚLĄSKIE");
        Assert.assertTrue( (dolnoSlaskie instanceof Dolnoslaskie));

        Province kujawskoPomorskie = Province.createProvince("KUJAWSKO-POMORSKIE");
        Assert.assertTrue( (kujawskoPomorskie instanceof KujawskoPomorskie));

        Province warminskoMazurskie = Province.createProvince("WARMIŃSKO-MAZURSKIE");
        Assert.assertTrue( (warminskoMazurskie instanceof WarminskoMazurskie));

        Province malopolskie = Province.createProvince("MAŁOPOLSKIE");
        Assert.assertTrue( (malopolskie instanceof Malopolskie));
    }

    @Test
    public void whenAddPlantToProvincesThenIsSet()
    {
        Province slaskie = Province.createProvince("ŚLĄSKIE");

        Plant wheat = Plant.createPlant("WHEAT");

        slaskie.setWheat(wheat);

        Assert.assertEquals(wheat, slaskie.getWheat());

        Plant oat = Plant.createPlant("Owies");
        slaskie.setOat(oat);
        Assert.assertEquals(oat, slaskie.getOat());
    }
}
