package com.anki.mynote.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class History {
	//クイズID
	private Integer id;
	//回答フラグ（true: 正解した、false: 間違った）
	private boolean isCorrect;
	//回答日時（Java 8以降は java.time.LocalDateTime の使用が推奨とのこと）
	private LocalDateTime answeredAt;
}
