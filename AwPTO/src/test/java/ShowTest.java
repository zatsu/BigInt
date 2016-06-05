package test.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.java.BigInt;

public class ShowTest {
	private BigInt bigIntPos;
	private BigInt bigIntNeg;
	
	@Before
	public void setup() throws Exception{
		bigIntPos = new BigInt("12345");
		bigIntNeg= new BigInt("-12345");
	}
	
	@Test
	public void showUjemnaLiczba(){
		String wynik = bigIntNeg.show();
		String oczekiwana = "-12345";
		assertEquals(oczekiwana, wynik);
	}
	@Test
	public void showDodatniaLiczba(){
		String wynik = bigIntPos.show();
		String oczekiwana = "12345";
		assertEquals(oczekiwana, wynik);
	}
	
}
