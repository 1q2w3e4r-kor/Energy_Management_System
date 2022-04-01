package graphHtmlString;

import java.util.List;

import dto.SmartPlugDTO;

public class GoogleChartReduction {

	public String getReductionChart(List<SmartPlugDTO> smartPlugDTO, List<SmartPlugDTO> reductionSmartPlugDTO,
			String title) {
		// TODO Auto-generated method stub
		String htmlString = "<html>\r\n" + "<head>\r\n" + "<!-- googleChart.js -->\r\n"
				+ "<script type=\"text/javascript\"\r\n"
				+ "	src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n"
				+ "<script src=\"https://code.jquery.com/jquery-3.3.1.min.js\"></script>\r\n" + "<script\r\n"
				+ "	src=\"https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js\"></script>\r\n"
				+ "<!-- googleChart.js script -->\r\n" + "<script type=\"text/javascript\">\r\n"
				+ "	// Load the Visualization API and the controls package.\r\n"
				+ "	google.charts.load('current', {\r\n" + "		'packages' : [ 'corechart', 'controls' ]\r\n"
				+ "	});\r\n" + "	// Set a callback to run when the Google Visualization API is loaded.\r\n"
				+ "	google.charts.setOnLoadCallback(drawDashboard);\r\n"
				+ "	// Callback that creates and populates a data table,\r\n"
				+ "	// instantiates a dashboard, a range slider and a pie chart,\r\n"
				+ "	// passes in the data and draws it.\r\n" + "	function drawDashboard() {\r\n"
				+ "		//날짜형식 변경하고 싶으시면 이 부분 수정하세요.\r\n" + "		var chartDateformat = 'yyyy-MM-dd/HH:mm';\r\n"
				+ "		//라인차트의 라인 수\r\n" + "		var chartLineCount = 30;\r\n" + "		//컨트롤러 바 차트의 라인 수\r\n"
				+ "		var controlLineCount = 30;\r\n" + "		// Create our data table.\r\n"
				+ "		var data = new google.visualization.DataTable();\r\n" + "		//그래프에 표시할 컬럼 추가\r\n"
				+ "		data.addColumn('datetime', 'Date');\r\n" + "		data.addColumn('number', 'Total Used');\r\n"
				+ "		data.addColumn('number', 'Total Reduction Used')\r\n"
				+ "		data.addColumn('number', 'Reduction Rate')\r\n" + "		//그래프에 표시할 데이터\r\n"
				+ "		var dataRow = [];\r\n";
		for (int i = 0; i < smartPlugDTO.size(); i++) {
			String date = smartPlugDTO.get(i).getDatetime().replace(" ", "T");
			Double totalAmpData = smartPlugDTO.get(i).getAmp() * 220;
			Double totalReductionAmpData = reductionSmartPlugDTO.get(i).getAmp() * 220;
			Double rate = ((totalAmpData - totalReductionAmpData) / totalAmpData) * 100;
			htmlString += " 		dataRow=[new Date('" + date + "'), " + totalAmpData + ", " + totalReductionAmpData + ", "
					+ rate + "]\r\n" + " 		data.addRow(dataRow);\r\n";
		}
		htmlString += "	// Create a dashboard.\r\n"
				+ "		var dashboard = new google.visualization.Dashboard(document\r\n"
				+ "				.getElementById('dashboard_div'));\r\n"
				+ "		// Create a range slider, passing some options\r\n"
				+ "		var control = new google.visualization.ControlWrapper({\r\n"
				+ "			'controlType' : 'ChartRangeFilter',\r\n" + "			'containerId' : 'filter_div',\r\n"
				+ "			'options' : {\r\n" + "				'ui' : {\r\n"
				+ "					'chartType' : 'LineChart',\r\n" + "					'chartOptions' : {\r\n"
				+ "						'chartArea' : {\r\n" + "							'width' : '60%',\r\n"
				+ "							'height' : 80\r\n" + "						},\r\n"
				+ "						'hAxis' : {\r\n" + "							'baselineColor' : 'none',\r\n"
				+ "							format : chartDateformat,\r\n"
				+ "							textStyle : {\r\n" + "								fontSize : 12\r\n"
				+ "							},\r\n" + "							gridlines : {\r\n"
				+ "								units : {\r\n" + "									years : {\r\n"
				+ "										format : [ 'yyyy년' ]\r\n"
				+ "									},\r\n" + "									months : {\r\n"
				+ "										format : [ 'MM월' ]\r\n"
				+ "									},\r\n" + "									days : {\r\n"
				+ "										format : [ 'dd일' ]\r\n"
				+ "									},\r\n" + "									hours : {\r\n"
				+ "										format : [ 'HH시' ]\r\n"
				+ "									}\r\n" + "								}\r\n"
				+ "							}\r\n" + "						}\r\n" + "					}\r\n"
				+ "				},\r\n" + "				filterColumnIndex : 0\r\n" + "			}\r\n" + "		});\r\n"
				+ "		// Create a pie chart, passing some options\r\n"
				+ "		var chart = new google.visualization.ChartWrapper({\r\n"
				+ "			'chartType' : 'LineChart',\r\n" + "			'containerId' : 'chart_div',\r\n"
				+ "			'options' : {\r\n";
		htmlString += "	 title : '" + title + "',\r\n" + "				isStacked : 'percent',\r\n"
				+ "				focusTarget : 'category',\r\n" + "				height : 500,\r\n"
				+ "				width : '100%',\r\n" + "				legend : {\r\n"
				+ "					position : \"top\",\r\n" + "					textStyle : {\r\n"
				+ "						fontSize : 13\r\n" + "					}\r\n" + "				},\r\n"
				+ "				pointSize : 5,\r\n" + "				tooltip : {\r\n"
				+ "					textStyle : {\r\n" + "						fontSize : 12\r\n"
				+ "					},\r\n" + "					showColorCode : true,\r\n"
				+ "					trigger : 'both'\r\n" + "				},\r\n" + "				hAxis : {\r\n"
				+ "					format : chartDateformat,\r\n" + "					gridlines : {\r\n"
				+ "						count : chartLineCount,\r\n" + "						units : {\r\n"
				+ "							years : {\r\n" + "								format : [ 'yyyy년' ]\r\n"
				+ "							},\r\n" + "							months : {\r\n"
				+ "								format : [ 'MM월' ]\r\n" + "							},\r\n"
				+ "							days : {\r\n" + "								format : [ 'dd일' ]\r\n"
				+ "							},\r\n" + "							hours : {\r\n"
				+ "								format : [ 'HH시' ]\r\n" + "							}\r\n"
				+ "						}\r\n" + "					},\r\n" + "					textStyle : {\r\n"
				+ "						fontSize : 12\r\n" + "					}\r\n" + "				},\r\n"
				+ "				vAxis : {\r\n" + "					minValue : 0.5,\r\n"
				+ "					viewWindow : {\r\n" + "						min : 0\r\n"
				+ "					},\r\n" + "					gridlines : {\r\n"
				+ "						count : -1\r\n" + "					},\r\n"
				+ "					textStyle : {\r\n" + "						fontSize : 12\r\n"
				+ "					}\r\n" + "				},\r\n" + "				annotations : {\r\n"
				+ "					pattern : chartDateformat,\r\n" + "					textStyle : {\r\n"
				+ "						fontSize : 15,\r\n" + "						bold : true,\r\n"
				+ "						italic : true,\r\n" + "						color : '#871b47',\r\n"
				+ "						auraColor : '#d799ae',\r\n" + "						opacity : 0.8,\r\n"
				+ "						pattern : chartDateformat\r\n" + "					}\r\n"
				+ "				}\r\n" + "			}\r\n" + "		});\r\n"
				+ "		// Establish dependencies, declaring that 'filter' drives 'pieChart',\r\n"
				+ "		// so that the pie chart will only display entries that are let through\r\n"
				+ "		// given the chosen slider range.\r\n" + "		dashboard.bind(control, chart);\r\n"
				+ "		// Draw the dashboard.\r\n" + "		dashboard.draw(data);\r\n" + "	}\r\n" + "</script>\r\n"
				+ "</head>\r\n" + "<body>\r\n" + "	<!-- makeGraph -->\r\n"
				+ "	<!--Div that will hold the dashboard-->\r\n" + "	<div id=\"dashboard_div\">\r\n"
				+ "		<!--Divs that will hold each control and chart-->\r\n"
				+ "		<div id=\"chart_div\" style=\"padding: 0px 20px 0px 0px;\"></div>\r\n"
				+ "		<div id=\"filter_div\" style=\"padding: 0px 20px 0px 0px;\"></div>\r\n" + "	</div>\r\n"
				+ "</body>\r\n" + "</html>";
		return htmlString;
	}

}
