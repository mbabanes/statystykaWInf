package gui.controllers.utill;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;

import static org.junit.Assert.*;

public class QuantityValueValidatorTest
{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenPutSomeNumberAsStringThenReturnNumberValue() throws ParseException
    {
        String numberAsString = "123";
        QuantityValueValidator quantityValueValidator = new QuantityValueValidator();

        Number actual = quantityValueValidator.validate(numberAsString);
        Number expected = new Integer(123);

        assertEquals(expected.intValue(), actual.intValue());
    }

    @Test
    public void whenPutSomeNumberWithSpaceThenReturnNumberValue() throws ParseException
    {
        String numberInFormat = "12 333";
        QuantityValueValidator quantityValueValidator = new QuantityValueValidator();

        Number actual = quantityValueValidator.validate(numberInFormat);
        int expected = 12333;

        assertEquals(expected, actual.intValue());
    }

    @Test
    public void whenPutSomeLettersThenThrowParseException() throws ParseException
    {
        String letters = "asdwqe";
        QuantityValueValidator quantityValueValidator = new QuantityValueValidator();
        expectedException.expect(ParseException.class);
        quantityValueValidator.validate(letters);
    }

    @Test
    public void whenPutNumberLessThanZeroThenThrowParseException() throws ParseException
    {
        String numberLessThanZero = "-12";
        QuantityValueValidator quantityValueValidator = new QuantityValueValidator();
        expectedException.expect(ParseException.class);
        quantityValueValidator.validate(numberLessThanZero);
    }
}