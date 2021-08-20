package com.example.filebrowser.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FileObject {
    private String name;
    private String path;
    private boolean isDirectory;
    private boolean isHidden;
}
