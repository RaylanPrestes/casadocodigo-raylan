package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioValidation implements Validator {

	public boolean supports(Class<?> clazz) {
		return Usuario.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Usuario usuario = (Usuario) obj;
		if (!usuario.getSenha().equals(usuario.getSenhaConfirma())) {
			errors.rejectValue("senhaConfirma", "valid.password");
		}
		
	}

}
