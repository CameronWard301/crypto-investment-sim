// noinspection JSUnresolvedVariable,JSUnresolvedFunction

google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic(){
    let data = new google.visualization.DataTable();
    data.addColumn('datetime', 'Date');
    data.addColumn('number', 'Value');
    let chartDataArray = [];
    let chartData = JSON.parse($('#chartData').val());
    for (const dataLog of chartData["history"]){
        let data = [];
        let dateObject = parseDate(dataLog["timestamp"])
        data.push(dateObject);
        data.push(dataLog["portfolioTotal"]);
        chartDataArray.push(data);
    }
    data.addRows(chartDataArray)

    let options = {
        hAxis: {
            title: 'Date',
            format: 'HH:00 dd/MM/yyyy'
        },
        vAxis: {
            title: 'Value in £'
        },
        legend: 'none',
        series: {
            0: {color: '#e09811'}
        },
        width: '100%',
        pointSize: 7,
        explorer: { actions: ['dragToZoom', 'rightClickToReset'] },
    };

    let chart = new google.visualization.LineChart(document.getElementById('chart'));

    chart.draw(data, options);
}

/**
 * Creates a new date object to the hour
 * @param dateString
 * @returns {Date}
 */
function parseDate(dateString) {
    let DateTime = dateString.split(" ");
    let theDate = DateTime[0].split("-")
    let theTime = DateTime[1].split(":")
    return new Date(parseInt(theDate[0]), parseInt(theDate[1]) - 1, parseInt(theDate[2]), parseInt(theTime[0]), 0, 0, 0)

}