(function () {
   'use strict';

   angular.module('CRMApp')
       .directive('requiredField', requiredField);

   function requiredField() {
      var directive = {
          restrict: 'E',
          templateUrl: 'js/directive/required/requiredField.html'
      };
      return directive;
   }
})();