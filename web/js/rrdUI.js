var monthName = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];

function loadAGraph(title, end, period, unit, step) {
	url = 'http://rrd.xeengvn.com/ccu/' + period + unit + "/e" + end + "/s" + step;
	insertGraph(title, url);
}
function loadToday() {
	currentTime = new Date();
	today = new Date(currentTime.getFullYear(), currentTime.getMonth(), currentTime.getDate(), 7, 0, 0, 0);
	endTime = Date.UTC(currentTime.getFullYear(), currentTime.getMonth(), currentTime.getDate(), 7, 0, 0, 0)/1000 + 3600*24 + currentTime.getTimezoneOffset()*60;
	loadAGraph("Hôm nay (" + today + ")", endTime, 24, 'h', 3600);
}

function loadYesterday() {
	currentTime = new Date();
	today = new Date(currentTime.getFullYear(), currentTime.getMonth(), currentTime.getDate(), 7, 0, 0, 0);
	yesterday = new Date(currentTime.getFullYear(), currentTime.getMonth(), currentTime.getDate(), 7, 0, 0, 0);
	yesterday.setDate(today.getDate() - 1);
	endTime = Date.UTC(currentTime.getFullYear(), currentTime.getMonth(), currentTime.getDate(), 7, 0, 0, 0)/1000 + currentTime.getTimezoneOffset()*60;
	loadAGraph("Hôm qua (" + yesterday + ")", endTime, 1, 'd', 3600*24);
}

function loadBeforeYesterday() {
	currentTime = new Date();
	yesterday = new Date(currentTime.getFullYear(), currentTime.getMonth(), currentTime.getDate(), 7, 0, 0, 0);
	yesterday.setDate(yesterday.getDate() - 1);
	bfYesterday = new Date(currentTime.getFullYear(), currentTime.getMonth(), currentTime.getDate(), 7, 0, 0, 0);
	bfYesterday.setDate(yesterday.getDate() - 1);
	endTime = Date.UTC(yesterday.getFullYear(), yesterday.getMonth(), yesterday.getDate(), 7, 0, 0, 0)/1000 + yesterday.getTimezoneOffset()*60;
	loadAGraph("Hôm kia (" + bfYesterday + ")", endTime, 1, 'd', 3600*24);
}

function loadThisMon() {
	currentTime = new Date();
	thisMon = new Date(currentTime.getFullYear(), currentTime.getMonth(), 1, 7, 0, 0, 0);
	nextMon = new Date(currentTime.getFullYear(), currentTime.getMonth(), 1, 7, 0, 0, 0);
	nextMon.setMonth(thisMon.getMonth() + 1);
	endTime = Date.UTC(nextMon.getFullYear(), nextMon.getMonth(), nextMon.getDate(), 7, 0, 0, 0)/1000 + currentTime.getTimezoneOffset()*60;
	loadAGraph("Tháng này (" + thisMon.getFullYear() + "/" + monthName[thisMon.getMonth()] + ")", endTime, 1, 'mon', 3600*24);
}

function loadLastMon() {
	currentTime = new Date();
	thisMon = new Date(currentTime.getFullYear(), currentTime.getMonth(), 1, 7, 0, 0, 0);
	lastMon = new Date(currentTime.getFullYear(), currentTime.getMonth(), 1, 7, 0, 0, 0);
	lastMon.setMonth(thisMon.getMonth() - 1);
	endTime = Date.UTC(thisMon.getFullYear(), thisMon.getMonth(), thisMon.getDate(), 7, 0, 0, 0)/1000 + currentTime.getTimezoneOffset()*60;
	loadAGraph("Tháng trước (" + lastMon.getFullYear() + "/" + monthName[lastMon.getMonth()] + ")", endTime, 1, 'mon', 3600*24);
}

function loadGraphs() {
	loadToday();
	loadYesterday();
	loadBeforeYesterday();
	loadThisMon();
	loadLastMon();
}

function toggle(id) {
	var obj = document.getElementById(id);
	if(obj) {
		if(obj.style.display && obj.style.display == 'block') 
			obj.style.display = 'none';
		else 
			obj.style.display = 'block';
	}
	else {
		alert('Obj[' + id + '] = ' + obj);
	}
}

function removeThis() {
	var rootObj = document.getElementById("root");
	if(confirm("Có chắc bạn muốn xóa graph này không ?")) {
		rootObj.removeChild(this);
	}
}
function insertGraph(title, url) {
	var rootObj = document.getElementById('root');
	var aDiv = document.createElement("div");
	rootObj.appendChild(aDiv);
	aDiv.className = "graphContainer";
	aDiv.onclick = removeThis;
	aDiv.innerHTML = "<img class='graph' src='" + url + "'/>" + "<span class='gTitle'>" + title + "</span>";
}
function genGraph() {
	var endDate = document.getElementById('end_date').value;
	var endTime = document.getElementById('end_time').value;
	var period = document.getElementById('period').value;
	var unit = document.getElementById('unit').value;
	var datestring= endDate;
	var offset = new Date().getTimezoneOffset();
	var url = "";
	var step = 3600;
	if(unit == 'h') {
		if(endTime == null || endTime == undefined || endTime == '') {
			endTime = "00:00";
		}
	}
	else {
		endTime = "07:00";
		step = step * 24;
	}
	datestring = datestring + " " + endTime;
	
	var parts = datestring.match(/(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2})/);
	var endUTC = Date.UTC(+parts[1], parts[2]-1, +parts[3], +parts[4], +parts[5])/1000 + offset*60;

	url = 'http://rrd.xeengvn.com/ccu/' +period + unit + "/e" + endUTC + "/s" + step;
	insertGraph("FROM:[" + datestring + " - " + period + unit + "] TO:[" + datestring + "]", url);
}
function unitChange() {
	var startTimeObj = document.getElementById('end_time');
	var unit = document.getElementById('unit').value;
	if( unit === 'h' )
		startTimeObj.disabled = false;
	else startTimeObj.disabled = true;
}
