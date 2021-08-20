package com.example.filebrowser.controller;

import com.example.filebrowser.domain.FileTransferObject;
import com.example.filebrowser.service.BrowserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {
    private BrowserService service;

    @GetMapping
    public FileTransferObject load1(@RequestBody String path) {
        return service.load(path);
    }
}
