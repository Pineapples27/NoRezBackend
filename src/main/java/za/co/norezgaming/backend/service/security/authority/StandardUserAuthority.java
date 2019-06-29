package za.co.norezgaming.backend.service.security.authority;

import org.springframework.security.core.GrantedAuthority;

public class StandardUserAuthority implements GrantedAuthority {
	@Override
	public String getAuthority() {
		return Authority.STANDARD_USER.getValue();
	}
}
