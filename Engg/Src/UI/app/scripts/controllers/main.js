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

      //basicService.getDrugsList()
      //   .then(function (data) {
      //       $scope.drugsList = data.drugList;
      //   }, function (data) {
      //       console.log(data);
      //   });

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
          basicService.getEventCount(searchCriteria, 'month')
         .then(function (data) {
             //var eventsCount = data.drugSummaryList;
             //$scope.dataResult = data.drugSummaryList;

             $scope.dataResult = [];
             var eventsCount = data.drugSummaryByMonthList;
             $scope.dataResult = data.drugSummaryByMonthList;

             var eventData = [];
             //$scope.monthData = d3.nest().key(function (a) {
             //    return moment(a.eventDate).format('YYYYMM');
             //}).rollup(function (a) {
             //    return d3.sum(a, function (a) {
             //        return a.eventCount
             //    })
             //}).entries(eventsCount);

             //$scope.monthData.map(function (a) {
             //    if (currentVal < parseInt(a.eventCount)) {
             //        currentVal = a.eventCount;
             //        maxCountObject = a;
             //    }
             //    eventData.push({ 'time': new Date(moment(a.key, 'YYYYMM')), 'count': a.values });
             //});
             var currentVal = 0;

             $scope.dataResult.map(function (a) {
                 //console.log(currentVal, a.eventCount);
                 if (currentVal < parseInt(a.eventCount)) {
                     currentVal = a.eventCount;
                     $scope.maxCountObject = a;
                 }
                 eventData.push({ 'time': new Date(a.startDate), 'count': a.eventCount });
             });

             if (eventData.length > 0) {
                 $('#eventGraphPanel').show();
                 var minDate = new Date(moment(eventData[0].time));
                 var maxDate = new Date(moment(eventData[eventData.length - 1].time));

                 $("#dateSlider").dateRangeSlider("bounds", minDate, maxDate);

                 $("#dateSlider").dateRangeSlider("min", minDate);

                 $("#dateSlider").dateRangeSlider("values", minDate, maxDate);
                 $('#noDataLabel').hide();
             } else {
                 //$('#eventGraphPanel').hide();
                 var minDate = new Date();
                 var maxDate = new Date();
                 $("#dateSlider").dateRangeSlider("bounds", minDate, maxDate);
                 $("#dateSlider").dateRangeSlider("min", minDate);

                 $("#dateSlider").dateRangeSlider("values", minDate, maxDate);
                 $('#noDataLabel').show();
             }

             if ($scope.regions.length > 0) {
                 $scope.regions = [];
             }

             if (eventData.length > 0) {
                 //$scope.regions.push({ start: new Date(2012, 0, 1), end: new Date(2013, 0, 1), class: 'regionYellow' });
                 bindChartEvent(eventData);
             } else {
                 bindChartEvent([]);
                 $("#dateSlider").dateRangeSlider({
                     formatter: function (val) {
                         return moment(val).format('MMM YYYY');
                     },
                     step: {
                         months: 1
                     }
                 });
             }

             var maxCountObject = $scope.maxCountObject;
             if (maxCountObject && maxCountObject.startDate) {
                 $scope.duration = moment.duration(3, 'M');
                 $scope.regions.push({ start: new Date(moment(maxCountObject.startDate).subtract(moment.duration(3, 'M'))), end: new Date(moment(maxCountObject.startDate).add(moment.duration(3, 'M'))), class: 'regionYellow' });
             }


         }, function (data) {
             console.log('Failed service', data);
         });
      };
      $("#dateSlider").dateRangeSlider({
          formatter: function (val) {
              return moment(val).format('MMM YYYY');
          },
          step: {
              months: 1
          }
      });

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
              $scope.monthData = d3.nest().key(function (a) {
                  return a.time.slice(0, 6)
              }).rollup(function (a) {
                  return d3.sum(a, function (a) {
                      return a.count
                  })
              }).entries(eventsCount);

              //console.log(monthData);

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
                //console.log(data);
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

      $scope.ERSummaryList = [];
      $scope.dischargeSummaryList = [];


      //$interval(function () {
      //     new DataService().loadData(function (data) {
      //        $scope.datapoints.push(data);
      //    });
      //}, 1000, 10);

      $scope.regions = [];
      var chart;
      function bindChartEvent(eventData) {
          chart = c3.generate({
              bindto: '#eventChart',
              data: {
                  json: eventData,
                  keys: {
                      x: 'time',
                      value: ['count'],
                  }
              },
              point: {
                  show: false
              },
              axis: {
                  x: {
                      type: 'timeseries',
                      tick: {
                          format: '%b %Y',
                          rotate: 40,
                          fit: true
                      },
                      label: {
                          text: 'Received Date',
                          position: 'inner-center'
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
                      },
                      tick: {
                          fit: true
                      }
                  }
              },
              regions: $scope.regions,
              color: {
                  pattern: ['#ff7f0e', '#1f77b4', '#aec7e8']
              },
              zoom: {
                  enabled: false
              },
              legend: {
                  show: false
              },
              onrendered: function () {

              }
          });
      }

      bindChartEvent([]);

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
          if (drug && drug.originalObject) {
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
                                var substanceNotfound = false;
                                angular.forEach(data.results[0].patient.drug, function (value, key) {
                                    if (value.medicinalproduct === $scope.search.selectedDrug.name) {
                                        substanceNotfound = value.medicinalproduct;
                                        if (value.openfda && value.openfda.substance_name && value.openfda.substance_name.length > 0) {
                                            basicService.getDrugRecall(value.openfda.substance_name[0])
                                            .then(function (subst) {
                                                if (subst) {
                                                    if (subst.error) {
                                                        $scope.recallNotfound = ture;
                                                    } else {
                                                        $scope.recallInformation = subst.results;
                                                    }
                                                }
                                            }, function (subst) {
                                                if (data.statusCode && data.statusCode === '404') {
                                                    $scope.recallNotfound = true;
                                                    $scope.recallInformation = [];
                                                }
                                            });
                                        }
                                    }
                                });

                                if (substanceNotfound !== false) {
                                    $scope.recallNotfound = true;
                                    $scope.recallInformation = [];
                                }
                            }
                        }
                    }, function (data) {

                    });
                  $scope.drugEventSpikeList = [];
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



      $scope.showSpikeInformatrion = function () {
          angular.element('#spikeInformation').show();
          angular.element('#eventInformation').hide();

          bindGroupCharts();
      };

      $scope.hideSpikeInformatrion = function () {
          angular.element('#spikeInformation').hide();
          angular.element('#eventInformation').show();
      };
      //angular.element("#slider").dateRangeSlider();

      $scope.getDischargeSummary = function (drugid) {
          $scope.dischargeSummaryList = [];

          basicService.getDischargeSummary(drugid)
            .then(function (data) {
                $scope.dischargeSummaryList = data.dishargeSummaryList
            }, function (data) {
                console.log('charct', data);
            });
      };

      $scope.getERSummary = function (drugid) {
          $scope.ERSummaryList = [];

          basicService.getERSummary(drugid)
            .then(function (data) {
                $scope.ERSummaryList = data.ERSummaryList
            }, function (data) {
                console.log('charct', data);
            });
      };

      var bindGroupCharts = function () {
          var ageGroupData = [
            ['Child', 30],
            ['Adult', 120],
            ['Old', 300]
          ];

          var weightGroupData = [
           ['Under 50', 200],
           ['51 - 70', 60],
           ['Over 70', 300]
          ];

          var genderData = [
              ['Unknown', 60],
              ['Male', 100],
              ['Female', 70]
          ];

          var countryData = [
              ['US', 60],
              ['IN', 100],
              ['GB', 70],
              ['CA', 20]
          ];

          bindGraph('#ageGroupPie', 'donut', ageGroupData, 'Age Group');
          bindGraph('#wightGroupPie', 'donut', weightGroupData, 'Weight Group');
          bindGraph('#genderGroupPie', 'donut', genderData, 'Gender');
          bindGraph('#countryPie', 'donut', countryData, 'Country');
      };

      var bindGraph = function (divId, type, data, title) {
          c3.generate({
              bindto: divId,
              data: {
                  // iris data from R
                  columns: data,
                  type: type
              },
              pie: {
                  //label:{
                  //    format:function(x){
                  //        return x;
                  //    }
                  //},
                  title: 'Test'
              },
              tooltip: {
                  format: {
                      value: function (x) {
                          return x;
                      }
                  }
              },
              donut: {
                  title: title
              }
          });
      };

  });
