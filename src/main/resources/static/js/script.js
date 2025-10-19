/**
 * 暗記アプリ MyNote
 *  JavaScript 2025/10/15作成
 */

//**********答えの選択 ***********/
document.addEventListener("DOMContentLoaded", function () {
  const form = document.querySelector("form");
  const radios = document.querySelectorAll('input[name="questionType"]');
  const answer3Block = document.getElementById("answer3Block");
  const answer4Block = document.getElementById("answer4Block");
  const answer3Textarea = document.getElementById("answer3");
  const answer4Textarea = document.getElementById("answer4");

  // ここで初期値を保存
    let originalAnswer1 = answer1.value;
    let originalAnswer2 = answer2.value;

  function updateQuestionTypeUI() {
    const selected = document.querySelector('input[name="questionType"]:checked');
    const type = selected ? selected.value : null;

    // ラベル切り替え用クラス
    form.classList.toggle("truefalse", type === "truefalse");

    if (type === "truefalse") {
		// 元の値を保存
		  originalAnswer1 = answer1.value;
		  originalAnswer2 = answer2.value;

		  // ◯✕を自動入力
		  answer1.value = '◯';
		  answer2.value = '✕';
		  answer1.readOnly = true;
		  answer2.readOnly = true;

      // ◯✕形式なら 3・4 を強制的に非表示
      answer3Block.style.display = "none";
      answer4Block.style.display = "none";
//	  document.getElementById('answer1').readOnly = true;
//	  document.getElementById('answer2').readOnly = true;
    } else {
      // multiple形式なら常に表示（空欄でも）
      answer3Block.style.display = "block";
      answer4Block.style.display = "block";
	  // 元の値を復元
	    answer1.value = originalAnswer1;
	    answer2.value = originalAnswer2;
	    answer1.readOnly = false;
	    answer2.readOnly = false;
    }
  }

  radios.forEach(radio => {
    radio.addEventListener("change", updateQuestionTypeUI);
  });

  updateQuestionTypeUI(); // 初期表示
});



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

//ポップアップ表示
function confirmBreak() {
  document.getElementById('popup').style.display = 'block';
}

//function showPopup() {
//  document.getElementById('popup2').classList.remove('hidden');
//  
//  setTimeout(() => {
//      document.getElementById('popup2').classList.add('fadeout');
//    }, 2000);
//
//    setTimeout(() => {
//      document.getElementById('popup2').classList.add('hidden');
//      document.getElementById('popup2').classList.remove('fadeout');
//    }, 3000);
//}

function closePopup() {
  document.getElementById('popup').style.display = 'none';
  document.getElementById('popup2').classList.add('hidden');
}
//function submitHideForm() {
//  document.querySelector('form').submit(); // もしくは document.getElementById('hideForm').submit();
//}

function goToHistory() {
  window.location.href = '/mynote/history'; // 履歴ページなどに遷移
}

function showPopup(...ids) {
  ids.forEach(id => {
    const popup = document.getElementById(id);
    const message = popup?.querySelector('p, span');

    if (popup && message && message.textContent.trim() !== '') {
      popup.classList.remove('hidden');

      setTimeout(() => {
        popup.classList.add('fadeout');
      }, 2000);

      setTimeout(() => {
        popup.classList.add('hidden');
        popup.classList.remove('fadeout');
      }, 3000);
    }
  });
}
//
//function showAnswerPopup() {
//  const popup = document.getElementById('answerPopup');
//  const hasAnswer = popup?.querySelector('p');
//
//  if (hasAnswer && hasAnswer.textContent.trim() !== '') {
//    popup.classList.remove('hidden');
//
//    setTimeout(() => {
//      popup.classList.add('fadeout');
//    }, 2000);
//
//    setTimeout(() => {
//      popup.classList.add('hidden');
//      popup.classList.remove('fadeout');
//    }, 3000);
//  }
//}




//表示非表示：トグル切替
//function toggleVisibility(button) {
//    const id = button.getAttribute("data-id");
//    const isVisible = button.getAttribute("data-visible") === "true";
//    const url = `/mynote/quizzes/${isVisible ? 'hide' : 'show'}/${id}`;
//
//    fetch(url, {
//        method: 'POST',
//        headers: {
//            'X-Requested-With': 'XMLHttpRequest',
//            'Content-Type': 'application/json'
//        }
//    })
//    .then(response => {
//        if (!response.ok) throw new Error("通信失敗");
//        // 表示状態を切り替え
//        button.setAttribute("data-visible", (!isVisible).toString());
//        button.textContent = isVisible ? "表示に戻す" : "非表示にする";
//    })
//    .catch(error => {
//        alert("切り替えに失敗しました");
//        console.error(error);
//    });
//}

