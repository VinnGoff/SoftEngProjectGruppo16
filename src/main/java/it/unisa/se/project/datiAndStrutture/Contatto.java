/**
 * @file Contatto.java
 * @brief Rappresenta un contatto nella rubrica
 * 
 * Ogni contatto ha un nome, un cognome e può avere fino a tre numeri di telefono
 * e tre indirizzi email.
 */
package it.unisa.se.project.datiAndStrutture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;

/**
 *
 * @author vgoff
 */
public class Contatto implements Comparable<Contatto>{
    private String nome;
    private String cognome;
    private ArrayList<NumeroTel> numeriTel;
    private ArrayList<Email> indirizziEmail;
    
    public static final int TELEFONI_MAX = 3;
    public static final int EMAIL_MAX = 3;
    
    /**
     * @brief Costruisce un nuovo contatto
     * @param nome Nome del contatto (può essere vuoto se cognome non lo è)
     * @param cognome Cognome del contatto (può essere vuoto se nome non lo è)
     * @throws IllegalArgumentException se entrambi firstName e lastName sono vuoti
     */
    public Contatto(String nome, String cognome){
        this.nome = nome;
        this.cognome = cognome;
        numeriTel = new ArrayList<>(TELEFONI_MAX);
        indirizziEmail = new ArrayList<>(EMAIL_MAX);
    }
    
    /**
     * @brief Restituisce il nome del contatto
     * @return Il nome del contatto
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * @brief Restituisce il cognome del contatto
     * @return Il cognome del contatto
     */
    public String getCognome() {
        return cognome;
    }
    
    /**
     * @brief Imposta il nome del contatto
     * @param nome Nuovo nome
     * @throws IllegalArgumentException se il nuovo nome è vuoto e il cognome è vuoto
     */
    public void setNome(String nome) {
        this.nome=nome;
    }
    
    /**
     * @brief Imposta il cognome del contatto
     * @param cognome Nuovo cognome
     * @throws IllegalArgumentException se il nuovo cognome è vuoto e il nome è vuoto
     */
    public void setCognome(String cognome) {
        this.cognome=cognome;
    }
    
    /**
     * @brief Aggiunge un numero di telefono al contatto
     * @param numero Numero di telefono da aggiungere
     * @throws IllegalStateException se il contatto ha già tre numeri
     */
    public void aggiungiNumeroTel(NumeroTel numero) {
        numeriTel.add(numero);
    }
    
    /**
     * @brief Aggiunge un indirizzo email al contatto
     * @param email Email da aggiungere
     * @throws IllegalStateException se il contatto ha già tre email
     */
    public void aggiungiEmail(Email email) {
        indirizziEmail.add(email);
    }
    
    /**
     * @brief Rimuove un numero di telefono
     * @param numero Numero da rimuovere
     */
    public void rimuoviNumeroTel(NumeroTel numero) {
        numeriTel.remove(numero);
    }
    
    /**
     * @brief Rimuove un indirizzo email
     * @param email Email da rimuovere
     */

    public void rimuoviEmail(Email email) {
        indirizziEmail.remove(email);
            
    }
    
    /**
     * @brief Restituisce la lista dei numeri di telefono
     * @return Lista dei numeri di telefono
     */
    public ArrayList<NumeroTel> getNumeriTel() {
        return numeriTel;
    }
    
    /**
     * @brief Restituisce la lista degli indirizzi email
     * @return Lista degli indirizzi email
     */
    public ArrayList<Email> getIndirizziEmail() {
        return indirizziEmail;
    }
    
    /**
     * @brief Restituisce una stampa completa con le informazioni del contatto
     * @return  Stringa con nome, cognome, numeri di telefono e indirizzi email
     */
    @Override
    public String toString() {
        return ("Nome: " + nome + "\nCognome: " + cognome + "\nNumeri di telefono: " + numeriTel.toString() + "\nIndirizzi e-mail: " + indirizziEmail.toString());
    }
    
    /**
     * @brief Confronta due contatti per l'ordinamento
     * @param c Il contatto da confrontare
     * @return negativo se this < c, 0 se uguali, positivo se this > other
     */
    @Override
    public int compareTo(Contatto c) {
        // Prima confronta per cognome
        int cognomeCompare = this.cognome.compareToIgnoreCase(c.cognome);
        if (cognomeCompare != 0) {
            return cognomeCompare;
        }
        // Se i cognomi sono uguali, confronta per nome
        return this.nome.compareToIgnoreCase(c.nome);
    }
}