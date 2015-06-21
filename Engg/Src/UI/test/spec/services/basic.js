'use strict';

describe('Service: basic', function () {

  // load the service's module
  beforeEach(module('jDeriveApp'));

  // instantiate service
  var basic;
  beforeEach(inject(function (_basic_) {
    basic = _basic_;
  }));

  it('should do something', function () {
    expect(!!basic).toBe(true);
  });

});
