package gui.controllers.utill;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;

import static org.junit.Assert.*;

public class ValidateQuantityValueTest
{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenPutSomeNumberAsStringThenReturnNumberValue() throws ParseException
    {
        String numberAsString = "123";
        ValidateQuantityValue validateQuantityValue = new ValidateQuantityValue(numberAsString);

        Number actual = validateQuantityValue.parse();
        Number expected = new Integer(123);

        assertEquals(expected.intValue(), actual.intValue());
    }

    @Test
    public void whenPutSomeNumberWithSpaceThenReturnNumberValue() throws ParseException
    {
        String numberInFormat = "12 333";
        ValidateQuantityValue validateQuantityValue = new ValidateQuantityValue(numberInFormat);

        Number actual = validateQuantityValue.parse();
        int expected = 12333;

        assertEquals(expected, actual.intValue());
    }

    @Test
    public void whenPutSomeLettersThenThrowParseException() throws ParseException
    {
        String letters = "asdwqe";
        ValidateQuantityValue validateQuantityValue = new ValidateQuantityValue(letters);
        expectedException.expect(ParseException.class);
        validateQuantityValue.parse();
    }

    @Test
    public void whenPutNumberLessThanZeroThenThrowParseException() throws ParseException
    {
        String numberLessThanZero = "-12";
        ValidateQuantityValue validateQuantityValue = new ValidateQuantityValue(numberLessThanZero);
        expectedException.expect(ParseException.class);
        validateQuantityValue.parse();
    }
}