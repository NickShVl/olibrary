package com.example.olibrary.controller;

import com.example.olibrary.dto.regal.RegalAddBookRequest;
import com.example.olibrary.dto.regal.RegalDeleteBookRequest;
import com.example.olibrary.model.Regal;
import com.example.olibrary.service.RegalService;
import com.example.olibrary.dto.regal.RegalCreateRequest;
import com.example.olibrary.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regals")
public class RegalController {
    @Autowired
    private RegalService regalService;
    @Autowired
    private AuthUtils authUtils;
    @GetMapping("/{regalId}")
    public Regal getRegalById(@PathVariable Long regalId) {
        authUtils.isAllowed("GET /regals/{regalId}");
        return regalService.getRegalById(regalId);
    }

    @DeleteMapping("/{regalId}")
    public void deleteRegalById(@PathVariable Long regalId) {
        authUtils.isAllowed("DELETE /regals/{regalId}");
        regalService.deleteRegalById(regalId);
    }

    @PostMapping("/create")
    public Regal createRegal(@RequestBody @Validated RegalCreateRequest request) {
        authUtils.isAllowed("POST /regals/create");
        return regalService.saveRegal(request.makeRegal());
    }

    @PostMapping("/add")
    public Regal addBookToRegal(@RequestBody @Validated RegalAddBookRequest request) {
        authUtils.isAllowed("POST /regals/add");
        return regalService.addBookToRegal(request.getBookId(), request.getRegalId());
    }

    @DeleteMapping("/delete")
    public Regal deleteBookFromRegal(@RequestBody @Validated RegalDeleteBookRequest request) {
        authUtils.isAllowed("DELETE /regals/delete");
        return regalService.deleteBookFromRegal(request.getBookId(), request.getRegalId());
    }
}
