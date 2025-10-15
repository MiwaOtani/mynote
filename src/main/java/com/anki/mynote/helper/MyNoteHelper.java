package com.anki.mynote.helper;

import com.anki.mynote.entity.Quiz;
import com.anki.mynote.form.MyNoteForm;

//MyNote：ヘルパー
//コントローラ側の主な役割はルーティングのため、それ以外の処理をヘルパークラスに委譲する
//「Form→Entity」「Entity→Form」の変換処理をstaticメソッドで記述
public class MyNoteHelper {
	//Quizエンティティへの変換
		public static Quiz convertQuiz(MyNoteForm form) {
			Quiz quiz = new Quiz();
			quiz.setId(form.getId());
			quiz.setQuestion(form.getQuestion());
			quiz.setAnswer1(form.getAnswer1());
			quiz.setAnswer2(form.getAnswer2());
			quiz.setAnswer3(form.getAnswer3());
			quiz.setAnswer4(form.getAnswer4());
			quiz.setCorrectAns(form.getCorrectAns());
			quiz.setCategoryId(form.getCategoryId());
			quiz.setImagePath(form.getImagePath());
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
