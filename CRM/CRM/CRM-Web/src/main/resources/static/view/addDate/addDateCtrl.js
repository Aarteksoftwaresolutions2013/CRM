(function() {
	'use strict';
	angular
			.module('CRMApp')
			.controller(
					'addDateCtrl',
					[
							'$scope',
							'$rootScope',
							'addDateService',
							'$filter',
							function($scope, $rootScope, addDateService,
									$filter) {

								$scope.date = {
									id : '',
									location : '',
									addtocalender : '',
									t_date : '',
									t_datedummy : '',
								}

								$rootScope.login = true;
								$scope.locationList = [];
								$scope.locationListDate = [];

								$scope.getAllLocation = function() {
									$scope.locationList = [];
									addDateService.findAllLocation().then(
											function(data) {
												$scope.locationList = data;
												console.log(data,
														"locationList");
											});
								}

								$scope.getAllDate = function() {
									$scope.locationListDate = [];
									addDateService
											.findAllDates()
											.then(
													function(data) {
														$scope.locationListDate = data;
														console
																.log(
																		$scope.locationListDate,
																		"locationListDateSecond");
														setTimeout(
																function() {
																	$scope
																			.functionOnLoad();
																}, 5);
													});
								}

								$scope.getAllDates = function() {
									$scope.locationListDate = [];
									addDateService
											.findAllDates()
											.then(
													function(data) {
														$scope.locationListDate = data;
														TableDatatablesManaged
																.clear();
														$scope.locationListDate = data;
														console
																.log(
																		$scope.locationListDate,
																		"locationListDateSecond");
														setTimeout(function() {
															TableDatatablesManaged
																	.init();
														}, 5);
													});
								}

								$scope.functionOnLoad = function() {
									jQuery(document).ready(function() {
										TableDatatablesManaged.init();
										ComponentsDateTimePickers.init();
									});
								}

								$scope.addDate = function() {
									console.log($scope.date);

									if ($scope.date != ''
											&& $scope.date != undefined
											&& $scope.date.id === '') {

										addDateService.saveDates($scope.date)
												.then(function(data) {
													$scope.clearFields();
													$scope.getAllDates();
												});
									} else {
										addDateService.updateDates($scope.date)
												.then(function(data) {
													$scope.clearFields();
													$scope.getAllDates();
												});
										return false;
									}
								}

								$scope.editDate = function(dateId) {
									addDateService
											.findDatesById(dateId)
											.then(
													function(data) {
														//console.log(data);
														$scope.date = {
															id : data[0].id,
															location : data[0].location,
															addtocalender : data[0].addtocalender,
															t_datedummy : data[0].t_date,
														}
														//console.log($scope.date);
													});
								}
								
								$scope.checkoptions = function (dates) {
									console.log("List", dates);
									var dateIdList = [];
									angular.forEach(dates, function (date, key) {
										console.log("Object", date, key);
									if (dates[key].checked) {
										console.log("DDDD", date.id);
										dateIdList.push(date.id);
									}
									});
									addDateService.deleteDatesById(dateIdList.toString())
									.then(function(data) {
										console.log("successfully deleted");
										$scope.clearFields();
										$scope.getAllDates();
									});
									//console.log("successfully deleted+++++", dateIdList, dateIdList.toString());
								}
								
								$scope.clearFields = function() {
									$scope.date = {
										id : '',
										location : '',
										addtocalender : '',
										t_date : '',
									}
								}

								$scope.getAllLocation();
								$scope.getAllDate();

							} ]);

})();
