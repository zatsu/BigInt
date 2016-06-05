package test.java;

import static org.junit.Assert.*;

import org.hamcrest.core.AnyOf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import main.java.BigInt;

public class MnozenieTest {
	private BigInt zero;
	private BigInt jeden;
	private BigInt liczbaDodatnia;
	private BigInt duzaLiczbaDodatnia;
	private BigInt liczbaUjemna;
	private BigInt duzaLiczbaUjemna;
	
	@Before
	public void setup() throws Exception{
		zero = new BigInt("0");
		jeden = new BigInt("1");
		liczbaDodatnia = new BigInt("123456789123456");
		duzaLiczbaDodatnia = new BigInt("987654321987654");
		liczbaUjemna = new BigInt("-123456789123456");
		duzaLiczbaUjemna = new BigInt("-987654321987654");
	}
	
	@Test
	public void mnozenieZeraTest() throws Exception {
       String wynik = zero.mnozenie(liczbaDodatnia).numer1;
       assertEquals("0",wynik);
	}
	
	@Test
	public void mnozeniePierwszaDodatniaDrugaUjemna() throws Exception {
       boolean wynik = liczbaDodatnia.mnozenie(liczbaUjemna).ujemna;
       assertTrue(wynik);
	}
	
	@Test
	public void mnozenieObieDodatnie() throws Exception {
       boolean wynik = duzaLiczbaDodatnia.mnozenie(liczbaDodatnia).ujemna;
       assertFalse(wynik);
	}
	
	@Test
	public void mnozenieObieUjemne() throws Exception {
       boolean wynik = duzaLiczbaUjemna.mnozenie(liczbaUjemna).ujemna;
       assertFalse(wynik);
	}
	
	@Test
	public void mnozenieJedenRazyLiczba() throws Exception {
	   BigInt testSpy = Mockito.spy(liczbaDodatnia.mnozenie(jeden));
       String oczekiwana = liczbaDodatnia.numer1;
       String wynik = testSpy.numer1;
       assertEquals(oczekiwana,wynik);
       Mockito.verify(testSpy, Mockito.times(0)).splitString(Mockito.any(BigInt.class));
	}
	
	@Test
	public void mnozenieLiczbaRazyJeden() throws Exception {
		BigInt testSpy = Mockito.spy(liczbaDodatnia.mnozenie(jeden));
	    String oczekiwana = liczbaDodatnia.numer1;
	    String wynik = testSpy.numer1;
	    assertEquals(oczekiwana,wynik);
	    Mockito.verify(testSpy, Mockito.times(0)).splitString(Mockito.any(BigInt.class));
	}
	
	@Test
	public void czyWynikZwracaObiekt() throws Exception {
		BigInt wynik = liczbaDodatnia.mnozenie(liczbaUjemna);
	       assertNotSame(liczbaDodatnia,wynik);
	       assertNotSame(liczbaUjemna,wynik);
	}
}
