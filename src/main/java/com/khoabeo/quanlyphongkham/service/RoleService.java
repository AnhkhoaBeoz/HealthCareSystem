package com.khoabeo.quanlyphongkham.service;

import com.khoabeo.quanlyphongkham.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    public List<RoleDTO> getALLRole();

    public RoleDTO getRoleByName(String name);

    public RoleDTO addRole(RoleDTO roleDTO);

    public void updateRole(String name, RoleDTO roleDTO);

    public void deleteRole(String name);
}
