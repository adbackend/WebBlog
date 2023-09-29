package com.ql.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	@NotNull(message = "Username이 전달되지 않았습니다.")
	@NotBlank(message = "Username은 필수 입력 항목입니다.")
	@Size(min = 1, max = 20, message = "Username은 1~20자로 입력해주세요.")
	private String username;
	
	@NotNull(message = "Password가 전달되지 않았습니다.")
	@NotBlank(message = "Password는 필수 입력 항목입니다.")
	@Size(min = 1, max = 20, message = "Password은 1~20자로 입력해주세요.")
	private String password;
	
	@NotNull(message = "Email이 전달되지 않았습니다.")
	@NotBlank(message = "Email은 필수 입력 항목입니다.")
	@Email(message = "이메일 형식이 아닙니다.")
	private String email;

}
