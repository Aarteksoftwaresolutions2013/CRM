(function() {
	'use strict';

	angular
			.module('CRMApp')
			.controller(
					'addLocationCtrl',
					[
							'$scope',
							'$rootScope',
							'$filter',
							'addLocationService',
							'$routeParams',
							'$sessionStorage',
							function($scope, $rootScope, $filter,
									addLocationService, $routeParams,
									$sessionStorage) {

								$scope.location = {
									id : '',
									location : '',
									shortName : '',
									addToCalendar : '',
								}
								$rootScope.login = true;
								$scope.locationsList = [];
								$scope.selectedIds = [];

								$scope.findAllLocation = function() {
									$scope.locationsList = [];
									addLocationService.findAllLocation().then(
											function(data) {
												$scope.locationsList = data;
												console.log(data,
														"locationList");
												setTimeout(function() {
													$scope.functionOnLoad();
												}, 0);
											});
								}
								$scope.findAllLocations = function() {
									$scope.locationsList = [];
									addLocationService.findAllLocation().then(
											function(data) {
												$scope.locationsList = data;
												console.log(data,
														"locationList");
											});
									TableDatatablesManaged.clear();
									setTimeout(function() {
										TableDatatablesManaged.init();
									}, 0);
								}

								$scope.functionOnLoad = function() {
									jQuery(document).ready(function() {
										TableDatatablesManaged.init();
									});
								}

								$scope.addLocation = function() {
									console.log($scope.location);
									if ($scope.location != ''
											&& $scope.location != undefined
											&& $scope.location.id === '') {
										console.log("app");
										addLocationService.addLocation(
												$scope.location).then(
												function(data) {
													$scope.clearFields();
													$scope.findAllLocations();
												});
									} else {
										addLocationService.updateLocation(
												$scope.location).then(
												function(data) {
													$scope.clearFields();
													$scope.findAllLocations();
												});
										return false;
									}
								}
								$scope.editLocation = function(locationId) {
									addLocationService
											.findLocationsById(locationId)
											.then(
													function(data) {
														console.log("edituser",
																data);
														$scope.location = {
															id : data[0].id,
															location : data[0].location,
															shortName : data[0].shortName,
															addToCalendar : data[0].addToCalendar,
														}
														console
																.log($scope.location);
													});
								}

								$scope.deleteLocations = function(locations) {
									console.log("List", locations);
									var checkedBoxes = [];
									angular.forEach(locations, function(
											location, key) {
										console.log("Object", location, key);
										if (locations[key].selected) {
											console.log("PPPPPPPPPPP",
													location.id);
											checkedBoxes.push(location.id);
										}
									});
									// console.log("+++++++++", checkedBoxes);
									addLocationService.deleteUser(checkedBoxes.toString())
											.then(function(data) {
												$scope.clearFields();
												$scope.findAllLocations();
											});
								}


								$scope.clearFields = function() {
									$scope.location = {
										id : '',
										location : '',
										shortName : '',
										addToCalendar : '',
									}
								}

								$scope.findAllLocation();

							} ]);
})();