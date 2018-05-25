(function() {

	'use strict';

	angular
			.module('CRMApp')
			.controller(
					'loginCtrl',
					[
							"$scope",
							'$rootScope',
							"loginService",
							"$location",
							'$sessionStorage',
							function($scope, $rootScope, loginService,
									$location, $sessionStorage) {

								$scope.user = {
									userName : "",
									password : ""
								}
								$rootScope.login = false;
								$scope.login = function() {
									if (!$scope.user.userName == ""
											&& !$scope.user.password == "") {
										console.log($scope.user);
										loginService
												.getLogin($scope.user)
												.then(
														function(data) {
															console.log(data);
															if (data.length == 0) {
																$scope.authenticationError = "Invalid username and password";
															} else {
																$sessionStorage.user = {
																	id : data[0].id,
																	userName : data[0].userName,
																	password : data[0].password,
																	department : data[0].department,
																	dialByUrl : data[0].dialByURL
																}
																console.log("---------------------");
																console.log($sessionStorage.user);
																$scope.authenticationError = "Login Sucessful.";
																$location
																		.path('/dashboard');
															}
														});
									}
								}

								$scope.functionOnLoad = function() {
									jQuery(document).ready(function() {
										Login.init();
									});
								}
								$scope.functionOnLoad();
							} ]);
})();