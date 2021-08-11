package com.example.filebrowser.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@Data
public class BrowserService {
    private String path;
    private File file;

    public File[] loadFileNames() {
        if (null == path) {
            path = System.getProperty("user.home");
            System.out.println(path);
        }
        file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            return files;
        }
        return new File[]{file};
    }
}
