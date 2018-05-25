(function() {
	'use strict';
	angular.module('CRMApp').directive('headerDirective', function(){
		var headerContent = {
				restrict : 'E',
				templateUrl : 'js/directive/header/header.html',
				controller : 'headerCtrl'
			};
			return headerContent;
	});
	
})();
