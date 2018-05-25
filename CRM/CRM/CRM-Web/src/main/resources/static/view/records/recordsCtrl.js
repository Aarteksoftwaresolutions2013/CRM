(function() {
	'use strict';

	angular
			.module('CRMApp')
			.controller(
					'recordsCtrl',
					[
							'$scope',
							'$rootScope',
							'recordsService',
							'$routeParams',
							'$filter',
							'$sessionStorage',
							'$location',
							'$uibModal',
							'$window',
							function($scope, $rootScope, recordsService,
									$routeParams, $filter, $sessionStorage,
									$location, $uibModal, $window) {

								$rootScope.login = true;
								$scope.visibleConfirm = false;
								$scope.editCount = false;
								$scope.cancelSelect = true;
								$scope.submit = false;
								$scope.logList = [];
								$scope.logArr = [];
								$scope.userListByTrDate = [];
								$scope.logString = "";
								$scope.textString = "";
								$scope.currentDate = $filter('date')(
										new Date(), 'yyyy-MM-dd hh:mm:ss');
								$scope.startDate = "";
								$scope.endDate = "";
								$scope.logMasterUserName = $sessionStorage.user.userName;
								$scope.trainingList;
								$scope.delegatesList;
								$scope.trainingLocationList;
								$scope.createSendInvoice = false;
								$scope.recordDtoOldValues;
								$scope.invoiceDiscount = "";

								$scope.bookingListArr = localStorage
										.getItem('bookingListArr');
								if ($scope.bookingListArr != '') {
									$scope.bookingListArr = JSON
											.parse($scope.bookingListArr);
									$scope.bookingArrLength = $scope.bookingListArr.length - 1;
								}
								// Getting value from the URL
								$scope.bookbid = $routeParams.bookbid;
								$scope.firstRender = $routeParams.firstRender;
								$scope.index = parseInt($routeParams.index);
								// use for decoding the id
								$scope.decodedBookId = atob($scope.bookbid);
								$scope.selectedBookingId = '';
								console.log($scope.decodedBookId,
										"$scope.decodedBookId");
								$scope.recordDto = {
									booking : {
										bId : $scope.decodedBookId,
										title : "",
										fName : "",
										scName : "",
										training : "",
										delegNum : "",
										secondDeleg : "",
										thirdDeleg : "",
										trLocation : "",
										email : "",
										invEmail : "",
										tel : "",
										trDateDummy : '',
										notes : "",
										status : "",
										fourthDeleg : "",
										submitDateTime : "",
										secondDelegEmail : "",
										thirdDelegEmail : "",
										fourthDelegEmail : "",
										callBackTime : ""
												| 'yyyy-MM-dd hh:mm:ss',
										startTime : "",
										endTime : "",
										flCost : "",
										trainGoCost : "",
										taxiCost : "",
										hotelCost : "",
										bookingPhase : "",
										consultant : "",
										hear : "",
										hearOther : "",
										bookingSession : "",
										reminder : "",
										bookingUser : "",
										calledUser : "",
										calledDate : "",
										callerNotes : "",
										typeOfCall : "",
										sId : '',
										demoDate : ""

									},
									invoice : {
										id : '',
										paymentType : "",
										additionalDelegates : "",
										vatAmount : "",
										chkvat : "",
										bId : parseInt($scope.decodedBookId),
										firstDelegFee : "",
										secondDelegFee : "",
										thirdDelegFee : "",
										discount : "",
										cancsurCharge : "",
										schAddr : "",
										website : "",
										total : "",
										fourthDelegFee : "",
										venue : "",
										date : "",
										poNo : '',
										trainCost : "",
										travelCost : "",
										softCost : "",
										softSetup : "",
										invSentDate : "",
										payReceiveDateDummy : "",
										payReceiveBy : "",
										additionalNotes : "",
										invSentToSchFinance : "",
										bACSRefNo : "",
										reconsWithBank : "",
										paidByBacs : "",
										lTBacs : "",
										checkNo : "",
										accNo : "",
										sortCode : "",
										paidByCheque : "",
										lTCheque : "",
										chequeDateDummy : "",
										checqueDepDateDummy : "",
										bankReceiveDateDummy : "",
										raisedInvInPayPal : "",
										dateOfTransfToBankDummy : "",
										flCost : "",
										trainGoCost : "",
										taxiCost : "",
										hotelCost : "",
										LT : "",

									},
									confirmation : {
										bId : parseInt($scope.decodedBookId),
										schoolGenRol : "",
										schConfirmDate : "",
										trainerConfirmed : "",
										rolRepRecieved : "",
										repPrinted : "",
										repPosted : "",
										presentPrep : "",
										presentOnlineDrive : "",
										oneDayRemind : "",
										oneWeekRemind : "",
										schoolAwareReq : "",
										userPasKnowSchool : "",
										delegLaptop : "",
										bringRolFFT : "",
										rolPecPrint : "",
										delegRem1Day : "",
										delegRem1Week : "",
										trainRem1Week : "",
										linkSent : "",
										infoSent : "",
										interestedAsset : "",
										callbackAsset : "",
										assetNotes : "",
										journeyPlan : "",
										attendanceConfirmed : "",
										inHouseRequirementConfirmedOn : "",
										inHouseNotes : "",
										trainingLocationRequirementConfirmedOn : "",
										trainingLocationDelegateConfirmedOn : "",
										softwareLinkSentOn : "",
										softwareInfoSentOn : "",
										attendanceConfirmedBy : "",
										receivedLocation : "",
										trainingMaterialCreated : "",
										informationOnSchools : "",
										hotelHasAProjector : "",
										hotelHasWifi : "",
										hotelHasExtensionLeads : "",
										hotelHasTea : "",
										hotelHasLunch : "",
										hotelHasAfterNoonTea : "",
										contactsReceived : "",
										contactsSigned : "",
										price : "",
										confirmationLocationNotes : "",
										confirmationLocationTrainingMaterialCreated : "",
										trainingMaterialSentOn : "",
										emailHotelToInform : "",
										confirmationTrainingMaterialReceivedOn : "",
										trainerConfirmed2 : ""
									},
									courierTracking : {
										id : '',
										bId : parseInt($scope.decodedBookId),
										sendDate : "",
										trackingId : "",
										courierCompany : "",
										websiteLink : "",
										deliveryConfirmedBy : "",
										receivedDateAndTime : "",
										comments : "",
									},
									delegateList : [],
									invNotesTab : {
										bId : parseInt($scope.decodedBookId),
										inNotes : "",
										expectations : "",
									},
									logMaster : {
										id : '',
										userName : "",
										comment : "",
										entryDate : "",
										bookingId : parseInt($scope.decodedBookId),
									},
									salesPerson : {
										sName : ""
									}
								}

								$scope.delegateList = {
									id : "",
									bid : $scope.decodedBookId,
									name : "",
									schoolName : "",
									role : "",
									emailAddress : ""
								}
								$scope.saveCount = false;
								// All combo box value
								$scope.statusList = [ "Enquiry", "Booking",
										"Cancelled", "Waiting List",
										"Conditional Booking" ];
								$scope.titles = [ "Mr", "Mrs", "Miss", "Ms",
										"Dr" ];
								$scope.sessions = [ "", "Morning 9-12am",
										"Afternoon 1-4pm", "full day" ];
								$scope.numberOfDelegates = [ "1", "2", "3",
										"4", "5", "6", "7", "8", "9", "10",
										"11", "12", "13", "14", "15", "16",
										"17", "18", "19", "20", "21", "22",
										"23", "24", "25", "26" ];
								$scope.phaseList = [ "", "Infant", "Junior",
										"Primary", "Secondary", "Special",
										"Special Primary", "Special Secondary",
										"Independent", "Consultant",
										"Pri & Sec", "KS5" ];
								$scope.marketingList = [ "Conference",
										"Newsletter", "Google", "Linkedin",
										"Facebook", "Twitter", "Word of Mouth",
										"Other" ];

								$scope.assetList = [ "", "Interested",
										"Not interested", "Purchased already" ];

								$scope.confirmationLocation = [ "Phone",
										"Email" ];

								$scope.paymentTypeList = [ "L", "S" ];
								$scope.currentTabList = [ "invoice",
										"invoiceDetails",
										"confirmationInHouse",
										"confirmationLocation", "asset",
										"tracking", "delegateList",
										"expectations", "marketing",
										"reminder", "booking", "log" ];

								$scope.paymentReceivedByList = [ "", "Cheque",
										"BACS", "Paypal", "Google checkout",
										"Cash" ];
								$scope.editDelegateId = '';

								$scope.payment = function() {
									var selecttype = $scope.recordDto.invoice.payReceiveBy;
									var cheque = angular.element(document
											.querySelector('#cheque'));
									var bacs = angular.element(document
											.querySelector('#bacs'));
									var paypal = angular.element(document
											.querySelector('#paypal'));
									var google = angular.element(document
											.querySelector('#google'));
									var cash = angular.element(document
											.querySelector('#cash'));
									if (selecttype == "Cheque") {
										cheque.removeClass('datahide');
										bacs.addClass('datahide');
										paypal.addClass('datahide');
										google.addClass('datahide');
										cash.addClass('datahide');
									} else if (selecttype == "BACS") {
										cheque.addClass('datahide');
										bacs.removeClass('datahide');
										paypal.addClass('datahide');
										google.addClass('datahide');
										cash.addClass('datahide');
									} else if (selecttype == "Paypal") {
										cheque.addClass('datahide');
										bacs.addClass('datahide');
										paypal.removeClass('datahide');
										google.addClass('datahide');
										cash.addClass('datahide');
									} else if (selecttype == "Google checkout") {
										cheque.addClass('datahide');
										bacs.addClass('datahide');
										paypal.removeClass('datahide');
										google.addClass('datahide');
										cash.addClass('datahide');
									} else {
										cheque.addClass('datahide');
										bacs.addClass('datahide');
										paypal.addClass('datahide');
										google.addClass('datahide');
										cash.addClass('datahide');
									}

								}

								$scope.saveVenue = function() {
									console
											.log("saveVenue function ....................");
									$scope.recordDto.invoice.venue = $scope.recordDto.booking.trLocation;
									var trloc = document
											.getElementById('trLocation');
									var trLocation = trloc.options[trloc.selectedIndex].text;
									$scope.recordDto.invoice.venue = trLocation;
								}

								$scope.getRecord = function() {
									if ($scope.decodedBookId != '') {
										recordsService
												.getrecord($scope.decodedBookId)
												.then(
														function(data) {
															console
																	.log(data,
																			"getRecord........");
															console
																	.log(data[0].invoice.id);
															data[0].invoice.discount = parseInt(0);
															if ($scope.firstRender == "firstRender") {
																$scope.recordDto = data[0];
																$scope
																		.calculateTotal();
															}
															setTimeout(
																	function() {
																		if (data[0].invoice.id != null) {
																			if (data[0].invoice.id == 0) {
																				data[0].invoice.id = "";
																				console
																						.log(
																								data[0].invoice.id,
																								"if0");
																			}
																			console
																					.log(
																							data[0].invoice.id,
																							"if");
																		} else {
																			console
																					.log(
																							data[0].invoice.id,
																							"else");
																		}
																		data[0].invoice.poNo = parseInt(data[0].invoice.poNo);
																		data[0].confirmation.infoSent = $filter(
																				'date')
																				(
																						new Date(),
																						'dd-MM-yyyy hh:mm:ss');
																		data[0].confirmation.linkSent = $filter(
																				'date')
																				(
																						new Date(),
																						'dd-MM-yyyy hh:mm:ss');

																		$scope.recordDto = data[0];
																		console
																				.log(data[0]);

																		if ($scope.index == -1) {
																			recordsService
																					.getUsersByTrDate(
																							localStorage
																									.getItem('selectedCalendarTrDate'))
																					.then(
																							function(
																									data) {
																								console
																										.log(
																												data,
																												"usersList");
																								$scope.userListByTrDate = data;
																							});
																		}
																		if ($scope.createSendInvoice != false) {

																			console
																					.log(
																							"sendInvoice recordsCtrl..........",
																							$scope.recordDto);
																			$scope.recordDto.confirmation.linkSent = parseInt(0);
																			$scope.recordDto.confirmation.infoSent = parseInt(0);
																			$scope.recordDto.invoice.discount = $scope.invoiceDiscount;
																			recordsService
																					.createAndSendInvoice(
																							$scope.recordDto)

																					.then(
																							function(
																									data) {
																								$scope.createSendInvoice = false;
																								console
																										.log(
																												data,
																												"createAndSendInvoice...........");

																							});

																		}
																	}, 5);

														})
									}
								}
								$scope.functionOnLoad = function() {
									jQuery(document).ready(function() {
										ComponentsDateTimePickers.init();
									});
								}

								$scope.getTraining = function() {
									recordsService.getTraining().then(
											function(data) {
												$scope.trainingList = data;
											});
								}

								$scope.getAllUser = function() {
									recordsService.getAllUser().then(
											function(data) {
												$scope.userList = data;
											});
								}

								$scope.getAllConsultants = function() {
									recordsService.getAllConsultants().then(
											function(data) {
												$scope.consultantList = data;
											});
								}

								$scope.getTrainingLocation = function() {
									recordsService
											.getTrainingLocation()
											.then(
													function(data) {
														$scope.trainingLocationList = data;
													});
								}

								$scope.onClickTab = function(tab) {
									$scope.currentTab = tab;
									var add = "#" + $scope.currentTab
									angular
											.forEach(
													$scope.currentTabList,
													function(value, key) {
														if (value === $scope.currentTab) {
															angular
																	.element(
																			document
																					.querySelector(add))
																	.addClass(
																			"defaultBlock");
														} else {
															var otherTab = "#"
																	+ value;
															angular
																	.element(
																			document
																					.querySelector(otherTab))
																	.addClass(
																			"defaultNone");
															angular
																	.element(
																			document
																					.querySelector(otherTab))
																	.removeClass(
																			"defaultBlock");
														}
													});

								}

								$scope.saveRecords = function(formName) {

									$scope.textString = "";
									$scope.logString = "";
									console.log($scope.recordDto, "newly11111");
									$scope.recordDto.invoice.discount = parseInt(0);
									$scope.delegateListValues = $(
											".deleListValue").find("input");
									var arr = [];
									for (var index = 0; index < $scope.delegateListValues.length; index++) {
										arr[index] = $scope.delegateListValues[index].value;
									}
									angular.forEach($scope.logArr, function(
											value, key) {
										$scope.textString = "Value for " + '"'
												+ value.key + " " + '"' + "is "
												+ '"' + value.value + '"';
										$scope.logString = $scope.logString
												+ '\n' + $scope.textString;
									})
									if ($scope.editDelegateId != '') {
										$scope.updateDelegateList(arr);
									} else {
										if (arr[0] != "" || arr[1] != ""
												|| arr[2] != "" || arr[3] != "") {
											$scope.delegateList = {
												id : "",
												bid : $scope.decodedBookId,
												name : arr[0],
												schoolName : arr[1],
												role : arr[2],
												emailAddress : arr[3]
											}
											$scope.logMasterObject = {
												id : '',
												userName : $sessionStorage.user.userName,
												comment : $scope.logString,
												entryDate : $scope.currentDate,
												bookingId : parseInt($scope.decodedBookId),
											}

											$scope.recordDto.logMaster = $scope.logMasterObject;

											if (formName.$valid) {
												if (arr != '') {
													$scope.delegatesList
															.push($scope.delegateList);
												}
												$scope.recordDto.courierTracking.bId = parseInt($scope.decodedBookId);
												$scope.recordDto.invoice.bId = parseInt($scope.decodedBookId);
												$scope.recordDto.confirmation.bId = parseInt($scope.decodedBookId);
												$scope.recordDto.confirmation.infoSent = parseInt(0);
												$scope.recordDto.confirmation.linkSent = parseInt(0);
												$scope.recordDto.invNotesTab.bId = parseInt($scope.decodedBookId);
												$scope.recordDto.delegateList = $scope.delegatesList;
												console.log($scope.logString);
												$scope.recordDtoOldValues = $scope.recordDto;
												recordsService
														.addRecord(
																$scope.recordDto)
														.then(
																function(data) {
																	$scope.editForm
																			.$setPristine();
																	$scope.saveCount = true;
																	if ($scope.cancelSelect
																			&& !$scope.delegatesList.length == 0) {
																		console
																				.log(
																						$scope.cancelSelect,
																						"nnn");
																		$(
																				".deleListValue")
																				.find(
																						"tr")
																				.last()
																				.remove();
																		$scope.cancelSelect = true;
																	}
																	$scope
																			.fetchDelegateList();
																});
											}
										}
									}
								}
								$scope.DialByURL = function() {
									javascript: (function() {
										window
												.open($sessionStorage.user.dialByUrl
														+ "&t="
														+ +$scope.recordDto.booking.tel
														+ window.getSelection()
																.toString())
									})();
								}
								// Function for save delegate list
								$scope.saveDelegateList = function(arr) {
									console.log(arr, "arr");
									if (arr[0] != "" || arr[1] != ""
											|| arr[2] != "" || arr[3] != "") {
										$scope.delegateList = {
											id : "",
											bid : $scope.decodedBookId,
											name : arr[0],
											schoolName : arr[1],
											role : arr[2],
											emailAddress : arr[3]
										}
										recordsService.saveDelegateList(
												$scope.delegateList).then(
												function(data) {
													$scope.fetchDelegateList();
												});
									} else {
										$scope.fetchDelegateList();
									}
								}
								$scope.editDelegate = function(id) {
									$scope.editDelegateId = id;
									console.log($scope.editDelegateId, "1223");
								}

								// Function for delete delegate list
								$scope.deleteDelegateList = function(delegateId) {
									recordsService.deleteDelegateList(
											delegateId).then(function(data) {
										$scope.fetchDelegateList();

									});
								}

								// Function for update delegate list
								$scope.updateDelegateList = function(arr) {
									if (arr[0] != "" || arr[1] != ""
											|| arr[2] != "" || arr[3] != "") {
										$scope.delegateList = {
											id : $scope.editDelegateId,
											bid : $scope.decodedBookId,
											name : arr[0],
											schoolName : arr[1],
											role : arr[2],
											emailAddress : arr[3]
										}
										// $scope.saveCount = true;
										recordsService
												.updateDelegateList(
														$scope.delegateList)
												.then(
														function(data) {
															$scope
																	.fetchDelegateList();
															$scope.editDelegateId = '';
															if ($scope.saveCount == true) {
																$scope.editCount = true;
															}
														});
									} else {
										$scope.fetchDelegateList();
									}
								}
								$scope.logs = [];
								$scope.text = '';

								$scope.fetchLogs = function(decodedBookId,
										startDate, endDate) {
									console.log(decodedBookId, startDate,
											endDate);
									$scope.logs = [];
									$scope.text = '';
									$scope.logList = [];
									if (startDate != '' && endDate != '') {
										recordsService
												.fetchLogs(startDate, endDate,
														decodedBookId)
												.then(
														function(data) {
															console.log(data,
																	"record");
															$scope.logList = data;
															if ($scope.logList != ''
																	&& angular
																			.isDefined($scope.logList)) {
																console
																		.log($scope.logList);
																angular
																		.forEach(
																				$scope.logList,
																				function(
																						value,
																						key) {
																					$scope.textString = value.userName
																							+ ' '
																							+ 'Changed On '
																							+ value.entryDate
																							+ '\n'
																							+ value.comment;
																					$scope.text = $scope.text
																							+ '\n\n'
																							+ $scope.textString;
																					$scope.logs
																							.push($scope.text);
																				})
															} else {
																$scope.text = '';
															}
														});
									}
								}

								$scope.fetchDelegateList = function() {
									recordsService
											.findDelegateListByBId(
													$scope.decodedBookId)
											.then(
													function(data) {
														$scope.delegatesList = data;
														console
																.log($scope.delegatesList);
													});
								}
								$scope.onExit = function() {
									console.log("inside exit function");
									$location.path('/dashboard');

								}

								$scope.createAndSendInvoice = function() {
									var status = document
											.getElementById('status').value;
									var invoiceNum = document
											.getElementById('invoiceNum').value;
									var total = document
											.getElementById('total').value;

									$scope.invoiceDiscount = document
											.getElementById('discount').value;

									if (status != 'Booking') {
										console.log("inside if.............");
										$scope.modalInstance = $uibModal
												.open({
													templateUrl : 'view/records/modal/statusWindow.html',
												})

									} else if (invoiceNum != '') {
										$scope.recordDto.confirmation.linkSent = parseInt(0);
										$scope.recordDto.confirmation.infoSent = parseInt(0);
										var modalInstanceSecond = $uibModal
												.open({
													templateUrl : 'view/records/modal/modelWindow2.html',
													controller : 'modalCtrl',
													size : 'sm',
													scope : $scope,

												});
									} else if (total != '') {
										console.log("total......", total);
										$scope.recordDto.confirmation.linkSent = parseInt(0);
										$scope.recordDto.confirmation.infoSent = parseInt(0);
										var modalInstanceSecond = $uibModal
												.open({
													templateUrl : 'view/records/modal/modelWindow.html',
													size : 'xm',
													scope : $scope,

												});
									} else {
										console
												.log("Invoice Total does not contain any value, an Invoice can not be created!");
										console.log("inside else............");
										$scope.recordDto.confirmation.linkSent = parseInt(0);
										$scope.recordDto.confirmation.infoSent = parseInt(0);
										var modalInstanceSecond = $uibModal
												.open({
													templateUrl : 'view/records/modal/blankInvoiceWindow.html',
													// controller : 'modalCtrl',
													size : 'xm',
													scope : $scope,

												});

									}
								}

								$scope.ok = function() {
									var checkedValue = document
											.querySelector('.messageCheckbox:checked').value;
									console
											.log(
													"elseModalCtrl checkedValue.................",
													checkedValue);
									if (checkedValue == 'HSBC') {
										$scope.recordDto.invoice.paymentType = 'L'
										console
												.log("payment type ok.................");

									} else {
										$scope.recordDto.invoice.paymentType = 'S'
									}

									$scope.recordDto.confirmation.linkSent = parseInt(0);
									$scope.recordDto.confirmation.infoSent = parseInt(0);
									var modalInstanceSecond = $uibModal
											.open({
												templateUrl : 'view/records/modal/modelWindow2.html',
												controller : 'modalCtrl',
												size : 'sm',
												scope : $scope,

											});

								}

								$scope.sendInvoice = function() {
									$scope.createSendInvoice = true;
									$scope.getRecord();
								}

								$scope.saveSuccess = function() {
									$scope.modalInstance = $uibModal
											.open({
												templateUrl : 'view/records/modal/saveChangesWindow.html',
												controller : 'recordsCtrl',
												size : 'sm',

											})
								}

								$scope
										.$watch(
												"recordDto",
												function(newValue, oldValue) {
													$scope.logArr = [];
													$scope.changedObject = {
														key : '',
														value : '',
														prevValue : ''
													}
													angular
															.forEach(
																	$scope.editForm,
																	function(
																			value,
																			key) {
																		if (key[0] == '$')
																			return;
																		if (!value.$pristine) {
																			$scope.changedObject = {
																				key : key,
																				value : value.$modelValue,
																			}
																			$scope.logArr
																					.push($scope.changedObject);

																		}
																	});
												}, true);

								$scope.nextRecord = function() {
									$scope.logs = [];
									$scope.text = '';
									$scope.logList = [];
									if ($scope.index != undefined) {

										recordsService
												.getrecord(
														$scope.bookingListArr[$scope.index + 1])
												.then(
														function(data) {
															data[0].invoice.poNo = parseInt(data[0].invoice.poNo);
															$scope.recordDto = data[0];
															recordsService
																	.findDelegateListByBId(
																			$scope.bookingListArr[$scope.index + 1])
																	.then(
																			function(
																					data) {
																				$scope.delegatesList = data;
																			});
															$scope.decodedBookId = $scope.bookingListArr[$scope.index + 1];

															$scope.index = $scope.index + 1;

														});
									}
								}

								$scope.previousRecord = function() {
									$scope.logs = [];
									$scope.text = '';
									$scope.logList = [];

									if ($scope.index != '') {
										recordsService
												.getrecord(
														$scope.bookingListArr[$scope.index - 1])
												.then(
														function(data) {
															data[0].invoice.poNo = parseInt(data[0].invoice.poNo);
															$scope.recordDto = data[0];
															recordsService
																	.findDelegateListByBId(
																			$scope.bookingListArr[$scope.index - 1])
																	.then(
																			function(
																					data) {
																				$scope.delegatesList = data;
																			});
															$scope.decodedBookId = $scope.bookingListArr[$scope.index - 1];
															$scope.index = $scope.index - 1;

														});
									}
								}

								$scope.printPlanner = function() {
									var startTime = document
											.getElementById('startTime').value;
									var endTime = document
											.getElementById('endTime').value;
									var tr = document
											.getElementById('training');
									var training = tr.options[tr.selectedIndex].text;
									var title = document
											.getElementById('title').value;
									var fullName = document
											.getElementById('fullName').value;
									var schoolName = document
											.getElementById('schoolName').value;
									var email = document
											.getElementById('email').value;
									var invEmail = document
											.getElementById('invEmail').value;
									var enQuiry = document
											.getElementById('status').value;
									var trloc = document
											.getElementById('trLocation');
									var trLocation = trloc.options[trloc.selectedIndex].text;
									var telephone = document
											.getElementById('telephone').value;
									var trDate = document
											.getElementById('trDate').value;
									var delegNum = document
											.getElementById('delegNum').value;
									var message = document
											.getElementById('message').value;
									var secondDeleg = document
											.getElementById('secondDeleg').value;
									var thirdDeleg = document
											.getElementById('thirdDeleg').value;
									var schAddr = document
											.getElementById('schAddr').value;

									var obj = [
											{
												"key" : "Traning",
												"value" : training
											},
											{
												"key" : "Title",
												"value" : title
											},
											{
												"key" : "Full Name",
												"value" : fullName
											},
											{
												"key" : "E-mail",
												"value" : email
											},
											{
												"key" : "E-mail to send our invoice to",
												"value" : invEmail
											}, {
												"key" : "Enquiry/Booking?",
												"value" : enQuiry
											}, {
												"key" : "Training Location",
												"value" : trLocation
											}, {
												"key" : "Tel Number",
												"value" : telephone
											}, {
												"key" : "Training Date",
												"value" : trDate

											}, {
												"key" : "Traning",
												"value" : training
											}, {
												"key" : "No of delegates",
												"value" : delegNum
											}, {
												"key" : "Message",
												"value" : message

											}, {
												"key" : "Second Delegates",
												"value" : secondDeleg
											}, {
												"key" : "Third Delegates",
												"value" : thirdDeleg
											}, {
												"key" : "School Address",
												"value" : schAddr
											} ];

									var obj1 = [ {
										"key" : "Traning Date:",
										"value" : trDate
									}, {
										"key" : "Training start Time:",
										"value" : startTime
									}, {
										"key" : "Training end Time:",
										"value" : endTime
									} ]

									var globalCounter = 0;
									var tr = "<tr>";
									var tr1 = "<tr>";
									for (var i = 0; i < Object.keys(obj1).length; i++) {

										if (obj1[i].value
												.toString()
												.substring(
														obj1[i].value
																.toString()
																.indexOf('.'),
														obj1[i].value
																.toString().length) < 2)
											obj1[i].value += "0";

										tr1 += "<td>" + obj1[i].key + "</td>"
												+ "<td>"
												+ obj1[i].value.toString()
												+ "</td></tr>";

									}

									for (var i = 0; i < Object.keys(obj).length; i++) {

										if (obj[i].value.toString().substring(
												obj[i].value.toString()
														.indexOf('.'),
												obj[i].value.toString().length) < 2)
											obj[i].value += "0";
										if (i < 1) {
											tr += "<td style='background-color:#45B39D;color:white;font-size: 14pt;width:40px;font-family: Calibri'>"
													+ obj[i].key
													+ "</td>"
													+ "<td style='background-color:#45B39D;color:white;font-size:14pt;width:60px;font-family: Calibri'>"
													+ obj[i].value.toString()
													+ "</td></tr>";
										} else {
											tr += "<td style= 'border-bottom: 1px solid black;width:40px'>"
													+ obj[i].key
													+ "</td>"
													+ "<td style= 'border-bottom: 1px solid black;width:60px'>"
													+ obj[i].value.toString()
													+ "</td></tr>";
										}
									}

									var elementPage = '<html><head><title></title></head><body><div><table style="color:#45B39D;font-size:14pt;font-family: Calibri" width=100% >'
											+ '<tbody >'
											+ tr1
											+ '</tbody></table></div><br><div><table cellpadding="4" cellspacing="30px" style="border-collapse: collapse;border: 1px solid black;font-size:9pt;font-family: Arial" width=100%>'
											+ '<tbody >'
											+ tr
											+ '</tbody></table></div><br><br><br><div><span style="color:#45B39D;" >Train Ticket:<span></div><br><br><br><br><div><span style="color:#45B39D;">Hotel info:<span></div></body>';
									var myWindow = document.write(elementPage)
									document.body.innerHTML = elementPage;

									// window.print();

									window.setTimeout(
											"javascript:SetPaperSize();", 400);
									javascript: print();
									window.location.reload();

								}
								$scope.generatePdf = function() {
									var docDefinition = {
										content : [
												// if you don't need styles, you
												// can use a simple string to
												// define a paragraph
												'This is a standard paragraph, using default style',

												// using a { text: '...' }
												// object lets you set styling
												// properties
												{
													text : 'This paragraph will have a bigger font',
													fontSize : 15
												},

												// if you set pass an array
												// instead of a string, you'll
												// be able
												// to style any fragment
												// individually
												{
													text : [
															'This paragraph is defined as an array of elements to make it possible to ',
															{
																text : 'restyle part of it and make it bigger ',
																fontSize : 15
															}, 'than the rest.' ]
												} ]
									};
									pdfMake.createPdf(docDefinition).open();

								};

								$scope.exportPlanner = function() {
									console.log("exportPlanner");
									$scope.recordDto.confirmation.linkSent = parseInt(0);
									$scope.recordDto.confirmation.infoSent = parseInt(0);
									$scope.modalInstance = $uibModal
											.open({
												templateUrl : 'view/records/modal/modelWindow3.html',
												controller : 'modalInstanceCtrl',
												size : 'xm',
												scope : $scope,
											})
								}

								$scope.$watch(function(scope) {
									return scope.recordDto.booking.trLocation
								}, function(newValue, oldValue) {
									$scope.setDisableConfimLocAndInhouse();
								});

								$scope.setDisableConfimLocAndInhouse = function() {
									if (parseInt($scope.recordDto.booking.trLocation) === parseInt(1)) {
										console
												.log(
														$scope.recordDto.booking.trLocation,
														'inhouse',
														$scope.visibleConfirm);
										$scope.visibleConfirm = true;
										$scope.setDefaultValueOnLocationChange();
										$scope.calculateTotal();
									} else if(parseInt($scope.recordDto.booking.trLocation) === parseInt(16)){
										$scope.visibleConfirm = false;
										$scope.setDefaultValueOnLocationChange();
										$scope.calculateTotal();
									}
										else {
											console
													.log(
															$scope.recordDto.booking.trLocation,
															'loca',
															$scope.visibleConfirm);
											$scope.visibleConfirm = false;
										}
									}
								
								$scope.setDefaultValueOnLocationChange = function(){
									$scope.recordDto.invoice.firstDelegFee = 0.0;
									$scope.recordDto.invoice.secondDelegFee = 0.0;
									$scope.recordDto.invoice.thirdDelegFee = 0.0;
									$scope.recordDto.invoice.fourthDelegFee = 0.0;
									$scope.recordDto.invoice.softCost = 0.0;
									$scope.recordDto.invoice.softSetup = 0.0;
									$scope.recordDto.invoice.trainCost = 0.0;
									$scope.recordDto.invoice.additionalDelegates = 0.0;
									$scope.recordDto.invoice.travelCost = 0.0;
								}

								
								$scope.calculateTotal = function() {
									console
											.log("inside calculateTotal function");
									$scope.recordDto.confirmation.linkSent = parseInt(0);
									$scope.recordDto.confirmation.infoSent = parseInt(0);
									setTimeout(
											function() {
												recordsService
														.calculateTotal(
																$scope.recordDto)
														.then(
																function(data) {
																	console
																			.log(
																					data,
																					"calculate");
																	var vatAmmount = data.vatAmmount;
																	var total = data.total;
																	$scope.recordDto.invoice.vatAmount = vatAmmount;
																	$scope.recordDto.invoice.total = total;
																});
											}, 2);

								}

								$scope.chengeBookingByUser = function() {
									$scope.decodedBookId = $scope.selectedBookingId;
									$scope.getRecord();
								}
								$scope.changeVat = function() {
									console.log(
											$scope.recordDto.invoice.chkvat,
											"checked");
									$scope.recordDto.confirmation.linkSent = parseInt(0);
									$scope.recordDto.confirmation.infoSent = parseInt(0);
									if ($scope.recordDto.invoice.chkvat) {
										recordsService
												.calculateTotal(
														$scope.recordDto)
												.then(
														function(data) {
															console
																	.log(data,
																			"calculate");
															var vatAmmount = data.vatAmmount;
															var total = data.total;
															var value = total
																	- vatAmmount;
															var totalValue = value
																	+ vatAmmount;
															$scope.recordDto.invoice.total = totalValue;
															$scope.recordDto.invoice.vatAmount = vatAmmount;

														});

									} else {
										$scope.recordDto.invoice.vatAmount = 0;
										recordsService
												.calculateTotal(
														$scope.recordDto)
												.then(
														function(data) {
															console
																	.log(data,
																			"calculate");
															var vatAmmount = data.vatAmmount;
															var total = data.total;
															var value = total
																	- vatAmmount;
															$scope.recordDto.invoice.total = value;

														});

									}
								}

								$scope.clearFields = function() {
									$scope.recordDto = {
										booking : {
											bId : "",
											title : "",
											fName : "",
											scName : "",
											training : "",
											delegNum : "",
											secondDeleg : "",
											thirdDeleg : "",
											trLocation : "",
											email : "",
											invEmail : "",
											tel : "",
											trDateDummy : "",
											notes : "",
											status : "",
											fourthDeleg : "",
											submitDateTime : "",
											secondDelegEmail : "",
											thirdDelegEmail : "",
											fourthDelegEmail : "",
											callBackTime : "",
											startTime : "",
											endTime : "",
											flCost : "",
											trainGoCost : "",
											taxiCost : "",
											hotelCost : "",
											bookingPhase : "",
											consultant : "",
											hear : "",
											hearOther : "",
											bookingSession : "",
											reminder : "",
											bookingUser : "",
											calledUser : "",
											calledDate : "",
											callerNotes : "",
											typeOfCall : "",
											sId : "",
											demoDate : ""
										},
										invoice : {
											id : "",
											paymentType : "",
											additionalDelegates : "",
											vatAmount : "",
											chkvat : "",
											bId : "",
											firstDelegFee : "",
											secondDelegFee : "",
											thirdDelegFee : "",
											discount : "",
											cancsurCharge : "",
											schAddr : "",
											website : "",
											total : "",
											fourthDelegFee : "",
											venue : "",
											date : "",
											poNo : "",
											trainCost : "",
											travelCost : "",
											softCost : "",
											softSetup : "",
											invSentDate : "",
											payReceiveDateDummy : "",
											payReceiveBy : "",
											additionalNotes : "",
											invSentToSchFinance : "",
											bACSRefNo : "",
											reconsWithBank : "",
											paidByBacs : "",
											lTBacs : "",
											checkNo : "",
											accNo : "",
											sortCode : "",
											paidByCheque : "",
											lTCheque : "",
											chequeDateDummy : "",
											checqueDepDateDummy : "",
											bankReceiveDateDummy : "",
											raisedInvInPayPal : "",
											dateOfTransfToBankDummy : "",
											flCost : "",
											trainGoCost : "",
											taxiCost : "",
											hotelCost : "",
											LT : ""
										},
										confirmation : {
											bId : "",
											schoolGenRol : "",
											schConfirmDate : "",
											trainerConfirmed : "",
											rolRepRecieved : "",
											repPrinted : "",
											repPosted : "",
											presentPrep : "",
											presentOnlineDrive : "",
											oneDayRemind : "",
											oneWeekRemind : "",
											schoolAwareReq : "",
											userPasKnowSchool : "",
											delegLaptop : "",
											bringRolFFT : "",
											rolPecPrint : "",
											delegRem1Day : "",
											delegRem1Week : "",
											trainRem1Week : "",
											linkSent : "",
											infoSent : "",
											interestedAsset : "",
											callbackAsset : "",
											assetNotes : "",
											journeyPlan : "",
											attendanceConfirmed : "",
											inHouseRequirementConfirmedOn : "",
											inHouseNotes : "",
											trainingLocationRequirementConfirmedOn : "",
											trainingLocationDelegateConfirmedOn : "",
											softwareLinkSentOn : "",
											softwareInfoSentOn : "",
											attendanceConfirmedBy : "",
											receivedLocation : "",
											trainingMaterialCreated : "",
											informationOnSchools : "",
											hotelHasAProjector : "",
											hotelHasWifi : "",
											hotelHasExtensionLeads : "",
											hotelHasTea : "",
											hotelHasLunch : "",
											hotelHasAfterNoonTea : "",
											contactsReceived : "",
											contactsSigned : "",
											price : "",
											confirmationLocationNotes : "",
											confirmationLocationTrainingMaterialCreated : "",
											trainingMaterialSentOn : "",
											emailHotelToInform : "",
											confirmationTrainingMaterialReceivedOn : "",
											TrainerConfirmed2 : ""
										},
										courierTracking : {
											id : "",
											bId : "",
											sendDate : "",
											trackingId : "",
											courierCompany : "",
											websiteLink : "",
											deliveryConfirmedBy : "",
											receivedDateAndTime : "",
											comments : "",
										},
										delegateList : {
											id : "",
											bid : "",
											name : "",
											schoolName : "",
											role : "",
											emailAddress : ""
										},
										invNotesTab : {
											bId : "",
											inNotes : "",
											expectations : "",
										},
										logMaster : {
											id : "",
											userName : "",
											comment : "",
											entryDate : "",
											bookingId : "",
										},
										salesPerson : {
											sName : ""
										}
									}
								}

								$scope.setDisableConfimLocAndInhouse();
								$scope.fetchDelegateList()
								$scope.getTraining();
								$scope.getAllUser();
								$scope.getTrainingLocation();
								$scope.getAllConsultants();
								if ($scope.decodedBookId == 0) {
									$scope.recordDto.booking.callBackTime = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.booking.trDateDummy = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.invoice.date = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.invoice.payReceiveDateDummy = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.invoice.chequeDateDummy = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');

									$scope.recordDto.invoice.checqueDepDateDummy = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.invoice.bankReceiveDateDummy = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.invoice.dateOfTransfToBankDummy = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.confirmation.trainingLocationRequirementConfirmedOn = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.confirmation.trainingLocationDelegateConfirmedOn = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.confirmation.contactsSigned = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');

									$scope.recordDto.confirmation.trainingMaterialSentOn = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.confirmation.confirmationTrainingMaterialReceivedOn = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.confirmation.callbackAsset = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');

									$scope.recordDto.booking.demoDate = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.courierTracking.sendDate = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.courierTracking.receivedDateAndTime = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.booking.submitDateTime = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.recordDto.booking.calledDate = $filter(
											'date')(new Date(),
											'dd-MM-yyyy hh:mm:ss');
									$scope.startDate = $filter('date')(
											new Date(), 'dd-MM-yyyy hh:mm:ss');
									$scope.endDate = $filter('date')(
											new Date(), 'dd-MM-yyyy hh:mm:ss');

									console.log($scope.decodedBookId);
								} else {
									$scope.getRecord();
								}
								$scope.functionOnLoad();

							} ]);
})();