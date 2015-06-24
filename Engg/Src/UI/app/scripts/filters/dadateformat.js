'use strict';

/**
 * @ngdoc filter
 * @name jDeriveApp.filter:daDateformat
 * @function
 * @description
 * # daDateformat
 * Filter in the jDeriveApp.
 */
angular.module('jDeriveApp')
  .filter('daDateformat', function (moment) {
      return function (input, inpitformat, format) {
          return moment(input, inpitformat).format(format);
      };
  });
