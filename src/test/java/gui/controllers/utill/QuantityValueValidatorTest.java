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
        QuantityValueValidator quantityValueValidator = new QuantityValueValidator(numberAsString);

        Number actual = quantityValueValidator.validate();
        Number expected = new Integer(123);

        assertEquals(expected.intValue(), actual.intValue());
    }

    @Test
    public void whenPutSomeNumberWithSpaceThenReturnNumberValue() throws ParseException
    {
        String numberInFormat = "12 333";
        QuantityValueValidator quantityValueValidator = new QuantityValueValidator(numberInFormat);

        Number actual = quantityValueValidator.validate();
        int expected = 12333;

        assertEquals(expected, actual.intValue());
    }

    @Test
    public void whenPutSomeLettersThenThrowParseException() throws ParseException
    {
        String letters = "asdwqe";
        QuantityValueValidator quantityValueValidator = new QuantityValueValidator(letters);
        expectedException.expect(ParseException.class);
        quantityValueValidator.validate();
    }

    @Test
    public void whenPutNumberLessThanZeroThenThrowParseException() throws ParseException
    {
        String numberLessThanZero = "-12";
        QuantityValueValidator quantityValueValidator = new QuantityValueValidator(numberLessThanZero);
        expectedException.expect(ParseException.class);
        quantityValueValidator.validate();
    }
}