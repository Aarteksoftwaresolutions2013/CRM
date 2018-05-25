(function() {
    'use strict';

    angular
	    .module('CRMApp')
	    .controller(
		    'addUserCtrl',
		    [
			    '$scope',
			    '$rootScope',
			    '$filter',
			    'addUserService',
			    '$routeParams',
			    '$sessionStorage',
			    function($scope, $rootScope, $filter,
				    addUserService, $routeParams,
				    $sessionStorage) {

				$scope.user = {
				    id : '',
				    userName : '',
				    password : '',
				    department : '',
				    dialByURL : ''
				}
				$rootScope.login = true;
				$scope.dialByUrlVisibility = false;
				$scope.selected = 0;
				$scope.userList;
				$scope.departmentList = ["Admin",
					"Accountancy", "Graphic", "IT",
					"Other", "SalesPerson"]

				$scope.getAllUser = function() {
				    addUserService.getAllUser().then(
					    function(data) {
						$scope.userList = data;
					    });
				}

				$scope.addUser = function() {
				    if ($scope.user != ''
					    && $scope.user != null) {
					addUserService
						.addUser($scope.user)
						.then(
							function(data) {
							    if ($sessionStorage.user.id === $scope.user.id) {
								$sessionStorage.user.dialByUrl = $scope.user.dialByURL;
							    }

							    $scope
								    .clearFields();
							    $scope.getAllUser();
							});
				    } else {
					$scope.message = "";
				    }
				}
				$scope.departmentChange = function() {
				    if ($scope.user.department === "SalesPerson") {
					$scope.dialByUrlVisibility = true;
				    } else {
					$scope.dialByUrlVisibility = false;
				    }
				}
				$scope.selectedItem = 0;
				$scope.editUser = function() {
				    addUserService
					    .findUserById($scope.selectedItem)
					    .then(
						    function(data) {
							console.log("edituser",
								data);
							$scope.user = {
							    id : data[0].id,
							    userName : data[0].userName,
							    password : data[0].password,
							    department : data[0].department,
							    dialByURL : data[0].dialByURL
							}
							if (data[0].department === "SalesPerson") {
							    $scope.dialByUrlVisibility = true;
							} else {
							    $scope.dialByUrlVisibility = false;
							}
						    });
				}

				$scope.deleteUser = function() {
				    addUserService.deleteUser($scope.user.id)
					    .then(function(data) {
						console.log("deleteuser");
						$scope.clearFields();
						$scope.getAllUser();
					    });
				}

				$scope.clearFields = function() {
				    $scope.user = {
					id : '',
					userName : '',
					password : '',
					department : '',
					dialByURL : ''
				    }
				}

				$scope.getAllUser();

			    } ]);
})();