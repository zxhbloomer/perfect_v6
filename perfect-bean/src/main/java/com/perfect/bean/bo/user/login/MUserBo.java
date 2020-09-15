package com.perfect.bean.bo.user.login;

import com.perfect.bean.entity.master.user.MUserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

public class MUserBo extends User implements Serializable {

    private static final long serialVersionUID = 1457807332111298820L;

    @Getter
    private Long id;
    @Getter
    private MUserEntity mUserEntity;

    /**
     * @param id
     * @param username
     * @param password
     * @param authorities
     */
    public MUserBo(Long id,
                       String username,
                       String password,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    /**
     * @param id
     * @param username
     * @param password
     * @param enabled
     * @param accountNonExpired
     * @param credentialsNonExpired
     * @param accountNonLocked
     * @param authorities
     */
    public MUserBo(Long id,
                       String username,
                       String password,
                       boolean enabled,
                       boolean accountNonExpired,
                       boolean credentialsNonExpired,
                       boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        this.id = id;
    }

    public MUserBo setUser(MUserEntity user) {
        this.mUserEntity = user;
        return this;
    }
}
