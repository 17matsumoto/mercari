package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.common.GlobalExceptionHandler;
import com.example.demo.form.LoginForm;

/**
 * ログイン関連コントローラー.
 * 
 * @author matsumotoyuyya
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * ログイン画面に遷移.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/toLogin")
	public String showLoginForm(LoginForm loginForm, Model model, @RequestParam(required = false) String error) {
		logger.error("login error:" + error);
		if (error != null) {
			System.err.println("login failed");
			model.addAttribute("errorMessage", "ログインに失敗しました。メールアドレスまたはパスワードが不正です。");

		}
		return "login/login";

	}
}
