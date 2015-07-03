'use strict';

/**
 * @ngdoc service
 * @name jDeriveApp.configService
 * @description
 * # configService
 * Service in the jDeriveApp.
 */
angular.module('jDeriveApp')
  .service('configService', function () {
      var service = {
          protocol: 'http',
          host: '52.24.0.121',
          port: '8080',
          contextRoot: 'danalytics'
      };
      if (service.port && service.port !== '') {
          service.apiUrl = '{0}://{1}:{2}/{3}'.format(service.protocol, service.host, service.port, service.contextRoot);
      } else {
          service.apiUrl = '{0}://{1}/{2}'.format(service.protocol, service.host, service.contextRoot);
      }      

      return service;
  });
