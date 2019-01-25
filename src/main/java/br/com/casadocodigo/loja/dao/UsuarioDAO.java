package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email).getResultList();

		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
		}

		return usuarios.get(0);
	}

	@Transactional
	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}

	public List<Usuario> listar() {
		return manager.createQuery("select distinct(u) from Usuario u", Usuario.class).getResultList();
	}
	
	public List<Usuario> buscaPorEmail(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email).getResultList();
		
		return usuarios;
	}
	
	public Usuario buscaPorNome(String nome) {
		Usuario usuario = manager.createQuery("select u from Usuario u where nome = :nome", Usuario.class)
		.setParameter("nome", nome)
		.getSingleResult();
		return usuario;
	}
	
	public void updateRole(String email, String nomeRole) {
		manager.createQuery("insert into usuario_role(email, role_nome) "
				+ "VALUES(:email, :nome)")
		.setParameter("email", email)
		.setParameter("nome", nomeRole);
	}
	
}