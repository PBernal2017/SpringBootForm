package com.bolsadeideas.springboot.web.form.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.web.form.models.domain.Role;

@Service
public class IRoleServiceImpl implements IRoleService {

	private List<Role> roles;

	public IRoleServiceImpl() {
		this.roles = new ArrayList<>();
		this.roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
		this.roles.add(new Role(2, "Usuario", "ROLE_USER"));
		this.roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
	}

	@Override
	public List<Role> listar() {
		return roles;
	}

	@Override
	public Role obtenerPorId(Integer id) {
		Role resultado = null;
		for (Role role : roles) {
			if (id == role.getId()) {
				resultado = role;
				break;
			}
		}
		return resultado;
	}

}
