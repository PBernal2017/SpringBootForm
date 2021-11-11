package com.bolsadeideas.springboot.web.form.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.web.form.services.IPaisServices;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private IPaisServices service;

	@Override
	public void setAsText(String idString) throws IllegalArgumentException {

		try {
			Integer id = Integer.parseInt(idString);
			this.setValue(service.obtenerPorId(id));
		} catch (NumberFormatException e) {
			setValue(null);
		}
	}
}
