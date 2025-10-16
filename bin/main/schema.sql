CREATE DATABASE IF NOT EXISTS mynote
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE mynote;

-- 注意）この順番でテーブルを作らないと参照元がないからエラーになる
-- カテゴリを格納するテーブル
CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY 
    ,category_name VARCHAR(64) NOT null 
);

-- 問題（クイズ）を格納するテーブル
CREATE TABLE quizzes (
    id INT AUTO_INCREMENT PRIMARY KEY 
    ,question VARCHAR(1000) NOT null 
    ,answer_1 VARCHAR(1000) NOT null
    ,answer_2 VARCHAR(1000) NOT null
    ,answer_3 VARCHAR(1000) 
    ,answer_4 VARCHAR(1000) 
    ,correct_ans TINYINT NOT NULL 
    ,category_id INT DEFAULT 1 COMMENT '外部キー：categories.category_idを参照'
    ,is_visible TINYINT NOT NULL DEFAULT 1 COMMENT '表示フラグ（0：非表示、1：表示）'
    ,image_path VARCHAR(255) NOT null 
    ,FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- 回答履歴を格納するテーブル
CREATE TABLE history (
    quiz_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '外部キー：quizzes.idを参照'
    ,is_correct TINYINT COMMENT '解答フラグ（0：間違った   1：正解した）'
    ,answered_at DATETIME
    ,FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);
