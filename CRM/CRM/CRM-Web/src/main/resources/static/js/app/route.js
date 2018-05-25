(function() {
    angular
	    .module('CRMApp')
	    .config(
		    [
			    '$routeProvider',
			    '$locationProvider',
			    '$httpProvider',
			    '$compileProvider',
			    function($routeProvider, $locationProvider,
				    $httpProvider, $compileProvider) {
				var interceptor = [
					'$q',
					'$rootScope',
					function($q, $rootScope) {
					    var service = {
						'request' : function(config,
							data, a) {
						    angular
							    .element(
								    document
									    .getElementById("loader"))
							    .css(
								    {
									display : "block"
								    });
						    return config;
						},
						"response" : function(config) {
						    angular
							    .element(
								    document
									    .getElementById("loader"))
							    .css(
								    {
									display : "none"
								    });
						    return config;
						},
						"responseError" : function(
							config) {
						    angular
							    .element(
								    document
									    .getElementById("loader"))
							    .css(
								    {
									display : "none"
								    });
						    return config;
						},
						"requestError" : function(
							config) {
						    angular
							    .element(
								    document
									    .getElementById("loader"))
							    .css(
								    {
									display : "none"
								    });
						    return config;
						}
					    };
					    return service;
					} ];
				$compileProvider
					.imgSrcSanitizationWhitelist(/^\s*(https?|ftp|file|blob):|data:image\//);
				$httpProvider.interceptors.push(interceptor);
				$httpProvider.defaults.useXDomain = true;
				$routeProvider
					.when(
						'/',
						{
						    templateUrl : 'view/login/login.html',
						    controller : 'loginCtrl'
						})
					.when(
						'/dashboard',
						{
						    templateUrl : 'view/dashboard/dashboard.html',
						    controller : 'dashboardCtrl'
						})
					.when(
						'/addUser',
						{
						    templateUrl : 'view/addUser/addUser.html',
						    controller : 'addUserCtrl'
						})
					.when(
						'/addSales',
						{
						    templateUrl : 'view/addSales/addSales.html',
						    controller : 'addSalesCtrl'
						})
					.when(
						'/records/:bookbid/:index/:firstRender',
						{
						    templateUrl : 'view/records/records.html',
						    controller : 'recordsCtrl'
						})
					.when(
						'/calendarPage',
						{
						    templateUrl : 'view/calendarPage/calendarPage.html',
						    controller : 'calendarPageCtrl'
						})
					.when(
						'/reporting',
						{
						    templateUrl : 'view/reporting/reporting.html',
						    controller : 'reportingCtrl'
						})
					.when(
						'/addLocation',
						{
						    templateUrl : 'view/addLocation/addLocation.html',
						    controller : 'addLocationCtrl'
						})
						.when(
								'/addDate',
								{
					templateUrl : 'view/addDate/addDate.html',
					controller : 'addDateCtrl'
								})
					.otherwise(
						{
						    redirectTo : '/'
						});
				$locationProvider.html5Mode(false);
			    } ]).run(
		    function($sessionStorage, $http, $rootScope, $location,
			    $sessionStorage) {

			$rootScope.$on("$locationChangeStart", function(event,
				next, current) {
			    if ($sessionStorage['user'] == null) {
				$location.path("/");
			    }
			});
		    });
})();