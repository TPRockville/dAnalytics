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
  })
.filter('timezoneFormat', function (moment) {
    return function (input, format) {
        return moment(input.slice(0,10)).format(format);
    };
});
