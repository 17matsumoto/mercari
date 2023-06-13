"use strict";


//大カテゴリー情報処理
$("#bigCategoryId").change(function() {
	$("#mediamCategoryId option:nth-child(n+2)").remove();
	$("#smalCategoryId option:nth-child(n+2)").remove();
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");

	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	let param = {
		id: $("#bigCategoryId option:selected").val()
	}
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
				op.text = res[i].name + "   (" + res[i].count.toLocaleString() + "個)";
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
				op.text = res[i].name + "   (" + res[i].count.toLocaleString() + "個)";
				document.getElementById("smalCategoryId").append(op);
			}
		}
	});
});



