package org.azt.mstore.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CustomAccessDecisionManager implements AccessDecisionManager  {

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		
		Iterator<ConfigAttribute> cas = configAttributes.iterator();
		
		while(cas.hasNext()) {
			ConfigAttribute ca = cas.next();
			String role = ((SecurityConfig) ca).getAttribute();
			
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (role.trim().equals(ga.getAuthority().trim())) {
					return;
				}
			}
		}
		
		throw new AccessDeniedException("Access Denied");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
