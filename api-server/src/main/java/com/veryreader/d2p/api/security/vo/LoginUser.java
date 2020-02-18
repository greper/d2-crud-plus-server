package com.veryreader.d2p.api.security.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.veryreader.d2p.api.modules.usersphere.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = -1L;
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private List<Long> roleIds;
    @JsonIgnore
    private Collection<GrantedAuthority> authorities;

    public LoginUser(User user) {
        username = user.getUsername();
        id = user.getId();
        password = user.getPassword();
        this.roleIds = user.getRoles();
    }

    public LoginUser(Long id, String username,List<Long> roleIds) {
        this.id =id;
        this.username = username;
        this.roleIds = roleIds;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities == null){
            authorities = new ArrayList<>();
            for (Long roleId : roleIds) {
                authorities.add(new SimpleGrantedAuthority(roleId.toString()));
            }
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<String> authoritiesToList(){
        List<String> list = new ArrayList<>( this.authorities.size());
        for (GrantedAuthority authority : this.authorities) {
            list.add(authority.getAuthority());
        }
        return list;
    }
}
