'use strict';

/**
 * @ngdoc directive
 * @name jDeriveApp.directive:daDateformat
 * @description
 * # daDateformat
 */
angular.module('jDeriveApp')
  .directive('daDateformat', function (moment) {
      return {
          restrict: 'A',
          link: function postLink(scope, element, attrs) {
              element.text('this is the daDateformat directive');
          }
      };
  })
.directive('whenScrollEnds', function () {
    return {
        restrict: "A",
        link: function (scope, element, attrs) {
            var visibleHeight = element.height();
            var threshold = 100;

            element.scroll(function () {
                var scrollableHeight = element.prop('scrollHeight');
                var hiddenContentHeight = scrollableHeight - visibleHeight;

                if (hiddenContentHeight - element.scrollTop() <= threshold) {
                    // Scroll is almost at the bottom. Loading more rows
                    scope.$apply(attrs.whenScrollEnds);
                }
            });
        }
    };
});
