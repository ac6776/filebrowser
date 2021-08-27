package com.example.filebrowser.service;

import com.example.filebrowser.domain.FileObject;
import com.example.filebrowser.domain.FileTransferObject;
import com.example.filebrowser.domain.Path;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrowserService {
    private static final String HOME_DIR = System.getProperty("user.home");

    public FileTransferObject load(Path path) {
        if (null == path) {
            path = new Path(HOME_DIR);
        }
        File file = new File(path.getPath());
        if (!file.exists()) {
            //todo throw FileNotFoundException
            throw new RuntimeException("File not found");
//            return null;
        }
        if (file.isFile()) {
            //todo
            return null;
        }

        FileObject parentFileObject = null;
        if (file.getParent() != null) {
            File parent = new File(file.getParent());
            parentFileObject = new FileObject(
                    parent.getName(),
                    parent.getPath(),
                    parent.isDirectory(),
                    parent.isHidden());
        }

        List<FileObject> fileObjects = Arrays.stream(file.listFiles())
                .map(f -> new FileObject(f.getName(), f.getAbsolutePath(), f.isDirectory(), f.isHidden()))
                .collect(Collectors.toList());
        return new FileTransferObject(
                parentFileObject,
                new FileObject(file.getName(), file.getPath(), file.isDirectory(), file.isHidden()),
                fileObjects);
    }
}
