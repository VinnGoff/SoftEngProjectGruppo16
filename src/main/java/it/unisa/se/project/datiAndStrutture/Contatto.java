/**
 * @file Contatto.java
 * @brief Rappresenta un contatto nella rubrica
 * @package it.unisa.se.project.datiAndStrutture
 * Ogni contatto ha un nome, un cognome e può avere fino a tre numeri di telefono
 * e tre indirizzi email.
 * 
 * @invariant numeriTel.size() <= TELEFONI_MAX
 * @invariant indirizziEmail.size() <= EMAIL_MAX
 * @invariant nome != null && cognome != null
 */
package it.unisa.se.project.datiAndStrutture;
/**
 * @name imports of contatto.java
 * @{
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;
 /**
 * @}
 */

/**
 *
 * @author vgoff
 * @brief classe Contatto
 * @implements Comparable<Contatto>
 */
public class Contatto implements Comparable<Contatto>{
    /**
     * @private nome stringa del contatto
     * @private cognome stringa del contatto
     * @private numeriTel arraylist del numero di telefono
     * @private indirizziEmail arraylist indirizzo email
     */
    private String nome;
    private String cognome;
    private ArrayList<NumeroTel> numeriTel;
    private ArrayList<Email> indirizziEmail;
    /**
     * @public Telefoni_max numero massimo di telefoni
     * @public email_max numero massimo di email
     */
    public static final int TELEFONI_MAX = 3;
    public static final int EMAIL_MAX = 3;
    
    /**
     * @name Contatto()
     * @{
     * @brief Costruisce un nuovo contatto
     * @param nome Nome del contatto (può essere vuoto se cognome non lo è)
     * @param cognome Cognome del contatto (può essere vuoto se nome non lo è)
     * @param num1 Il primo numero di telefono
     * @param num2 Il secondo numero di telefono
     * @param num3 Il terzo numero di telefono
     * @param mail1 Il primo indirizzo e-mail
     * @param mail2 Il secondo indirizzo e-mail
     * @param mail3 Il terzo indirizzo e-mail
     * @pre nome != null && cognome != null
     * @post getNumeriTel().size() <= TELEFONI_MAX
     * @post getIndirizziEmail().size() <= EMAIL_MAX
     *
     */
    public Contatto(String nome, String cognome, NumeroTel num1, NumeroTel num2, NumeroTel num3, Email mail1, Email mail2, Email mail3){
        this.nome = nome;
        this.cognome = cognome;
        numeriTel = new ArrayList<>(TELEFONI_MAX);
        indirizziEmail = new ArrayList<>(EMAIL_MAX);
        
        numeriTel.add(num1);
        numeriTel.add(num2);
        numeriTel.add(num3);
        
        indirizziEmail.add(mail1);
        indirizziEmail.add(mail2);
        indirizziEmail.add(mail3);
    }
    /**
     * @}
     */   
    /**
     * @nome getNome()
     * @{
     * @brief Restituisce il nome del contatto
     * @return Il nome del contatto
     */
    public String getNome() {
        return nome;
    }
    /**
     * @}
     */
    /**
     * @name getCognome()
     * @{
     * @brief Restituisce il cognome del contatto
     * @return Il cognome del contatto
     */
    public String getCognome() {
        return cognome;
    }
    /**
     * @}
     */
    
    /**
     * @name setNome
     * @{
     * @brief Imposta il nome del contatto
     * @param nome Nuovo nome
     * @throws IllegalArgumentException se il nuovo nome è vuoto e il cognome è vuoto
     */
    public void setNome(String nome) {
        this.nome=nome;
    }
    /**
     * @}
     */
    /**
     * @name setCognome
     * @{
     * @brief Imposta il cognome del contatto
     * @param cognome Nuovo cognome
     * @throws IllegalArgumentException se il nuovo cognome è vuoto e il nome è vuoto
     */
    public void setCognome(String cognome) {
        this.cognome=cognome;
    }
    /**
     * @}
     */
    /**
     * @name getNumeriTel()
     * @{
     * @brief Restituisce la lista dei numeri di telefono
     * @return Lista dei numeri di telefono
     * @post result.size() <= TELEFONI_MAX
     */
    public ArrayList<NumeroTel> getNumeriTel() {
        return new ArrayList<>(numeriTel);
    }
    /**
     * @}
     */   
    /**
     * @name getIndirizziEmail()
     * @{
     * @brief Restituisce la lista degli indirizzi email
     * @return Lista degli indirizzi email
     * @post result.size() <= EMAIL_MAX
     */
    public ArrayList<Email> getIndirizziEmail() {
        return new ArrayList<>(indirizziEmail);
    }
    /**
     * @}
     */    
    /**
     * @name toString()
     * @{
     * @brief Restituisce una stampa completa con le informazioni del contatto
     * @return  Stringa con nome, cognome, numeri di telefono e indirizzi email
     */
    @Override
    public String toString() {
        return ("Nome: " + nome + "\nCognome: " + cognome + "\nNumeri di telefono: " + numeriTel.toString() + "\nIndirizzi e-mail: " + indirizziEmail.toString());
    }
    /**
     * @}
     */    
    /**
     * @name compareTo()
     * @{
     * @brief Confronta due contatti per l'ordinamento
     * @param c Il contatto da confrontare
     * @return negativo se this < c, 0 se uguali, positivo se this > other
     * @pre c != null
     * @post result == 0 implies (this.cognome.equalsIgnoreCase(c.cognome) && this.nome.equalsIgnoreCase(c.nome))
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
    /**
     * @}
     */
}
