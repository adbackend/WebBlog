package com.ql.blog.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ql.blog.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		// 권한 목록
		Collection<GrantedAuthority> roleList = new ArrayList<>();
		
		roleList.add(() -> {
				return "ROLE_" + user.getRole();
		});
		
		return roleList;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정이 만료되지 않았는지 반환
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정이 잠겨있는지 반환
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 비밀번호가 만료되지 않았는지 반환
		return true;
	}

	@Override
	public boolean isEnabled() { // 계정이 활성화 되었는지 반환
		return true;
	}

}
