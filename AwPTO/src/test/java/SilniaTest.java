package test.java;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.java.BigInt;

public class SilniaTest {
	private BigInt zero;
	private BigInt liczbaUjemna;
	
	@Before
	public void setup() throws Exception{
		zero = new BigInt("0");
		liczbaUjemna = new BigInt("-456498732153");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void silniaZLiczbyUjemnej() throws Exception {
		liczbaUjemna.silnia();
	}
	
	@Test
	public void silniaZZera() throws Exception {
		String wynik = zero.silnia().numer1;
		assertEquals("1",wynik);
	}
	
	@Test
	public void czyWynikZwracaObiekt() throws Exception {
		BigInt liczba = new BigInt("5");
		BigInt wynik = liczba.silnia();
	       assertNotSame(liczba,wynik);
	       assertEquals("120",wynik.numer1);
	}

}
