package com.bolsadeideas.springboot.web.form.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.web.form.editors.NombreMayusculaEditor;
import com.bolsadeideas.springboot.web.form.editors.PaisPropertyEditor;
import com.bolsadeideas.springboot.web.form.editors.RolesPropertyEditor;
import com.bolsadeideas.springboot.web.form.models.domain.Pais;
import com.bolsadeideas.springboot.web.form.models.domain.Role;
import com.bolsadeideas.springboot.web.form.models.domain.Usuario;
import com.bolsadeideas.springboot.web.form.services.IPaisServices;
import com.bolsadeideas.springboot.web.form.services.IRoleService;
import com.bolsadeideas.springboot.web.form.validators.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
// Mantiene los datos de la sesion
public class FormController {

	@Autowired
	private UsuarioValidador validador;

	@Autowired
	private IPaisServices IpaisServices;

	@Autowired
	private IRoleService IroleService;

	@Autowired
	private PaisPropertyEditor paisPropertyEditor;

	@Autowired
	private RolesPropertyEditor rolesPropertyEditor;

	@InitBinder
	public void initbinder(WebDataBinder binder) {
		binder.addValidators(validador);
		// evento del ciclo de vida del controlador
		// para poder validar con validaciones con anotaciones
		// y con nuestro validador, hay que poner en set para que
		// admita las dos validaciones. Con set no funcionan las anotaciones

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNaciemiento", new CustomDateEditor(dateFormat, false));

		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class, "pais", paisPropertyEditor);
		binder.registerCustomEditor(Role.class, "roles", rolesPropertyEditor);

	}

	@ModelAttribute("genero")
	public List<String> genero() {
		return Arrays.asList("Hombre", "Mujer");
	}

	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		return this.IroleService.listar();
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return IpaisServices.listar();
	}

	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString() {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}

	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> roles = new HashMap<String, String>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");

		return roles;
	}

	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("España", "México", "Chile", "Argentina", "Perú", "Colomnbia", "Venezuela");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("ES", "España");
		paises.put("MX", "México");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Perú");
		paises.put("CO", "Colomnbia");
		paises.put("VE", "Venezuela");

		return paises;
	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Pablo");
		usuario.setApellido("Bernal");
		usuario.setIdentificador("123.456.789-K");
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Algun valor secreto");
		// PASAR VALORES POR DEFECTO
		usuario.setPais(new Pais(1, "ES", "España"));
		usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER")));
		model.addAttribute("titulo", "Prueba Formulario");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	// @ModelAttribute: indicar el nombre con el cual se pasa a la vista de forma
	// autoamtica
	@PostMapping("/form") // habilitar las validaciones
	public String procesarFormulario(@Valid Usuario usuario, BindingResult result, Model model) {
		// validador.validate(usuario, result);
		// BindingResult, representa el resultado de la validacion
		// se inyecta de forma automática cuando esta anotado con @Valid
		// tiene que estar situado justo después del objeto que se valida
		// y no despúes del Modelo

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Resultado del formulario: ");

			/*
			 * Map<String, String> errores = new HashMap<>();
			 * result.getFieldErrors().forEach(err -> { errores.put(err.getField(),
			 * "El campo ".concat(err.getField().concat(" ").concat(err.getDefaultMessage())
			 * )); }); model.addAttribute("error", errores);
			 */
			// Thymeleaf lo gestiona de forma automática

			return "form";
		}

		return "redirect:/ver";
	}

	@GetMapping("/ver")
	public String ver(@SessionAttribute(name = "usuario", required = false) Usuario usuario, Model model,
			SessionStatus status) {
		if (usuario == null) {
			return "redirect:/form";	
		}
		model.addAttribute("titulo", "Resultado del formulario: ");
		// el usuario se elimina de la sesion
		status.setComplete();

		return "resultado";
	}
}
