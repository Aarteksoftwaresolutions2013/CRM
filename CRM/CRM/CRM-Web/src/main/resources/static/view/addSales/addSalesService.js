(function() {
	angular.module('CRMApp').factory(
		'addSalesService',
		function($http, $q) {
			var obj = {};
			obj.getAllSalesPerson = function() {
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-Type' : 'application/json'
					}
				};
				$http.get('getAllSalesPerson', config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function(data, status, headers, config) {
					deferred.reject();
				});
				return deferred.promise;
			}

			obj.addSalesPerson = function(salesPerson) {
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-Type' : 'application/json'
					}
				};
				var data = angular.toJson(salesPerson)
				$http.post('saveSalesPerson', data, config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function(data, status, headers, config) {
					deferred.reject();
				});
				return deferred.promise;
			}

			obj.editSalesPerson = function(salesPersonId) {
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-Type' : 'application/json'
					}
				};
				$http.get('getSalesPersonById', config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function(data, status, headers, config) {
					deferred.reject();
				});
				return deferred.promise;
			}

			obj.deleteSalesPerson = function(salesPerson) {
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-Type' : 'application/json'
					}
				};
				var data = angular.toJson(salesPerson);
				$http.post('saveSalesPerson', data, config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function(data, status, headers, config) {
					deferred.reject();
				});
				return deferred.promise;
			}
			return obj;
		});
})();