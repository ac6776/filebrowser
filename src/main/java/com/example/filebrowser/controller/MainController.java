package com.example.filebrowser.controller;

import com.example.filebrowser.domain.FileTransferObject;
import com.example.filebrowser.domain.Path;
import com.example.filebrowser.service.BrowserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class MainController {
    private BrowserService service;

    @GetMapping
    public FileTransferObject start() {
        return service.load(null);
    }

    @PostMapping
    public FileTransferObject load(@RequestBody Path path) {
        System.out.println(path.getPath());
        return service.load(path);
    }
}
