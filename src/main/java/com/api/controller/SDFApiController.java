package com.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.api.model.*;
import com.api.repository.*;
import com.api.service.FileService;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SDFApiController {

    // Get the list of users
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> getUser(@RequestBody Map<String, Object> userinfo ) {
        System.out.println("Code: " + userinfo.get("email"));
        System.out.println("Node ID: " + userinfo.get("password"));
        Map<String, Object> user = new HashMap<>();
        user.put("email", userinfo.get("email"));
        user.put("uid", "user-abc-123");
        user.put("token", "mock-jwt-token");
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/connections")
    public ResponseEntity<Map<String, Object>> getConnections(){
        List<String> connections = new ArrayList<>();
        String[] conn = {"PostgreSQL", "MySQL", "SQL Server", "Oracle", "Hive", "Snowflake"};
        Collections.addAll(connections, conn);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("connectionlist", Collections.unmodifiableList(connections));
        return ResponseEntity.ok(response);
    }
}
