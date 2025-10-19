package com.anki.mynote.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.anki.mynote.entity.History;
import com.anki.mynote.entity.Quiz;

public interface MyNoteService {
	//表示可能な全ての問題を取得する
	List<Quiz> findAllQuiz();
	//管理用（非表示含む全件表示）
	List<Quiz> findAllQuizAdmin();
	//指定されたIDの問題を取得する
	Quiz findByIdQuiz(Integer id);
	//問題を新規登録する
	void insertQuiz(Quiz quiz);
	//問題を更新する
	void updateQuiz(Quiz quiz);
	//指定されたIDの問題を削除する
	void deleteQuiz(Integer id);
	//指定されたIDの問題を非表示する
	void hideQuiz(Integer id);
	//指定されたIDの問題を再度表示する
	void showQuiz(Integer id);
	//画像パスを保存する
	void saveQuiz(Quiz quiz);
	//履歴を取得する
	void insertHistoryQuiz(History history);
	void updateHistoryQuiz(History history);
    History selectHistoryByQuizId(@Param("quizId") Integer quizId);
	void insertOrUpdateHistory(History history);
}
