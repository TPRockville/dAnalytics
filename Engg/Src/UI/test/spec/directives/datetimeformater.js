'use strict';

describe('Directive: datetimeFormater', function () {

  // load the directive's module
  beforeEach(module('jDeriveApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<datetime-formater></datetime-formater>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the datetimeFormater directive');
  }));
});
