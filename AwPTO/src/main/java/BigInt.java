package main.java;
 
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
 
public class BigInt {
 
	private static Pattern cyfraZeZnakiem = Pattern.compile("\\A-?[0-9]*\\z");
    public boolean ujemna;
    public String wynik = "";                                                         // wynik dzia³ania
    public String numer1, numer2;

 
    public BigInt(String value) throws Exception{    	
        value = value.trim();
        if (value.startsWith("-")) {
        	value = value.substring(1, value.length());
            this.ujemna = true;
        }
        while(value.startsWith("0")&&value.length()>1){
        	value = value.substring(1, value.length());
        }
        validate(value);
        this.numer1 = value;       
    }
    
    private static void validate(String value) throws Exception {
        if (!cyfraZeZnakiem.matcher(value).matches()) {                         
            throw new Exception("To nie jest liczba!");                           
        }
    }
    
    public String show() {
        String liczba ="";
        if(this.ujemna){
        	liczba +="-";
        }
        return liczba += this.numer1;
    }
 
    public String subIntegers(BigInt b) throws Exception {
        numer2 = b.numer1;
        Boolean swap = false; 
  
// sprawdzanie jaka bedzie operacja!
// dla +a - -b czyli a+b
        if (ujemna == false && b.ujemna == true) {

            b.ujemna = false; 
            wynik = addIntegers(b);
            b.ujemna = true;
            return wynik;
        }
// dla -a - +b czyli -(a+b)
        if (ujemna == true && b.ujemna == false) {
            //zmiana flag
            b.ujemna = true;
            wynik = addIntegers(b);
            b.ujemna = false;
            return wynik;
        }
 
        if (ujemna == true && b.ujemna == true) { 
            
            b.ujemna = false;
            wynik = addIntegers(b);
            b.ujemna = true;
            return wynik;
        }
 
        System.out.println("Odejmowanie: " + numer1 + "-" + numer2);
        char[] num1char;
        char[] num2char;
 
// swapy
        if (checkSize(numer1, b) == true) {
            //brak swapa jest git
            num1char = numer1.toCharArray();
            num2char = numer2.toCharArray();
        } else {
            //tutaj trzeba dokleic minus do wyniku!
            num1char = numer2.toCharArray();
            num2char = numer1.toCharArray();
            swap = true;
        }
 
        if (num1char.length > num2char.length) {
            num2char = formatToSameLength(num1char, num2char);
        } else if (num1char.length < num2char.length) {
            num1char = formatToSameLength(num2char, num1char);
        }
        final char[] subtracting = new char[num1char.length + 1];
        for (int i = num1char.length - 1; i >= 0; i--) {
 
            if (num1char[i] < num2char[i]) {
                if (num1char[i - 1] > 0) {
                    num1char[i - 1]--;
                    final int sub = Character.getNumericValue(num1char[i]) + 10 - Character.getNumericValue(num2char[i]);
                    final char[] csum = String.valueOf(sub).toCharArray();
                    subtracting[i] = csum[0];
                }
 
            }
            if (num1char[i] >= num2char[i]) {
                final int sub = Character.getNumericValue(num1char[i]) - Character.getNumericValue(num2char[i]);
                final char[] csum = String.valueOf(sub).toCharArray();
                subtracting[i] = csum[0];
            }
            //kasowanie zer
 
            wynik = String.valueOf(subtracting);
            int k = 0;
            while (wynik.charAt(k) == '0') {
                k++;
            }
            wynik = wynik.substring(k, wynik.length());
 
            if (swap == true) {
//wynik = "-"+String.valueOf(subtracting);
                wynik = "-" + wynik;
            }
 
        }// zamkniecie fora
        return wynik.trim();
    } // koniec odejmowania
 
    public String addIntegers(BigInt b) throws Exception {                  // funkcja dodawania
        numer2 = b.numer1;                                                     // do zmiennej numer jeden zapisujemy wartosc zmiennej numer1 z parametru

 
        // dodawanie za pomoca odejmnowania!
        // dla -a + +b = -a+b = b-a = -(a-b)
        if (ujemna == true && b.ujemna == false) {
            System.out.println("wchodzi tutaj?");
            // zmiana flag
            ujemna = false;
            //b.ujemna=false;
            // musze zrobic swapa LUL
            //if(checkSize(numer1, b)==true){
            // System.out.println("tu wchodzi?");
 
            String temp = numer1;
            numer1 = b.numer1;
            b.numer1 = temp;
            boolean zmiana = true;
            wynik = subIntegers(b);
            //odwracamy swapa dla poprawnego dzialania kolejnych operacji
            ujemna = true;
            if (zmiana == true) {
                temp = numer1;
                numer1 = b.numer1;
                b.numer1 = temp;
            }
 
            return wynik;
        }
 
        //dla +a + -b = a-b
        if (ujemna == false && b.ujemna == true) {
            // zmiana flag
            b.ujemna = false;
            wynik = subIntegers(b);
            b.ujemna = true;
            return wynik;
        }
 
        //System.out.println("Dodawanie: " + numer1 + "+" + numer2);             // wypisanie co robimy i z jakimi liczbami
        char[] num1chars = numer1.toCharArray();                               // do tablicy num1char wrzucamy znaki ze zmiennej numer1
        char[] num2chars = numer2.toCharArray();                               // do tablicy num2char wrzucamy znaki ze zmiennej numer2
        if (num1chars.length > num2chars.length) // jesli tablica num1char jest wiêksza od num2char
        {
            num2chars = formatToSameLength(num1chars, num2chars);              // sformatuj tablice num2char do tej samej dlugosci
        } else if (num1chars.length < num2chars.length) // jesli tablica num2char jest wiêksza od num1char
        {
            num1chars = formatToSameLength(num2chars, num1chars);              // sformatuj tablice num1char do tej samej dlugosci
        }
        final char[] wyniki = new char[num1chars.length + 1];                  // o jeden wiêksza tablica aby w pierwszym indeksie zapisac przeniesienie np. 999+1
        char przeniesienie = '0';                                              // znak liczby przeniesienia
        for (int i = num1chars.length - 1; i >= 0; i--) {                      // for przechodz¹cy po tablicy od ty³u
            final int sum = Character.getNumericValue(num1chars[i]) // do zmiennej sum dodajemy liczbê o podanym indeksie z tablicy num1char
                    + Character.getNumericValue(num2chars[i]) // do zmiennej sum dodajemy liczbê o podanym indeksie z tablicy num2char
                    + Character.getNumericValue(przeniesienie);                // do zmiennej sum dodajemy liczbê o przeniesienia
            final char[] csum = String.valueOf(sum).toCharArray();             // do tablicy csum dodawane s¹ znaki ze zmiennej sum
            przeniesienie = '0';                                               // ustawiamy przeniesnie na 0
            if (csum.length > 1 && i == 0) {                                   // jeœli tablica wyniku jest wiêksza od 1 i for jest na pocz¹tku tablicy
                wyniki[i + 1] = csum[1];                                       // do tablicy wyniki na 1 indeks wrzucamy reszte z dodawania
                wyniki[0] = csum[0];                                           // do tablicy wyniki na 0 indeks wrzucamy przeniesienie
            } else if (csum.length > 1) {                                      // jeœli jesteœmy dalej w tablicy csum
                przeniesienie = csum[0];                                       // do przeniesienia podstaw wartoœæ z 0 indeksu
                wyniki[i + 1] = csum[1];                                       // od wyniku dodaj czesc mniejsza od 1 indeksu
            } else {
                wyniki[i + 1] = csum[0];                                       // do tablicy wyniku dodaj liczbe
            }
        }
        if (ujemna == true && b.ujemna == true) {
 
            String wynik = "-" + String.valueOf(wyniki).trim();
            //System.out.println(wynik);
            return wynik;
        } // dla a+b
        else {
            //System.out.println(String.valueOf(addition));
            String wynik = String.valueOf(wyniki).trim();
            return wynik;
        }                                      // zwracamy wynik
    }
 
    public BigInt mnozenie(BigInt liczba2) throws Exception{        
        
        if(this.numer1.equals("0") || liczba2.numer1.equals("0")){
        	return new BigInt("0");
        }else if(this.numer1.equals("1")){
        	return new BigInt(liczba2.numer1);
        }else if(liczba2.numer1.equals("1")){
        	return new BigInt(this.numer1);
        }
        
        List<Integer> mnozna = new ArrayList<Integer>();
        List<Integer> mnoznik = new ArrayList<Integer>();
        List<String> wynik = new ArrayList<String>();
        
        if (this.numer1.length() > liczba2.numer1.length()) {
            mnozna = splitString(this);
            mnoznik = splitString(liczba2);
        } else {
            mnozna = splitString(liczba2);
            mnoznik = splitString(this);
        }
 
        String zera = "";
 
        for (int i = 0; i < mnozna.size(); i++) {
            for (int j = 0; j < mnoznik.size(); j++) {
                for (int k = 0; k < (i + j); k++) {
                    zera += "0000";
                }
                wynik.add(Integer.toString(mnozna.get(i) * mnoznik.get(j)) + zera);
                zera = "";
            }
        }
 
        int size = wynik.get(wynik.size() - 1).length();
 
        for (int i = 0; i < wynik.size() - 1; i++) {
            while (wynik.get(i).length() != size) {
                wynik.set(i, "0" + wynik.get(i));
            }
        }
        int przeniesienie = 0;
        String wynikOstateczny = "";
 
        for (int i = (size - 1); i >= 0; i--) {
            int localSum = 0;
            for (int j = 0; j < wynik.size(); j++) {
                localSum += Integer.parseInt(Character.toString((wynik.get(j).charAt(i))));
            }
            localSum += przeniesienie;
 
            przeniesienie = localSum / 10;
            localSum = localSum % 10;
            wynikOstateczny = Integer.toString(localSum) + wynikOstateczny;
        }

        if ((liczba2.ujemna && this.ujemna ) || (!this.ujemna && !liczba2.ujemna)) {
            return new BigInt(wynikOstateczny);
        } else {
        	return new BigInt("-"+wynikOstateczny);
        }
 
    }
 
    public BigInt dzielenie(BigInt liczba2) throws Exception{
        
        String dzielna = this.numer1;
        String dzielnik = liczba2.numer1;
           
        if(dzielna.equals(dzielnik)){
            return new BigInt("1");
        }
        else if(dzielna.length() < dzielnik.length()){
            return new BigInt("0");
        }else if(dzielna.equals("0")){
            return new BigInt("0");
        }else if(dzielnik.equals("0")){
        	throw new IllegalArgumentException("Dzielenie przez 0!");
        }else if(itsSmaller(dzielna, dzielnik)){
        	return new BigInt("0");
        }else{
        	
            int size = dzielna.length();
            while(dzielnik.length() != size){
                dzielnik = "0" + dzielnik;
            }
            boolean stop = false;
            int przeniesienie = 0;
            int przeniesienie2 = 0;
            String wynikOstateczny= "";
            String wynikDzielenia = "";
            String tmp = dzielnik;
            String count ="";
             while(!stop){     
                        wynikOstateczny= "";
                        wynikDzielenia = "";
                        for(int i=size-1; i>=0; i--){
                            int localSum = 0;
                            localSum += Integer.parseInt(Character.toString(tmp.charAt(i)));
                            localSum += Integer.parseInt(Character.toString(dzielnik.charAt(i)));
                                   
                        localSum += przeniesienie;
                   
                        przeniesienie = localSum/10;
                        localSum = localSum%10;            
                        wynikOstateczny = Integer.toString(localSum) + wynikOstateczny;
                        }
                        if(przeniesienie != 0 ){
                            wynikOstateczny = przeniesienie + wynikOstateczny;
                            dzielnik = "0" + dzielnik;
                        }
                       
                       
                        if(!itsSmaller(wynikOstateczny, dzielna)){
                            stop = true;
                        }
                       
                           
                           
                           
                                przeniesienie2 = 1;
                               
                            for(int i=count.length()-1; i>=0; i--){                    
                                int localSum = 0;
                                localSum += Integer.parseInt(Character.toString(count.charAt(i)));
                                localSum += przeniesienie2;
                           
                       
                            przeniesienie2 = localSum/10;
                            localSum = localSum%10;            
                            wynikDzielenia = Integer.toString(localSum) + wynikDzielenia;
                            }
                       
                       
                       
                        if(dzielna.equals(wynikOstateczny)){
                            stop = true;
                        }
                       
                        tmp = wynikOstateczny;
                        if(przeniesienie2 != 0){
                            count = przeniesienie2 + wynikDzielenia;
                        }else{
                           
                                count = wynikDzielenia;
                           
                        }
                    }   
             if ((liczba2.ujemna && this.ujemna) || (!this.ujemna && !liczba2.ujemna)) {
                 return new BigInt(count);
             } else {
             	return new BigInt("-"+count);
             }
        }
        
       
    }
   
    public boolean itsSmaller(String a, String b){
        List<Integer> list = splitString(a);
        List<Integer> list2 = splitString(b);
        int a1 = list.get(list.size()-1);
        int b1 = list2.get(list2.size()-1);
        if(a1 < b1){
            return true;
        }else if(a1 == b1){
                list.remove(list.size()-1);
                list2.remove(list2.size()-1);
            if(list.size()!=0){
                return itsSmaller(list, list2);
            }else{
                return true;
            }
        }else{
            return false;
        }      
    };
   
    public boolean itsSmaller(List<Integer> list, List<Integer> list2){
        int a1 = list.get(list.size()-1);
        int b1 = list2.get(list2.size()-1);
        if(a1 < b1){
            return true;
        }else if(a1 == b1){
                list.remove(list.size()-1);
                list2.remove(list2.size()-1);
            if(list.size()!=0){
                return itsSmaller(list, list2);
            }else{
                return true;
            }
        }else{
            return false;
        }      
    };
   
    public List<Integer> splitString(String number){       
        List<Integer> list = new ArrayList<Integer>();
        String tmp = "";
        int count = 0;
        for(int i=(number.length()-1); i>=0;i--){      
            count ++;
            tmp = number.charAt(i) + tmp;
            if(count == 9 || i == 0){
                list.add(Integer.parseInt(tmp));
                tmp = "";
                count =0;
            }
           
        }      
        return list;
       
    }
   
    public List<Integer> splitString(BigInt number){       
        List<Integer> list = new ArrayList<Integer>();
        String tmp = "";
        int count = 0;
        for(int i=(number.numer1.length()-1); i>=0;i--){    
            count ++;
            tmp = number.numer1.charAt(i) + tmp;
            if(count == 4 || i == 0){
                list.add(Integer.parseInt(tmp));
                tmp = "";
                count =0;
            }
           
        }      
        return list;
       
    }
 
    private static char[] formatToSameLength(char[] num1chars, char[] num2chars) { // metoda ustawiajaca ta sama dlugosc liczb
        final int diff = num1chars.length - num2chars.length;                      // obliczanie roznicy pomiedzy parametrami
        final char[] num = new char[num1chars.length];                             // tworzenie tablicy num o dlugosci num1char+1
        for (int i = 0; i < diff; i++) {                                           // dopóki jest ró¿nica
            num[i] = '0';                                                          // do tablicy num dodawaj 0
        }
        for (int i = 0; i < num2chars.length; i++) {                               // dopóki przechodzimy po tablicy num2char
            num[diff + i] = num2chars[i];                                          // do tablicy num dodajemy znaki z num2char
        }
        return num;
    }
 
    private boolean checkSize(String numer1, BigInt b) { // sprawdzam czy number 1 jest wieksze od 2 jesli tak to true

        char[] num1char = numer1.toCharArray();
        char[] num2char = b.numer1.toCharArray();
        if (num1char.length > num2char.length) {

            return true;
        }
        if (num1char.length < num2char.length) {

            return false;
        }
 
        if (num1char.length == num2char.length) {
            for (int i = 0; i < num1char.length; i++) {
                if (num1char[i] > num2char[i]) {

                    return true; // num1 jest wieksze
                }
                if (num1char[i] < num2char[i]) {
                    return false; // num2 jest mniejsze bedzie swap
                }
            }
        }
 
        return true;
    }
 
    public BigInt silnia() throws Exception {
        BigInt licznik = new BigInt("1");
        BigInt wynik = new BigInt("1");
        BigInt adder = new BigInt("1");
        String licznik2 = "1";
        BigInt temp;
        if(this.numer1.equals("0")){
        	return wynik;
        }
        if(this.ujemna){
        	throw new IllegalArgumentException("Tylko dodatnie liczby!");
        }
        while (!(licznik.numer1).equals(this.numer1)) {
            temp = new BigInt(licznik2);
            licznik = temp;
            wynik = wynik.mnozenie(licznik);
            licznik2 = temp.addIntegers(adder);
        }
        return wynik;
    }
    
 
    public static void main(String args[]) throws Exception {
        BigInt number1 = new BigInt("121");
        BigInt number2 = new BigInt("0");
      //  BigInt mnozenie = number1.mnozenie(number2);
       // System.out.println(mnozenie.show());
       //System.out.println(number1.dzielenie(number2));
       BigInt silnia =  number2.silnia();
       System.out.println(silnia.show());
 
    }
}