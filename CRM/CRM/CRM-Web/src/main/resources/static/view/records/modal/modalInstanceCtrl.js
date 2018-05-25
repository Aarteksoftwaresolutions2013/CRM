(function() {
	'use strict';

	angular.module('CRMApp').controller('modalInstanceCtrl',
			[ '$scope','recordsService','$uibModal',

			function($scope, recordsService, $uibModal) {
				$scope.planner = function(){
					
				recordsService.exportPlanner(
						$scope.recordDto)

				.then(function(data) {
					console.log(data, "exportPlanner");
					$scope.values = data.booking;
				});
				
				}
				$scope.planner();
				
			} ]);
})();