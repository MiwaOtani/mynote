package com.anki.mynote.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anki.mynote.entity.Quiz;
import com.anki.mynote.repository.MyNoteMapper;
import com.anki.mynote.service.MyNoteService;

import lombok.RequiredArgsConstructor;

//
//MyNote：サービス実装クラス
//
@Service
@Transactional
@RequiredArgsConstructor
public class MyNoteServiceImpl implements MyNoteService {
	
	//DI
	private final MyNoteMapper myNoteMapper;

	@Override
	public List<Quiz> findAllQuiz() {
		// 全件表示
		return myNoteMapper.selectAll();
	}

	@Override
	public List<Quiz> findAllQuizAdmin() {
		// 管理用：全件表示
		return myNoteMapper.selectAllAdmin();
	}

	@Override
	public Quiz findByIdQuiz(Integer id) {
		// 1件表示
		return myNoteMapper.selectById(id);
	}

	@Override
	public void insertQuiz(Quiz quiz) {
		// 新規登録
		myNoteMapper.insert(quiz);
	}

	@Override
	public void updateQuiz(Quiz quiz) {
		// 更新
		myNoteMapper.update(quiz);
	}

	@Override
	public void deleteQuiz(Integer id) {
		// 削除
		myNoteMapper.delete(id);
	}

	@Override
	public void hideQuiz(Integer id) {
		// 非表示
		myNoteMapper.hide(id);
	}

	@Override
	public void showQuiz(Integer id) {
		//表示
	    myNoteMapper.show(id);
	}

	@Override
	public void saveQuiz(Quiz quiz) {
		// TODO 自動生成されたメソッド・スタブ
		myNoteMapper.save(quiz);
	}

}
