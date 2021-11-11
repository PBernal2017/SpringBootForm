package com.bolsadeideas.springboot.web.form.services;

import java.util.List;

import com.bolsadeideas.springboot.web.form.models.domain.Role;

public interface IRoleService {

	public List<Role> listar();
	public Role obtenerPorId(Integer id);
}
