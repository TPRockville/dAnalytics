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
          protocol: '@@protocol',
          host: '@@host',
          port: '@@port'
      };

      service.apiUrl = '{0}://{1}:{2}'.format(service.protocol, service.host, service.port);

      return service;
  });
