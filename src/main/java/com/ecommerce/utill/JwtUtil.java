package com.ecommerce.utill;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	public String parseToken(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

			String username = body.getSubject();
			return username;

		} catch (JwtException | ClassCastException e) {
			return null;
		}
	}

	public boolean validateToken(String jwt, UserDetails user) {
		return this.parseToken(jwt).equalsIgnoreCase(user.getUsername());
	}

	public String generateToken(UserDetails user) {
		Claims claims = Jwts.claims().setSubject(user.getUsername());
		claims.put("role", user.getAuthorities());

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

}
