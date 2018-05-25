(function() {
	angular.module('CRMApp').factory(
			'addLocationService',
			function($http, $q) {
				var obj = {};
				obj.findAllLocation = function() {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-type' : 'application/json'
						}
					};
					$http.get('findAllLocation', config).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function() {
						console.log("data reject");
						deferred.reject();
					});
					return deferred.promise;
				}

				obj.addLocation = function(location) {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-Type' : 'application/json'
						}
					};
					if (location.addToCalendar === '') {
						location.addToCalendar = 0;
					}
					var data = angular.toJson(location)
					$http.post('saveLocations', data, config).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function(data, status, headers, config) {
						deferred.reject();
					});
					return deferred.promise;
				}

				obj.findLocationsById = function(locationsId) {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-Type' : 'application/json'
						}
					};
					$http.get('findLocationsById/' + locationsId, config)
							.success(function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function(data, status, headers, config) {
								deferred.reject();
							});
					return deferred.promise;
				}

				obj.deleteUser = function(checkedBoxes) {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-Type' : 'application/json'
						}
					};
					$http.get('deleteLocations/' + checkedBoxes, config).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function(data, status, headers, config) {
						deferred.reject();
					});
					return deferred.promise;
				}

				obj.updateLocation = function(location) {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-Type' : 'application/json'
						}
					};
					if (location.addToCalendar === '') {
						location.addToCalendar = 0;
					}
					var data = angular.toJson(location)
					$http.post('updateLocations', data, config).success(
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