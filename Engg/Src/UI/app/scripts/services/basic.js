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
              .error(function (data) {
                  deferred.reject(data);
              });
          return deferred.promise;
      };

      services.getDrugSubstance = function (drugname) {
          var url = '{0}{1}'.format('https://api.fda.gov/drug/event.json?search=patient.drug.medicinalproduct:', drugname)
          var deferred = $q.defer();
          $http.get(url)
              .success(function (data) {
                  deferred.resolve(data);
              })
              .error(function (data) {
                  deferred.reject(data);
              });
          return deferred.promise;
      };

      services.getDrugRecall = function (substance) {
          var url = '{0}{1}'.format('https://api.fda.gov/drug/enforcement.json?search=openfda.substance_name:', substance)
          var deferred = $q.defer();
          $http.get(url)
              .success(function (data) {
                  deferred.resolve(data);
              })
              .error(function (data) {
                  deferred.reject(data);
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
              .error(function (data) {
                  deferred.reject(data);
              });
          return deferred.promise;
      };

      services.getAgeGroups = function () {
          var url = '{0}/{1}'.format(configService.apiUrl, 'jderive/agegroup/list');
          var deferred = $q.defer();
          $http.get(url)
              .success(function (data) {
                  deferred.resolve(data);
              })
              .error(function (data) {
                  deferred.reject(data);
              });
          return deferred.promise;
      };

      services.getWeightGroups = function () {
          var url = '{0}/{1}'.format(configService.apiUrl, 'jderive/weightgroup/list');
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

      services.getEventCount = function (searchCriteria) {
          var url = '{0}/{1}?{2}'.format(configService.apiUrl, 'jderive/drugs/eventcount', searchCriteria);
          var deferred = $q.defer();
          $http.get(url)
              .success(function (data) {
                  deferred.resolve(data);
              })
              .error(function (data) {
                  deferred.reject(data);
              });
          return deferred.promise;
      };

      services.getDrugsList = function () {
          var url = '{0}/{1}/{2}'.format(configService.apiUrl, 'jderive/drugs/name', 'a');
          var deferred = $q.defer();
          $http.get(url)
              .success(function (data) {
                  deferred.resolve(data);
              })
              .error(function (data) {
                  deferred.reject(data);
              });
          return deferred.promise;
      };

      services.getDrugEventSpikes = function (drugid) {
          var url = '{0}/{1}/{2}/spike'.format(configService.apiUrl, 'jderive/drugs', drugid);
          var deferred = $q.defer();
          $http.get(url)
              .success(function (data) {
                  deferred.resolve(data);
              })
              .error(function (data) {
                  deferred.reject(data);
              });
          return deferred.promise;
      };

      services.getDrugCharacterization = function (drugid) {
          var url = '{0}/{1}/{2}/characterization'.format(configService.apiUrl, 'jderive/drugs', drugid);
          var deferred = $q.defer();
          $http.get(url)
              .success(function (data) {
                  deferred.resolve(data);
              })
              .error(function (data) {
                  deferred.reject(data);
              });
          return deferred.promise;
      };

      services.getDrugReaction = function (drugid) {
          var url = '{0}/{1}/{2}/reaction'.format(configService.apiUrl, 'jderive/drugs', drugid);
          var deferred = $q.defer();
          $http.get(url)
              .success(function (data) {
                  deferred.resolve(data);
              })
              .error(function (data) {
                  deferred.reject(data);
              });
          return deferred.promise;
      };

      return services;
  });
