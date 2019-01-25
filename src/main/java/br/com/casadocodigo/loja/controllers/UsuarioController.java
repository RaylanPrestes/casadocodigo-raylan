package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private RoleDAO roleDao;
	
	
	@InitBinder("Usuario")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional
	public ModelAndView cadastra(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes, Errors errors) {
		
		if(!usuarioDao.buscaPorEmail(usuario.getEmail()).isEmpty()){
			errors.rejectValue("email", "usuario.existente");
		}
		
		if(result.hasErrors()) {
			return form(usuario);
		}
		
		usuarioDao.gravar(usuario);
		
		redirectAttributes.addFlashAttribute("sucesso", "Usuario cadastrado com sucesso!");
		
		return new ModelAndView("redirect:/usuarios");
	}

	@RequestMapping("/form")
	private ModelAndView form(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("/cadastroUsuarioForm");
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listaUsuarios() {
		List<Usuario> usuarios = usuarioDao.listar();
		ModelAndView modelAndView = new ModelAndView("/listaUsuarios");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView; 
	}
	
	@RequestMapping("/alteraPermissao/{nome}")
	public ModelAndView alteraPermissaoForm(@PathVariable("nome") String nome) {
		ModelAndView modelAndView = new ModelAndView("/alteraPermissaoForm");
		Usuario usuario = usuarioDao.buscaPorNome(nome);
		List<Role> roles = roleDao.buscaRoles();
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("roles", roles);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/atualizar/{nome}")
	@Transactional
	public ModelAndView setPermissao(@PathVariable("nome") String nome, @RequestParam List<Role> roles, RedirectAttributes redirectAttributes) {
		
		Usuario usuario = usuarioDao.buscaPorNome(nome);
				
		System.out.println(usuario.getNome());
		
		System.out.println(roles);
		
		usuario.setRoles(roles);
		
		usuarioDao.updateRole(usuario.getEmail(), roles.get(0).getNome());
		
		redirectAttributes.addFlashAttribute("sucesso", "Permissões alteradas com sucesso");
		return new ModelAndView("redirect:/usuarios");
	}
}
