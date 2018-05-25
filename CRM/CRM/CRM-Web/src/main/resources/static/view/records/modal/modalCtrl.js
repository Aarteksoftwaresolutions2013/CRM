(function() {
	'use strict';

	angular
			.module('CRMApp')
			.controller(
					'modalCtrl',
					[
							'$scope',
							'recordsService',
							'$uibModal',

							function($scope, recordsService, $uibModal) {

								var status = document.getElementById('status').value;
								console.log("Inside Modal Ctrl..........",
										status);
								var invoiceNum = document
										.getElementById('invoiceNum').value;
								var total = document.getElementById('total').value;
								console.log($scope.recordDto,
										"modal Ctrl.............L or S");
								$scope.saveRecords = function() {

									recordsService
											.addRecord($scope.recordDto)

											.then(
													function(data) {
														console
																.log(data,
																		"saveRecords...........");
													});

								}
								$scope.saveRecords();

							} ]);
})();