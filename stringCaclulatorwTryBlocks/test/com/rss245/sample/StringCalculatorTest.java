package com.rss245.sample;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.rss245.sample.StringCalculator;
import com.rss245.sample.StringCalculator.OPERATION;
import com.rss245.sample.exceptions.NegativesNotAllowedAppException;

public class StringCalculatorTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// .......... Siemen's Original JUnit Tests but  added

	@Test
	public void addNumbers()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(5, calculator.add("2,3"));
	}

	@Test
	public void subtractNumbers()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(4, calculator.subtract("10,2,4"));
	}

	@Test
	public void multiplyNumbers()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(6, calculator.multiply("2,3"));
	}

	// .............................................................
	// Note the rs prefix is used to identify tests I (rs) created
	// Add tests

	@Test
	public void rs_addNumberswDelim()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(5, calculator.add("//;\n3;2"));
	}

	@Test
	public void rs_addNumbersNull()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(0, calculator.add(""));
	}

	@Test
	public void rs_addNumbers()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(5, calculator.add("3,2"));
	}

	@Test
	public void rs_addNumberswNewLine()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(5, calculator.add("3\n2"));
	}

	@Test
	public void rs_addNumberswNewLine_Comma()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(12, calculator.add("6\n2,4"));
	}
	
	@Test
	public void rs_addNumCustomDelim7()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(28, calculator.add("//+\n1+2+3+4+5+6+7"));
	}
	@Test
	public void rs_addNumCustomDelim7dash()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(0, calculator.add("//-\n-1-2-3-4-5-6-7"));
	}

	@Test
	public void rs_addNumCustomDelim7dash2negative()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(0, calculator.add("//-\n--1-2--3--4-5-6--7"));
	}
	
	// Subtract tests
	public void rs_subtractNumberswDelim()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(1, calculator.subtract("//;\n3;2"));
	}

	@Test
	public void rs_subtractNumbersNull()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(0, calculator.subtract(""));
	}

	@Test
	public void rs_subtractNumbers()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(1, calculator.subtract("3,2"));
	}

	@Test
	public void rs_subtractNumberswNewLine_()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(1, calculator.subtract("3\n2"));
	}

	@Test
	public void rs_subtractNumberswNewLine_Comma()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(9, calculator.subtract("14\n2,3"));
	}
	
	@Test
	public void rs_subtractNumCustomDelimPlus7()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(-26, calculator.subtract("//+\n1+2+3+4+5+6+7"));
	}
	@Test
	public void rs_subtractNumCustomDelim7tilda()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(0, calculator.subtract("//~\n-1~2~3~4~5~6~7"));
	}
	
// multiply tests 
	public void rs_multiplyNumberswDelim()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(6, calculator.multiply("//;\n3;2"));
	}

	@Test
	public void rs_multiplyNumbersNull()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(0, calculator.multiply(""));
	}

	@Test
	public void rs_multiplyNumbers()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(6, calculator.multiply("3,2"));
	}

	@Test
	public void rs_multiplyNumberswNewLine_()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(6, calculator.multiply("3\n2"));
	}

	@Test
	public void rs_multiplyNumberswNewLine_Comma()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(84, calculator.multiply("14\n2,3"));
	}
	
	@Test
	public void rs_multiplyNumberswNegatives()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(0, calculator.multiply("14\n-2,3,-7"));
	}

	@Test
	public void rs_multiplyNumCustomDelimAsteric()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(5040, calculator.multiply("//*\n1*2*3*4*5*6*7"));
	}
	@Test
	public void rs_multiplyNumCustomDelim7Exclaimation()  {
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(0, calculator.multiply("//!\n-1!2!3!4!5!6!7"));
	}



// Special utility method	
	@Test
	public void rs_NegativesNotAllowedException() throws NegativesNotAllowedAppException {
		thrown.expect(NegativesNotAllowedAppException.class);
		thrown.expectMessage("- Negative values flagged: [-2, -7, -8]");
		StringCalculator calculator = new StringCalculator();
		Assert.assertEquals(null, calculator.processParameters(OPERATION.ADD,
				"-2,9,-7,-8", ",\n"));
	}

}
