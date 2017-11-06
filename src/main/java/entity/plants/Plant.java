package entity.plants;

//        pszenica;żyto;jęczmień;owies;ziemniaki;burakiCukrowe;
//        wheat, rye, barley, oats, potatoes, sugar beets;

public abstract class Plant
{
    private String name;
    private long quantity;


    public static Plant createPlant(String name)
    {
        switch(name.toLowerCase())
        {
            case "pszenica":
            {
                return new Wheat();
            }

            case "żyto":
            {
                return new Rye();
            }

            case "jęczmień":
            {
                return new Barley();
            }

            case "owies":
            {
                return new Oat();
            }

            case "ziemniaki":
            {
                return new Potatoes();
            }

            case "buraki cukrowe":
            {
                return new SugarBeets();
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

    public long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(long quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "Plant{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
