package com.example.filebrowser.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileTransferObject {
    private String parentPath;
    private List<FileObject> fileObjectList;

    public void addFileObject(FileObject file) {
        if (fileObjectList == null) {
            fileObjectList = new ArrayList<>();
        }
        fileObjectList.add(file);
    }
}