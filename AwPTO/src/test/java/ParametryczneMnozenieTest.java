package test.java;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.java.BigInt;

@RunWith(Parameterized.class)
public class ParametryczneMnozenieTest {

private String _value;
private String _value2;
private String _result;
	
	public ParametryczneMnozenieTest(String val1, String val2, String result)
	{
		this._value = val1;
		this._value2 = val2;
		this._result = result;
	}
	
	@Parameters
	public static Collection<Object[]> none(){
		Object[][] none = new Object[][]{
			{"2234321356789","98765876543","220674707282010628900427"},
			{"123456789123456789","-987654321987654321","-121932631356500531347203169112635269"},
			{"-934156789","-934156789","872648906434790521"}
		};
		return Arrays.asList(none);
	}
	
	@Test
	public void testMnozenie() throws Exception {
				String wynik = new BigInt(_value).mnozenie(new BigInt(_value2)).show();
				assertEquals(_result, wynik);
	}
}
