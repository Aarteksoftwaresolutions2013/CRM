(function() {
	'use strict';

	angular.module('CRMApp').controller('headerCtrl',
			[ '$scope', '$sessionStorage', '$location','$rootScope', 'dashboardService', 
				function($scope, $sessionStorage, $location, $rootScope, dashboardService) {

				console.log("Header");
				$scope.recordId = "";
				$rootScope.Login = false;

				$scope.dashboardFilterdto = {
				    limit : 1,
				    trainingLocation : "Show All",
				    year : "Show All",
				    dates : "Show All",
				    trainingBookingEnquiry : "Show All",
				    schoolNames : "",
				    fullName : "",
				    course : "All courses",
				    courseBookingEnquiry : "Show All",
				    software : "Show All",
				    softwareBookingEnquiry : "Show All",
				    months : "Show All",
				    notConfirmedLocations : "Show All",
				    notConfirmedInHouse : "Show All",
				    payedBy : 'Show All',
				    invoiceNumberDummy : "",
				    email : '',
				    telephone : '',
				    status : 0,
				    noOfItems : 1,
				    pageNo : 1
				}
				$rootScope.userName  = localStorage.getItem('userName');
				$scope.logout = function() {
					delete $sessionStorage.user;
					$location.path('/');
					
				}
				
				$scope.getBookingLatestRecord = function() {
				    //$scope.getCountOfAllBooking();
				    dashboardService
					    .getBookingSingleRecord(
						    $scope.dashboardFilterdto)
					    .then(
						    function(value) {
							console.log(value);
							if(value != ''){
							$scope.recordId = btoa(value[0].booking.bId);}
							else{
							    $scope.recordId = btoa(0); 
							}
						    }, function(reason) {
							console.log(reason);
						    });
				}
				
				$scope.getBookingLatestRecord();

			} ]);
})();