package com.footbalmanager.app.configuration.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.footbalmanager.app.domain.Player;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.footbalmanager.app.configuration.security.SecurityConstants.ADMIN_USER;
import static com.footbalmanager.app.configuration.security.SecurityConstants.EXPIRATION_TIME;
import static com.footbalmanager.app.configuration.security.SecurityConstants.SECRET;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException {
		try {
			Player creds = new ObjectMapper()
					.readValue(req.getInputStream(), Player.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getUsername(),
							creds.getPassword(),
							new ArrayList<>())
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain, Authentication auth) {

		String token = Jwts.builder()
				.setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
		res.addHeader("Access-Control-Expose-Headers", "access_token, is_admin");
		res.addHeader("access_token", token);

		if (Objects.equals(((User) auth.getPrincipal()).getUsername(), ADMIN_USER)) {
			res.addHeader("is_admin", "true");
		}
	}
}