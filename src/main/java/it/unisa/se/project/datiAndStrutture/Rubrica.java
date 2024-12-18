/**
 * @file Rubrica.java
 * @brief Gestisce una collezione di contatti
 * 
 * Fornisce operazioni per aggiungere, rimuovere, cercare contatti
 * e salvare/caricare la rubrica da file
 * 
 * @brief Gestisce una collezione di contatti
 * @invariant contatti != null
 * @package it.unisa.se.project.datiAndStrutture
 */
package it.unisa.se.project.datiAndStrutture;

/**
 *@name imports of rubrica.java
 * @{
 * @author vgoff
 */
import java.io.*;
import java.util.*;
/**
 * @}
 */
 /**
  * @brief classe Rubrica con i metodi di rubrica
  */
public class Rubrica {
    /**
    * @private contatti lista tipo Contatto
    */
    private List<Contatto> contatti;
    
    /**
     * @brief Costruttore della rubrica
     */
    public Rubrica() {
        this.contatti = new ArrayList<>();
    }
    /**
     * @name aggiungiContato()
     * @{ 
     * @brief Aggiunge un nuovo contatto
     * @param contatto Il contatto da aggiungere
     * @pre contatto != null
     * @post contatti.contains(contatto)
     * function public void
     */
    public void aggiungiContatto(Contatto contatto) {
        if (contatto == null) {
            throw new IllegalArgumentException("Il contatto non può essere null");
        }
        contatti.add(contatto);
        Collections.sort(contatti);
    }
    /**
    * @}
    */    

    /**
     * @name rimuoviContatto()
     * @{
     * @brief Rimuove un contatto
     * @param contatto Il contatto da rimuovere
     * @pre contatto != null
     * @post !contatti.contains(contatto)
     * function public void
     */
        public void rimuoviContatto(Contatto contatto) {
        contatti.remove(contatto);
    }
    /**
    * @}
    */       
    /**
     * @name cercaContatto()
     * @{
     * @brief Cerca contatti per nome o cognome
     * @param query Stringa di ricerca
     * @return Lista dei contatti che soddisfano la ricerca
     * @pre query != null
     * @post result != null
     */
    public List<Contatto> cercaContatto(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>(contatti);
        }
        //final searchQuery
        final String searchQuery = query.toLowerCase().trim();
        List<Contatto> risultati = new ArrayList<>();
        //ciclo for
        for (Contatto c : contatti) {
            if (c.getNome().toLowerCase().contains(searchQuery) ||
                c.getCognome().toLowerCase().contains(searchQuery) ||
                c.getNumeriTel().stream().anyMatch(n -> n.toString().contains(searchQuery)) ||
                c.getIndirizziEmail().stream().anyMatch(e -> e.toString().toLowerCase().contains(searchQuery))) {
                risultati.add(c);
            }
        }
        return risultati;
    }
    /**
    * @}
    */

    /**
     * @name salvaFile()
     * @{
     * @brief Salva la rubrica su file
     * @param percorso Percorso del file
     * @throws IOException in caso di errori di I/O
     * @pre percorso != null && !percorso.isEmpty()
     */
    
    public void salvaFile(String percorso) throws IOException {
        /**try*/
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorso))) {
            writer.write("Nome,Cognome,Telefono1,Telefono2,Telefono3,Email1,Email2,Email3");
            writer.newLine();
            /**ciclo for*/
            for (Contatto c : contatti) {
                writer.write(String.format("%s,%s,", c.getNome(), c.getCognome()));
            /**controllo numero massimo di numeri telefono inseribili*/
                List<NumeroTel> numeri = c.getNumeriTel();
                for (int i = 0; i < Contatto.TELEFONI_MAX; i++) {
                    writer.write(i < numeri.size() ? numeri.get(i).toString() : "");
                    writer.write(",");
                }
             /**controllo numero massimo di email inseribili*/           
                List<Email> emails = c.getIndirizziEmail();
                for (int i = 0; i < Contatto.EMAIL_MAX; i++) {
                    writer.write(i < emails.size() ? emails.get(i).toString() : "");
                    if (i < Contatto.EMAIL_MAX - 1) writer.write(",");
                }
                writer.newLine();
            }
        }
    }
    /**
    * @}
    */    

    /**
     * @name caricaFile()
     * @{
     * @brief Carica la rubrica da file
     * @param percorso Percorso del file
     * @throws IOException in caso di errori di I/O
     * @pre percorso != null && !percorso.isEmpty()
     * @post contatti != null
     * function public void
     */
    public void caricaFile(String percorso) throws IOException {
        contatti.clear();
    /**try*/
        try (BufferedReader reader = new BufferedReader(new FileReader(percorso))) {
            String line;

            reader.readLine();
            /**ciclo while con try*/
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");

                    String nome = parts.length > 0 ? parts[0].trim() : "";
                    String cognome = parts.length > 1 ? parts[1].trim() : "";

                    NumeroTel num1 = (parts.length > 2 && !parts[2].trim().isEmpty()) ? new NumeroTel(parts[2].trim()) : null;
                    NumeroTel num2 = (parts.length > 3 && !parts[3].trim().isEmpty()) ? new NumeroTel(parts[3].trim()) : null;
                    NumeroTel num3 = (parts.length > 4 && !parts[4].trim().isEmpty()) ? new NumeroTel(parts[4].trim()) : null;

                    Email mail1 = (parts.length > 5 && !parts[5].trim().isEmpty()) ? new Email(parts[5].trim()) : null;
                    Email mail2 = (parts.length > 6 && !parts[6].trim().isEmpty()) ? new Email(parts[6].trim()) : null;
                    Email mail3 = (parts.length > 7 && !parts[7].trim().isEmpty()) ? new Email(parts[7].trim()) : null;
                    /**condizione if numeri e email vuoti*/
                    if (num1 == null) num1 = new NumeroTel("");
                    if (num2 == null) num2 = new NumeroTel("");
                    if (num3 == null) num3 = new NumeroTel("");

                    if (mail1 == null) mail1 = new Email(""); 
                    if (mail2 == null) mail2 = new Email("");
                    if (mail3 == null) mail3 = new Email("");
                    /**caso nome o cognome non vuoti*/
                    if (!nome.isEmpty() || !cognome.isEmpty()) {
                        Contatto nuovoContatto = new Contatto(nome, cognome, num1, num2, num3, mail1, mail2, mail3);
                        contatti.add(nuovoContatto);
                    } else {
                        System.err.println("Contatto ignorato: tutti i campi sono vuoti.");
                    }
                    /**catch Exception e*/
                } catch (Exception e) {
                    System.err.println("Errore durante il parsing della riga: " + line);
                    System.err.println("Dettaglio: " + e.getMessage());
                }
            }
        }
        Collections.sort(contatti);
    }
    /**
    * @}
    */    
    
    /**
     * @brief Metodo che consente di avere tutti i contatti che ci sono in rubrica
     * @return ArrayList della rubrica 
     */
    public List<Contatto> getContatti() {
        return new ArrayList<>(contatti);
    }
}
