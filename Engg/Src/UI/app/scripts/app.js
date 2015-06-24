'use strict';

/**
 * @ngdoc overview
 * @name jDeriveApp
 * @description
 * # jDeriveApp
 *
 * Main module of the application.
 */

// First, checks if it isn't implemented yet.
if (!String.prototype.format) {
    String.prototype.format = function () {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function (match, number) {
            return typeof args[number] != 'undefined'
              ? args[number]
              : match
            ;
        });
    };
}

angular
  .module('jDeriveApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap.datetimepicker',
    'ui.select2',
    'gridshore.c3js.chart',
    'angucomplete-alt',
    'angularMoment'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
        .when('/details', {
            templateUrl: 'views/details.html',
            controller: 'DetailsCtrl'
        })
      .otherwise({
        redirectTo: '/'
      });
  });
