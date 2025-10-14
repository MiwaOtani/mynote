package com.anki.mynote.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
					System.out.println("imagePath = " + quiz.getImagePath());

					return "mynote/detail";
				} else {
					//対象データがない場合はフラッシュメッセージを設定
					attributes.addFlashAttribute("errorMessage", "対象データがありません");
					//リダイレクト //SpringMVCでは「redirect:」という文字列を使ってリダイレクトを行う
					return "redirect:/mynote/list";
				}
			}
			
			//*****登録・更新処理追加*****//
			// クイズ登録画面を表示する
		    @GetMapping("/register")
		    public String showRegisterForm() {
		        return "mynote/register"; // templates/mynote/quiz_register.html を表示
		    }

			//画像処理：検討中
			@PostMapping("/register")
			public String registerQuiz(
			    @RequestParam("question") String question,
			    @RequestParam("image") MultipartFile image,
			    RedirectAttributes attributes
			) {
			    String imagePath = null;
			    if (!image.isEmpty()) {
			        try {
			            String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
			            Path savePath = Paths.get("uploads", filename);
			            Files.copy(image.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);
			            imagePath = "/uploads/" + filename; // Web表示用パス
			        } catch (IOException e) {
			            attributes.addFlashAttribute("errorMessage", "画像の保存に失敗しました");
			            return "redirect:/mynote";
			        }
			    }

			    Quiz quiz = new Quiz();
			    quiz.setQuestion(question);
			    quiz.setImagePath(imagePath);
			    service.saveQuiz(quiz);

			    attributes.addFlashAttribute("successMessage", "登録しました！");
			    return "redirect:/mynote";
			}
//			@PostMapping("/mynote/register")
//			public String registerQuiz(
//			    @RequestParam("question") String question,
//			    @RequestParam("image") MultipartFile image,
//			    RedirectAttributes attributes
//			) {
//			    // 画像保存処理（例：ローカル or S3）
//			    String imagePath = null;
//			    if (!image.isEmpty()) {
//			        try {
//			            String filename = image.getOriginalFilename();
//			            Path savePath = Paths.get("uploads", filename);
//			            Files.copy(image.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);
//			            imagePath = savePath.toString();
//			        } catch (IOException e) {
//			            attributes.addFlashAttribute("errorMessage", "画像の保存に失敗しました");
//			            return "redirect:/mynote";
//			        }
//			    }
//
//			    // DB登録処理（Quizエンティティなど）
//			    Quiz quiz = new Quiz();
//			    quiz.setQuestion(question);
//			    quiz.setImagePath(imagePath); // DBに画像パスを保存
//			    service.saveQuiz(quiz);
//
//			    attributes.addFlashAttribute("successMessage", "登録しました！");
//			    return "redirect:/mynote";
//			}

}
