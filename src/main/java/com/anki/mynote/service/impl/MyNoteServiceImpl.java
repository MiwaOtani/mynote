package com.anki.mynote.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anki.mynote.entity.History;
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
	@Autowired
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
		//画像パス保存
		myNoteMapper.save(quiz);
	}

	@Override
	public void insertHistoryQuiz(History history) {
		myNoteMapper.insertHistory(history);
	}

	@Override
	public void updateHistoryQuiz(History history) {
		myNoteMapper.updateHistory(history);
	}

	@Override
	public History selectHistoryByQuizId(Integer quizId) {
		return myNoteMapper.selectHistoryByQuizId(quizId);
	}
	@Override
	public void insertOrUpdateHistory(History history) {
	    History existing = myNoteMapper.selectHistoryByQuizId(history.getQuizId());
	    //System.out.println("履歴検索結果: " + (existing != null ? existing.toString() : "なし"));
	    if (existing != null) {
	        //System.out.println("履歴あり → UPDATE: " + history);
	        myNoteMapper.updateHistory(history);
	    } else {
	        //System.out.println("履歴なし → INSERT: " + history);
	        myNoteMapper.insertHistory(history);
	    }
	}

}
