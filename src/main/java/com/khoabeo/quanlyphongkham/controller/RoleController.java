package com.khoabeo.quanlyphongkham.controller;

import com.khoabeo.quanlyphongkham.dto.RoleDTO;
import com.khoabeo.quanlyphongkham.entity.Role;
import com.khoabeo.quanlyphongkham.mapper.SimpleMapper;
import com.khoabeo.quanlyphongkham.service.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @GetMapping("/")
    public ResponseEntity<List<RoleDTO>> getListRole() {
        return ResponseEntity.ok(
                this.roleService.getALLRole()
        );
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @GetMapping("/{name}")
    public ResponseEntity<RoleDTO> getRoleByName(@PathVariable String name) {
        return ResponseEntity.ok(this.roleService.getRoleByName(name));
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PostMapping("/addRole")
    public ResponseEntity<RoleDTO> getRoleByName(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(this.roleService.addRole(roleDTO));
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("/update/{name}")
    public ResponseEntity<String> updateRole(@PathVariable String name, @RequestBody RoleDTO roleDTO) {
        this.roleService.updateRole(name, roleDTO);
        return ResponseEntity.ok("UPDATE ROLE SUCCESSFULLY");
    }

    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteRoleByName(@PathVariable String name) {
        this.roleService.deleteRole(name);
        return ResponseEntity.ok("Delete ROLE SUCCESSFULLY");
    }
}

