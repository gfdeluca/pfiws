var app = angular.module('pfi', []);

app.controller('Selectores', function($scope, $http, $interval) {	
	$http.get('/pfiWs/services/query/1').then(function(response) {
		$scope.organismsPoblations = response.data;

		$scope.dataByOrganismPoblation = []
		angular.forEach(response.data.response, function(item) {
			$scope.dataByOrganismPoblation.push({x: Math.log(item.gamma), y: item.pearsonCoefficient});
		});
//		var arrOrganismsPoblationsGamma = [], arrOrganismsPoblationsPearson = [];
//		angular.forEach(response.data.response, function(item) {
//			arrOrganismsPoblationsGamma.push(Math.log(item.gamma));
//			arrOrganismsPoblationsPearson.push(item.pearsonCoefficient);
//		});
//									
//		$scope.dataByOrganismPoblation = [ {
//			x : arrOrganismsPoblationsGamma,
//			y : arrOrganismsPoblationsPearson,
//			type : 'line'
//		} ]
	});
});

app.directive('scatterPlot2', function() {
	// Create a link function
	function linkFunc(scope, element, attrs) {
		scope.$watch(attrs.data, function(plots) {
			var chart = new CanvasJS.Chart(element[0], {
				zoomEnabled: true,
				panEnabled: true,
				axisX:{
					title : attrs.xaxis,
					interlacedColor: "#F0F8FF"
				},
				axisY:{
					title : attrs.yaxis,
					gridColor: "black" ,
			        gridThickness: 2
				},
				legend: {
					horizontalAlign: "right",
					verticalAlign: "center"
				},
				axisY:{
					includeZero: false
				},
				data: [
				       {
				    	   type: "line",
				    	   dataPoints: plots
				       }
				       ]
	          	});
	 
	          chart.render();

		}, true);
	}

	// Return this function for linking ...
	return {
		link : linkFunc
	};
});