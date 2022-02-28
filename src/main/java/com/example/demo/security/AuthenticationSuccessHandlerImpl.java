package com.example.demo.security;

import java.io.IOException;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UserService;



public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{
	@Autowired
	private UserService userService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		HttpSession session = request.getSession();
		Usuario authUser = userService.getUserByName(userDetails.getUsername());
		session.setAttribute("usuario", authUser);
		session.setAttribute("usuario.nombre", authUser.getNombreUsuario());
		session.setAttribute("usuario.id", authUser.getIdUsuario());

		boolean isUsuario = false;
		boolean isAdmin = false;
		
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("USER")) {
				isUsuario = true;
				session.setAttribute("usuario.roles", grantedAuthority.getAuthority().equals("USER"));
				break;
			}else if (grantedAuthority.getAuthority().equals("ADMIN")) {
				isAdmin = true;
				session.setAttribute("usuario.roles", grantedAuthority.getAuthority().equals("ADMIN"));
				break;
			}
		}

		
		String targetUrl;
		if (isUsuario) {
			targetUrl = "/user/userInfo/" + authUser.getIdUsuario();
		} else if (isAdmin) {
			targetUrl = "/index";
		} else {
			throw new IllegalStateException();
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
}
