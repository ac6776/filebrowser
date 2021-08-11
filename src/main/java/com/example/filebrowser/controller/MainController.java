package com.example.filebrowser.controller;

import com.example.filebrowser.domain.FileObject;
import com.example.filebrowser.service.BrowserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {
    private BrowserService service;

    @GetMapping(value = {"/","{path}"})
    public FileObject load(@PathVariable(required = false) String path) {
        System.out.println(path);
        return service.loadFileNames(path);
    }
}
