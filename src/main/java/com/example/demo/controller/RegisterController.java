package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.common.Authority;
import com.example.demo.domain.UserInfo;
import com.example.demo.form.RegisterForm;
import com.example.demo.servise.UserService;

/**
 * ユーザー登録関連コントローラー.
 * 
 * @author matsumotoyuyya
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	/**
	 * ユーザー登録ページの表示
	 * 
	 * @param registerForm 登録ユーザーフォーム
	 * @return ユーザー登録ページ
	 */
	@GetMapping("/")
	public String toRegister(RegisterForm registerForm) {
		return "register/register";
	}

	/**
	 * ユーザー情報をインサートします.
	 * 
	 * @param registerForm 登録ユーザーフォーム.
	 * @param model        モデル
	 * @return ログイン画面
	 */
	@PostMapping("/registerUser")
	public String register(@Validated RegisterForm registerForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return toRegister(registerForm);
		}
		Optional<UserInfo> existUser = userService.findByMail(registerForm.getMailAddress());
		if (!existUser.isEmpty()) {
			model.addAttribute("message", "そのメールアドレスはすでに使用されています");
			return toRegister(registerForm);
		}

		UserInfo user = new UserInfo();
		BeanUtils.copyProperties(registerForm, user);
		Authority authority = Authority.getByValue(1);
		user.setAuthority(authority.getValue());
		userService.insert(user);
		return "redirect:/login/toLogin";
	}

}
