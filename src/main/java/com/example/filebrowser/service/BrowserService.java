package com.example.filebrowser.service;

import com.example.filebrowser.domain.FileObject;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Data
public class BrowserService {
    private String path;
    private File file;

    public FileObject loadFileNames(String path) {
        if (null == path) {
            path = System.getProperty("user.home");
        }
        file = new File(path);
        FileObject fileObject = new FileObject();
        if (!file.exists()) {
            return fileObject;
        }
        for (File f : file.listFiles()) {
            fileObject.put(f);
        }
        return fileObject;
    }
}
