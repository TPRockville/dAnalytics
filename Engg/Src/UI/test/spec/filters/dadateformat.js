'use strict';

describe('Filter: daDateformat', function () {

  // load the filter's module
  beforeEach(module('jDeriveApp'));

  // initialize a new instance of the filter before each test
  var daDateformat;
  beforeEach(inject(function ($filter) {
    daDateformat = $filter('daDateformat');
  }));

  it('should return the input prefixed with "daDateformat filter:"', function () {
    var text = 'angularjs';
    expect(daDateformat(text)).toBe('daDateformat filter: ' + text);
  });

});
