package com.example.netnote;
/**
 * Clasa reprezintă o Notă (Note).
 * O Notă conține un identificator unic și un conținut.
 */
public class Note {
    private String id;
    private String content;
  
    // Constructor gol necesar pentru serializare JSON
    public Note() {}

    // Constructor pentru inițializare rapidă
    public Note(String id, String content) {
        this.id = id;
        this.content = content;
    }
    
    // Getter pentru ID
    public String getId() {
        return id;
    }
    
    // Setter pentru ID
    public void setId(String id) {
        this.id = id;
    }

    // Getter pentru conținut
    public String getContent() {
        return content;
    }
    
    // Setter pentru conținut
    public void setContent(String content) {
        this.content = content;
    }
}