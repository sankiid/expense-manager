function drawIncomeChart(incomes) {
    google.charts.load("current", {packages:["corechart"]});
    hsh = {};
    for(var i=0;i < incomes.length; ++i){
        var inc = incomes[i];
        var category = inc['category']['name'];
        if (typeof(hsh[category]) != 'undefined') {
    		hsh[category] = hsh[category] + inc['amount'];
        } else {
        	hsh[category] = inc['amount'];
        }
    }
    data = [['category', 'amount']];
    for (var key in hsh) {
    	data.push([key, hsh[key]]);
    }
    console.log(data);
    var chart_data = google.visualization.arrayToDataTable(data);
    var options = {
      title: 'INCOME',
      pieHole: 0.4,
    };
    var chart = new google.visualization.PieChart(document.getElementById('income-donutchart'));
    chart.draw(chart_data, options);
}


function drawExpenseChart(expenses) {
    google.charts.load("current", {packages:["corechart"]});
    hsh = {};
    for(var i=0;i < expenses.length; ++i){
        var inc = expenses[i];
        var category = inc['category']['name'];
        if (typeof(hsh[category]) != 'undefined') {
    		hsh[category] = hsh[category] + inc['amount'];
        } else {
        	hsh[category] = inc['amount'];
        }
    }
    data = [['category', 'amount']];
    for (var key in hsh) {
    	data.push([key, hsh[key]]);
    }
    console.log(data);
    var chart_data = google.visualization.arrayToDataTable(data);
    var options = {
      title: 'EXPENSE',
      pieHole: 0.4,
    };
    var chart = new google.visualization.PieChart(document.getElementById('expense-donutchart'));
    chart.draw(chart_data, options);
}