'use strict';

/**
 * @ngdoc service
 * @name jDeriveApp.basic
 * @description
 * # basic
 * Service in the jDeriveApp.
 */
angular.module('jDeriveApp')
  .service('basicService', function ($http, $q, configService) {
      var services = {};

      services.getCountbyReceivedDate = function (fromDate, toDate, countBy) {
          var url = 'https://api.fda.gov/drug/event.json?search=receivedate:[' + fromDate + '+TO+' + toDate + ']&count=' + countBy;
          var deferred = $q.defer();
          $http.get(url)
              .success(function (data) {
                  deferred.resolve(data);
              })
              .error(function () {
                  deferred.reject('Failed to get details.');
              });
          return deferred.promise;
      };

      services.getDrungDetails = function () {
          var deferred = $q.defer();

          var data = [
              { drugName: '81 MG ASPIRIN ', reaction: 'Depression', count: '3121' },
              { drugName: 'ADDERALL', reaction: 'Mood swings', count: '4526' },
              { drugName: 'CALCITRIOL', reaction: 'Somnolence', count: '32123' }
          ];

          deferred.resolve(data);
          return deferred.promise;
      };

      services.getCountries = function () {
          var url = '{0}/{1}'.format(configService.apiUrl, 'jderive/country/list');
          var deferred = $q.defer();
          $http.get(url)
              .success(function (data) {
                  deferred.resolve(data);
              })
              .error(function () {
                  deferred.reject('Failed to get details.');
              });
          return deferred.promise;
      };

      return services;
  });
