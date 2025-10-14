package com.anki.mynote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anki.mynote.entity.Quiz;
import com.anki.mynote.service.MyNoteService;

import lombok.RequiredArgsConstructor;

//
//MyNote：コントローラ
//
@Controller
@RequestMapping("/mynote")
@RequiredArgsConstructor
public class MyNoteController {
	//DI
	private final MyNoteService service;
	
	// トップページ（/mynote）
	@GetMapping
	public String top() {
		return "mynote/index"; //mynote/index.html を表示
	}
			//問題一覧を表示する（/mynote/quizzes）
			@GetMapping("/quizzes")
			public String list(Model model) {
				model.addAttribute("quizzes", service.findAllQuiz());
				return "mynote/list"; //mynote/list.html を表示
			}
			//指定されたIDの問題を表示する
			@GetMapping("/quizzes/{id}")
			public String detail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
				//IDに対応するクイズ情報を取得
				Quiz quiz = service.findByIdQuiz(id);
				if (quiz != null) {
					//対象データがある場合はモデルに格納
					model.addAttribute("quiz", quiz);
					return "mynote/detail";
				} else {
					//対象データがない場合はフラッシュメッセージを設定
					attributes.addFlashAttribute("errorMessage", "対象データがありません");
					//リダイレクト //SpringMVCでは「redirect:」という文字列を使ってリダイレクトを行う
					return "redirect:/mynote/list";
				}
			}
			
			//*****登録・更新処理追加*****//
			

}
