package za.co.norezgaming.backend.service.security.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.co.norezgaming.backend.domain.account.Account;
import za.co.norezgaming.backend.service.security.AuthenticationService;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	private final AuthenticationService authenticationService;

	@Autowired
	public UserDetailsServiceImplementation(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Override
	public UserDetails loadUserByUsername(String gamerTag) throws UsernameNotFoundException {
		Account account = authenticationService.findByGamerTag(gamerTag);

		return User.withUsername(gamerTag)
				.password(account.getPassword())
				.authorities("DEFAULT")
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();

	}

}

