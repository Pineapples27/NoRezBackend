package za.co.norezgaming.backend.service.security.models;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

	private JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	private JWTAuthentication getAuthentication(String token) throws Exception {
		return StringUtils.isNotBlank(token) &&
				jwtTokenProvider.validateToken(token) ?
				jwtTokenProvider.getAuthentication(token) : null;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = null;
        try {
            authentication = getAuthentication(jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest));
        } catch (Exception e) {
            e.printStackTrace();
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

		filterChain.doFilter(servletRequest, servletResponse);
	}

}
