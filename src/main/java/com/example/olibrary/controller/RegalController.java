package com.example.olibrary.controller;

import com.example.olibrary.model.Regal;
import com.example.olibrary.service.RegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regals")
public class RegalController {
    @Autowired
    private RegalService regalService;
    @GetMapping("/{regalId}")
    public Regal getRegalById(@PathVariable Long regalId) {
        return regalService.getRegalById(regalId);
    }

    @DeleteMapping("/{regalId}")
    public void deleteRegalById(@PathVariable Long regalId) {
        regalService.deleteRegalById(regalId);
    }

    @PostMapping("/create")
    public Regal createRegal(@PathVariable Regal regal) {
        return regalService.saveRegal(regal);
    }
}
