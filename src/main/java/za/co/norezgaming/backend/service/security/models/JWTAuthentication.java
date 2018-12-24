package za.co.norezgaming.backend.service.security.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
public class JWTAuthentication implements Authentication {

	private Collection<GrantedAuthority> authorities;
	private Object credentials;
	private Object details;
	private Object principal;
	private boolean authenticated;
	private String name;

}
