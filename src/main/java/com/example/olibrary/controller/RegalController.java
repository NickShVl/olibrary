package com.example.olibrary.controller;

import com.example.olibrary.dto.regal.RegalAddBookRequest;
import com.example.olibrary.dto.regal.RegalDeleteBookRequest;
import com.example.olibrary.model.Regal;
import com.example.olibrary.service.RegalService;
import com.example.olibrary.dto.regal.RegalCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public Regal createRegal(@RequestBody @Validated RegalCreateRequest request) {
        return regalService.saveRegal(request.makeRegal());
    }

    @PostMapping("/add")
    public Regal addBookToRegal(@RequestBody @Validated RegalAddBookRequest request) {
        return regalService.addBookToRegal(request.getBookId(), request.getRegalId());
    }

    @DeleteMapping("/delete")
    public Regal deleteBookFromRegal(@RequestBody @Validated RegalDeleteBookRequest request) {
        return regalService.deleteBookFromRegal(request.getBookId(), request.getRegalId());
    }
}
