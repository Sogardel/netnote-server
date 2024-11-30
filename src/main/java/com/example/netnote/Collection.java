package com.example.netnote;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Clasa reprezintă o Colecție de Note.
 * Fiecare colecție are un nume unic și un set de note stocat într-un HashMap.
 */
public class Collection {
	// Numele unic al colecției
    private String name;
    // Notițele din colecție
    private ConcurrentHashMap<String, Note> notes = new ConcurrentHashMap<>();
    
    // Constructor pentru inițializare
    public Collection(String name) {
        this.name = name;
    }
    // Getter pentru numele colecției
    public String getName() {
        return name;
    }
    
    // Setter pentru numele colecției
    public void setName(String name) {
        this.name = name;
    }
    
    // Getter pentru notițe
    public ConcurrentHashMap<String, Note> getNotes() {
        return notes;
    }
    
    // Setter pentru notițe
    public void setNotes(ConcurrentHashMap<String, Note> notes) {
        this.notes = notes;
    }

    // Metodă pentru a adăuga o notiță în colecție
    public void addNote(Note note) {
        this.notes.put(note.getId(), note);
    }
}