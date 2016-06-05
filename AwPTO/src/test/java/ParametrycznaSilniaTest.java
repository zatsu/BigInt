package test.java;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.java.BigInt;

@RunWith(Parameterized.class)
public class ParametrycznaSilniaTest {

	private String _value;
	private String _result;
		
		public ParametrycznaSilniaTest(String val1, String result)
		{
			this._value = val1;
			this._result = result;
		}
		
		@Parameters
		public static Collection<Object[]> none(){
			Object[][] none = new Object[][]{
				{"0","1"},
				{"1","1"},
				{"10","3628800"},
				{"20","2432902008176640000"},
			};
			return Arrays.asList(none);
		}
		
		@Test
		public void testSilnia() throws Exception {
					String wynik = new BigInt(_value).silnia().numer1;
					assertEquals(_result, wynik);
		}
	}