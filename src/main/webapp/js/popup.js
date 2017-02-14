var app = angular.module('pfi',[]);

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

app.controller('ModalController', function($scope, $http, $interval) {
    $scope.showModal2 = false;
    
    $scope.hide = function(m){
    	$scope.showModal2 = false;
    }
        
    $interval(function() {
		return $http.get('/pfiWs/services/status').then(function successCallback(response) {
			updatedData = response.data;
			
			if (updatedData.res != 0) {
				$scope.progress = updatedData.response.progress;
				$scope.percentage = ((updatedData.response.progress * 100) / updatedData.response.total);
				$scope.totalProgress = updatedData.response.total;
				$scope.showModal2 = true;
			} 
			
			if (updatedData.res == 0 && $scope.showModal2 == true) {
				$scope.showModal2 = false;
			}
		})
    }, 1000);
});