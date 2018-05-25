(function() {
	angular.module('CRMApp').factory(
		'recordsService',
		function($http, $q) {
			var obj = {};

			obj.getrecord = function(decodedBookId) {
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-Type' : 'application/json'
					}
				};
				$http.get('findBookingById/' + decodedBookId, config)
					.success(function(data, status, headers, config) {
						console.log(data[0])
						deferred.resolve(data);
					}).error(function(data, status, headers, config) {
					deferred.reject();
				});
				return deferred.promise;
			}
			obj.calculateTotal = function(record) {
				var deferred = $q.defer();
				var config = {
					header : {
						'Content-type' : 'application/json'
					}
				};
				var data = angular.toJson(record);
				$http.post('calculateTotal', data, config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function() {
					deferred.reject();
				});
				return deferred.promise;
			}
			
			obj.getUsersByTrDate = function(selectedCalendarTrDate) {
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-Type' : 'application/json'
					}
				};
				$http.get('getUsersByTrDate/' + selectedCalendarTrDate, config)
					.success(function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function(data, status, headers, config) {
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
			obj.saveDelegateList = function(delegateList) {
				var deferred = $q.defer();
				var config = {
					header : {
						'Content-type' : 'application/json'
					}
				};
				var data = angular.toJson(delegateList);
				$http.post('saveDelegateList', data, config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function() {
					deferred.reject();
				});
				return deferred.promise;
			}
			
			obj.deleteDelegateList = function(delegateId) {
				var deferred = $q.defer();
				var config = {
					header : {
						'Content-type' : 'application/json'
					}
				};
				$http.get('deleteDelegateList/'+ delegateId, config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function() {
					deferred.reject();
				});
				return deferred.promise;
			}

			obj.updateDelegateList = function(delegateList) {
				var deferred = $q.defer();
				var config = {
					header : {
						'Content-type' : 'application/json'
					}
				};
				var data = angular.toJson(delegateList);
				$http.post('updateDelegateList', data, config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function() {
					deferred.reject();
				});
				return deferred.promise;
			}
			
			obj.addRecord = function(record) {
				var deferred = $q.defer();
				var config = {
					header : {
						'Content-type' : 'application/json'
					}
				};
				var data = angular.toJson(record);
				$http.post('saveRecordsPage', data, config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function() {
					deferred.reject();
				});
				return deferred.promise;
			}
			
			obj.createAndSendInvoice = function(record) {
				var deferred = $q.defer();
				var config = {
					header : {
						'Content-type' : 'application/json'
					}
				};
				var data = angular.toJson(record);
				console.log(data,"data.........")
				$http.post('createAndSendInvoice', data, config).success(
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
				$http.get('getAllConsultants', config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function() {
						console.log("data reject");
					deferred.reject();
				});
				return deferred.promise;
			}
			
			obj.fetchLogs = function(startDate, endDate , bookingId){
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-type' : 'application/json'
					}
				};
				$http.get('commentByBIdAndStartDateAndEndDate/' + bookingId + '/' + startDate + '/' + endDate, config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function() {
						console.log("data reject");
					deferred.reject();
				});
				return deferred.promise;
			}
			
			obj.findDelegateListByBId = function(bookingId){
				var deferred = $q.defer();
				var config = {
					headers : {
						'Content-type' : 'application/json'
					}
				};
				$http.get('delegateListByBId/'+ bookingId, config).success(
					function(data, status, headers, config) {
						deferred.resolve(data);
					}).error(function() {
						console.log("data reject");
					deferred.reject();
				});
				return deferred.promise;
			}


			return obj;
		});
})();