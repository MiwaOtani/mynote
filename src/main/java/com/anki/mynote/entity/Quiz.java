package com.anki.mynote.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
	private Integer id;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private Integer correctAns;
	private Integer categoryId;
	//表示フラグ（true: 表示、false: 非表示）
	private boolean isVisible;
	//回答履歴と１対１の関係
	private History history;
	//カテゴリと１対多の関係
	private List<Category> categories;
	
	private String imagePath; // 画像の保存先パス

}
