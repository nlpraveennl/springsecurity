package com.gmail.nlpraveennl.configuration;

import java.io.Serializable;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("permissionEvaluator")
public class PermissionEvaluator implements org.springframework.security.access.PermissionEvaluator {

    /**
     * @param authentication     represents the user in question. Should not be null.
     * @param targetDomainObject the domain object for which permissions should be
     *                           checked. May be null in which case implementations should return false, as the null
     *                           condition can be checked explicitly in the expression.
     * @param permission         a representation of the permission object as supplied by the
     *                           expression system. Not null.
     * @return true if the permission is granted, false otherwise
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication != null && permission instanceof String) {
//            User loggedUser = (User) authentication.getPrincipal();
//            String permissionToCheck = (String) permission;
        }
        return true;
    }

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission)
	{
		return true;
	}

}