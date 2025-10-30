## 📝 暗記アプリmynote
 
資格試験の勉強中「この問題だけは何度も見返したい！」「もっと簡単に覚えたい！」という瞬間、ありますよね。  
このWebアプリケーションはそんな“痒いところに手が届く”機能を備えた、自分だけのオリジナル暗記帳を作成できる学習支援ツールです。

 🌟「あと一押し！」を支える、あなた専用の暗記サポーター🌟
 
 ＜こんな方に使ってほしい＞
- 覚えるべきポイントを集中管理したい人
- 自分のペースで暗記したい人
---

## 🚀 特徴

- オリジナル問題作成機能（文字入力、コピペ可、キャプチャ貼り付け可）
- 〇×解答機能
- 2択～4択の選択式解答機能
- 正解率の表示機能（全体および設定したカテゴリ毎）
- シンプルで直感的なUI
---

## ⚙️ 開発環境

| 項目 | 使用技術 |
|------|------------|
| 言語 | Java 21 |
| フレームワーク | Spring Boot / MyBatis / Thymeleaf / Lambok |
| IDE | Eclipse |
| Web サーバ | Apache Tomcat |
| データベース | MySQL |
| フロントエンド | HTML / CSS / JavaScript |

---

## 📁 ディレクトリ構成
```

mynote/
├── src/main/java/
│   └── com.anki.mynote
│       ├── config/
│       │   ├── WebConfig.java         # /uploads/を外部からアクセス可能にするクラス
│       ├── controller/
│       │   └── MyNoteController.java  # 各ページへの遷移と処理（ルーティング）
│       ├── entity/
│       │   ├── Category.java          # カテゴリデータモデル
│       │   ├── History.java           # 閲覧履歴データモデル
│       │   └── Quiz.java              # 問題と回答データモデル
│       ├── form/
│       │   └── MyNoteForm.java        # 問題作成登録フォームのモデル（フォーム用エンティティ）
│       ├── helper/
│       │   └── MyNoteHelper.java      # 「Form→Entity」「Entity→Form」の変換処理
│       ├── repository/
│       │   └── MyNoteMapper.java      # SQLとオブジェクトをマッピングするSQL Mapper
│       ├── service/
│       │   ├── impl/
│       │   │   └── MyNoteServiceImpl.java  # サービス実装クラス
│       │   └── MyNoteService.java          # サービスクラス
│       └── MynoteApplication.java          # Webアプリケーション起動用クラス
│   
├── src/main/resources/
│   ├── com.anki.mynote.repository
│   │   └── MyNoteMapper.xml           # Mapper用SQL XML
│   ├── templates.mynote
│   │   ├── admin.html                 # 設定/管理ページ
│   │   ├── common.html                # ヘッダー/フッター
│   │   ├── detail.html                # 問題詳細ページ
│   │   ├── form.html                  # 問題登録フォーム
│   │   ├── history.html               # 履歴閲覧ページ
│   │   ├── index.html                 # トップページ
│   │   └── list.html                  # 問題一覧ページ
│   ├── static/
│   │       ├── css/                   # スタイル定義
│   │       ├── js/                    # 各種スクリプト
│   │       └── images/                # 各種テンプレート使用画像
│   ├── data.sql                       # DB用デモデータ
│   └── schema.sql                     # DB用テーブル作成SQL
│ 
├── uploads/       # アップロード画像保存先  
└── build/         # コンパイル済みクラス

````

---

## 💾 データベース設定

使用するデータベースは **MySQL** です。  
以下の SQL を実行してデータベースとテーブルを作成してください。

````
schema.sql
data.sql
````
DB接続情報は `application-local.properties` 内で設定しています。
```
spring.application.name=mynote
spring.datasource.url=jdbc:mysql://localhost:3306/mynote?useSSL=false&serverTimezone=Asia/Tokyo
spring.datasource.username=root
spring.datasource.password="（あなたのパスワード）";
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
ActiveProfilesでサーバー用ローカル用を出し分けているので、  
ローカルでの初回起動時には `application-local.properties` を読み込むように実行構成に引数を追加する必要があります。  
プロジェクト名右クリック→実行→実行構成→引数タブを選択し、「プログラムの引数」に  
--spring.profiles.active=local  
と入力してから実行してください。  

---

## 🚀 起動方法

1. プロジェクトを Eclipse にインポート
   （File → Import → Existing Projects into Workspace）
2. Tomcat サーバにデプロイ
3. MySQL サーバを起動し、上記 SQL を実行
4. ブラウザで以下にアクセス：

```
http://localhost:8081/mynote
```

---

## 🛠️ 使用技術

- HTML5
- CSS3
- JavaScript
- Spring Framework

---

## 💻 使い方

-「勉強する」ボタン  
  → 登録した問題一覧が表示される → 表示された問題を回答する →「次の問題へすすむ」 
  
-「学習履歴をみる」ボタン    
  → いままで勉強した履歴がわかる  
  
-「問題を作成する」ボタン    
  → 覚えたい問題を新しく登録することができる  
  
-「歯車（設定）」ボタン    
  → 登録済み問題の編集ができる （表示・非表示の切り替え、内容の更新など）  

---

## 📦 インストール・ログインは不要

 Webアプリケーションのため、URLを開くだけで使用開始できます。

---

## 📌 注意点

- インターネット接続が不安定な場合は更新や登録に時間がかかる可能性があります。
- 著作権保護のため、個人利用の範囲でご利用ください。

---

## 🧑‍💻 開発向けメモ

＜今後の展開/追加機能＞
-  問題の順番入れ替え機能の追加
-  正解率の詳細表示（回数表示やよく間違える問題など）
-  管理機能の強化（変更の際のパスワード入力など）
-  問題作成フォームの強化（カメラ撮影で取り込み可など）

---

## 👩‍💻 作者情報

**Author:** Miwa  
**Language:** Japanese / 日本語

---

## 📝 ライセンス

本プロジェクトは学習・個人利用目的で自由に利用できます。  
商用利用の場合は作者への連絡を推奨します。

