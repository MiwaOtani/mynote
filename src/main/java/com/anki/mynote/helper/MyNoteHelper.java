package com.anki.mynote.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.anki.mynote.entity.Quiz;
import com.anki.mynote.form.MyNoteForm;

//MyNote：ヘルパー
//コントローラ側の主な役割はルーティングのため、それ以外の処理をヘルパークラスに委譲する
//「Form→Entity」「Entity→Form」の変換処理をstaticメソッドで記述
public class MyNoteHelper {
	//Quizエンティティへの変換
		public static Quiz convertQuiz(MyNoteForm form, MultipartFile image) {
			Quiz quiz = new Quiz();
			quiz.setId(form.getId());
			quiz.setQuestion(form.getQuestion());
			quiz.setAnswer1(form.getAnswer1());
			quiz.setAnswer2(form.getAnswer2());
			quiz.setAnswer3(form.getAnswer3());
			quiz.setAnswer4(form.getAnswer4());
			quiz.setCorrectAns(form.getCorrectAns());
			quiz.setCategoryId(form.getCategoryId());
			if (image != null && !image.isEmpty()) {
		        try {
		            String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
		            Path savePath = Paths.get("uploads", filename);
		            Files.copy(image.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);
		            quiz.setImagePath("/uploads/" + filename);
		            System.out.println("画像保存成功: " + savePath.toAbsolutePath());

		        } catch (IOException e) {
		            // ログ出力や例外処理は呼び出し元で対応
		        	System.err.println("画像保存失敗: " + e.getMessage());
		            quiz.setImagePath(null);
		        }
		    }
			//quiz.setImagePath(form.getImagePath());
			return quiz;
		}
		//	MyNoteFormへの変換
		public static MyNoteForm convertMyNoteForm(Quiz quiz) {
			MyNoteForm form = new MyNoteForm();
			form.setId(quiz.getId());
			form.setQuestion(quiz.getQuestion());
			form.setAnswer1(quiz.getAnswer1());
			form.setAnswer2(quiz.getAnswer2());
			form.setAnswer3(quiz.getAnswer3());
			form.setAnswer4(quiz.getAnswer4());
			form.setCorrectAns(quiz.getCorrectAns());
			form.setCategoryId(quiz.getCategoryId());
			form.setImagePath(quiz.getImagePath());
			//更新画面設定
			form.setIsNew(false);
			return form;
		}
}
