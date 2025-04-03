package emt.lab.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum ROLE implements GrantedAuthority {
    LIBRARIAN, CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}
