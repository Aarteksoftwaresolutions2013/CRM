(function() {
	'use strict';

	angular
			.module('CRMApp')
			.controller(
					'reportingCtrl',
					[
							'$scope',
							'$rootScope',
							'$filter',
							'reportingService',
							'$routeParams',
							'$uibModal',
							function($scope, $rootScope, $filter,
									reportingService, $routeParams, $uibModal) {

								$rootScope.login = true;
								$scope.userList;

								$scope.functionOnLoad = function() {
									jQuery(document).ready(function() {
										TableDatatablesManaged.init();
									});
								}

								$scope.repotingDto = {
									period : "Show All",
									status : "Show All",
									payment : "All",
									user : "Show All"

								}
								$scope.getSelectedItemFromReportingFilter = function() {
									console.log($scope.repotingDto);
								}
								$scope.revenueVat = function() {
									console.log("inside revanue vat function");
									console.log($scope.repotingDto);
									if ($scope.repotingDto.period == "Show All") {
										console.log("Please select period");
										$scope.modalInstance = $uibModal
												.open({
													templateUrl : 'view/reporting/reportsPopup/periodChangePopup.html',
													size : 'sm',

												});
									} else {
										reportingService.revenueVat(
												$scope.repotingDto).then(
												function(data) {
													$scope.value = data;
													console.log(data, "revenuevat");
												});
									}

								}
								$scope.revenue = function() {
									console.log("inside revanue function");
									console.log($scope.repotingDto.period);
									if ($scope.repotingDto.period == "Show All") {
										console.log("Please select period");
										$scope.modalInstance = $uibModal
												.open({
													templateUrl : 'view/reporting/reportsPopup/periodChangePopup.html',
													size : 'sm',

												});
									} else {
										reportingService.revenue(
												$scope.repotingDto).then(
												function(data) {
													$scope.value = data;
												});
									}

								}

								$scope.noOfCourses = function() {
									console.log("inside revanue function");
									console.log($scope.repotingDto.status);
									if ($scope.repotingDto.status == "Show All") {
										console.log("Please select status");
										$scope.modalInstance = $uibModal
												.open({
													templateUrl : 'view/reporting/reportsPopup/statusChangePopup.html',
													size : 'sm',

												});

									} else {
										reportingService.noOfCourse(
												$scope.repotingDto).then(
												function(data) {
													$scope.value = data;
												});
									}

								}

								$scope.locations = function() {
									console.log("inside locatoins function");
									console.log($scope.repotingDto);
									if ($scope.repotingDto.status == "Show All") {
										console.log("Please select status");
										$scope.modalInstance = $uibModal
												.open({
													templateUrl : 'view/reporting/reportsPopup/statusChangePopup.html',
													size : 'sm',

												});

									} else {
										reportingService.locatoins(
												$scope.repotingDto).then(
												function(data) {
													$scope.value = data;
												});
									}

								}
								$scope.outstanding = function() {
									console.log("inside outstanding function");
									console.log($scope.repotingDto);
									if ($scope.repotingDto.status == "Show All") {
										console.log("Please select status");
										$scope.modalInstance = $uibModal
												.open({
													templateUrl : 'view/reporting/reportsPopup/statusChangePopup.html',
													size : 'sm',

												});

									} else {
										reportingService.outstanding(
												$scope.repotingDto).then(
												function(data) {
													$scope.value = data;
												});
									}

								}
								$scope.vatReport = function() {
									console.log("inside vatReport function");
									console.log($scope.repotingDto);
									if ($scope.repotingDto.status == "Show All") {
										console.log("Please select status");
										$scope.modalInstance = $uibModal
												.open({
													templateUrl : 'view/reporting/reportsPopup/statusChangePopup.html',
													size : 'sm',

												});

									} else {
										reportingService.vatReport(
												$scope.repotingDto).then(
												function(data) {
													$scope.value = data;
												});
									}

								}

								$scope.periodList = [ "Show All",
										"next 3 months", "weekly", "1 month",
										"3 month", "6 month", "1 year",
										"2 years", "3 years", "4 years",
										"5 years" ];
								$scope.statusList = [ "Show All", "Enquiry",
										"Booking" ];
								$scope.paymentList = [ "All", "Received",
										"Not Received" ];
								$scope.getAllUser = function() {
									reportingService.getAllUser().then(
											function(data) {
												$scope.userList = data;
												console.log(data);
											});
								}
								$scope.getAllUser();

							} ]);
})();