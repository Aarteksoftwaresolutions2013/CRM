(function() {
	angular.module('CRMApp').factory(
		'addUserService',
		function($http, $q) {
			var obj = {};
			obj.getAllUser = function() {
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-type' : 'application/json'
					}
				};
				$http.get('getAllUser', config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function() {
					console.log("data reject");
					deferred.reject();
				});
				return deferred.promise;
			}

			obj.addUser = function(user) {
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-Type' : 'application/json'
					}
				};
				var data = angular.toJson(user)
				$http.post('saveUser', data, config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function(data, status, headers, config) {
					deferred.reject();
				});
				return deferred.promise;
			}
			
			obj.findUserById = function(userId) {
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-Type' : 'application/json'
					}
				};
				$http.get('findUserById/' + userId, config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function(data, status, headers, config) {
					deferred.reject();
				});
				return deferred.promise;
			}
			
			obj.deleteUser = function(userId) {
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-Type' : 'application/json'
					}
				};
				$http.get('deleteUser/' + userId, config).success(
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