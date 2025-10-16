package com.anki.mynote.controller;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anki.mynote.entity.Quiz;
import com.anki.mynote.form.MyNoteForm;
import com.anki.mynote.helper.MyNoteHelper;
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
			//管理用に非表示含め全件表示する
			@GetMapping("/admin")
			public String adminList(Model model) {
				model.addAttribute("quizzes", service.findAllQuizAdmin());
				return "mynote/admin"; // 管理者用テンプレート
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
			
			//回答履歴を表示する（/mynote/history）
			@GetMapping("/history")
			public String history(Model model) {
			    List<Quiz> quizzes = service.findAllQuizAdmin(); // クイズ一覧を取得(非表示も含む)
			    model.addAttribute("quizzes", quizzes);     // テンプレートに渡す

			 // 未回答（answeredAtがnull）を除外
			    List<Quiz> answeredQuizzes = quizzes.stream()
			        .filter(q -> q.getHistory() != null && q.getHistory().getAnsweredAt() != null)
			        .toList();

			    int total = answeredQuizzes.size();// クイズの総数
			    int correct = (int) answeredQuizzes.stream()
			        .filter(q -> q.getHistory().isCorrect())
			        .count();// 正解数

			    double rate = total > 0 ? (double) correct / total * 100 : 0.0; // 0除算防止
			    model.addAttribute("correctRate", rate); // 正答率を渡す
			    
			    // ★ひとことリスト★
			    List<String> messages = List.of(
			        "ねこでも一回は立ち上がるよ？",
			        "間違いは食事のチャンス！",
			        "寝たほうがいいんじゃない？",
			        "焦らず、じらす",
			        "猫ならできる！"
			    );
			    // ランダムに1つ選ぶ
			    String randomMessage = messages.get(new Random().nextInt(messages.size()));
			    model.addAttribute("dailyMessage", randomMessage);

				return "mynote/history"; //mynote/history.html を表示
			}
			
			
			//*****登録・更新処理追加*****//
			//新規登録画面を表示する
			@GetMapping("/quizzes/form")
			public String newQuiz(@ModelAttribute MyNoteForm form) {
				//	新規登録画面の設定
				form.setIsNew(true);
				form.setQuestionType("multiple"); // デフォルト表示は複数回答形式
				form.setCorrectAns(1); // checkedは1をデフォルトで指定
				return "mynote/form";
			}

			//新規登録を実行する
			@PostMapping("/quizzes/save")
			public String create(@Validated MyNoteForm form, BindingResult bindingResult, RedirectAttributes attributes) {
				//===バリデーションチェック===
				//入力チェックNG：入力画面を表示する
				if (bindingResult.hasErrors()) {
					//新規登録画面の設定
					form.setIsNew(true);
					if (form.getQuestionType() == null) {
				        form.setQuestionType("multiple"); // null のときだけ補完
				        form.setCorrectAns(1); 
				    }
					return "mynote/form";
				}
				//=====================
				//エンティティへの変換
				Quiz Quiz = MyNoteHelper.convertQuiz(form, form.getImage());
				//登録実行
				service.insertQuiz(Quiz);
				//フラッシュメッセージ
				attributes.addFlashAttribute("message","新しい問題が作成されました！");
				//PRGパターン：一覧画面へリダイレクト
				return "redirect:/mynote/quizzes";
			}
			//指定されたIDの修正画面を表示する
			@GetMapping("/quizzes/edit/{id}")
			public String edit(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
				//IDに対応する「問題」を取得
				Quiz target = service.findByIdQuiz(id);
				if (target != null) {
					//対象データがある場合はFormへの変換
					MyNoteForm form = MyNoteHelper.convertMyNoteForm(target);
					//モデルに格納
					model.addAttribute("myNoteForm", form);
					return "mynote/form";
				} else {
					//対象データがない場合はフラッシュメッセージを設定
					attributes.addFlashAttribute("errorMessage","対象データがありません");
					//一覧画面へリダイレクト
					return "redirect:/mynote/quizzes";
				}
			}
			//「問題」の内容を更新する
			@PostMapping("/quizzes/update")
			public String update(@Validated MyNoteForm form, BindingResult bindingResult, RedirectAttributes attributes) {
				//===バリデーションチェック===
				//入力チェックNG：入力画面を表示する
				if (bindingResult.hasErrors()) {
					//新規登録画面の設定
					form.setIsNew(false);
					return "mynote/form";
				}
				//=====================
				//エンティティへの変換
				Quiz Quiz = MyNoteHelper.convertQuiz(form, form.getImage());
				//更新処理
				service.updateQuiz(Quiz);
				//フラッシュメッセージ
				attributes.addFlashAttribute("message","問題が更新されました！");
				//PRGパターン
				return "redirect:/mynote/quizzes";
			}
			//指定されたIDの「問題」を削除する
			@PostMapping("/quizzes/delete/{id}")
			public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
				//削除処理
				service.deleteQuiz(id);
				//フラッシュメッセージ
				attributes.addFlashAttribute("message","問題が削除されました");
				//PRGパターン
				return "redirect:/mynote/quizzes";
			}
			//指定されたIDの「問題」を非表示にする（管理ページのみ出現するボタンの処理）
			@PostMapping("/quizzes/hide/{id}")
			public String hide(@PathVariable Integer id, Model model,@RequestParam String redirectTo, RedirectAttributes redirectAttributes) {
				// 非表示処理（表示フラグを false にする）
				service.hideQuiz(id);
				//model.addAttribute("quizzes", service.findAllQuizAdmin()); // 最新データ再取得
				redirectAttributes.addFlashAttribute("message", "問題を非表示にしました");
				return "redirect:" + redirectTo;// 同じテンプレートを返す
			}
			//指定されたIDの「問題」を再度表示にする（管理ページのみ出現するボタンの処理）
			@PostMapping("/quizzes/show/{id}")
			public String show(@PathVariable Integer id, Model model, @RequestParam String redirectTo, RedirectAttributes redirectAttributes) {
				// 表示処理（表示フラグを true にする）
				service.showQuiz(id);
				//model.addAttribute("quizzes", service.findAllQuizAdmin());
				redirectAttributes.addFlashAttribute("message", "問題を表示に戻しました");
				return "redirect:" + redirectTo;
			}


//			@PostMapping("/quizzes/hide/{id}")
//			@ResponseBody
//			public String hide(@PathVariable Integer id, Model model) {
//			    service.hideQuiz(id);
////			    model.addAttribute("message", "問題を非表示にしました");
//			    return "OK";
//			}
//
//			@PostMapping("/quizzes/show/{id}")
//			@ResponseBody
//			public String show(@PathVariable Integer id, Model model) {
//			    service.showQuiz(id);
////			    model.addAttribute("message", "問題を表示に戻しました");
//			    return "OK";
//			}
			
//			//トグル型
//			@PostMapping("/toggle/{id}")
//			public String toggleVisibility(@PathVariable Integer id, RedirectAttributes attributes) {
//				// 表示状態を切り替える
//				MyNoteService.toggleVisibility(id);
//				attributes.addFlashAttribute("message", "問題の表示状態を切り替えました");
//				return "redirect:/mynote/quizzes";
//			}
		

}
