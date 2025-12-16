package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * PUBLIC_INTERFACE
 * Simple REST controller exposing endpoints expected by the frontend:
 * - GET /           -> welcome string
 * - GET /health     -> basic health JSON
 * - GET /api/info   -> info JSON
 *
 * Note: Actuator is also present but these endpoints are provided explicitly to match the frontend calls.
 */
@RestController
public class ApiController {

    @GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String rootWelcome() {
        return "Welcome to GAI - KAVIA Backend";
    }

    @GetMapping(path = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> health() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("status", "UP");
        resp.put("timestamp", Instant.now().toString());
        return resp;
    }

    @GetMapping(path = "/api/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> info() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("name", "GAI - KAVIA Backend");
        resp.put("version", "0.1.0");
        resp.put("description", "Backend services for GAI - KAVIA");
        resp.put("time", Instant.now().toString());
        return resp;
    }
}
