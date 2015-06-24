'use strict';

/**
 * @ngdoc directive
 * @name jDeriveApp.directive:daDateformat
 * @description
 * # daDateformat
 */
angular.module('jDeriveApp')
  .directive('daDateformat', function (moment) {
    return {
      restrict: 'A',
      link: function postLink(scope, element, attrs) {
        element.text('this is the daDateformat directive');
      }
    };
  });
