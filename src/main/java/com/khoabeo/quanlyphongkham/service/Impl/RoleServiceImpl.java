package com.khoabeo.quanlyphongkham.service.Impl;

import com.khoabeo.quanlyphongkham.dto.RoleDTO;
import com.khoabeo.quanlyphongkham.entity.Role;
import com.khoabeo.quanlyphongkham.exception.ExceptionNotFound;
import com.khoabeo.quanlyphongkham.mapper.SimpleMapper;
import com.khoabeo.quanlyphongkham.repository.RoleRepository;
import com.khoabeo.quanlyphongkham.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDTO> getALLRole() {
        return this.roleRepository.findAll().stream().map(
                it -> SimpleMapper.MAPPER.roletoRoleDto(it)
        ).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleByName(String name) {
        return SimpleMapper.MAPPER.roletoRoleDto(this.roleRepository.findByName(name).orElseThrow(() ->
                new ExceptionNotFound("NOT FOUND ROLE WITH NAME : " + name)
        ));
    }

    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        Role newRole = SimpleMapper.MAPPER.roleDtoToRole(roleDTO);

        return SimpleMapper.MAPPER.roletoRoleDto(this.roleRepository.save(
                newRole
        ));
    }

    @Override
    public void updateRole(String name, RoleDTO roleDTO) {
        Role getRoleExist = this.roleRepository.findByName(name).orElseThrow(
                () -> new ExceptionNotFound("CANT UPDATE BECAUSE NOT FOUND ROLE WITH : " + name)
        );
        getRoleExist.setName(roleDTO.getName());
        this.roleRepository.save(getRoleExist);
        return;
    }

    @Override
    public void deleteRole(String name) {
        Role getRoleExist = this.roleRepository.findByName(name).orElseThrow(
                () -> new ExceptionNotFound("CANT UPDATE BECAUSE NOT FOUND ROLE WITH : " + name)
        );
        this.roleRepository.delete(getRoleExist);
        return;
    }
}
