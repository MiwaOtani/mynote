package com.anki.mynote.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.anki.mynote.entity.History;
import com.anki.mynote.entity.Quiz;

@Mapper
public interface MyNoteMapper {
		//表示可能な全ての問題を取得する
		List<Quiz> selectAll();
		//管理用（非表示含む全件表示）
		List<Quiz> selectAllAdmin();
		//指定されたIDの問題を取得する
		Quiz selectById(@Param("id") Integer id); //メソッドの引数に名前を付けて別作成のマッパーファイル内のSQLプレースホルダを関連付けることができる
		//問題を新規登録する
		void insert(Quiz quiz);
		//問題を更新する
		void update(Quiz quiz);
		//指定されたIDの問題を削除する
		void delete(@Param("id") Integer id);
		//指定されたIDの問題を非表示する
		void hide(@Param("id") Integer id);
		//指定されたIDの問題を再度表示する
		void show(@Param("id") Integer id);
		//画像パスを保存する
		void save(Quiz quiz);
		//履歴を取得する
		void insertHistory(History history);
	    void updateHistory(History history);
	    History selectHistoryByQuizId(@Param("quizId") Integer quizId);
}
