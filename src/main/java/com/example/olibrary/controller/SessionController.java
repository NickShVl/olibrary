package com.example.olibrary.controller;

import com.example.olibrary.dto.regal.RegalAddBookRequest;
import com.example.olibrary.dto.regal.RegalCreateRequest;
import com.example.olibrary.dto.regal.RegalDeleteBookRequest;
import com.example.olibrary.dto.session.CreateSessionRequest;
import com.example.olibrary.model.Regal;
import com.example.olibrary.model.Session;
import com.example.olibrary.service.RegalService;
import com.example.olibrary.service.SessionService;
import com.example.olibrary.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private AuthUtils authUtils;
    @GetMapping("/{sessionId}")
    public Session getRegalById(@PathVariable Long sessionId) {
        authUtils.isAllowed("GET /session/{sessionId}");
        return sessionService.getSessionById(sessionId);
    }

    @DeleteMapping("/{sessionId}")
    public void deleteRegalById(@PathVariable Long sessionId) {
        authUtils.isAllowed("DELETE /session/{sessionId}");
        sessionService.deleteSessionById(sessionId);
    }

    @PostMapping("/create")
    public Session createRegal(@RequestBody CreateSessionRequest request) {
        authUtils.isAllowed("POST /session/create");
        return sessionService.createSession(request.getUserId(), request.getBookId());
    }

    @PostMapping("/{sessionId}/next")
    public Integer setNextPart(@PathVariable Long sessionId) {
        authUtils.isAllowed("POST /session/{sessionId}/next");
        return sessionService.setNextPart(sessionId);
    }
    @PostMapping("/previous/{sessionId}")
    public Integer setPreviousPart(@PathVariable Long sessionId) {
        authUtils.isAllowed("POST /session/{sessionId}/previous");
        return sessionService.setPreviousPart(sessionId);
    }
    @GetMapping("/{sessionId}/book")
    public String getBook(@PathVariable Long sessionId) {
        authUtils.isAllowed("GET /session/{sessionId}/book");
        return sessionService.getBook(sessionId);
    }
}
