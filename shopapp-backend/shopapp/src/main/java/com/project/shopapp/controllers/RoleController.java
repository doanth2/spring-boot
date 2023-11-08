package com.project.shopapp.controllers;
import com.project.shopapp.entities.Role;
import com.project.shopapp.services.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}roles")
@RequiredArgsConstructor

public class RoleController {
    private final RoleServiceImpl roleServiceImpl;
    @GetMapping("")
    public ResponseEntity<?> getAllRoles() {
        List<Role> roles = roleServiceImpl.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}
