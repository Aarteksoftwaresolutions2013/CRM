(function() {
	angular.module('CRMApp').factory(
			'reportingService',
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
				obj.revenueVat = function(repotingDto) {
					var deferred = $q.defer();
					var config = {
						header : {
							'Content-type' : 'application/json'
						}
					};
					var data = angular.toJson(repotingDto);
					console.log("inside revenueVat service", data);
					$http.post('revenueVat', data, config).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function() {
						deferred.reject();
					});
					return deferred.promise;
				}
				obj.revenue = function(repotingDto) {
					var deferred = $q.defer();
					var config = {
						header : {
							'Content-type' : 'application/json'
						}
					};
					var data = angular.toJson(repotingDto);
					console.log("inside revenue service", data);
					$http.post('revenue', data, config).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function() {
						deferred.reject();
					});
					return deferred.promise;
				}
				obj.noOfCourse = function(repotingDto) {
					var deferred = $q.defer();
					var config = {
						header : {
							'Content-type' : 'application/json'
						}
					};
					var data = angular.toJson(repotingDto);
					console.log("inside btnCourseClick service", data);
					$http.post('noOfCourse', data, config).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function() {
						deferred.reject();
					});
					return deferred.promise;
				}
				obj.locatoins = function(repotingDto) {
					var deferred = $q.defer();
					var config = {
						header : {
							'Content-type' : 'application/json'
						}
					};
					var data = angular.toJson(repotingDto);
					console.log("inside locations service", data);
					$http.post('locatoins', data, config).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function() {
						deferred.reject();
					});
					return deferred.promise;
				}
				obj.outstanding = function(repotingDto) {
					var deferred = $q.defer();
					var config = {
						header : {
							'Content-type' : 'application/json'
						}
					};
					var data = angular.toJson(repotingDto);
					console.log("inside outstanding service", data);
					$http.post('outstanding', data, config).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function() {
						deferred.reject();
					});
					return deferred.promise;
				}
				obj.vatReport = function(repotingDto) {
					var deferred = $q.defer();
					var config = {
						header : {
							'Content-type' : 'application/json'
						}
					};
					var data = angular.toJson(repotingDto);
					console.log("inside vatReport service", data);
					$http.post('vatReport', data, config).success(
							function(data, status, headers, config) {
								deferred.resolve(data);
							}).error(function() {
						deferred.reject();
					});
					return deferred.promise;
				}

				return obj;

			});
})();