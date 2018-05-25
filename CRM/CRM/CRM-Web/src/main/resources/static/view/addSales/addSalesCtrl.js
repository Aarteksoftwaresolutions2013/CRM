(function() {
	'use strict';

	angular.module('CRMApp').controller(
		'addSalesCtrl',
		[
			'$scope',
			'$rootScope',
			'$filter',
			'addSalesService',
			'$routeParams',
			function($scope, $rootScope, $filter, addSalesService, $routeParams) {
				
				$scope.bookbid = $routeParams.bookbid;
                console.log($scope.bookbid);
				$scope.salesPersonList = [];
				$rootScope.login = true;
				$scope.salesPerson = {
					sId : "",
					sName : "",
					surName : "",
					email : "",
					skypeID : "",
					status : 1,
					entryDate : "",
					dialByURL : ""
				}

				$scope.getAllSalesPerson = function() {
					addSalesService.getAllSalesPerson().then(
						function(value) {
							console.log(value);
							$scope.salesPersonList = value;
							TableDatatablesManaged.clear();
							setTimeout(function() {
								TableDatatablesManaged.init();
							}, 0);
						}, function(reason) {
							console.log(reason);
						});
				}
				$scope.getAllSalesPerson();

				$scope.functionOnLoad = function() {
					jQuery(document).ready(function() {
						TableDatatablesManaged.init();
					});
				}

				$scope.addSalesPerson = function(salesPerson) {
					console.log(salesPerson, "sales");
					$scope.salesPerson.entryDate = new Date();
					$scope.salesPerson.entryDate = $filter('date')($scope.salesPerson.entryDate, "yyyy-MM-dd HH:mm:ss");
					if (salesPerson != '' && salesPerson != null) {
						addSalesService.addSalesPerson(salesPerson).then(
							function(data) {
								$scope.clearFields();
								$scope.getAllSalesPerson();
							});
					} else {
						$scope.message = "";
					}
				}

				$scope.editSalesperson = function(salesPerson) {
					console.log(salesPerson)
					$scope.salesPerson = {
						sId : salesPerson.sId,
						sName : salesPerson.sName,
						surName : salesPerson.surName,
						email : salesPerson.email,
						skypeID : salesPerson.skypeID,
						status : salesPerson.status,
						entryDate : salesPerson.entryDate,
						dialByURL : salesPerson.dialByURL
					}
				}

				$scope.deleteSalesPerson = function(salesPerson) {
					console.log($scope.salesPerson);
					if ($scope.salesPerson != '' && $scope.salesPerson != null) {
						$scope.salesPerson.status = 0;

						addSalesService.deleteSalesPerson($scope.salesPerson)
							.then(function(data) {
								console.log(data);
								if (data) {
									$scope.clearFields();
									$scope.getAllSalesPerson();
								}
							});
					} else {
						$scope.message = "Please select any one field";
					}
				}

				$scope.clearFields = function() {
					$scope.salesPerson = {
						sId : "",
						sName : "",
						surName : "",
						email : "",
						skypeID : "",
						status : 1,
						entryDate : "",
						dialByURL : ""
					}
				}

			} ]);
})();