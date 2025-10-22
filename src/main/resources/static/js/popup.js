/****************************
   暗記アプリ MyNote
   JavaScript 2025/10/22更新
   popup.js
*******************************/

//********** ポップアップ表示 ***********/
function confirmBreak() {
  document.getElementById('popup').style.display = 'block';
}

function closePopup() {
  document.getElementById('popup').style.display = 'none';
  document.getElementById('popup2').classList.add('hidden');
}

function goToHistory() {
  window.location.href = '/mynote/history'; // 履歴ページなどに遷移
}

function showPopup(...ids) {
  ids.forEach(id => {
    const popup = document.getElementById(id);
    const message = popup?.querySelector('p, span');
	console.log('popup:', popup);
	console.log('message:', message?.textContent);


    if (popup && message && message.textContent.trim() !== '') {
      popup.classList.remove('hidden');
	  console.log('表示されました');

      setTimeout(() => {
		console.log('フェードアウト開始');
        popup.classList.add('fadeout');
      }, 2000);

      setTimeout(() => {
        popup.classList.add('hidden');
        popup.classList.remove('fadeout');
		console.log('非表示完了');
      }, 3000);
    } else {
		console.log('条件に合わず表示されません');
	}
  });
}


//********** 登録フォーム確認ポップアップ表示 ***********/
	function handleConfirm() {
	  const question = document.querySelector("textarea[name='question']").value.trim();
	  const answer1 = document.querySelector("textarea[name='answer1']").value.trim();
	  const answer2 = document.querySelector("textarea[name='answer2']").value.trim();
	  const category = document.querySelector("#categoryId").value;
	  const correctAns = document.querySelector("input[name='correctAns']:checked");

	  if (!question || !answer1 || !answer2 || !category || !correctAns) {
	    alert("必須項目が未入力です。入力内容を確認してください。");
	    return;
	  }

	  // 内容を埋める
	  const confirmContent = document.getElementById("confirmContent");
	  confirmContent.innerHTML = `
	    <p><strong>問題文：</strong>${question}</p>
	    <p><strong>カテゴリ：</strong>${document.querySelector("#categoryId option:checked").textContent}</p>
	    <p><strong>回答1：</strong>${answer1}</p>
	    <p><strong>回答2：</strong>${answer2}</p>
	    ${document.querySelector("textarea[name='answer3']").value ? `<p><strong>回答3：</strong>${document.querySelector("textarea[name='answer3']").value}</p>` : ""}
	    ${document.querySelector("textarea[name='answer4']").value ? `<p><strong>回答4：</strong>${document.querySelector("textarea[name='answer4']").value}</p>` : ""}
	    <p><strong>正解：</strong>回答${correctAns.value}</p>
	  `;

	  // 画像プレビュー
	  const fileInput = document.getElementById("imageInput");
	  const confirmImage = document.getElementById("confirmImage");
	  if (fileInput.files && fileInput.files[0]) {
	    const reader = new FileReader();
	    reader.onload = function (e) {
	      confirmImage.src = e.target.result;
	      confirmImage.style.display = "block";
	    };
	    reader.readAsDataURL(fileInput.files[0]);
	  } else {
	    confirmImage.style.display = "none";
	  }

	  // ポップアップ表示
	  document.getElementById("confirmPopup").classList.remove("hidden");
	}

function cancelPopup() {
  document.getElementById("confirmPopup").classList.add("hidden");
}

function submitForm() {
  document.querySelector(".edit-form form").submit();
}


