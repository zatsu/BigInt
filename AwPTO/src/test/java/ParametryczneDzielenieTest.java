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
public class ParametryczneDzielenieTest {

	private String _value;
	private String _value2;
	private String _result;
		
		public ParametryczneDzielenieTest(String val1, String val2, String result)
		{
			this._value = val1;
			this._value2 = val2;
			this._result = result;
		}
		
		@Parameters
		public static Collection<Object[]> none(){
			Object[][] none = new Object[][]{
				{"123456789123456789","987654321987","124999"},
				{"0","-987654321987654321","0"},
				{"-4353453453459","9876521987","-440"},
			};
			return Arrays.asList(none);
		}
		
		@Test
		public void testDzielenie() throws Exception {
					String wynik = new BigInt(_value).dzielenie(new BigInt(_value2)).show();
					assertEquals(_result, wynik);
		}
	}