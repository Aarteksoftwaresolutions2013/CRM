(function() {
	angular.module('CRMApp').factory(
			'addDateService',
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

				obj.findAllDates = function() {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-type' : 'application/json'
						}
					};
					$http.get('findAllDate', config).success(
							function(data, status, headers, config) {
								console.log(data);
								deferred.resolve(data);
							}).error(function() {
						console.log("data reject");
						deferred.reject();
					});
					return deferred.promise;
				}

				obj.saveDates = function(date) {
					console.log(date);
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-type' : 'application/json'
						}
					};
					var data = angular.toJson(date)
					$http.post('saveDates', data, config).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function() {
						console.log("data reject");
						deferred.reject();
					});
					return deferred.promise;
				}

				obj.updateDates = function(date) {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-Type' : 'application/json'
						}
					};
					if (date.addToCalendar === '') {
						date.addToCalendar = 0;
					}
					var data = angular.toJson(date)
					$http.post('updateDates', data, config).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function(data, status, headers, config) {
						deferred.reject();
					});
					return deferred.promise;
				}
				
				obj.findDatesById = function(datesId) {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-Type' : 'application/json'
						}
					};
					$http.get('findDatesById/' + datesId, config)
							.success(function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function(data, status, headers, config) {
								deferred.reject();
							});
					return deferred.promise;
				}
				
				obj.deleteDatesById = function(datesIdList) {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-Type' : 'application/json'
						}
					};
					$http.get('deleteDates/' + datesIdList, config).success(
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