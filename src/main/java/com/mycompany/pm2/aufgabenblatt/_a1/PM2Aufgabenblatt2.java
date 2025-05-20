/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pm2.aufgabenblatt._a1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://www.baeldung.com/java-maps-streams
 * https://www.geeksforgeeks.org/stream-map-java-examples/
 * https://www.geeksforgeeks.org/stream-filter-java-examples/
 * https://medium.com/@rtj1857/sorting-in-java-using-streams-dc7fd6597872
 * https://www.geeksforgeeks.org/intstream-of-in-java/
 * @author infwuy396
 * 
 *  person.getBirthDate().compareTo(LocalDate.ofYearDay(1984, 1)) == 1 
 */
public class PM2Aufgabenblatt2 {

    public static void main(String[] args) {
        PM2Map<Integer, Person> persons = new PM2Map<>();
        String[] firstNames = {"Tom", "Ole", "Emil", "Moritz", "Sebastian", "Judith", "Julia", "Susanne", "Martina", "Sybille"};
        String[] lastNames = {"Müller", "Mühlhausen", "Spree", "Klein", "Neuhaus", "Meier", "Schulz", "Heinze", "Groß", "Hoffmann"};
        
        Random randomGenerator = new Random();
        
        for(var i=0; i<10; i++) {
            var birthDate = LocalDate.ofYearDay(1950, 1);
            
            long yearsToAdd = (long) (randomGenerator.nextFloat()*53+1);
            long monthsToAdd = (long) (randomGenerator.nextFloat()*11+1);
            long daysToAdd = (long) (randomGenerator.nextFloat()*30+1);
        
            birthDate = birthDate.plusYears(yearsToAdd);
            birthDate = birthDate.plusMonths(monthsToAdd);
            birthDate = birthDate.plusDays(daysToAdd);
            
            int numberOfChildren = (int) (randomGenerator.nextFloat()*5);
            var person = new Person(firstNames[i], lastNames[i], birthDate, numberOfChildren);
            persons.put(i, person);
        }
        
        //Stream<ArrayList<Person>> personStream = persons.entrySet().stream().filter(element -> ((Person)((Map.Entry)element).getValue()).getBirthDate().compareTo(LocalDate.ofYearDay(1984, 1)) == 1 );
        
        //Object[] processedPersonArray = personStream.sorted(Comparator.comparing(Person::getNumberOfChildren))
                //.toArray();
        
        //for(Object person: processedPersonArray) {
        //    System.out.println(((Person)person).toString());
        //}
    }
}