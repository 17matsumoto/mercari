"use strict";


//大カテゴリー情報処理
$("#bigCategory").click(function() {
	console.log("通ってるよね？");
	$("#bigCategory ").remove();
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");

	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	let param = {
		id: $("#bigCategory option:selected").val()
	}
	//大カテゴリーコード送信URL
	//let send_url = "/item/searchCategory";
	$.ajax({
		url: "/category/searchMediamCategory",
		type: "POST",
		contentType: "application/json",
		cache: false,

		data: JSON.stringify(param),
		dataType: "json",
		success: function(res) {

			for (let i = 0; i < res.length; i++) {
				let op = document.createElement("option");
				op.value = res[i].id;
				op.text = res[i].name + "   (" + res[i].medCount.toLocaleString() + "個)";
				document.getElementById("mediamCategoryId").append(op);
			}
		}
	});
});

//中カテゴリー情報処理
$("#mediamCategoryId").change(function() {
	$("#smalCategoryId option:nth-child(n+2)").remove();
	//入力値
	let param = {
		id: $("#mediamCategoryId option:selected").val()
	}
	//データの送信先
	//let send_url = "/searchSmalCategory";　//----②
	$.ajax({
		url: "/category/searchSmalCategory",
		type: "POST",
		contentType: "application/json",
		cache: false,
		data: JSON.stringify(param),
		dataType: "json",
		success: function(res) {
			for (let i = 0; i < res.length; i++) {
				let op = document.createElement("option");
				op.value = res[i].id;
				op.text = res[i].name + "   (" + res[i].smalCount.toLocaleString() + "個)";
				document.getElementById("smalCategoryId").append(op);
			}
		}
	});
});



