package com.example.filebrowser.service;

import com.example.filebrowser.domain.FileObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Data
@AllArgsConstructor
public class BrowserService {
    private FileObject fileObject;

    public FileObject loadFileNames(String path) {
        if (null == path) {
            path = System.getProperty("user.home");
        }
        File file = new File(path);
        if (!file.exists()) {
            return this.fileObject;
        }
        for (File f : file.listFiles()) {
            this.fileObject.put(f);
        }
        return this.fileObject;
    }
}
