
-- categoriesテーブルへのデータ登録
INSERT INTO categories (category_name) VALUES ('分類なし');
INSERT INTO categories (category_name) VALUES ('テクノロジ');
INSERT INTO categories (category_name) VALUES ('マネジメント');
INSERT INTO categories (category_name) VALUES ('ストラテジ');
-- quizzesテーブルへのデータ登録
INSERT INTO quizzes (question, answer_1, answer_2, correct_ans) 
VALUES ('問題です。イルカは何類でしょう？', '魚類','哺乳類', 2);
VALUES ('問題です。イルカは何類でしょう？', '魚類','哺乳類', '両生類','どれでもない',3);

-- historyテーブルへのデータ登録
INSERT INTO history (quiz_id, is_correct, answered_at) 
VALUES (1,1,CURRENT_TIMESTAMP);
VALUES (2,1,CURRENT_TIMESTAMP);