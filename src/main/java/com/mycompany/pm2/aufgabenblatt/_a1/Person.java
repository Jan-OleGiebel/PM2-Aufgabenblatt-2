/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pm2.aufgabenblatt._a1;

import com.google.common.base.Preconditions;
import java.time.LocalDate;

/**
 *
 * @author jan-ole
 */
public class Person implements Comparable {
    private final String firstName;
    private String lastName;
    private final LocalDate birthDate;
    private int numberOfChildren;
    
    /**
     * Konstruktor der Klasse Person.
     * 
     * Vorbedingungen: alle Parameter, die Referenztypen sind dürfen nicht null sein. Wenn doch wird eine NullPointerException geworfen.
     * Außerdem muss die Anzahl an Kindern positiv oder gleich 0 sein. Wenn nicht wird eine IllegalArgumentException geworfen.
     * 
     * Nachbrdingungen: keine.
     * @param firstName Der Vorname.
     * @param lastName Der Nachname.
     * @param birthDate Das Geburtsdatum.
     * @param numberOfChildren Die Anzahl an Kindern.
     */
    public Person(String firstName, String lastName, LocalDate birthDate, int numberOfChildren) {
        Preconditions.checkNotNull(firstName);
        Preconditions.checkNotNull(lastName);
        Preconditions.checkNotNull(birthDate);
        Preconditions.checkArgument(!(numberOfChildren < 0), "The number of children must be positive!");
        
        this.numberOfChildren = numberOfChildren;
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
    
    /**
     * Gibt den Vornamen zurück.
     * 
     * Vorbedingungen: keine.
     * Nachbedingungen: gibt den Vornamen zurück, welcher nie null ist.
     * @return Vornamen
     */
    public String getFirstName() {
        return this.firstName;
    }
    
    /**
     * Gibt den Nachnamen zurück.
     * 
     * Vorbedingungen: keine.
     * Nachbedingungen: gibt den Nachnamen zurück, welcher nie null ist.
     * @return Nachnamen
     */
    public String getLastName() {
        return this.lastName;
    }
    
    /**
     * Gibt das Geburtsdatum zurück.
     * 
     * Vorbedingungen: keine.
     * Nachbedingungen: gibt das Geburtsdatum zurück, welches nie null ist.
     * @return Vornamen
     */
    public LocalDate getBirthDate() {
        return this.birthDate;
    }
    
    /**
     * Gibt die Anzahl an Kindern zurück.
     * 
     * Vorbedingungen: keine.
     * Nachbedingungen: gibt die Anzahl an Kinder zurück, welcher nie null ist.
     * @return Vornamen
     */
    public int getNumberOfChildren() {
        return this.numberOfChildren;
    }
    
    /**
     * Setzt die Anzahl an Kindern.
     * 
     * Vorbedingungen: Die Anzahl an Kindern muss positiv oder gleich 0 sein. Wenn nicht wird eine IllegalArgumentException geworfen.
     * Nachbedingungen: keine.
     * @return Vornamen
     */
    public void setNumberOfChildren(int newNumberOfChildren) {
        Preconditions.checkArgument(!(newNumberOfChildren < 0), "The number of children must be positive!");
        
        this.numberOfChildren = numberOfChildren;
    }
    
    /**
     * Setzt den Nachnamen.
     * 
     * Vorbedingungen: der neue Nachname darf nicht null sein. Wenn doch wird eine NullPointerException geworfen.
     * Nachbedingungen: keine.
     * @return Vornamen
     */
    public void setLastName(String newLastName) {
        Preconditions.checkNotNull(newLastName);
        
        this.lastName = newLastName;
    }
    
    /**
     * Vergleicht das Geburtsdatum eines Objektes Person mit dem aktuellen.
     * 
     * Vorbedingungen: die übergebene Objektreferenz darf nicht null sein. Wenn doch wird eine NullPointerException geworfen.
     * nachbedingungen: wenn Das Geburtsdatum kleiner ist, wird -1 zurückgegeben. Wenn es größer ist 1 und wenn es gleich ist 0.
     * @param personToCompare
     * @return int (Siehe Nachbedingungen.)
     */
    @Override
    public int compareTo(Object personToCompare) {
        Preconditions.checkNotNull(personToCompare);
        
        return this.birthDate.compareTo(((Person)personToCompare).getBirthDate());
    }
    
    /**
     * Vergleicht ein Objekt Person mit dem aktuellen.
     * 
     * Vorbedingungen: die übergebene Objektreferenz darf nicht null sein. Wenn doch wird eine NullPointerException geworfen.
     * nachbedingungen: wenn Vorname, Nachname und das Geburtsdatum beider Personen übereinstimmen, wird true zurückgegeben. ANsonsten false.
     * @param personToCompare
     * @return boolean (Siehe Nachbedingungen.)
     */
    @Override
    public boolean equals(Object personToCompare) {
        Preconditions.checkNotNull(personToCompare);
        
        return this.firstName.equals(((Person)personToCompare).getFirstName()) 
                && this.lastName.equals(((Person)personToCompare).getLastName())
                && this.birthDate.equals(((Person)personToCompare).getBirthDate());
    }
    
    /**
     * Erstellt den Hash-Code für das aktuelle Personen Objekt.
     * 
     * Vorbedingungen: keine.
     * Nachbedingungen: erstellt den Hash-Code aus der Summe des Hash-Codes des Vornamen und des Geburtsdatums.
     * @return int (Siehe Vorbedingungen.)
     */
    @Override
    public int hashCode() {
        return this.firstName.hashCode() + this.birthDate.hashCode();
    }
    
    /**
     * Konvertiert das aktuelle Personen-Objekt zu einem String.
     * 
     * 
     * Vorbedingungen: keine.
     * Nachbedingungen: ein String, welcher den Vornamen, Nachnamen, das Geburtsdatum und die Anzahl der Kinder enthält und nicht null ist.
     * @return String (Siehe Nachbedingungen.)
     */
    @Override
    public String toString() {
        return "First name: " + this.firstName + ", Last name: " + this.lastName + ", Birthday: " + this.birthDate.toString() + " Number of children: " + this.numberOfChildren;
    }
}