package gui.controllers.utill;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Comparator;

public class PlantsValuesComparator implements Comparator<String>
{
    @Override
    public int compare(String x, String y)
    {
        Number n = null;
        Number n2 = null;
        try
        {
            n = NumberFormat.getNumberInstance().parse(x);
            n2 = NumberFormat.getNumberInstance().parse(y);
            return Long.compare(n.longValue(), n2.longValue());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
