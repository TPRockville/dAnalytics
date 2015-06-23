'use strict';

/**
 * @ngdoc function
 * @name jDeriveApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the jDeriveApp
 */
angular.module('jDeriveApp')
  .controller('MainCtrl', function ($scope, basicService, $filter, $interval, configService) {
      var moment = window.moment;
      $scope.awesomeThings = [
        'HTML5 Boilerplate',
        'AngularJS',
        'Karma'
      ];

      $scope.demo1 = {
          min: 0,
          max: 100
      };

      $scope.serverUrl = configService.apiUrl;

      $scope.searchAdvanced = true;

      $scope.radioReaction = 'reaction';

      $scope.countries = [];
      $scope.ageGroups = [];
      $scope.drugsList = [];

      basicService.getCountries()
         .then(function (data) {
             $scope.countries = data.countryList;
         }, function (data) {
             console.log(data);
         });
      basicService.getAgeGroups()
         .then(function (data) {
             $scope.ageGroups = data.ageGroupList;
         }, function (data) {
             console.log(data);
         });

      basicService.getDrugsList()
         .then(function (data) {
             $scope.drugsList = data.drugList;
         }, function (data) {
             console.log(data);
         });

      //$scope.weightGroups = [];
      //basicService.getWeightGroups()
      //   .then(function (data) {
      //       $scope.weightGroups = data.weightGroupList;
      //   }, function (data) {
      //       console.log(data);
      //   });

      $scope.search = {};

      //$scope.search.fromDate = new Date('01/01/2014');

      //$scope.search.toDate = new Date();

      //$scope.$watch($scope.search, function (newValue, oldValue) {
      //    loadData();
      //});

      //loadData();

      $scope.loadEventData = function (searchCriteria) {
          basicService.getEventCount(searchCriteria)
         .then(function (data) {
             var eventsCount = data.drugSummaryList;
             $scope.dataResult = data.drugSummaryList;
             var eventData = [];
             if (eventsCount.length > 0) {
                 $('#eventGraphPanel').show();
                 var minDate = new Date(moment(eventsCount[0].eventDate));
                 var maxDate = new Date(moment(eventsCount[eventsCount.length - 1].eventDate));

                 $("#dateSlider").dateRangeSlider("bounds", minDate, maxDate);

                 $("#dateSlider").dateRangeSlider("min", minDate);

                 $("#dateSlider").dateRangeSlider("values", minDate, maxDate);
                 $('#noDataLabel').hide();
             } else {
                 //$('#eventGraphPanel').hide();
                 var minDate = new Date();
                 var maxDate = new Date();
                 $("#dateSlider").dateRangeSlider({
                     bounds:
                      {
                          min: minDate,
                          max: maxDate
                      }
                 });

                 $("#dateSlider").dateRangeSlider("values", minDate, maxDate);
                 $('#noDataLabel').show();
             }

             var currentVal = 0;
             var maxCountObject = {};

             eventsCount.map(function (a) {
                 if (currentVal < parseInt(a.eventCount)) {
                     currentVal = a.eventCount;
                     maxCountObject = a;
                 }
                 eventData.push({ 'time': new Date(a.eventDate), 'count': a.eventCount });
             });
             if ($scope.regions.length > 0)
                 $scope.regions = [];
             $scope.regions.push({ start: new Date(moment(maxCountObject.eventDate).subtract(moment.duration(22, 'd'))), end: new Date(moment(maxCountObject.eventDate).add(moment.duration(5, 'd'))), class: 'regionYellow' });
             //$scope.regions.push({ start: new Date(2012, 0, 1), end: new Date(2013, 0, 1), class: 'regionYellow' });
             chart.load({
                 json: eventData,
                 keys: {
                     x: 'time',
                     value: ['count'],
                 }
             });


         }, function (data) {
             console.log('Failed service', data);
         });
      };
      $("#dateSlider").dateRangeSlider();

      $("#dateSlider").bind("valuesChanged", function (e, data) {
          if (chart) {
              //{max: {x: 5}, min: {x: 0}}
              chart.axis.range({ max: { x: new Date(data.values.max) }, min: { x: new Date(data.values.min) } });
          }
      });

      //$("#dateSlider").dateRangeSlider("values", new Date(2012, 0, 1), new Date(2012, 0, 31));
      //$('#eventGraphPanel').hide();
      $scope.apiDataPoint = 'local';
      $scope.loadEventData('');
      //if ($scope.apiDataPoint == 'local') {
          
      //} else {
      //    //loadData();
      //}

      $scope.allSpikes = ['2013-04-25', '2013-04-28'];

      /*Random Data Generator */
      function loadData() {
          var eventsCount = [];
          var fromDate = '20040101';
          var toDate = $filter('date')(new Date(), 'yyyyMMdd');

          if ($scope.search.fromDate) {
              fromDate = moment($scope.search.fromDate).format('YYYYMMDD');
          }

          if ($scope.search.toDate) {
              toDate = moment($scope.search.toDate).format('YYYYMMDD');
          }

          basicService.getCountbyReceivedDate(fromDate, toDate, 'receivedate')
          .then(function (data) {
              if (data) {
                  eventsCount = data.results;
              }
              var myKeys = [];
              var myvalues = [];
              var eventData = [];
              //var eventData = d3.nest().key(function (a) {
              //    return a.time.slice(0, 6)
              //}).rollup(function (a) {
              //    return d3.sum(a, function (a) {
              //        return a.count
              //    })
              //}).entries(eventsCount);

              var currentVal = 0;
              var maxCountObject = {};

              eventsCount.map(function (a) {
                  if (currentVal < parseInt(a.eventCount)) {
                      currentVal = a.eventCount;
                      maxCountObject = a;
                  }
                  var currentDate = new Date(moment(a.time, 'YYYYMMDD'));
                  eventData.push({ 'time': currentDate, 'count': a.count });
                  if ($scope.regions.length > 0)
                      $scope.regions = [];
                  $scope.regions.push({ start: new Date(moment(maxCountObject.eventDate).subtract(moment.duration(22, 'd'))), end: new Date(moment(maxCountObject.eventDate).add(moment.duration(5, 'd'))), class: 'regionYellow' });
                  
              });
              $scope.datapoints = eventData;

              chart.load({
                  json: $scope.datapoints,
                  keys: {
                      x: 'time',
                      value: ['count'],
                  }
              });
          },
            function (data) {
                alert('Failed to get details.');
                console.log(data);
            });

      };

      window.scope = $scope;

      //$scope.refreshChart = function () {
      //    loadData();
      //}

      $scope.datapoints = [];
      $scope.datacolumns = [{ 'id': 'count', 'type': 'line', 'name': 'Events' }];
      $scope.datax = { 'id': 'time', 'type': 'timeseries' };

      $scope.drugEventSpikeList = [];
      $scope.drugEventSpikeList = [];

      $scope.recallNotfound = false;
      $scope.recallInformation = [];

      //$interval(function () {
      //     new DataService().loadData(function (data) {
      //        $scope.datapoints.push(data);
      //    });
      //}, 1000, 10);

      $scope.regions = [];

      var chart = c3.generate({
          bindto: '#eventChart',
          data: {
              json: []
          },
          point: {
              show: false
          },
          axis: {
              x: {
                  type: 'timeseries',
                  tick: {
                      format: '%m/%d/%Y',
                      rotate: 40
                  },
                  label: {
                      text: 'Received Date',
                      position: 'outer-center'
                      // inner-right : default
                      // inner-center
                      // inner-left
                      // outer-right
                      // outer-center
                      // outer-left
                  }
              },
              y: {
                  label: {
                      text: 'Count',
                      position: 'outer-top'
                      // inner-top : default
                      // inner-middle
                      // inner-bottom
                      // outer-top
                      // outer-middle
                      // outer-bottom
                  }
              }
          },
          regions: $scope.regions,
          color: {
              pattern: ['#ff7f0e', '#1f77b4', '#aec7e8']
          },
          zoom: {
              enabled: true
          },
          legend: {
              show: false
          }
      });

      basicService.getDrungDetails()
         .then(function (data) {
             //var eventData = d3.nest().key(function (a) {
             //    return a.time.slice(0, 6)
             //}).rollup(function (a) {
             //    return d3.sum(a, function (a) {
             //        return a.count
             //    })
             //}).entries(eventsCount);
             $scope.drugDetails = data;
         }, function (data) {

         });

      $scope.searchDrugChanged = function (drug) {
          if (drug.originalObject) {
              $scope.search.selectedDrug = drug.originalObject;
          }
      };

      $scope.resetSearch = function () {
          $scope.$broadcast('angucomplete-alt:clearInput');
          $scope.search = {};
          $scope.loadEventData('');
      };
      $scope.recallNotfound = false;
      $scope.recallInformation = [];

      $scope.searchEvents = function () {
          var search = $scope.search;

          var searchUrl = '';

          if ($scope.search) {
              searchUrl += $scope.search.selectedDrug ? '&drugId=' + $scope.search.selectedDrug.id : '';

              if ($scope.search.selectedDrug) {
                  basicService.getDrugSubstance($scope.search.selectedDrug.name)
                    .then(function (data) {
                        if (data) {
                            if (data.results.length > 0) {
                                //var drugSubstance = data.results.patient.drug[0].substance_name[0];

                                basicService.getDrugRecall('HYDROXYZINE HYDROCHLORIDE')
                                .then(function (data) {
                                    if (data) {
                                        if (data.error) {
                                            $scope.recallNotfound = ture;
                                        } else {
                                            $scope.recallInformation = data.results;
                                        }
                                    }
                                }, function (data) {

                                });
                            }
                        }
                    }, function (data) {

                    });

                  basicService.getDrugEventSpikes($scope.search.selectedDrug.id)
                    .then(function (data) {
                        $scope.drugEventSpikeList = data.drugEventSpikeList;
                    }, function (data) {
                        console.log('spike', data)
                    });

                  basicService.getDrugCharacterization($scope.search.selectedDrug.id)
                   .then(function (data) {
                       $scope.drugCharSummaryList = data.drugCharSummaryList
                   }, function (data) {
                       console.log('charct', data);
                   });

                  basicService.getDrugReaction($scope.search.selectedDrug.id)
                  .then(function (data) {
                      $scope.drugReactionSummaryList = data.drugReactionSummaryList
                  }, function (data) {
                      console.log('charct', data);
                  });
              }

              searchUrl += $scope.search.age ? '&ageGroupId=' + $scope.search.age : '';

              searchUrl += $scope.search.country ? '&countryId=' + $scope.search.country : '';

              searchUrl += $scope.search.fromDate ? '&startDate=' + moment($scope.search.fromDate).utc().valueOf() : '';

              searchUrl += $scope.search.toDate ? '&endDate=' + moment($scope.search.toDate).utc().valueOf() : '';
          } else {
              $scope.drugEventSpikeList = [];
              $scope.drugEventSpikeList = [];
              $scope.drugReactionSummaryList = [];

              $scope.recallNotfound = false;
              $scope.recallInformation = [];
          }
          if ($scope.apiDataPoint == 'local') {
              $scope.loadEventData(searchUrl);
          } else {
              loadData();
          }
          
      };
      //angular.element("#slider").dateRangeSlider();
  });
