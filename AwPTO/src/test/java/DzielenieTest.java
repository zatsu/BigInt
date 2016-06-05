package test.java;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.java.BigInt;

public class DzielenieTest {
	private BigInt zero;
	private BigInt liczbaDodatnia;
	private BigInt duzaLiczbaDodatnia;
	private BigInt liczbaUjemna;
	private BigInt duzaLiczbaUjemna;
	
	@Before
	public void setup() throws Exception{
		zero = new BigInt("0");
		liczbaDodatnia = new BigInt("123456789123456");
		duzaLiczbaDodatnia = new BigInt("987654321987654");
		liczbaUjemna = new BigInt("-123456789123456");
		duzaLiczbaUjemna = new BigInt("-987654321987654");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void dzieleniePrzezZero() throws Exception {
		liczbaDodatnia.dzielenie(zero);
	}
	
	@Test
	public void dzielenieZera() throws Exception {
		BigInt testSpy = Mockito.spy(zero.dzielenie(liczbaDodatnia));
		String wynik = testSpy.numer1;
		assertEquals("0",wynik);
	    Mockito.verify(testSpy, Mockito.times(0)).itsSmaller(Mockito.anyString(),Mockito.anyString());
	}
	
	@Test
	public void dzieleniePierwszaDodatniaDrugaUjemna() throws Exception {
       boolean wynik = duzaLiczbaDodatnia.dzielenie(liczbaUjemna).ujemna;
       assertTrue(wynik);
	}
	
	@Test
	public void dzielenieObieDodatnie() throws Exception {
       boolean wynik = duzaLiczbaDodatnia.dzielenie(liczbaDodatnia).ujemna;
       assertFalse(wynik);
	}
	
	@Test
	public void dzielenieObieUjemne() throws Exception {
       boolean wynik = duzaLiczbaUjemna.dzielenie(liczbaUjemna).ujemna;
       assertFalse(wynik);
	}
	
	@Test
	public void dzieleniePierwszaUjemnaDrugaDodatnia() throws Exception {
       boolean wynik = duzaLiczbaUjemna.dzielenie(liczbaDodatnia).ujemna;
       assertTrue(wynik);
	}
	
	@Test
	public void dzielnikWiekszyOdDzielnej() throws Exception {
		String wynik = liczbaDodatnia.dzielenie(duzaLiczbaDodatnia).numer1;
	       assertEquals("0",wynik);
	}
	
	@Test
	public void dzielnaTakaSamaJakDzielnik() throws Exception {
		String wynik = liczbaDodatnia.dzielenie(liczbaDodatnia).numer1;
	       assertEquals("1",wynik);
	}
	
	@Test
	public void czyWynikZwracaObiekt() throws Exception {
		BigInt wynik = liczbaDodatnia.mnozenie(liczbaUjemna);
	       assertNotSame(liczbaDodatnia,wynik);
	       assertNotSame(liczbaUjemna,wynik);
	}
}
