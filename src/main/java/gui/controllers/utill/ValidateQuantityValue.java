package gui.controllers.utill;

import java.text.NumberFormat;
import java.text.ParseException;

public class ValidateQuantityValue
{
    private final static String SPACE = " ";
    private final static String NON_BREAKING_SPACE = "\u00A0";

    private String value;
    private Number valueAsNumber;

    public ValidateQuantityValue(String value)
    {
        this.value = value;
        changeSpaceToNonBreakingSpace();
    }



    public Number parse() throws ParseException
    {
        return parseValue();
    }


    private void changeSpaceToNonBreakingSpace()
    {
        value = value.replaceAll(SPACE, NON_BREAKING_SPACE);
    }

    private Number parseValue() throws ParseException
    {
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        valueAsNumber = formatter.parse(value);

        checkNumberIsMoreThanZero();
        return valueAsNumber;

    }

    private void checkNumberIsMoreThanZero() throws ParseException
    {
        if (valueAsNumber.longValue() < 0)
            throw new ParseException("Liczba musi być wieksza od 0", 1);
    }
}
