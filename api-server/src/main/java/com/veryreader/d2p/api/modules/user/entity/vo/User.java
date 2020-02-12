package com.veryreader.d2p.api.modules.user.entity.vo;

import com.veryreader.d2p.api.modules.user.entity.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author auto generator
 * @since 2020-02-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private List<String> roles;

}
