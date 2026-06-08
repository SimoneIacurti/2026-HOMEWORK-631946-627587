package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Proprieta {
    
    private static final String FILE_PROPERTIES = "diadia.properties";
    private static final Properties prop = new Properties();
    
    static {
        try (InputStream is = Proprieta.class.getClassLoader().getResourceAsStream(FILE_PROPERTIES)) {
            if (is == null) {
                throw new RuntimeException("Attenzione: File '" + FILE_PROPERTIES + "' non trovato nel classpath!");
            }
            
            prop.load(is);
            
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la lettura del file " + FILE_PROPERTIES, e);
        }
    }

    public static int getCFUiniziali() {
        return Integer.parseInt(prop.getProperty("cfu_iniziali", "20").trim());
    }
    
    public static int getPesoMaxBorsa() {
        return Integer.parseInt(prop.getProperty("peso_max_borsa", "10").trim());
    }   
    
    public static int getSogliaMagica() {
        return Integer.parseInt(prop.getProperty("soglia_magica", "3").trim());
    }
    
    public static String getCiboPreferito() {
        return prop.getProperty("cibo_preferito", "osso").trim();
    }
}
