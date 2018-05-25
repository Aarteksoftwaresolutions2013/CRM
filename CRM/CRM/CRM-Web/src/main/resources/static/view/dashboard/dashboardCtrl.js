(function() {

    'use strict';

    angular
	    .module('CRMApp')
	    .controller(
		    'dashboardCtrl',
		    [
			    '$scope',
			    '$rootScope',
			    'dashboardService',
			    '$location',
			    '$sessionStorage',
			    '$localStorage',
			    function($scope, $rootScope, dashboardService,
				    $location, $sessionStorage, $localStorage) {

				$scope.bookingList = [];
				$rootScope.login = true;
				$scope.bookbid = "";
				$scope.bookingListArr = [];
				$scope.trainingList;
				$scope.total_count = 20;
				$scope.itemsPerPage = 10;
				$scope.itemsPerPageForView = 10;
				$scope.itemsfrom = 1;
				$scope.search = '';
				$scope.trBookEnq = '';
				$scope.trLocationSection = '';
				$scope.courseSection = '';
				$scope.softwareSection = '';
				$scope.confirmedSection = '';
				$scope.confirmedSectionInHouse = '';
				$scope.payedBySection = ''
			    $scope.fullNameValue = '';
				$scope.schoolNameValue = '';

				$scope.pagination = {
				    current : 1
				};

				$scope.statusList = [ "Show All", "Enquiry",
					"Booking", "Waiting List" ];

				$scope.yearList = [ "Show All", "2017" ];

				$scope.dateList = [];

				$scope.paymentBy = [ "Show All", "Cheque",
					"BACS", "Paypal", "Google checkout",
					"Cash" ];

				$scope.softwareList = {
					0 : "All Software products",
					14 : "ASSET for Primary Schools Software",
					15 : "ASSET for Secondary Schools Software",
					"EYFSonline" : "EYFSonline",
					"Show All" : "Show All" };

				$scope.notConfirmedLocationList = {
				    BringRolFFT : "Bring ROL FFT logins",
				    RolPecPrint : "Bring ROL report",
				    TrainerConfirmed : "Confirmed with trainer",
				    AttendanceConfirmed : "Delegate Confirmed attendance",
				    DelegLaptop : "Delegate will bring laptop",
				    PresentPrep : "Presentation prepared",
				    DelegRem1Day : "Remind delegate(s) 1 day before the training",
				    DelegRem1Week : "Remind delegate(s) 1 week  before the training",
				    TrainRem1Week : "Remind trainer 1 week  before the training",
				    RepPosted : "Reports posted",
				    RolPecPrint : "Reports printed",
				    RolRepRecieved : "Rol Report Received"

				};
				$scope.notConfirmedInHouseList = {
				    TrainerConfirmed : "Confirmed with trainer",
				    JourneyPlan : "Journey planner is done",
				    PresentOnlineDrive : "Presentation on the online drive and informed trainer",
				    PresentPrep : "Presentation Prepared",
				    OneDayRemind : "Remind schools 1 day before the training",
				    OneWeekRemind : "Remind schools 1 week before the training",
				    RepPosted : "Reports Posted",
				    RepPrinted : "Reports Printed",
				    RolRepRecieved : "ROL Report Received",
				    SchConfirmDate : "School confirmed the date",
				    SchoolGenRol : "School generated ROL logins for all delegates",
				    SchoolAwareReq : "schools aware of internet and projector requirements",
				    UserPasKnowSchool : "someone from school know username and password for RAISE AND FFT",
				    CourierTrackingid : "Tracking"
				};

				$scope.dashboardFilterDto = {
				    limit : 10,
				    trainingLocation : "Show All",
				    year : "Show All",
				    dates : "Show All",
				    trainingBookingEnquiry : "Show All",
				    schoolNames : "",
				    fullName : "",
				    course : "All courses",
				    courseBookingEnquiry : "Show All",
				    software : "Show All",
				    softwareBookingEnquiry : "Show All",
				    months : "Show All",
				    notConfirmedLocations : "Show All",
				    notConfirmedInHouse : "Show All",
				    payedBy : "Show All",
				    invoiceNumberDummy : "",
				    email : "",
				    telephone : "",   
				    status : 0,
				    noOfItems : $scope.itemsPerPage,
				    pageNo : $scope.pagination.current

				}

				$scope.dashboardFilterDtoDefault = {
				    limit : 10,
				    trainingLocation : "Show All",
				    year : "Show All",
				    dates : "Show All",
				    trainingBookingEnquiry : "Show All",
				    schoolNames : "",
				    fullName : "",
				    course : "All courses",
				    courseBookingEnquiry : "Show All",
				    software : "Show All",
				    softwareBookingEnquiry : "Show All",
				    months : "Show All",
				    notConfirmedLocations : "Show All",
				    notConfirmedInHouse : "Show All",
				    payedBy : "Show All",
				    invoiceNumberDummy : "",
				    email : "",
				    telephone : "",
				    status : 0,
				    noOfItems : $scope.itemsPerPage,
				    pageNo : $scope.pagination.current

				}

				$scope.sort = function(keyname) {
				    $scope.sortKey = keyname;
				    $scope.reverse = !$scope.reverse;

				}
				if (!$sessionStorage.user == "") {
				    localStorage.setItem('userName',
					    $sessionStorage.user.userName);
				    $rootScope.userName = localStorage
					    .getItem('userName');
				}
				$scope.functionOnLoad = function() {
				    jQuery(document).ready(function() {
					BootstrapTable.init()
				    });
				}

				$scope.getBooking = function() {
					if( $scope.dashboardFilterDto.fullName != ""){
						$scope.fullNameValue = $scope.dashboardFilterDto.fullName;
						$scope.dashboardFilterDto.fullName = '("%' + $scope.dashboardFilterDto.fullName + '%")'
					}
				    dashboardService
					    .getBooking(
						    $scope.dashboardFilterDto)
					    .then(
						    function(value) {
							$scope.bookingList = value;
							console
								.log(
									$scope.bookingList,
									"list",
									$scope.bookingList.length);
							angular
								.forEach(
									value,
									function(
										newValue,
										key) {
									    $scope.bookingListArr
										    .push(newValue.booking.bId);
									});
							if (!$sessionStorage.user == "") {
							    localStorage
								    .setItem(
									    'bookingListArr',
									    JSON
										    .stringify($scope.bookingListArr));
							}
							if ($scope.bookingList.length === parseInt(0)) {
							    $scope.total_count = 0;
							    $scope.itemsPerPageForView = 0;
							    console
								    .log(
									    parseInt(0),
									    $scope.dashboardFilterDto.pageNo);
							} else {
							    $scope.itemsPerPageForView = 1 * $scope.bookingList.length;
							    console.log("@@@@@@@@@@",$scope.itemsPerPageForView);
							    console
								    .log(
									    $scope.dashboardFilterDto,
									    "count",
									    $scope.dashboardFilterDto.pageNo);
							    $scope
								    .getTotalCount($scope.dashboardFilterDto);
							    $scope.dashboardFilterDto.fullName = $scope.fullNameValue;
							    $scope.fullNameValue = '';
							}

						    }, function(reason) {
							console.log(reason);
						    });
				}
				$scope.getAllDates = function() {
				    dashboardService.getAllTrainingDateByYear()
					    .then(function(data) {
						$scope.dateList = data;
						console.log(data);
					    });
				}

				$scope.getTrainingLocation = function() {
				    dashboardService
					    .getTrainingLocation()
					    .then(
						    function(data) {
							$scope.trainingLocationList = data;
							console.log(data);
						    });
				}

				$scope.getTraining = function() {
				    dashboardService.getTraining().then(
					    function(data) {
						$scope.trainingList = data;
						console.log(data);
					    });
				}

				$scope.editRecord = function(bookbid, index) {
				    // use for encoding the id
				    $scope.bookbid = btoa(bookbid);
				    $scope.firstRender = "firstRender";
				    console.log($scope.bookbid, "editrecord");
				    $location.path('/records/' + $scope.bookbid
					    + '/' + index + '/' + $scope.firstRender);
				}

				$scope.monthList = function() {
				    $scope.monthsYearList = [];
				    var months = [ "January", "February",
					    "March", "April", "May", "June",
					    "July", "August", "September",
					    "October", "November", "December" ];
				    var date = new Date();
				    var month = date.getMonth(), count = 1;
				    var year = date.getFullYear();
				    for (var i = month; i < 12; i++) {
					$scope.monthsYearList.push(months[i]
						+ " " + year);
					if (i == 11) {
					    i = -1;
					    year++;
					}
					if (count == 12) {
					    break;
					}
					count++;
				    }
				}

				$scope.viewReporting = function() {
				    $location.path('/reporting');
				}

				$scope.getBookingByFilters = function() {
				    $scope.dashboardFilterDto.pageNo = 1;
				    $scope.dashboardFilterDto.status = 0;

				    if ($scope.dashboardFilterDto.courseBookingEnquiry != 'Show All') {
					console
						.log($scope.dashboardFilterDto.courseBookingEnquiry);
					$scope.trBookEnq = $scope.dashboardFilterDto.trainingBookingEnquiry
					$scope.dashboardFilterDto.trainingBookingEnquiry = $scope.dashboardFilterDto.courseBookingEnquiry
				    } else {
					if ($scope.dashboardFilterDto.softwareBookingEnquiry != 'Show All') {
					    console
						    .log($scope.dashboardFilterDto.softwareBookingEnquiry);
					    $scope.trBookEnq = $scope.dashboardFilterDto.trainingBookingEnquiry
					    $scope.dashboardFilterDto.trainingBookingEnquiry = $scope.dashboardFilterDto.softwareBookingEnquiry
					}
				    }
				    if (angular.equals(
					    $scope.dashboardFilterDto,
					    $scope.dashboardFilterDtoDefault)) {
					$scope.dashboardFilterDto.status = 0;
					$scope.getBooking();
				    } else {
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    }

				}
				$scope.getBookingByFiltersFromTrLocationSection = function() {
				    $scope.trLocationSection = 'trLocationSection';
				    $scope.setFilterOnSectionChange();
				    $scope.dashboardFilterDto.pageNo = 1;
				    $scope.dashboardFilterDto.status = 0;
				    if (angular.equals(
					    $scope.dashboardFilterDto,
					    $scope.dashboardFilterDtoDefault)) {
					$scope.dashboardFilterDto.status = 0;
					$scope.getBooking();
				    } else {
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    }

				}
				$scope.getBookingByFiltersFromCourseSection = function() {
				    $scope.trLocationSection = '';
				    $scope.courseSection = 'courseSection';
				    $scope.setFilterOnSectionChange();
				    $scope.dashboardFilterDto.pageNo = 1;
				    $scope.dashboardFilterDto.status = 0;
				    if (angular.equals(
					    $scope.dashboardFilterDto,
					    $scope.dashboardFilterDtoDefault)) {
					$scope.dashboardFilterDto.status = 0;
					$scope.getBooking();
				    } else {
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    }

				}

				$scope.getBookingByFiltersFromSoftwareSection = function() {
				    $scope.trLocationSection = '';
				    $scope.courseSection = '';
				    $scope.softwareSection = 'softwareSection';

				    $scope.setFilterOnSectionChange();
				    $scope.dashboardFilterDto.pageNo = 1;
				    $scope.dashboardFilterDto.status = 0;
				    if (angular.equals(
					    $scope.dashboardFilterDto,
					    $scope.dashboardFilterDtoDefault)) {
					$scope.dashboardFilterDto.status = 0;
					$scope.getBooking();
				    } else {
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    }

				}

				$scope.getBookingByFiltersFromConfirmedSection = function() {
				    $scope.trLocationSection = '';
				    $scope.courseSection = ''
				    $scope.softwareSection = ''
				    $scope.confirmedSection = 'confirmedSection'

				    $scope.setFilterOnSectionChange();
				    $scope.dashboardFilterDto.pageNo = 1;
				    $scope.dashboardFilterDto.status = 0;
				    if (angular.equals(
					    $scope.dashboardFilterDto,
					    $scope.dashboardFilterDtoDefault)) {
					$scope.dashboardFilterDto.status = 0;
					$scope.getBooking();
				    } else {
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    }
				}
				
				$scope.getBookingByFiltersFromConfirmedSectionInHouse = function() {
				    $scope.trLocationSection = '';
				    $scope.courseSection = ''
				    $scope.softwareSection = ''
				    $scope.confirmedSection = ''
				    $scope.confirmedSectionInHouse = 'confirmedSectionInHouse'

				    $scope.setFilterOnSectionChange();
				    $scope.dashboardFilterDto.pageNo = 1;
				    $scope.dashboardFilterDto.status = 0;
				    if (angular.equals(
					    $scope.dashboardFilterDto,
					    $scope.dashboardFilterDtoDefault)) {
					$scope.dashboardFilterDto.status = 0;
					$scope.getBooking();
				    } else {
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    }
				}
				
				$scope.getBookingByFiltersFromPayedBySection = function() {
				    $scope.trLocationSection = '';
				    $scope.courseSection = '';
				    $scope.softwareSection = '';
				    $scope.confirmedSection = '';
				    $scope.confirmedSectionInHouse = ''
				    $scope.payedBySection = 'payedBySection';

				    $scope.setFilterOnSectionChange();
				    $scope.dashboardFilterDto.pageNo = 1;
				    $scope.dashboardFilterDto.status = 0;
				    if (angular.equals(
					    $scope.dashboardFilterDto,
					    $scope.dashboardFilterDtoDefault)) {
					$scope.dashboardFilterDto.status = 0;
					$scope.getBooking();
				    } else {
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    }

				}
				
				
				$scope.getBookingByFiltersFromSchoolName = function() {
				    $scope.trLocationSection = '';
				    $scope.courseSection = '';
				    $scope.softwareSection = '';
				    $scope.confirmedSection = '';
				    $scope.confirmedSectionInHouse = ''
				    $scope.payedBySection = '';
				    $scope.schoolNameValue = 'schoolNameValue';

				    $scope.setFilterOnSectionChange();
				    $scope.dashboardFilterDto.pageNo = 1;
				    $scope.dashboardFilterDto.status = 0;
				    if (angular.equals(
					    $scope.dashboardFilterDto,
					    $scope.dashboardFilterDtoDefault)) {
					$scope.dashboardFilterDto.status = 0;
					$scope.getBooking();
				    } else {
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    }

				}

				$scope.setFilterOnSectionChange = function() {
				    if ($scope.trLocationSection === 'trLocationSection') {
					$scope.dashboardFilterDto.schoolNames = "";
					$scope.dashboardFilterDto.fullName = "";
					$scope.dashboardFilterDto.course = "All courses";
					$scope.dashboardFilterDto.courseBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.software = "Show All";
					$scope.dashboardFilterDto.softwareBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.months = "Show All";
					$scope.dashboardFilterDto.notConfirmedLocations = "Show All";
					$scope.dashboardFilterDto.notConfirmedInHouse = "Show All";
					$scope.dashboardFilterDto.payedBy = "Show All";
					$scope.dashboardFilterDto.invoiceNumberDummy = "";
					$scope.dashboardFilterDto.telephone = "";
					$scope.dashboardFilterDto.status = 1;
					$scope.dashboardFilterDto.email = '';
				    } else {
					if ($scope.courseSection === 'courseSection') {

					    $scope.trBookEnq = $scope.dashboardFilterDto.trainingBookingEnquiry;
					    $scope.dashboardFilterDto.softwareBookingEnquiry = 'Show All';
					    $scope.dashboardFilterDto.trainingBookingEnquiry = $scope.dashboardFilterDto.courseBookingEnquiry
					    $scope.dashboardFilterDto.trainingLocation = "Show All";
					    $scope.dashboardFilterDto.year = "Show All";
					    $scope.dashboardFilterDto.dates = "Show All";
					    $scope.dashboardFilterDto.schoolNames = "";
					    $scope.dashboardFilterDto.fullName = "";
					    $scope.dashboardFilterDto.software = "Show All";
					    $scope.dashboardFilterDto.softwareBookingEnquiry = "Show All";
					    $scope.dashboardFilterDto.months = "Show All";
					    $scope.dashboardFilterDto.notConfirmedLocations = "Show All";
					    $scope.dashboardFilterDto.notConfirmedInHouse = "Show All";
					    $scope.dashboardFilterDto.payedBy = "Show All";
					    $scope.dashboardFilterDto.invoiceNumberDummy = "";
					    $scope.dashboardFilterDto.telephone = "";
					    $scope.dashboardFilterDto.status = 1;
					    $scope.dashboardFilterDto.email = '';
					} else {
					    if ($scope.softwareSection === 'softwareSection') {
					    	console.log($scope.dashboardFilterDto.software);
                        if($scope.dashboardFilterDto.software == '0'){
                        	console.log($scope.dashboardFilterDto.software);
                        	var softIds = ['14', '15'];
                        	console.log(softIds.toString());
                        	$scope.dashboardFilterDto.software = softIds.toString();
                        } 
						$scope.trBookEnq = $scope.dashboardFilterDto.trainingBookingEnquiry;
						$scope.dashboardFilterDto.courseBookingEnquiry = 'Show All';
						$scope.dashboardFilterDto.trainingBookingEnquiry = $scope.dashboardFilterDto.softwareBookingEnquiry
						$scope.dashboardFilterDto.trainingLocation = "Show All";
						$scope.dashboardFilterDto.year = "Show All";
						$scope.dashboardFilterDto.dates = "Show All";
						$scope.dashboardFilterDto.schoolNames = "";
						$scope.dashboardFilterDto.fullName = "";
						$scope.dashboardFilterDto.course = "All courses";
						$scope.dashboardFilterDto.courseBookingEnquiry = "Show All";
						$scope.dashboardFilterDto.months = "Show All";
						$scope.dashboardFilterDto.notConfirmedLocations = "Show All";
						$scope.dashboardFilterDto.notConfirmedInHouse = "Show All";
						$scope.dashboardFilterDto.payedBy = "Show All";
						$scope.dashboardFilterDto.invoiceNumberDummy = "";
						$scope.dashboardFilterDto.telephone = "";
						$scope.dashboardFilterDto.status = 1;
						$scope.dashboardFilterDto.email = '';
					    }else{
					    if ($scope.confirmedSection === 'confirmedSection') {
						$scope.dashboardFilterDto.trainingLocation = "Show All";
						$scope.dashboardFilterDto.year = "Show All";
						$scope.dashboardFilterDto.dates = "Show All";
						$scope.dashboardFilterDto.trainingBookingEnquiry = "Show All";
						$scope.dashboardFilterDto.schoolNames = "";
						$scope.dashboardFilterDto.fullName = "";
						$scope.dashboardFilterDto.course = "All courses";
						$scope.dashboardFilterDto.courseBookingEnquiry = "Show All";
						$scope.dashboardFilterDto.software = "Show All";
						$scope.dashboardFilterDto.softwareBookingEnquiry = "Show All";
						$scope.dashboardFilterDto.months = "Show All";
						$scope.dashboardFilterDto.payedBy = "Show All";
						$scope.dashboardFilterDto.notConfirmedInHouse = "Show All";
						$scope.dashboardFilterDto.invoiceNumberDummy = "";
						$scope.dashboardFilterDto.telephone = "";
						$scope.dashboardFilterDto.status = 1;
						$scope.dashboardFilterDto.email = '';
					    }
					    else{
					    	if ($scope.confirmedSectionInHouse === 'confirmedSectionInHouse') {
								$scope.dashboardFilterDto.trainingLocation = "Show All";
								$scope.dashboardFilterDto.year = "Show All";
								$scope.dashboardFilterDto.dates = "Show All";
								$scope.dashboardFilterDto.trainingBookingEnquiry = "Show All";
								$scope.dashboardFilterDto.schoolNames = "";
								$scope.dashboardFilterDto.fullName = "";
								$scope.dashboardFilterDto.course = "All courses";
								$scope.dashboardFilterDto.courseBookingEnquiry = "Show All";
								$scope.dashboardFilterDto.software = "Show All";
								$scope.dashboardFilterDto.softwareBookingEnquiry = "Show All";
								$scope.dashboardFilterDto.months = "Show All";
								$scope.dashboardFilterDto.payedBy = "Show All";
								$scope.dashboardFilterDto.notConfirmedLocations = "Show All";
								$scope.dashboardFilterDto.invoiceNumberDummy = "";
								$scope.dashboardFilterDto.telephone = "";
								$scope.dashboardFilterDto.status = 1;
								$scope.dashboardFilterDto.email = '';
							    }else{
							    	if ($scope.payedBySection === 'payedBySection') {
										$scope.dashboardFilterDto.trainingLocation = "Show All";
										$scope.dashboardFilterDto.year = "Show All";
										$scope.dashboardFilterDto.dates = "Show All";
										$scope.dashboardFilterDto.trainingBookingEnquiry = "Show All";
										$scope.dashboardFilterDto.schoolNames = "";
										$scope.dashboardFilterDto.fullName = "";
										$scope.dashboardFilterDto.course = "All courses";
										$scope.dashboardFilterDto.courseBookingEnquiry = "Show All";
										$scope.dashboardFilterDto.software = "Show All";
										$scope.dashboardFilterDto.softwareBookingEnquiry = "Show All";
										$scope.dashboardFilterDto.months = "Show All";
										$scope.dashboardFilterDto.notConfirmedLocations = "Show All";
										$scope.dashboardFilterDto.notConfirmedInHouse = "Show All";
										$scope.dashboardFilterDto.invoiceNumberDummy = "";
										$scope.dashboardFilterDto.telephone = "";
										$scope.dashboardFilterDto.status = 1;
										$scope.dashboardFilterDto.email = '';
									    }else{
									    	if($scope.schoolNameValue === 'schoolNameValue'){
									    		$scope.dashboardFilterDto.trainingLocation = "Show All";
												$scope.dashboardFilterDto.year = "Show All";
												$scope.dashboardFilterDto.dates = "Show All";
												$scope.dashboardFilterDto.trainingBookingEnquiry = "Show All";
												$scope.dashboardFilterDto.payedBy = "Show All";
												$scope.dashboardFilterDto.fullName = "";
												$scope.dashboardFilterDto.course = "All courses";
												$scope.dashboardFilterDto.courseBookingEnquiry = "Show All";
												$scope.dashboardFilterDto.software = "Show All";
												$scope.dashboardFilterDto.softwareBookingEnquiry = "Show All";
												$scope.dashboardFilterDto.months = "Show All";
												$scope.dashboardFilterDto.notConfirmedLocations = "Show All";
												$scope.dashboardFilterDto.notConfirmedInHouse = "Show All";
												$scope.dashboardFilterDto.invoiceNumberDummy = "";
												$scope.dashboardFilterDto.telephone = "";
												$scope.dashboardFilterDto.status = 1;
												$scope.dashboardFilterDto.email = '';
									    	}
									    }
							    }
					    }
					}}
				    }
				}

				$scope.searchDefaultValues = function() {
				    console
					    .log($scope.dashboardFilterDto.email);
				    if ($scope.dashboardFilterDto.email != ''
					    && $scope.dashboardFilterDto.email != undefined) {
					$scope.dashboardFilterDto.trainingLocation = "Show All";
					$scope.dashboardFilterDto.year = "Show All";
					$scope.dashboardFilterDto.dates = "Show All";
					$scope.dashboardFilterDto.trainingBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.schoolNames = "";
					$scope.dashboardFilterDto.fullName = "";
					$scope.dashboardFilterDto.course = "All courses";
					$scope.dashboardFilterDto.courseBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.software = "Show All";
					$scope.dashboardFilterDto.softwareBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.months = "Show All";
					$scope.dashboardFilterDto.notConfirmedLocations = "Show All";
					$scope.dashboardFilterDto.notConfirmedInHouse = "Show All";
					$scope.dashboardFilterDto.payedBy = "Show All";
					$scope.dashboardFilterDto.invoiceNumberDummy = "";
					$scope.dashboardFilterDto.telephone = "";
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    }

				}

				$scope.defaultValuesTelephone = function() {
				    if ($scope.dashboardFilterDto.telephone != ''
					    && $scope.dashboardFilterDto.telephone != undefined) {
					$scope.dashboardFilterDto.trainingLocation = "Show All";
					$scope.dashboardFilterDto.year = "Show All";
					$scope.dashboardFilterDto.dates = "Show All";
					$scope.dashboardFilterDto.trainingBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.schoolNames = "";
					$scope.dashboardFilterDto.fullName = "";
					$scope.dashboardFilterDto.course = "All courses";
					$scope.dashboardFilterDto.courseBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.software = "Show All";
					$scope.dashboardFilterDto.softwareBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.months = "Show All";
					$scope.dashboardFilterDto.notConfirmedLocations = "Show All";
					$scope.dashboardFilterDto.notConfirmedInHouse = "Show All";
					$scope.dashboardFilterDto.payedBy = "Show All";
					$scope.dashboardFilterDto.invoiceNumberDummy = "";
					$scope.dashboardFilterDto.email = "";
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    }
				}

				$scope.defaultValuesInvoiceNumber = function() {
				
					$scope.dashboardFilterDto.trainingLocation = "Show All";
					$scope.dashboardFilterDto.year = "Show All";
					$scope.dashboardFilterDto.dates = "Show All";
					$scope.dashboardFilterDto.trainingBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.schoolNames = "";
					$scope.dashboardFilterDto.fullName = "";
					$scope.dashboardFilterDto.course = "All courses";
					$scope.dashboardFilterDto.courseBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.software = "Show All";
					$scope.dashboardFilterDto.softwareBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.months = "Show All";
					$scope.dashboardFilterDto.notConfirmedLocations = "Show All";
					$scope.dashboardFilterDto.notConfirmedInHouse = "Show All";
					$scope.dashboardFilterDto.payedBy = "Show All";
					$scope.dashboardFilterDto.telephone = "";
					$scope.dashboardFilterDto.email = "";
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    
				}

				$scope.defaultValuesFullNames = function() {
				    if ($scope.dashboardFilterDto.fullName != ''
					    && $scope.dashboardFilterDto.fullName != undefined) {
					$scope.dashboardFilterDto.trainingLocation = "Show All";
					$scope.dashboardFilterDto.year = "Show All";
					$scope.dashboardFilterDto.dates = "Show All";
					$scope.dashboardFilterDto.trainingBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.schoolNames = "";
					$scope.dashboardFilterDto.invoiceNumberDummy = "";
					$scope.dashboardFilterDto.course = "All courses";
					$scope.dashboardFilterDto.courseBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.software = "Show All";
					$scope.dashboardFilterDto.softwareBookingEnquiry = "Show All";
					$scope.dashboardFilterDto.months = "Show All";
					$scope.dashboardFilterDto.notConfirmedLocations = "Show All";
					$scope.dashboardFilterDto.notConfirmedInHouse = "Show All";
					$scope.dashboardFilterDto.payedBy = "Show All";
					$scope.dashboardFilterDto.telephone = "";
					$scope.dashboardFilterDto.email = "";
					$scope.dashboardFilterDto.status = 1;
					$scope.getBooking();
				    }
				}

				$scope.pageChanged = function(newPage) {
				    console.log(newPage);
				    if (angular.equals(
					    $scope.dashboardFilterDto,
					    $scope.dashboardFilterDtoDefault)) {
					$scope.dashboardFilterDto.status = 0;
				    } else {
					$scope.dashboardFilterDto.status = 1;
				    }
				    $scope.dashboardFilterDto.pageNo = newPage;
				    dashboardService
					    .getBooking(
						    $scope.dashboardFilterDto)
					    .then(
						    function(value) {
							$scope.bookingList = value;
							console
								.log(
									$scope.bookingList,
									"listSecond",
									$scope.bookingList.length);
							if ($scope.bookingList.length < 10) {
							    $scope.itemsPerPageForView = $scope.total_count;
							    $scope.itemsfrom = (newPage - 1) * 10;
							    console
								    .log(newPage - 1);
							} else {
							    $scope.itemsPerPageForView = newPage * 10;
							    $scope.itemsfrom = $scope.itemsPerPageForView - 10;
							}

							if ($scope.itemsfrom === 0) {
							    $scope.itemsfrom = 1;
							}

							angular
								.forEach(
									value,
									function(
										newValue,
										key) {
									    $scope.bookingListArr
										    .push(newValue.booking.bId);
									});
							if (!$sessionStorage.user == "") {
							    localStorage
								    .setItem(
									    'bookingListArr',
									    JSON
										    .stringify($scope.bookingListArr));
							}

						    }, function(reason) {
							console.log(reason);
						    });

				};
				$scope.pageCount = function(filterDto) {
				   // console.log("!!!!!123",$scope.dashboardFilterdto)
				    dashboardService
					    .getCountOfAllBooking(filterDto)
					    .then(
						    function(value) {
							console.log(value);
							$scope.total_count = value;
							console.log("!@#$%%%%^",$scope.total_count)
							if ($scope.trBookEnq != '') {
							    $scope.dashboardFilterDto.trainingBookingEnquiry = 'Show All';
							    $scope.trBookEnq = '';
							}
							
						    }, function(reason) {
							console.log(reason);
						    });

				}
				$scope.getTotalCount = function(filterDto) {
				    $scope.pageCount(filterDto);
				    console.log(filterDto);
				}
				
				$scope.$watch(function(scope) 
					{ return scope.dashboardFilterDto.trainingLocation },
				              function(newValue, oldValue) {
				                 console.log(newValue, oldValue);
				                 if(newValue != oldValue && newValue != 'Show All' && oldValue != -1 && newValue != -1 && oldValue !=""){
				                     console.log(newValue);
				                 $scope.dashboardFilterDto.dates = "Show All";}
				              }
				 );

				$scope.getBooking();
				$scope.trainingLocationList;
				$scope.getTrainingLocation();
				$scope.getTraining();
				$scope.monthList();
				$scope.getAllDates();

			    } ]);
})();