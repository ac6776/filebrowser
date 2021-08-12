package com.example.filebrowser.domain;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class FileObject {
    private static final Logger LOGGER = Logger.getGlobal();
    private int size;
    private Map<String, File> fileMap;

    public FileObject() {
        FileHandler
        LOGGER.info("Start ");
        this.size = 0;
        this.fileMap = new HashMap<>();
        LOGGER.info("End ");
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
