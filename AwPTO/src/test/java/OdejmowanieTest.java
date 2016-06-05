package test.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.BigInt;

public class OdejmowanieTest {
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
	
	@Test  //wieksza od mniejszej
	public void dwieDodatnie() throws Exception{
		BigInt test = new BigInt(duzaLiczbaDodatnia.subIntegers(liczbaDodatnia));
		boolean wynik = test.ujemna;
		assertFalse(wynik);
		
	}
	
	@Test //odejmowanie przez dodawanie!
	public void drugaUjemna() throws Exception{
		String wynik = liczbaDodatnia.subIntegers(liczbaUjemna);
		assertEquals("246913578246912", wynik);
	}
	
	
	@Test //test flag kilka operacji zmnieniajace flagi
	public void zmianaFlag() throws Exception{
		String wynik1 = duzaLiczbaUjemna.addIntegers(liczbaDodatnia);
		String wynik2 = duzaLiczbaUjemna.subIntegers(liczbaDodatnia);
		assertEquals("-864197532864198",wynik1);
		assertEquals("-1111111111111110",wynik2);
	}
	
	@Test 
	public void pierwszaUjemna() throws Exception{
		BigInt test = new BigInt(liczbaUjemna.subIntegers(duzaLiczbaDodatnia));
		boolean wynik = test.ujemna;
		assertTrue(wynik);
	}
}
