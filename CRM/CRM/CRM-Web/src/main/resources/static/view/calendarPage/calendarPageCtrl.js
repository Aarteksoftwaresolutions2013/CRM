 (function() {
	'use strict';
	angular.module('CRMApp').controller(
			'calendarPageCtrl',
			
			[ '$scope', '$rootScope', 'calendarPageService', '$location','$filter','$window', '$localStorage',
					function($scope, $rootScope, calendarPageService, $location,$filter,$window, $localStorage) {
						$rootScope.login = true;

						$scope.allCalendarLocationDate = {
							title : "",
							start : ""
						}
						
						$scope.listOfDates = [];
						// $scope.list = [];
						$scope.listOfDatesAndLocation = [];
						$scope.functionOnLoad = function(){
							jQuery(document).ready(function() {
								AppCalendar.init()
							});
						}
						
						$scope.calendarColor = {
								id : '',
								date : '',
								dateDummy : '',
								color : '',
						}
						
						$scope.calendarDTO = {
								year :  " ",
								month : " ",
								phase : "Show All",
								consultant: "All"
						}
						
					 
                        $scope.calendarColors = {
						
						"rgb(154, 205, 50)" :  "YellowGreen",
						"rgb(255, 250, 205)" :  "Info",
						"rgb(135, 206, 250)" :  "LightSkyBlue",
						"rgb(221, 160, 221)" :  "Plum",
						"rgb(255, 165, 0)" :    "Orange",
						"rgb(255, 255, 255)" :  "Control"
				       }
						
						$scope.phaseList = [ "Infant", "Junior", "Primary", "Secondary", "Special Primary", "Special Secondary", "Independent", "Consultant", "Pri & Sec", "Conditional Booking" ];
						$scope.consultantList=[
							{
								id:'0',
								consultants:'Not Allocated'	
							},
							{
								id:'0',
								consultants:'Enquiries'	
							},
							{
								id:'0',
								consultants:'Meeting Room'	
							},
							{
								id:'0',
								consultants:'Donations'	
							},
							{
								id:'0',
								consultants:'Courses'	
							}
						]
						$scope.getAllLocationDate = function() {
							$scope.allLocationList = [];
						    $scope.listOfDates = [];
						    $scope.calendarDTO.year = new Date().getFullYear();
						    $scope.calendarDTO.month = new Date().getMonth() + 1;
						    console.log(new Date().getFullYear(), new Date().getMonth());
						    calendarPageService.getAllLocationDate($scope.calendarDTO).then(
							    function(value) {
								console.log("calender ctrl",value)
								$scope.listOfDatesAndLocation = value;
								angular.forEach($scope.listOfDatesAndLocation, 
									function(value, key){
								    var date = new Date(value.tDate);
								    var year = date.getFullYear();
								    var month = date.getMonth();
								    var day = date.getDate();
								    $scope.allCalendarLocationDate = {
											title : value.location,
											start : new Date(year, month, day),
											id : value.bId,
											backgroundColor: value.backgroudColor,
											textColor: 'black',
								
										}
								    $scope.listOfDates.push($scope.allCalendarLocationDate);
								});
								$scope.allLocationList =  $scope.listOfDates;
								$('#calendar').fullCalendar('removeEvents');
								$('#calendar').fullCalendar( 'addEventSource', $scope.allLocationList, true);
							    });
						}
						$scope.selectedItemChanged = function(data){
							 $scope.filterdlist = [];
							$('#calendar').fullCalendar('removeEvents');
							$scope.listOfDates = [];
							 $scope.calendarDTO.year = new Date().getFullYear();
							 $scope.calendarDTO.month = new Date().getMonth() + 1;
						    $scope.content= angular.element(document.querySelector('.fc-left'));
							 var text = $scope.content[0].innerText;
							 var date = text;
							 $scope.newDate =new Date(date);
							 console.log($scope.newDate);
							 $scope.mm = $filter('date')(new Date(date), 'yyyy,M');
							 var d = $scope.mm;
							 var newDate = [];
							 newDate = d.split(",");
							 console.log(newDate);
							 $scope.calendarDTO.year = newDate[0];
							 $scope.calendarDTO.month = newDate[1];
							 calendarPageService.getAllLocationDate($scope.calendarDTO).then(
									function(value) {
								$scope.listOfDatesAndLocation = value;
								angular.forEach($scope.listOfDatesAndLocation, 
									function(value, key){
								    var date = new Date(value.tDate);
								    var year = date.getFullYear();
								    var month = date.getMonth();
								    var day = date.getDate();
								    $scope.allCalendarLocationDate = {
											title : value.location,
											start : new Date(year, month, day),
											id : value.bId,
											backgroundColor: value.backgroudColor,
											textColor: 'black',
								
										}
								    $scope.listOfDates.push($scope.allCalendarLocationDate);
								});
								$scope.filterdlist =  $scope.listOfDates;
								$('#calendar').fullCalendar( 'addEventSource', $scope.filterdlist, true);
							    });
						}
						
						$scope.getAllConsultants = function(){
							$scope.listOfConsultants = [];
							calendarPageService.getAllConsultants().then(function(data){
								$scope.listOfConsultantList = data;
								console.log("listOfConsultants",data)
								$scope.listOfConsultants=$scope.listOfConsultantList.concat($scope.consultantList);
							}),function(error) {
								  console.log("inside error function");
					      }
							
						}
						 $scope.getselectedConsultants = function(){
							 $scope.consulatntFilterdList = [];
							 $('#calendar').fullCalendar('removeEvents');
								$scope.listOfDates = [];
								 $scope.calendarDTO.year = new Date().getFullYear();
								 $scope.calendarDTO.month = new Date().getMonth() + 1;
								 $scope.content= angular.element(document.querySelector('.fc-left'));
								 var text = $scope.content[0].innerText;
								 var date = text;
								 $scope.newDate =new Date(date);
								 console.log($scope.newDate);
								 $scope.mm = $filter('date')(new Date(date), 'yyyy,M');
								 var d = $scope.mm;
								 var newDate = [];
								 newDate = d.split(",");
								 console.log(newDate);
								 $scope.calendarDTO.year = newDate[0];
								 $scope.calendarDTO.month = newDate[1];
								 calendarPageService.getAllLocationDate($scope.calendarDTO).then(
										function(value) {
									$scope.listOfDatesAndLocation = value;
									angular.forEach($scope.listOfDatesAndLocation, 
										function(value, key){
									    var date = new Date(value.tDate);
									    var year = date.getFullYear();
									    var month = date.getMonth();
									    var day = date.getDate();
									    $scope.allCalendarLocationDate = {
												title : value.location,
												start : new Date(year, month, day),
												id : value.bId,
												backgroundColor: value.backgroudColor,
												textColor: 'black',
									
											}
									    $scope.listOfDates.push($scope.allCalendarLocationDate);
									});
									$scope.consulatntFilterdList =  $scope.listOfDates;
									$('#calendar').fullCalendar( 'addEventSource', $scope.consulatntFilterdList, true);
								    });
							  }
						
						

						$scope.anyFunction = function(id, selectedCalendarTrDate) {
							if(id.length > 0){
								console.log(id, selectedCalendarTrDate);
	                            $scope.bookbid = btoa(id[0]);
								console.log($scope.bookbid,$location);
								 localStorage.setItem('selectedCalendarTrDate',
										 selectedCalendarTrDate, "selectedCalendarTrDate..............");
								$location.path('/records/' + $scope.bookbid
										+ '/' + -1 +'/'+'firstRender');
								 setTimeout(() => {
									$scope.$apply();
								}, 300);
							}
							
						};
						
						$scope.prevClick = function(){
							$('#calendar').fullCalendar('removeEvents');
							 $scope.prevList = [];
							$scope.listOfDates = [];
							 $scope.calendarDTO.year = new Date().getFullYear();
							 $scope.calendarDTO.month = new Date().getMonth() + 1;
							console.log("prev is clicked");
							 $scope.content= angular.element(document.querySelector('.fc-left'));
							 var text = $scope.content[0].innerText;
							 var date = text;
							 $scope.newDate =new Date(date);
							 console.log($scope.newDate);
							 $scope.mm = $filter('date')(new Date(date), 'yyyy,M');
							 var d = $scope.mm;
							 var newDate = [];
							 newDate = d.split(",");
							 console.log(newDate);
							 $scope.calendarDTO.year = newDate[0];
							 $scope.calendarDTO.month = newDate[1];
							 calendarPageService.getAllLocationDate($scope.calendarDTO).then(
									    function(value) {
										console.log("calender ctrl",value)
										$scope.listOfDatesAndLocation = value;
										angular.forEach($scope.listOfDatesAndLocation, 
											function(value, key){
										    var date = new Date(value.tDate);
										    var year = date.getFullYear();
										    var month = date.getMonth();
										    var day = date.getDate();
										    $scope.allCalendarLocationDate = {
													title : value.location,
													start : new Date(year, month, day),
													id : value.bId,
													backgroundColor: value.backgroudColor,
													textColor: 'black',
										
												}
										    $scope.listOfDates.push($scope.allCalendarLocationDate);
										});
										$scope.prevList =  $scope.listOfDates;
										$('#calendar').fullCalendar('removeEvents');
										$('#calendar').fullCalendar( 'addEventSource', $scope.prevList, true);
										console.log($scope.list);
									    });
							 $('table.calendar > tbody > tr > td:nth-child(-n+2)').addClass('weekend');
							
						};
						$scope.nextClick = function(){
							$('#calendar').fullCalendar('removeEvents');
							$scope.nextList = [];
							$scope.listOfDates = [];
							 $scope.calendarDTO.year = new Date().getFullYear();
							 $scope.calendarDTO.month = new Date().getMonth() + 1;
							console.log("next is clicked");
							 $scope.content= angular.element(document.querySelector('.fc-left'));
							 var text = $scope.content[0].innerText;
							 var date = text;
							 $scope.newDate =new Date(date);
							 console.log($scope.newDate);
							 $scope.mm = $filter('date')(new Date(date), 'yyyy,M');
							 var d = $scope.mm;
							 var newDate = [];
							 newDate = d.split(",");
							 console.log(newDate);
							 $scope.calendarDTO.year = newDate[0];
							 $scope.calendarDTO.month = newDate[1];
							// console.log(year);
							 calendarPageService.getAllLocationDate($scope.calendarDTO)
						      .then(function(value) {
						    	  $scope.listOfDatesAndLocation = value;
						    	console.log("inside success function", $scope.listOfDatesAndLocation);
						    	if($scope.listOfDatesAndLocation.length != 0){
						    	angular.forEach($scope.listOfDatesAndLocation, 
										function(value, key){
									    var date = new Date(value.tDate);
									    var year = date.getFullYear();
									    var month = date.getMonth();
									    var day = date.getDate();
									    $scope.allCalendarLocationDate = {
												title : value.location,
												start : new Date(year, month, day),
												id : value.bId,
												backgroundColor: value.backgroudColor,
												textColor: 'black',
									
											}
									    $scope.listOfDates.push($scope.allCalendarLocationDate);
									});
									$scope.nextList =  $scope.listOfDates;
									console.log($scope.nextList, "next");
									$('#calendar').fullCalendar('removeEvents');
					    	       $('#calendar').fullCalendar( 'addEventSource', $scope.nextList, true);
						    	}else{
						    	$scope.nextList = [];
								$('#calendar').fullCalendar( 'addEventSource', $scope.nextList, true);
						    	
						    	}
								  },function(error) {
									  
							   console.log("inside error function");
							   
							 });
							 $('table.calendar > tbody > tr > td:nth-child(-n+2)').addClass('weekend');
						};
						
						$scope.saveCalendarColor = function(){
							console.log("...........", $scope.calendarColor);
							console.log("app");
							calendarPageService.saveCalendarColor(
									$scope.calendarColor).then(
									function(data) {
										
										// $scope.getAllLocationDate();
									});
						
							
						}
						$scope.dateClick = function(date, bgColor) {
							angular.forEach($scope.calendarColors, function(colorName, key){
								console.log(colorName, key);
								if(bgColor == key){
									console.log(colorName, "inside if", date);
									$scope.calendarColor.dateDummy = date;
									$scope.calendarColor.color = colorName;
									$scope.saveCalendarColor();
								}
						  })
						  console.log("inside date click function", date, bgColor );
							
                         };
						
						
						$scope.viewLocation = function(){
							$location.path('/addLocation');
						}
						
						$scope.viewDate = function(){
							$location.path('/addDate');
						}
						$scope.functionOnLoad();
						$scope.getAllConsultants();
						
					} ]);

})();
