package com.example.olibrary.utils;

import com.example.olibrary.enums.Role;
import com.example.olibrary.exceptions.AccessDenialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AuthUtils {
    @Autowired
    private UserUtils userUtils;

    private static final TreeMap<Role, ArrayList<String>> allowedMap;
    static {
        allowedMap = new TreeMap<Role, ArrayList<String>>();
        allowedMap.put(
                Role.USER,
                new ArrayList<>(
                        Arrays.asList(
                                "GET /authors/{authorId}",
                                "GET /books/{bookId}",
                                "GET /genres/{genreId}",
                                "GET /regals/{regalId}",
                                "DELETE /regals/{regalId}",
                                "POST /regals/create",
                                "POST /regals/add",
                                "DELETE /regals/delete",
                                "POST /genres/download",
                                "GET /session/{sessionId}",
                                "DELETE /session/{sessionId}",
                                "POST /session/create",
                                "POST /session/{sessionId}/next",
                                "POST /session/{sessionId}/previous",
                                "GET /session/{sessionId}/book"
                        )));
        allowedMap.put(
                Role.ADMIN,
                new ArrayList<>(
                        Arrays.asList(
                                "GET /authors/{authorId}",
                                "DELETE /authors/{authorId}",
                                "POST /authors/create",
                                "GET /books/{bookId}",
                                "DELETE /books/{bookId}",
                                "POST /books/create",
                                "GET /genres/{genreId}",
                                "DELETE /genres/{genreId}",
                                "POST /genres/create",
                                "GET /regals/{regalId}",
                                "DELETE /regals/{regalId}",
                                "POST /regals/create",
                                "POST /regals/add",
                                "DELETE /regals/delete",
                                "GET /users/{userId}",
                                "DELETE /users/{userId}",
                                "POST /users/create",
                                "POST /genres/download",
                                "POST /s3/upload",
                                "GET /session/{sessionId}",
                                "DELETE /session/{sessionId}",
                                "POST /session/create",
                                "POST /session/{sessionId}/next",
                                "POST /session/{sessionId}/previous",
                                "GET /session/{sessionId}/book"
                        )));
    }

    public boolean isAllowed(String inputUri) {
        Role userRole = userUtils.getCurrentUser().getRole();
        boolean res = allowedMap.get(userRole).contains(inputUri);
        if (!res) throw new AccessDenialException("Method not allowed");
        return res;
    }
}
