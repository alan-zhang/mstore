package org.azt.mstore.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.azt.mstore.dao.AuthorityDao;
import org.azt.mstore.dao.ResourcesDao;
import org.azt.mstore.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource  {

	private PathMatcher pathMatcher = new AntPathMatcher();
	
	@Autowired
	private AuthorityDao authorityDao;
	
	@Autowired
	private ResourcesDao resourcesDao;
	
	private Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	@PostConstruct  
    public void init() {  
        loadResourceDefine();  
    } 
	
	private void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		
		List<Authority> authorities = this.authorityDao.getAllAuthorities();
		
		for (Authority authority : authorities) {
			String authName = authority.getAuthorityName();
			ConfigAttribute ca = new SecurityConfig(authName);
			
			List<String> reourcesPaths = this.resourcesDao.getResourcePathsByAuthorityName(authName);
			
			for (String url : reourcesPaths) {
				if (resourceMap.containsKey(url)) {  				  
	                Collection<ConfigAttribute> value = resourceMap.get(url);  
	                value.add(ca);  
	                resourceMap.put(url, value);  
	            } else {  
	                Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();  
	                atts.add(ca);  
	                resourceMap.put(url, atts);  
	            }
			}			
		}
		
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		
		String url = ((FilterInvocation) object).getRequestUrl();
		if ("/".equals(url)) {
			return null;
		}
		int firstQuestionMarkIndex = url.indexOf("?");
		
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}

		Iterator<String> ite = resourceMap.keySet().iterator();
		
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (pathMatcher.match(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
