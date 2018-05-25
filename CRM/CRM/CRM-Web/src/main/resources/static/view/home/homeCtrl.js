(function() {

    'use strict';

    angular
	    .module('CRMApp')
	    .controller(
		    'homeCtrl', 
		    [
			    '$rootScope',
			    '$scope',
			    'dashboardService',
			    '$localStorage',
			    function($rootScope, $scope, dashboardService, $localStorage) {

				$rootScope.Login = false;

			    } ]);
})();