'use strict';

/**
 * @ngdoc directive
 * @name jDeriveApp.directive:datetimeFormater
 * @description
 * # datetimeFormater
 */
angular.module('jDeriveApp')
  .directive('datetimeFormater', function ($filter) {
      return {
          restrict: 'A',
          require: 'ngModel',
          scope: {
              myModel: '=?ngModel',
              myFormat: '=?datetimeFormater'
          },
          link: function postLink(scope, element, attrs, ngModel) {
              scope.$watch(function () {
                  return ngModel.$modelValue;
              }, function (newValue) {
                  element.val($filter('date')(ngModel.$modelValue, attrs.datetimeFormater));
              });
              
          }
      };
  });
