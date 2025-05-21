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
 * Webseiten, die Inspiration geliefert haben:
 * https://www.baeldung.com/java-maps-streams
 * https://www.geeksforgeeks.org/stream-map-java-examples/
 * https://www.geeksforgeeks.org/stream-filter-java-examples/
 * https://medium.com/@rtj1857/sorting-in-java-using-streams-dc7fd6597872
 * https://www.geeksforgeeks.org/intstream-of-in-java/
 * @author infwuy396
 * 
 *
 */

public class PM2Aufgabenblatt2 {

    public static void main(String[] args) {
        PM2Map<String, Person> persons = new PM2Map<>();
        String[] firstNames = {"Tom", "Ole", "Emil", "Moritz", "Sebastian", "Judith", "Julia", "Susanne", "Martina", "Sybille"};
        String[] lastNames = {"Müller", "Mühlhausen", "Spree", "Klein", "Neuhaus", "Meier", "Schulz", "Heinze", "Groß", "Hoffmann"};
        
        Random randomGenerator = new Random();
        
        for(var i=0; i<10; i++) {
            var firstName = firstNames[i];
            var lastName = lastNames[i];
            
            var birthDate = LocalDate.ofYearDay(1950, 1);
            
            long yearsToAdd = (long) (randomGenerator.nextFloat()*53+1);
            long monthsToAdd = (long) (randomGenerator.nextFloat()*10+1);
            long daysToAdd = (long) (randomGenerator.nextFloat()*29+1);
        
            birthDate = birthDate.plusYears(yearsToAdd);
            birthDate = birthDate.plusMonths(monthsToAdd);
            birthDate = birthDate.plusDays(daysToAdd);
            
            int numberOfChildren = (int) (randomGenerator.nextFloat()*5);
            var person = new Person(firstName, lastName, birthDate, numberOfChildren);
            persons.put(lastName, person);
        }
        
        System.out.println("Personen vor dem Filern und Sortieren:");
        for(Object person: persons.values().toArray()) {
            System.out.println(((Person)person).toString());
        }
        
        System.out.println("\n");
        
        Object[] processedPersonArray = persons.values().stream()
                .filter(person -> person.getBirthDate().compareTo(LocalDate.ofYearDay(1984, 1)) < 0)
                .sorted(Comparator.comparing(Person::getNumberOfChildren).reversed())
                .toArray();
        
        System.out.println("Personen nach dem Filern und Sortieren:");
        for(Object person: processedPersonArray) {
            System.out.println(((Person)person).toString());
        }
    }
}