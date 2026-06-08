package it.uniroma3.diadia;

import java.io.*;
import java.util.Scanner;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;
import it.uniroma3.diadia.personaggi.TipoPersonaggio;

public class CaricatoreLabirinto {

    // Marker costanti per il parsing del file
    private static final String MARKER_STANZE_NORMALI = "Stanze:";
    private static final String MARKER_STANZE_MAGICHE = "Stanze magiche:";
    private static final String MARKER_STANZE_BLOCCATE = "Stanze chiuse:";
    private static final String MARKER_STANZE_BUIE = "Stanze buie:";
    private static final String MARKER_ESTREMI = "Estremi:";
    private static final String MARKER_ATTREZZI = "Attrezzi:";
    private static final String MARKER_PERSONAGGI = "Personaggi:";
    private static final String MARKER_USCITE = "Uscite:";

    private LineNumberReader reader;
    private Labirinto.LabirintoBuilder builder;

    public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
        this.reader = new LineNumberReader(new FileReader(nomeFile));
        this.builder = Labirinto.newBuilder();
    }

    public CaricatoreLabirinto(Reader reader) {
        this.reader = new LineNumberReader(reader);
        this.builder = Labirinto.newBuilder();
    }

    public void carica() throws FormatoFileNonValidoException {
        try {
            this.leggiECreaStanze(MARKER_STANZE_MAGICHE);
            this.leggiECreaStanzeMagiche(MARKER_STANZE_BLOCCATE);
            this.leggiECreaStanzeChiuse(MARKER_STANZE_BUIE);
            this.leggiECreaStanzeBuie(MARKER_ESTREMI);
            this.leggiEstremi();
            this.leggiECollocaAttrezzi(MARKER_PERSONAGGI);
            this.leggiECollocaPersonaggi(MARKER_USCITE);
            this.leggiEImpostaUscite();
        } finally {
            try {
                this.reader.close();
            } catch (IOException e) {
                throw new RuntimeException("Errore durante la chiusura del file", e);
            }
        }
    }

    private void leggiECreaStanze(String markerFineBlocco) throws FormatoFileNonValidoException {
        this.leggiRigaCheCominciaPer(MARKER_STANZE_NORMALI);
        String riga = leggiProssimaRiga();
        while (riga != null && !riga.startsWith(markerFineBlocco)) {
            String nomeStanza = riga.trim();
            if (!nomeStanza.isEmpty()) {
                this.builder.addStanza(nomeStanza);
            }
            riga = leggiProssimaRiga();
        }
        check(riga != null, "Marker di fine blocco (" + markerFineBlocco + ") non trovato dopo le Stanze.");
    }

    private void leggiECreaStanzeMagiche(String markerFineBlocco) throws FormatoFileNonValidoException {
        String riga = leggiProssimaRiga();
        while (riga != null && !riga.startsWith(markerFineBlocco)) {
            if (!riga.trim().isEmpty()) {
                try (Scanner scanner = new Scanner(riga)) {
                    check(scanner.hasNext(), "Nome stanza magica mancante.");
                    String nome = scanner.next();
                    
                    if (scanner.hasNextInt()) {
                        int sogliaMagica = scanner.nextInt();
                        this.builder.addStanzaMagica(nome, sogliaMagica);
                    } else {
                        this.builder.addStanzaMagica(nome);
                    }
                }
            }
            riga = leggiProssimaRiga();
        }
        check(riga != null, "Marker di fine blocco (" + markerFineBlocco + ") non trovato dopo le Stanze Magiche.");
    }

    private void leggiECreaStanzeChiuse(String markerFineBlocco) throws FormatoFileNonValidoException {
        String riga = leggiProssimaRiga();
        while (riga != null && !riga.startsWith(markerFineBlocco)) {
            if (!riga.trim().isEmpty()) {
                try (Scanner scanner = new Scanner(riga)) {
                    check(scanner.hasNext(), "Nome stanza bloccata mancante.");
                    String nome = scanner.next();
                    check(scanner.hasNext(), "Direzione bloccata mancante per la stanza: " + nome);
                    String direzione = scanner.next();
                    check(scanner.hasNext(), "Attrezzo sbloccante mancante per la direzione " + direzione + " nella stanza " + nome);
                    String attrezzoChiave = scanner.next();

                    this.builder.addStanzaBloccata(nome, direzione, attrezzoChiave);
                }
            }
            riga = leggiProssimaRiga();
        }
        check(riga != null, "Marker di fine blocco (" + markerFineBlocco + ") non trovato dopo le Stanze Chiuse.");
    }

    private void leggiECreaStanzeBuie(String markerFineBlocco) throws FormatoFileNonValidoException {
        String riga = leggiProssimaRiga();
        while (riga != null && !riga.startsWith(markerFineBlocco)) {
            if (!riga.trim().isEmpty()) {
                try (Scanner scanner = new Scanner(riga)) {
                    check(scanner.hasNext(), "Nome stanza buia mancante.");
                    String nome = scanner.next();
                    check(scanner.hasNext(), "Attrezzo illuminante mancante per la stanza: " + nome);
                    String attrezzoLuce = scanner.next();

                    this.builder.addStanzaBuia(nome, attrezzoLuce);
                }
            }
            riga = leggiProssimaRiga();
        }
        check(riga != null, "Marker di fine blocco (" + markerFineBlocco + ") non trovato dopo le Stanze Buie.");
    }

    private void leggiEstremi() throws FormatoFileNonValidoException {
        String nomeIniziale = leggiProssimaRiga();
        check(nomeIniziale != null, "Nome della Stanza Iniziale mancante.");
        this.builder.addStanzaIniziale(nomeIniziale.trim());

        String nomeVincente = leggiProssimaRiga();
        check(nomeVincente != null, "Nome della Stanza Vincente mancante.");
        this.builder.addStanzaVincente(nomeVincente.trim());
    }

    private void leggiECollocaAttrezzi(String markerFineBlocco) throws FormatoFileNonValidoException {
        this.leggiRigaCheCominciaPer(MARKER_ATTREZZI);
        String riga = leggiProssimaRiga();
        while (riga != null && !riga.startsWith(markerFineBlocco)) {
            if (!riga.trim().isEmpty()) {
                try (Scanner scanner = new Scanner(riga)) {
                    check(scanner.hasNext(), "Nome attrezzo mancante.");
                    String nomeAttrezzo = scanner.next();
                    check(scanner.hasNext(), "Peso attrezzo mancante per: " + nomeAttrezzo);
                    String pesoStr = scanner.next();
                    check(scanner.hasNext(), "Stanza di destinazione mancante per: " + nomeAttrezzo);
                    String nomeStanza = scanner.next();

                    try {
                        int peso = Integer.parseInt(pesoStr);
                        this.builder.addAttrezzoAStanza(nomeStanza, nomeAttrezzo, peso);
                    } catch (NumberFormatException e) {
                        check(false, "Formato peso non valido per l'attrezzo: " + nomeAttrezzo);
                    }
                }
            }
            riga = leggiProssimaRiga();
        }
        check(riga != null, "Marker di fine blocco (" + markerFineBlocco + ") non trovato dopo gli Attrezzi.");
    }

    private void leggiECollocaPersonaggi(String markerFineBlocco) throws FormatoFileNonValidoException {
        String riga = leggiProssimaRiga();
        while (riga != null && !riga.startsWith(markerFineBlocco)) {
            if (!riga.trim().isEmpty()) {
                try (Scanner scanner = new Scanner(riga)) {
                    check(scanner.hasNext(), "Tipo di personaggio mancante.");
                    String tipoStr = scanner.next().toUpperCase();
                    TipoPersonaggio tipo;
                    
                    try {
                        tipo = TipoPersonaggio.valueOf(tipoStr);
                    } catch (IllegalArgumentException e) {
                        check(false, "Tipo personaggio sconosciuto: " + tipoStr);
                        return;
                    }
                    
                    check(scanner.hasNext(), "Nome del personaggio mancante.");
                    String nome = scanner.next();
                    check(scanner.hasNext(), "Messaggio di presentazione mancante per: " + nome);
                    String presentazione = scanner.next();
                    check(scanner.hasNext(), "Stanza di collocamento mancante per: " + nome);
                    String nomeStanza = scanner.next();

                    AbstractPersonaggio personaggio = istanziaPersonaggio(tipo, nome, presentazione, scanner);

                    Stanza stanzaDestinazione = this.builder.getMappaStanze().get(nomeStanza);
                    check(stanzaDestinazione != null, "Impossibile posizionare " + nome + ": la stanza '" + nomeStanza + "' non esiste.");
                    stanzaDestinazione.setPersonaggio(personaggio);
                }
            }
            riga = leggiProssimaRiga();
        }
        check(riga != null, "Marker di fine blocco (" + markerFineBlocco + ") non trovato dopo i Personaggi.");
    }

    private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
        String riga = leggiProssimaRiga();
        while (riga != null) {
            if (!riga.trim().isEmpty()) {
                try (Scanner scanner = new Scanner(riga)) {
                    check(scanner.hasNext(), "Stanza di partenza mancante per l'uscita.");
                    String stanzaPartenza = scanner.next();
                    check(scanner.hasNext(), "Direzione dell'uscita mancante nella stanza: " + stanzaPartenza);
                    String direzione = scanner.next();
                    check(scanner.hasNext(), "Stanza di destinazione mancante uscendo a " + direzione + " da: " + stanzaPartenza);
                    String stanzaDestinazione = scanner.next();

                    this.builder.addAdiacenza(stanzaPartenza, stanzaDestinazione, direzione);
                }
            }
            riga = leggiProssimaRiga();
        }
    }


    private AbstractPersonaggio istanziaPersonaggio(TipoPersonaggio tipo, String nome, String presentazione, Scanner scanner) throws FormatoFileNonValidoException {
        switch (tipo) {
            case STREGA:
                return new Strega(nome, presentazione);
                
            case CANE:
                return new Cane(nome, presentazione);
                
            case MAGO:
                if (scanner.hasNext()) {
                    String nomeAttrezzo = scanner.next();
                    check(scanner.hasNextInt(), "Peso dell'attrezzo del mago (" + nome + ") mancante.");
                    int pesoAttrezzo = scanner.nextInt();
                    return new Mago(nome, presentazione, new Attrezzo(nomeAttrezzo, pesoAttrezzo));
                }
                return new Mago(nome, presentazione, null);
                
            default:
                throw new FormatoFileNonValidoException("Tipo personaggio non gestito dalla factory: " + tipo);
        }
    }

    private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
        try {
            String riga = this.reader.readLine();
            check(riga != null && riga.startsWith(marker), "Errore di struttura: mi aspettavo la riga iniziasse con '" + marker + "'");
            return riga.substring(marker.length());
        } catch (IOException e) {
            throw new FormatoFileNonValidoException(e.getMessage());
        }
    }

    private String leggiProssimaRiga() throws FormatoFileNonValidoException {
        try {
            return this.reader.readLine();
        } catch (IOException e) {
            throw new FormatoFileNonValidoException("Impossibile leggere la riga successiva: " + e.getMessage());
        }
    }

    private void check(boolean condizione, String messaggioErrore) throws FormatoFileNonValidoException {
        if (!condizione) {
            throw new FormatoFileNonValidoException(
                "Formato File Non Valido alla riga " + this.reader.getLineNumber() + " -> " + messaggioErrore
            );
        }
    }

    public Labirinto getLabirinto() {
        return this.builder.getLabirinto();
    }
}