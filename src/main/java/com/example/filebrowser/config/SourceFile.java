package com.example.filebrowser.config;

import com.example.filebrowser.domain.FileObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SourceFile {
    @Bean
    @Scope(scopeName = "prototype")
    public FileObject fileObject() {
        return new FileObject();
    }
}
