package com.bolsadeideas.springboot.web.form.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.web.form.services.IRoleService;

@Component
public class RolesPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private IRoleService service;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			Integer id = Integer.parseInt(text);
			setValue(service.obtenerPorId(id));
		} catch (NumberFormatException e) {
			setValue(null);
		}
		
	}

}
