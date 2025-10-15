package com.anki.mynote.form;

import jakarta.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//MyNote：問題作成 登録フォーム
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyNoteForm {
	//問題ID
	private Integer id;
	//問題
	@NotBlank(message = "問題文は必須です。")
	private String question;
	//答え1
	@NotBlank(message = "答え1は必須です。")
	private String answer1;
	//答え2
	@NotBlank(message = "答え2は必須です。")
	private String answer2;
	
	private String answer3;
	private String answer4;
	private Integer correctAns;
	private Integer categoryId;
	private String imagePath; 
	private MultipartFile image; //  追加
	//新規判定
	private Boolean isNew; //TrueまたはFalse
	private String questionType; // "multiple" or "truefalse"
}
