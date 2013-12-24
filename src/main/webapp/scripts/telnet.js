function getInfo() {

	$("tr").each(function() {
		trId = $(this).attr("id");

		myajax(trId);

	});
}

function myajax(bayName) {
	// var JSONObject = [ "zhaojian", "11", ];
	var ajaxURL = '/logview/telnet/getjson/' + bayName;

	$.ajax({
		url : ajaxURL,
		type : 'GET',
		async : true,
		// contentType : "application/json; charset=utf-8",
		dataType : "json",
		// data : JSON.stringify(JSONObject),
		error : function(jqXHR, textStatus, errorThrown) {

			// alert(jqXHR.status + ' ' + jqXHR.responseText);
			var errInfo = document.getElementById("errInfo");
			tobeIter = jqXHR;
			errInfo.innerHTML = "error";
			for (x in tobeIter) {
				errInfo.innerHTML += x + ":" + tobeIter[x] + "<br/>";
			}
			errInfo.innerHTML += "textStatus :::" + textStatus;
		},
		success : function(data) {

			tobeIter = data;
			$("#" + bayName).empty();
			for (x in tobeIter) {
				errInfo.innerHTML += x + ":" + tobeIter[x] + "<br/>";
				if (bayName == "head")
					$("#" + bayName).append("<th>" + tobeIter[x] + "</th>");
				else
					$("#" + bayName).append("<td>" + tobeIter[x] + "</td>");
			}

		}
	});
}
