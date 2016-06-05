package test.java;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.java.BigInt;

@RunWith(Parameterized.class)
public class ValidateTest {

	private String _value;
	
	public ValidateTest(String value)
	{
		this._value = value;
	}
	
	@Parameters
	public static Collection<Object[]> none(){
		Object[][] none = new Object[][]{
			{"asdasdas"},
			{"3123dsadas3213"},
			{"-213dasd"},
			{"-3-3-3"},
			{"9-"},
		};
		return Arrays.asList(none);
	}
	
	@Test(expected=Exception.class)
	public void testException() throws Exception {
				 BigInt nowy = new BigInt(_value);
	}
}
