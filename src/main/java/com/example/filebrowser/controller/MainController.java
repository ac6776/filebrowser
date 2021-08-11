package com.example.filebrowser.controller;

import com.example.filebrowser.service.BrowserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@AllArgsConstructor
public class MainController {
    private BrowserService service;

    @GetMapping
    public File[] load() {
        return service.loadFileNames();
    }
}
