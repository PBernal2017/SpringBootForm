package com.bolsadeideas.springboot.web.form.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.web.form.models.domain.Pais;

@Service
public class IPaisServicesImpl implements IPaisServices {

	private List<Pais> lista;
	public IPaisServicesImpl() {
		this.lista = Arrays.asList(new Pais (1, "ES", "España"), 
					new Pais (2, "MX", "México"), 
					new Pais (3, "CL", "Chile"), 
					new Pais (4, "AR", "Argentina"), 
					new Pais (5, "PE", "Perú"), 
					new Pais (6, "CO", "Colomnbia"), 
					new Pais (7, "VE", "Venezuela"));
			
	}

	@Override
	public List<Pais> listar() {

		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado = null;
		for(Pais pais: this.lista) {
			if(id == pais.getId()) {
				resultado = pais;
				break;
			}
		}
		return resultado;
	}

}
