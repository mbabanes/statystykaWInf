package entity.plants;


import org.junit.Assert;
import org.junit.Test;

public class PlantTest
{
    @Test
    public void whenCreateAndInstanceOfPlantThenReturnCorrectClass()
    {
        Plant wheat = Plant.createPlant("pszenica");
        Assert.assertTrue( (wheat instanceof Wheat)  );

        Plant rye = Plant.createPlant("żyto");
        Assert.assertTrue( (rye instanceof Rye)  );

        Plant oat = Plant.createPlant("owies");
        Assert.assertTrue( (oat instanceof Oat)  );

        Plant barley = Plant.createPlant("jęczmień");
        Assert.assertTrue( (barley instanceof  Barley) );

        Plant potatoes = Plant.createPlant("ziemniaki");
        Assert.assertTrue( (potatoes instanceof Potatoes)  );

        Plant sugarBeets = Plant.createPlant("Buraki Cukrowe");
        Assert.assertTrue( (sugarBeets instanceof SugarBeets)  );
    }
}
