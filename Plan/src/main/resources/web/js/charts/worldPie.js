function worldPie(id, worldSeries, gmSeries) {
    Highcharts.chart(id, {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {text: ''},
        subtitle: {
            text: 'Click the slices to view used GameMode'
        },
        plotOptions: {
            pie: {
              allowPointSelect: true,
              cursor: 'pointer',
              dataLabels: {
                enabled: false
              },
              showInLegend: true
            }
        },
        tooltip: {
            formatter: function() {
                return '<b>'+this.point.name+':</b> ' + formatTimeAmount(this.y) + ' ('+this.percentage.toFixed(2)+'%)';
            }
        },
        series: [worldSeries],
        drilldown: {
            series: gmSeries
        }
    });
}

function formatTimeAmount(ms) {
    var out = "";

    var seconds = Math.floor(ms / 1000);

    var dd = Math.floor(seconds / 86400);
    seconds -= (dd * 86400);
    var dh = Math.floor(seconds / 3600);
    seconds -= (dh * 3600);
    var dm = Math.floor(seconds / 60);
    seconds -= (dm * 60);
    seconds = Math.floor(seconds);
    if (dd !== 0) {
        out += dd.toString() + "d ";
    }
    if (dh !== 0) {
        out += dh.toString() + "h ";
    }
    if (dm !== 0) {
        out += dm.toString() + "m ";
    }
    out += seconds.toString() + "s ";

    return out;
}