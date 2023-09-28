package com.example.demo.Service;

import com.example.demo.Entity.Note;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {

    private final Map<Long, Note> noteMap = new HashMap<>();
    private long nextId = 1;

    public List<Note> listAll() {
        return new ArrayList<>(noteMap.values());
    }

    public Note add(Note note) {
        long id = generateId();
        note.setId(id);
        noteMap.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (!noteMap.containsKey(id)) {
            throw new IllegalArgumentException("Note with id " + id + " not found");
        }
        noteMap.remove(id);
    }

    public void update(Note note) {
        long id = note.getId();
        if (!noteMap.containsKey(id)) {
            throw new IllegalArgumentException("Note with id " + id + " not found");
        }
        noteMap.put(id, note);
    }

    public Note getById(long id) {
        if (!noteMap.containsKey(id)) {
            throw new IllegalArgumentException("Note with id " + id + " not found");
        }
        return noteMap.get(id);
    }

    private synchronized long generateId() {
        return nextId++;
    }

}


