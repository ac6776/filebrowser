package com.example.filebrowser.domain;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class FileObject {
    private int size;
    private Map<String, File> fileMap;

    public FileObject() {
        this.size = 0;
        this.fileMap = new HashMap<>();
    }

    public void put(File file) {
        fileMap.put(file.getName(), file);
        size++;
    }

    public int getSize() {
        return size;
    }

    public Map<String, File> getFileMap() {
        return fileMap;
    }
}
