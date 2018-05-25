(function() {
	angular.module('CRMApp').factory(
			'calendarPageService',
			function($http, $q) {
				var obj = {};

				obj.getAllLocationDate = function(year, month, phase) {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-type' : 'application/json'
						}
					};
					$http.get(
							'findAllLocationDate/' + year + '/' + month + '/'
									+ phase, config).success(
							function(data, status, headers, config) {
								console.log("calender....", data);
								deferred.resolve(data);
							}).error(function() {
						console.log("data reject");
						deferred.reject();
					});
					return obj;
					
				}
				var obj = {};

				obj.getAllLocationDate = function(calendarDTO) {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-type' : 'application/json'
						}
					};
					 var data = angular.toJson(calendarDTO);
					$http.post(
							'findAllLocationDate', data, config).success(function(data) {
								   console.log("xyz11111",data)
									deferred.resolve(data);
								}).error(function() {
						console.log("data reject");
						deferred.reject();
					});
					return deferred.promise;
					
				}
				  obj.selectedItemChanged = function(phase) {
					console.log(phase);
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-type' : 'application/json'
						}
					};
					$http.get('selectedItemChanged/' + phase).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function() {
						deferred.reject();
					});
					return deferred.promise;
				}

				obj.getAllConsultants = function() {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-type' : 'application/json'
						}
					};
					$http.get('getAllConsultants').success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function() {
						deferred.reject();
					});
					return deferred.promise;
				}
				
				obj.saveCalendarColor = function(calendarColor) {
					var deferred = $q.defer();
					var config = {
						headers : {
							'Content-Type' : 'application/json'
						}
					};
					var data = angular.toJson(calendarColor)
					$http.post('saveCalendarColor', data, config).success(
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