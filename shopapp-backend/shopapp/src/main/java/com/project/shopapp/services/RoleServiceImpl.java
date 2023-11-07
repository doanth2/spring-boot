package com.project.shopapp.services;

import com.project.shopapp.entities.Role;
import com.project.shopapp.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public List<Role> getAllRoles() {

        return roleRepository.findAll();
    }
}
