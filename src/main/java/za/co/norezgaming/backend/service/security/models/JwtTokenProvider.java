package za.co.norezgaming.backend.service.security.models;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class JwtTokenProvider {

	private static final long SECOND = 1000;
	private static final long MINUTE = 60 * SECOND;
	private static final long HOUR = 60 * MINUTE;
	private static final long DAY = 24 * HOUR;

	public static final String AUTHORITIES = "authorities";
	/**
	 * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
	 * microservices environment, this key would be kept on a config-server.
	 */
	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;

	public static final long VALIDITY_IN_MILLISECONDS = 90 * DAY;

	@Value("${security.jwt.token.short.expire-length:300000}")
	public static final long SHORT_VALIDITY_IN_MILLISECONDS = 5 * MINUTE;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String accountId, String username, String role) {

		Long validityInMilliseconds = VALIDITY_IN_MILLISECONDS;
		Map<String, Object> claims = new HashMap<>();
		claims.put("UUID", accountId);
		claims.put("gamerTag", username);
		claims.put("Role", role);

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()
				.addClaims(claims)
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public JWTAuthentication getAuthentication(String token) {
		Claims claims = getClaims(token);
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) claims.get(AUTHORITIES);
		String subject = claims.getSubject();

		return new JWTAuthentication(authorities, "", claims, subject, true, subject);
	}

	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean validateToken(String token) throws Exception {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;

		} catch (IllegalArgumentException e) {
			throw new Exception("Expired or invalid JWT token");

		}
	}

}
