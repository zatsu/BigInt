package test.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.BigInt;

public class DodawanieTest {
	//private BigInt zero;
	private BigInt liczbaDodatnia;
	private BigInt duzaLiczbaDodatnia;
	private BigInt liczbaUjemna;
	private BigInt duzaLiczbaUjemna;
	
	@Before
	public void setup() throws Exception{
		//zero = new BigInt("0");
		liczbaDodatnia = new BigInt("123456789123456");
		duzaLiczbaDodatnia = new BigInt("987654321987654");
		liczbaUjemna = new BigInt("-123456789123456");
		duzaLiczbaUjemna = new BigInt("-987654321987654");
	}
	
	
	
	@Test
	public void dwieDodatnie() throws Exception {
	      String wynik = liczbaDodatnia.addIntegers(duzaLiczbaDodatnia);
	       assertEquals("1111111111111110",wynik);
		}
	
	
	
	@Test
	public void pierwszaUjemna() throws Exception{
		String wynik = duzaLiczbaUjemna.addIntegers(liczbaDodatnia);
		assertEquals("-864197532864198", wynik);
	}
	
	@Test 
	public void drugaUjemnaWieksza() throws Exception{
		BigInt test = new BigInt(liczbaDodatnia.addIntegers(duzaLiczbaUjemna));
		boolean wynik = test.ujemna;
		assertTrue(wynik);
	}
	
	@Test
	public void dwieUjemne() throws Exception{
		BigInt test = new BigInt(liczbaUjemna.addIntegers(duzaLiczbaUjemna));
		boolean wynik = test.ujemna;
		assertTrue(wynik);		
	}
	
	
	@Test
	public void BigIntzWyniku() throws Exception{
		BigInt test = new BigInt(liczbaDodatnia.addIntegers(liczbaDodatnia));
		assertNotNull(test);	
	}
	
	
	
}
