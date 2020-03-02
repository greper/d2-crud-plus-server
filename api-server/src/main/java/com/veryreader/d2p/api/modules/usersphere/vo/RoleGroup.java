package com.veryreader.d2p.api.modules.usersphere.vo;

import com.veryreader.d2p.api.modules.permission.entity.Platform;
import com.veryreader.d2p.api.modules.permission.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RoleGroup {
    private Platform platform;
    private List<Role> roles;

    public RoleGroup(Platform platform) {
        this.platform = platform;
    }

    public RoleGroup addRole(Role role){
        if(roles == null){
            roles = new ArrayList<>();
        }
        roles.add(role);
        return this;
    }

}
