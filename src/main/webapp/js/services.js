var app = angular.module('pfi', []);

app.controller('ModalController', function($scope, $http, $interval) {
    $scope.showModal2 = false;
    
    $scope.hide = function(m){
    	$scope.showModal2 = false;
    }
        
    $interval(function() {
		return $http.get('/pfiWs/services/status').then(function successCallback(response) {
			updatedData = response.data;
			
			if (updatedData.res != 0) {
				$scope.percentage = ((updatedData.response.progress * 100) / updatedData.response.total);
				$scope.showModal2 = true;
			} 
			
			if (updatedData.res == 0 && $scope.showModal2 == true) {
				$scope.showModal2 = false;
			}
		})
    }, 1000);
});

app.controller('General', function($scope, $http) {
	$scope.processData = function() {
		$http.get('/pfiWs/services/process').then(function(response) {
			
		});
	}
	
	$scope.getBestR = function() {
		$http.get('/pfiWs/services/getOrgPob').then(function(response) {
			$scope.dataAgrupatedByOrganism = [];
			
			angular.forEach(response.data.response.OrganismsPoblations, function(value, key) {
				var arrGammas = [], arrRPearson = [];
				angular.forEach(value, function(value, key) {
					arrGammas.push(Math.log(key));
					arrRPearson.push(value);
				});
				
				var data = { x: arrGammas, y: arrRPearson, mode : 'markers+lines', name: key }
				$scope.dataAgrupatedByOrganism.push(data);
			});
		});
	}
});

app.controller('Selectores', function($scope, $http, $interval) {
	
	$http.get('/pfiWs/services/query').then(function(response) {
		$scope.organisms = response.data;
		
		$http.get('/pfiWs/services/query/' + $scope.organisms.response[0].idOrganism).then(function(response) {
			$scope.organismsPoblations = response.data;

			$scope.dataByOrganismPoblation = []
			angular.forEach(response.data.response, function(item) {
				$scope.dataByOrganismPoblation.push({x: Math.log(item.gamma), y: item.pearsonCoefficient});
			});
			
			$scope.selectedOrganism = $scope.organisms.response[0];
			createGammaIndicator($scope.selectedOrganism.porcGC);
		});
	});
	
	$http.get('/pfiWs/services/getOrgPob').then(function(response) {
		$scope.dataAgrupatedByOrganism = [];
		
		angular.forEach(response.data.response.OrganismsPoblations, function(value, key) {
			var arrGammas = [], arrRPearson = [];
			angular.forEach(value, function(value, key) {
				arrGammas.push(Math.log(key));
				arrRPearson.push(value);
			});
			
			var data = { x: arrGammas, y: arrRPearson, mode : 'markers+lines', name: key }
			$scope.dataAgrupatedByOrganism.push(data);
		});
	});
	
	$scope.getOrganismsPoblations = function(organism) {
		waitingDialog.show('Por favor espere...');
		$scope.selectedOrganism = organism;
		createGammaIndicator(organism.porcGC);
		$http.get('/pfiWs/services/query/' + organism.idOrganism).then(function(response) {
			$scope.organismsPoblations = response.data;

			$scope.dataByOrganismPoblation = []
			angular.forEach(response.data.response, function(item) {
				$scope.dataByOrganismPoblation.push({x: Math.log(item.gamma), y: item.pearsonCoefficient});
			});
		});
		waitingDialog.hide();
	}
	$scope.getCodons = function(organismPoblation) {
		$scope.selectedOrganismPoblation = organismPoblation;
		$http.get('/pfiWs/services/query/'+$scope.selectedOrganism.idOrganism+'/'+organismPoblation.idOrganimPoblation).then(function(response) {
			var arrCalculatedCodonsPoblations = [], arrStrCalculatedCodonsPoblations = [];
			var arrCodons = [];
			var arrEnergies = [], arrStrEnergies = [];
			angular.forEach(response.data.response.CalculatedCodonPoblation, function(item) {
				arrStrCalculatedCodonsPoblations.push(('' + item.poblationLn).replace('.',','));
				arrCalculatedCodonsPoblations.push(item.poblationLn);

				arrCodons.push(item.idCodon.name);
				arrStrEnergies.push(('' + item.energy).replace('.',','));
				arrEnergies.push(item.energy);
			});

			var arrExperimentalCodonsPoblations = [], arrStrExperimentalCodonsPoblations = [];
			angular.forEach(response.data.response.ExperimentalCodonPoblation, function(item) {
				arrStrExperimentalCodonsPoblations.push(('' + item.poblationLn).replace('.',','));
				arrExperimentalCodonsPoblations.push(item.poblationLn);
			});

			var linearRegresion = findLineByLeastSquares(arrCalculatedCodonsPoblations, arrExperimentalCodonsPoblations);
			$scope.dataByGamma = [
					{
						x : arrCalculatedCodonsPoblations,
						y : arrExperimentalCodonsPoblations,
						mode : 'markers',
						text : arrCodons
					}, {
						x : linearRegresion[0],
						y : linearRegresion[1],
						mode : 'lines'
					} ]

			$scope.dataInformation = [ {
				calculated : arrStrCalculatedCodonsPoblations,
				energies : arrStrEnergies,
				experimental : arrStrExperimentalCodonsPoblations,
				codons : arrCodons
			} ]

		});
	}

	function findLineByLeastSquares(values_x, values_y) {
		var sum_x = 0;
		var sum_y = 0;
		var sum_xy = 0;
		var sum_xx = 0;
		var count = 0;

		/*
		 * We'll use those variables for faster read/write
		 * access.
		 */
		var x = 0;
		var y = 0;
		var values_length = values_x.length;

		if (values_length != values_y.length) {
			throw new Error(
					'The parameters values_x and values_y need to have same size!');
		}

		/*
		 * Nothing to do.
		 */
		if (values_length === 0) {
			return [ [], [] ];
		}

		/*
		 * Calculate the sum for each of the parts necessary.
		 */
		for (var v = 0; v < values_length; v++) {
			x = values_x[v];
			y = values_y[v];
			sum_x += x;
			sum_y += y;
			sum_xx += x * x;
			sum_xy += x * y;
			count++;
		}

		/*
		 * Calculate m and b for the formular: y = x * m + b
		 */
		var m = (count * sum_xy - sum_x * sum_y)
				/ (count * sum_xx - sum_x * sum_x);
		var b = (sum_y / count) - (m * sum_x) / count;

		/*
		 * We will make the x and y result line now
		 */
		var result_values_x = [];
		var result_values_y = [];

		for (var v = 0; v < values_length; v++) {
			x = values_x[v];
			y = x * m + b;
			result_values_x.push(x);
			result_values_y.push(y);
		}

		return [ result_values_x, result_values_y ];
	}
});

app.directive('scatterPlot', function() {

	// Create a link function
	function linkFunc(scope, element, attrs) {
		scope.$watch(attrs.data, function(plots) {
			var layout = {
				'width' : attrs.width,
				'height' : attrs.height,
				'autoScale' : false,
				'margin' : {
					't' : 50,
					'b' : 50,
					'l' : 75,
					'r' : 0
				},
				'xaxis' : {
					'title' : attrs.xaxis, 
					'showgrid' : false,
					'zeroline' : false
				},
				'yaxis' : {
					'title' : attrs.yaxis,
					'showline' : false,
				}
			};

			Plotly.newPlot(element[0], plots, layout);
		}, true);
	}

	return {
		link : linkFunc
	};
});

app.directive('scatterPlot2', function() {
	// Create a link function
	function linkFunc(scope, element, attrs) {
		scope.$watch(attrs.data, function(plots) {
			var chart = new CanvasJS.Chart(element[0], {
				zoomEnabled: true,
				panEnabled: true,
				width:1000,
				dataPointMaxWidth: 5,
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

	return {
		link : linkFunc
	};
});

app.directive('modal', function(){
    return {
        template: '<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true"><div class="modal-dialog modal-lg"><div class="modal-content" ng-transclude><div class="modal-header"><h4 class="modal-title" id="myModalLabel">Modal title</h4></div></div></div></div>', 
        restrict: 'E',
        transclude: true,
        replace:true,
        scope:{visible:'=', onSown:'&', onHide:'&'},   
        link:function postLink(scope, element, attrs){
            
            $(element).modal({
                show: false, 
                keyboard: attrs.keyboard, 
                backdrop: attrs.backdrop
            });
            
            scope.$watch(function(){return scope.visible;}, function(value){
                
                if(value == true){
                    $(element).modal('show');
                }else{
                    $(element).modal('hide');
                }
            });
            
            $(element).on('shown.bs.modal', function(){
              scope.$apply(function(){
                scope.$parent[attrs.visible] = true;
              });
            });
            
            $(element).on('shown.bs.modal', function(){
              scope.$apply(function(){
                  scope.onSown({});
              });
            });

            $(element).on('hidden.bs.modal', function(){
              scope.$apply(function(){
                scope.$parent[attrs.visible] = false;
              });
            });
            
            $(element).on('hidden.bs.modal', function(){
              scope.$apply(function(){
                  scope.onHide({});
              });
            });
        }
    };
}
);

app.directive('modalHeader', function(){
return {
    template:'<div class="modal-header"><h4 class="modal-title">{{title}}</h4></div>',
    replace:true,
    restrict: 'E',
    scope: {title:'@'}
};
});

app.directive('modalFooter', function(){
return {
    template:'<div class="modal-footer" ng-transclude></div>',
    replace:true,
    restrict: 'E',
    transclude: true
};
});