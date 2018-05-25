(function() {

    angular
	    .module('CRMApp')
	    .factory(
		    'dashboardService',
		    function($http, $q) {
			var obj = {};
			obj.getBooking = function(dashboardFilterDto) {
			    if (dashboardFilterDto.trainingLocation == "Show All") {
				dashboardFilterDto.trainingLocation = -1;
			    }
			    ;
			    if (dashboardFilterDto.course == "All courses") {
				dashboardFilterDto.course = -1;
			    }
			    ;
			    var deferred = $q.defer();
			    var config = {
				headers : {
				    'Content-Type' : 'application/json'
				}
			    };
			    var data = angular.toJson(dashboardFilterDto);
			    $http
				    .post('getBooking', data, config)
				    .success(
					    function(data, status, headers,
						    config) {
						if (dashboardFilterDto.trainingLocation == -1) {
						    dashboardFilterDto.trainingLocation = "Show All";
						}
						;
						if (dashboardFilterDto.course == -1) {
						    dashboardFilterDto.course = "All courses";
						}
						;
						console.log("Service = ", data);
						deferred.resolve(data);
					    }).error(
					    function(data, status, headers,
						    config) {
						deferred.reject();
					    });
			    return deferred.promise;
			}

			obj.getCountOfAllBooking = function(dashboardFilterDto) {
			    if (dashboardFilterDto.trainingLocation == "Show All") {
				dashboardFilterDto.trainingLocation = -1;
			    }
			    ;
			    if (dashboardFilterDto.course == "All courses") {
				dashboardFilterDto.course = -1;
			    }
			    ;
			    var deferred = $q.defer();
			    var config = {
				headers : {
				    'Content-Type' : 'application/json'
				}
			    };
			    var data = angular.toJson(dashboardFilterDto);
			    //console.log("AAAA",data);
			    $http
				    .post('getTotalNumOfBookingRecords', data,
					    config)
				    .success(
					    function(data, status, headers,
						    config) {
						if (dashboardFilterDto.trainingLocation == -1) {
						    dashboardFilterDto.trainingLocation = "Show All";
						}
						;
						if (dashboardFilterDto.course == -1) {
						    dashboardFilterDto.course = "All courses";
						}
						;
						console.log("service=", data);
						deferred.resolve(data);
					    }).error(
					    function(data, status, headers,
						    config) {
						deferred.reject();
					    });
			    return deferred.promise;
			}

			obj.getBookingSingleRecord = function(
				dashboardFilterDto) {
			    if (dashboardFilterDto.trainingLocation == "Show All") {
				dashboardFilterDto.trainingLocation = -1;
			    }
			    ;
			    if (dashboardFilterDto.course == "All courses") {
				dashboardFilterDto.course = -1;
			    }
			    ;
			    var deferred = $q.defer();
			    var config = {
				headers : {
				    'Content-Type' : 'application/json'
				}
			    };
			    var data = angular.toJson(dashboardFilterDto);
			    $http
				    .post('getBooking', data, config)
				    .success(
					    function(data, status, headers,
						    config) {
					    	//console.log("PPPPPPPPPPPPPPP",data);
						if (dashboardFilterDto.trainingLocation == -1) {
						    dashboardFilterDto.trainingLocation = "Show All";
						}
						;
						if (dashboardFilterDto.course == -1) {
						    dashboardFilterDto.course = "All courses";
						}
						;
						deferred.resolve(data);
					    }).error(
					    function(data, status, headers,
						    config) {
						deferred.reject();
					    });
			    return deferred.promise;
			}

			obj.getTrainingLocation = function() {
			    var deferred = $q.defer();
			    var config = {
				headers : {
				    'Content-type' : 'application/json'
				}
			    };
			    $http.get('getTrainingLocation', config).success(
				    function(data, status, headers, config) {
					deferred.resolve(data);
				    }).error(function() {
				deferred.reject();
			    });
			    return deferred.promise;
			}

			obj.getTraining = function() {
			    var deferred = $q.defer();
			    var config = {
				headers : {
				    'Content-type' : 'application/json'
				}
			    };
			    $http.get('getTraining', config).success(
				    function(data, status, headers, config) {
					deferred.resolve(data);
				    }).error(function() {
				deferred.reject();
			    });
			    return deferred.promise;
			}

			/*
			 * obj.getAllFullNameAndSchoolName = function() { var
			 * deferred = $q.defer(); var config = { headers : {
			 * 'Content-type' : 'application/json' } };
			 * $http.get('getAllFullNameAndSchoolName',
			 * config).success( function(data, status, headers,
			 * config) { deferred.resolve(data); }).error(function() {
			 * console.log("data reject"); deferred.reject(); });
			 * return deferred.promise; }
			 */

			obj.getAllTrainingDateByYear = function() {
			    var deferred = $q.defer();
			    var config = {
				headers : {
				    'Content-Type' : 'application/json'
				}
			    };
			    //var data = angular.toJson(dashboardFilterDto);
			    $http.get('getAllTrainingDateByYear', config)
				    .success(
					    function(data, status, headers,
						    config) {
						deferred.resolve(data);
					    }).error(
					    function(data, status, headers,
						    config) {
						deferred.reject();
					    });
			    return deferred.promise;
			}

			return obj;
		    });
})();