var t;
var mybool = true;
var info = "";
var serverX = [], serverY1 = [], y2 = [];
var d1 = [];

function selectCallback(xaxis) {
	clearTimeout(t);
	t = setTimeout(showTable, 1000, xaxis);

}

function showTable(xaxis) {
	errInfo.innerHTML += "left: " + xaxis.min + " right: " + xaxis.max;
	if (xaxis.min == undefined)
		return;
	$("tr").hide();
	$("#head").show();
	$("tr").each(function() {
		trId = $(this).attr("id");

		if (trId >= xaxis.min && trId <= xaxis.max)
			$(this).show();

	});

}

function myajax() {
	// var JSONObject = [ "zhaojian", "11", ];
	$.ajax({
		url : '/logview/mycontrol/getjson/second',
		type : 'GET',
		async : false,
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

			errInfo.innerHTML += "sucess ";

			errInfo.innerHTML += data + "<br/>";
			tobeIter = data;

			for (x in tobeIter) {
				errInfo.innerHTML += x + ":" + tobeIter[x] + "<br/>";
			}

			for (i in data.countsY) {
				d1.push([ data.timeX[i], data.countsY[i] ]);
			}
		}
	})
}

(function basic_time(container) {

	var options, o;

	/*
	 * for (i = 0; i < 100; i++) { x = start+(i*1000*3600*24*36.5); d1.push([x,
	 * i+Math.random()*30+Math.sin(i/20+Math.random()*2)*20+Math.sin(i/10+Math.random())*10]); }
	 */

	myajax();
	options = {
		/*
		 * points : { show : true },
		 */
		mouse : {
			relative : true,
			track : true
		},
		bars : {
			barWidth:100,
			show : true,
			shadowSize : 0
		},
		xaxis : {
			mode : 'time',
			labelsAngle : 45,
			noTicks : 12
		},
		yaxis : {

			autoscale : true
		},
		selection : {
			mode : 'x'
		},
		HtmlText : false,
		title : 'Time'
	};

	// Draw graph with default options, overwriting with passed options
	function drawGraph(opts) {

		// Clone the options, so the 'options' variable always keeps intact.
		o = Flotr._.extend(Flotr._.clone(options), opts || {});

		selectCallback(o.xaxis);
		// Return a new graph.
		return Flotr.draw(container, [ d1 ], o);
	}

	graph = drawGraph();

	Flotr.EventAdapter.observe(container, 'flotr:select', function(area) {
		// Draw selected area
		graph = drawGraph({
			xaxis : {
				min : area.x1,
				max : area.x2,
				mode : 'time',
				labelsAngle : 45,
				noTicks : 12

			},
			yaxis : {
				min : area.y1,
				max : area.y2, 
				autoscale : true
			}
		});
	});

	// When graph is clicked, draw the graph with default area.
	Flotr.EventAdapter.observe(container, 'flotr:click', function() {
		graph = drawGraph();
	});
})(document.getElementById("container"));

/*
 * (function() {
 * 
 * var container = document.getElementById('container'), data, detail,
 * detailOptions, summary, summaryOptions, vis, selection; // Data Format: data = [ [
 * x, y1 ], // First Series // [x, y2] // Second Series ]; // Sample the sine
 * function for data // var start = new Date("2009/01/01 01:00").getTime(); var
 * step = 1; var num = 18;
 * 
 * for (var i = 0; i < num * step; i = i + step){ var myDate = new Date();
 * myDate.setDate(myDate.getDate()+i); x.push(myDate); } alert(x); while
 * (y1.length < num) { var tmp = parseInt(Math.random() * 100) % num; var j = 0;
 * for (j = 0; j < y1.length; j++) { if (y1[j] == tmp) break; } if (j ==
 * y1.length) y1.push(tmp); } // Configuration for detail: detailOptions = {
 * name : 'detail', data : data, height : 150, // Flotr Configuration config : {
 * bars : { show : true, shadowSize : 0, barWidth : 0.5 }, mouse : { track :
 * true, trackY : false, trackAll : true, sensibility : 1, trackDecimals : 4,
 * position : 'ne', }, xaxis : { noTicks : 5, showLabels : true, mode : 'time', //
 * labelsAngle : 45 }, yaxis : { autoscale : true, autoscaleMargin : 0.05,
 * noTicks : 4, showLabels : true, }, title : 'log viewer', }, }; //
 * Configuration for summary: summaryOptions = { name : 'summary', data : data,
 * height : 50, // Flotr Configuration config : { 'lite-lines' : { show : true,
 * lineWidth : 1, fill : true, fillOpacity : 0.2, fillBorder : true, }, xaxis : {
 * noTicks : 5, autoscale : true, // autoscaleMargin : 0.5, showLabels : true,
 * mode : 'time', // labelsAngle : 45, // max: 200 }, yaxis : { autoscale :
 * true, autoscaleMargin : 0.1, }, handles : { show : true, }, selection : {
 * mode : 'x', }, grid : { verticalLines : false, } }, }; // Building a custom
 * vis: vis = new envision.Visualization(); detail = new
 * envision.Component(detailOptions); summary = new
 * envision.Component(summaryOptions); interaction = new envision.Interaction(); //
 * Render Visualization vis.add(detail).add(summary).render(container); //
 * Wireup Interaction interaction.leader(summary).follower(detail).add(
 * envision.actions.selection, { callback : selectCallback });
 * 
 * 
 * 
 * 
 * 
 * })();
 */