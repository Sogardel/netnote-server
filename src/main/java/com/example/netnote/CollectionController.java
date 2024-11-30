package com.example.netnote;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;

/**
 * Controlerul pentru gestionarea colecțiilor și a notelor.
 * Expune rutele REST pentru operațiile de creare, citire și actualizare.
 */

@RestController
@RequestMapping("/netnote")// Prefixul comun pentru toate rutele
public class CollectionController {
	
	private static final String DATA_FILE = "collections.json";
    private final ConcurrentHashMap<String, Collection> collections = new ConcurrentHashMap<>();

    
    
    public CollectionController() {
        loadCollectionsFromFile();
    }
    
    /**
     * Răspuns pentru ruta de bază `/netnote`.
     * @return Mesaj de bun venit și instrucțiuni de utilizare.
     */    @GetMapping
    public ResponseEntity<String> baseEndpoint() {
        return ResponseEntity.ok("Welcome to the NetNote Server! Use /{collectionName} to manage collections.");
    }
     /**
      * Creează o colecție nouă.
      * @param collectionName Numele colecției.
      * @return Mesaj de succes sau eroare.
      */
    @PostMapping("/{collectionName}")
    public ResponseEntity<String> createCollection(@PathVariable String collectionName) {
        if (collections.containsKey(collectionName)) {
            return ResponseEntity.badRequest().body("Collection already exists");
        }
        collections.put(collectionName, new Collection(collectionName));
        saveCollectionsToFile(); // Salvează datele după creare

        return ResponseEntity.ok("Collection created");
    }
    /**
     * Obține toate notițele dintr-o colecție.
     * @param collectionName Numele colecției.
     * @return Lista de notițe sau un răspuns 404 dacă colecția nu există.
     */
    @GetMapping("/{collectionName}/notes")
    public ResponseEntity<?> getNotes(@PathVariable String collectionName) {
        Collection collection = collections.get(collectionName);
        if (collection == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(collection.getNotes());
    }
    /**
     * Adaugă o notiță într-o colecție.
     * @param collectionName Numele colecției.
     * @param note Obiectul Notă care trebuie adăugat.
     * @return Mesaj de succes sau eroare.
     */
    @PostMapping("/{collectionName}/notes")
    public ResponseEntity<String> addNote(@PathVariable String collectionName, @RequestBody Note note) {
        Collection collection = collections.get(collectionName);
        if (collection == null) {
            return ResponseEntity.notFound().build();
        }
        // Adăugarea notiței în colecție
        collection.addNote(note);
        saveCollectionsToFile(); // Salvează colecțiile după adăugare
        return ResponseEntity.ok("Notiță adăugată");
    }
    
    // Metodă pentru salvarea colecțiilor într-un fișier JSON
    public void saveCollectionsToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(DATA_FILE), collections);
            System.out.println("Colecțiile au fost salvate cu succes în fișier.");
        } catch (IOException e) {
            System.err.println("Eroare la salvarea colecțiilor: " + e.getMessage());
        }
    }
    // Metodă pentru încărcarea colecțiilor din fișier JSON
    public void loadCollectionsFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(DATA_FILE);
            if (file.exists()) {
                collections.putAll(objectMapper.readValue(file, 
                    new TypeReference<ConcurrentHashMap<String, Collection>>() {}));
                System.out.println("Colecțiile au fost încărcate din fișier.");
            } else {
                System.out.println("Fișierul de colecții nu există. Se va crea unul nou.");
            }
        } catch (IOException e) {
            System.err.println("Eroare la încărcarea colecțiilor: " + e.getMessage());
        }
    }
}
