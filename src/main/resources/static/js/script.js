/**
 * 暗記アプリ MyNote
 *  JavaScript 2025/10/15作成
 */

//**********答えの選択 ***********/
function toggleAnswers(isChoice) {
  document.getElementById("answers").style.display = isChoice ? "block" : "none";
  document.getElementById("truefalse").style.display = isChoice ? "none" : "block";
}

//**********画像登録処理 ***********/
const preview = document.getElementById('preview');
const imageInput = document.getElementById('imageInput');

// ファイル選択時のプレビュー
imageInput.addEventListener('change', function () {
  const file = this.files[0];
  if (file && file.type.startsWith('image/')) {
    const reader = new FileReader();
    reader.onload = function (e) {
      preview.src = e.target.result;
      preview.style.display = 'block';
    };
    reader.readAsDataURL(file);
  }
});

// ペースト時のプレビュー
document.getElementById('paste_zone').addEventListener('paste', function (e) {
  const items = e.clipboardData.items;
  for (let item of items) {
    if (item.type.indexOf("image") !== -1) {
      const blob = item.getAsFile();
      preview.src = URL.createObjectURL(blob);
      preview.style.display = 'block';

      // ペーストされた画像を input に設定（送信可能にする）
      const dataTransfer = new DataTransfer();
      dataTransfer.items.add(blob);
      imageInput.files = dataTransfer.files;
    }
  }
});