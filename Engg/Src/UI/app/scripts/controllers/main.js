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

      $scope.demo1 = {
          min: 0,
          max: 100
      };

      $scope.serverUrl = configService.apiUrl;

      $scope.searchAdvanced = true;

      $scope.radioReaction = 'reaction';

      $scope.chartLoading = false;

      $scope.countries = [];
      $scope.ageGroups = [];
      $scope.drugsList = [];

      $scope.reactionSkip = 0;
      $scope.reactionCount = 5;

      $scope.genderGroups = [
          { id: 0, name: 'Not Specified' },
          { id: 1, name: 'Female' },
          { id: 2, name: 'Male' }
      ];



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

      $scope.drugfocus = function () {
          //console.log($scope.search.selectedDrug);
          //if (!$scope.search.selectedDrug || ($scope.search.selectedDrug && $scope.search.selectedDrug === null)) {
          //    $scope.$broadcast('angucomplete-alt:clearInput');
          //}
      }

      $scope.weightGroups = [];
      basicService.getWeightGroups()
         .then(function (data) {
             $scope.weightGroups = data.weightGroupList;
         }, function (data) {
             console.log(data);
         });

      $scope.search = { searchAnywhere: true };

      $scope.loadEventData = function (searchCriteria) {
          $scope.chartLoading = true;
          basicService.getEventCount(searchCriteria, 'month')
         .then(function (data) {

             $scope.dataResult = [];
             var eventsCount = data.drugSummaryByMonthList;
             $scope.dataResult = data.drugSummaryByMonthList;

             var eventData = [];
             var currentVal = 0;

             $scope.dataResult.map(function (a) {
                 if (currentVal < parseInt(a.eventCount)) {
                     currentVal = a.eventCount;
                     $scope.maxCountObject = a;
                 }
                 eventData.push({ 'time': a.startDate.slice(0, 10), 'count': a.eventCount });
             });

             if (eventData.length > 0) {
                 $('#eventGraphPanel').show();
                 var minDate = new Date(moment(eventData[0].time).format('YYYY-MM-DD'));
                 var maxDate = new Date(moment(eventData[eventData.length - 1].time.format('YYYY-MM-DD')));
                 console.log(eventData[0].time,minDate, maxDate);
                 if (minDate === maxDate) {
                     $("#dateSlider").dateRangeSlider("bounds", minDate, new Date(moment(maxDate).add(moment.duration(1, 'M'))));
                 } else {
                     $("#dateSlider").dateRangeSlider("bounds", minDate, maxDate);
                 }
                 $("#dateSlider").dateRangeSlider("min", minDate);
                 $("#dateSlider").dateRangeSlider("max", maxDate);

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
             $scope.chartLoading = false;

         }, function (data) {
             $scope.chartLoading = false;
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

      $scope.search.apiDataPoint = 'local';
      $scope.loadEventData('');
      $scope.allSpikes = ['2013-04-25', '2013-04-28'];

      function loadData(searchText) {
          $scope.chartLoading = true;
          var eventsCount = [];
          var fromDate = '20040101';
          var toDate = $filter('date')(new Date(), 'yyyyMMdd');

          if ($scope.search.fromDate) {
              fromDate = moment($scope.search.fromDate).format('YYYYMMDD');
          }

          if ($scope.search.toDate) {
              toDate = moment($scope.search.toDate).format('YYYYMMDD');
          }

          basicService.getCountbyReceivedDate(fromDate, toDate, 'receivedate', searchText)
          .then(function (data) {
              $scope.chartLoading = false;
              if (data) {
                  eventsCount = data.results;
              }
              var myKeys = [];
              var myvalues = [];
              var eventData = [];
              console.log(data);
              $scope.monthData = d3.nest().key(function (a) {
                  return a.time.slice(0, 6)
              }).rollup(function (a) {
                  return d3.sum(a, function (a) {
                      return a.count
                  })
              }).entries(eventsCount);

              var currentVal = 0;
              var maxCountObject = {};

              $scope.monthData.map(function (a) {
                  if (currentVal < parseInt(a.values)) {
                      currentVal = a.values;
                      maxCountObject = a;
                  }
                  var currentDate = new Date(moment(a.key, 'YYYYMMDD'));
                  eventData.push({ 'time': currentDate, 'count': a.values });

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
              if ($scope.regions.length > 0)
                  $scope.regions = [];
              $scope.regions.push({ start: new Date(moment(maxCountObject.key, 'YYYYMM').subtract(moment.duration(3, 'M'))), end: new Date(moment(maxCountObject.key, 'YYYYMM').add(moment.duration(3, 'M'))), class: 'regionYellow' });
              $scope.datapoints = eventData;

              bindChartEvent(eventData);
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

      $scope.selectedSearch = '';


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
                          fit: true,
                          format: d3.format("d")
                      }
                  }
              },
              regions: $scope.regions,
              color: {
                  pattern: ['#FF0000', '#ff7f0e', '#1f77b4', '#aec7e8']
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
          } else {
              $scope.search.selectedDrug = null;
          }
      };

      $scope.resetSearch = function () {
          $scope.hideSpikeInformatrion();
          $scope.$broadcast('angucomplete-alt:clearInput');
          $scope.search = {};
          $scope.drugEventSpikeList = [];
          $scope.selectedSearch = '';
          $scope.loadEventData('');
          $scope.search.apiDataPoint = 'local';
      };
      $scope.recallNotfound = false;
      $scope.recallInformation = [];

      $scope.searchEvents = function () {
          $scope.hideSpikeInformatrion();
          var search = $scope.search;

          var searchUrl = '';

          if (!$scope.search.selectedDrug || ($scope.search.selectedDrug && $scope.search.selectedDrug === null)) {
              $scope.$broadcast('angucomplete-alt:clearInput');
          }

          if ($scope.search) {
              searchUrl += $scope.search.selectedDrug ? '&drugId=' + $scope.search.selectedDrug.id : '';

              if ($scope.search.selectedDrug) {
                  $scope.recallInformation = [];
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
                        if ($scope.drugEventSpikeList && $scope.drugEventSpikeList.length > 0) {
                            $scope.showSpikeInformatrion($scope.drugEventSpikeList[0].spikeDate);
                        }
                    }, function (data) {
                        console.log('spike', data)
                    });

                  basicService.getDrugCharacterization($scope.search.selectedDrug.id)
                   .then(function (data) {
                       $scope.drugCharSummaryList = data.drugCharSummaryList
                   }, function (data) {
                       console.log('charct', data);
                   });
                  $scope.reactionSkip = 0;
                  loadReactions($scope.reactionSkip, $scope.reactionCount);

                  $scope.getDischargeSummary($scope.search.selectedDrug.id);
                  $scope.getERSummary($scope.search.selectedDrug.id);
              }



              $scope.selectedSearch = '';

              $scope.remoteSearch = '';

              if ($scope.search.selectedDrug) {
                  $scope.selectedSearch += 'for selected drug ' + $scope.search.selectedDrug.name;
                  $scope.remoteSearch += 'patient.drug.medicinalproduct:' + $scope.search.selectedDrug.name;
              }

              if ($scope.search.gender) {
                  searchUrl += '&genderId=' + $scope.search.gender.id;
                  $scope.selectedSearch += ($scope.selectedSearch ? ',' : '') + $scope.search.gender.name;

                  $scope.remoteSearch += ($scope.remoteSearch !== '' ? '+AND+' : '') + 'patient.patientsex:' + $scope.search.gender.id;
              }

              if ($scope.search.age) {
                  searchUrl += '&ageGroupId=' + $scope.search.age;
                  $scope.selectedSearch += ($scope.selectedSearch ? ',' : '') + ' age group ' + $("#select2-chosen-2").html();
              }

              if ($scope.search.country) {
                  searchUrl += '&countryId=' + $scope.search.country;
                  $scope.selectedSearch += ($scope.selectedSearch ? ',' : '') + ' country ' + $scope.search.country;
              }

              if ($scope.search.fromDate) {
                  searchUrl += '&startDate=' + moment($scope.search.fromDate).unix();
                  $scope.selectedSearch += ' for ' + moment($scope.search.fromDate).format('MM/DD/YYYY');
              }
              if ($scope.search.toDate) {
                  searchUrl += '&endDate=' + moment($scope.search.toDate).unix();
                  $scope.selectedSearch += ($scope.search.fromDate ? ' to ' : ' till ') + moment($scope.search.toDate).format('MM/DD/YYYY');
              }

              if ($scope.search.weight) {
                  searchUrl += '&weightGroupId=' + $scope.search.weight;
                  $scope.selectedSearch += ($scope.selectedSearch ? ',' : '') + ' ' + $("#select2-chosen-3").html();
              }

          } else {
              $scope.drugEventSpikeList = [];
              $scope.drugEventSpikeList = [];
              $scope.drugReactionSummaryList = [];

              $scope.recallNotfound = false;
              $scope.recallInformation = [];
          }
          if ($scope.search.apiDataPoint == 'local') {
              $scope.loadEventData(searchUrl);
          } else {
              loadData($scope.remoteSearch);
          }

      };

      function loadReactions(skip, count) {
          basicService.getDrugReaction($scope.search.selectedDrug.id, skip, count)
          .then(function (data) {
              $scope.drugReactionSummaryList = data.drugReactionSummaryList;
          }, function (data) {
              console.log('charct', data);
          });
      }

      $scope.showSpikeInformatrion = function (date) {
          //angular.element('#spikeInformation').show();
          //angular.element('#eventInformation').hide();
          //angular.element('#spikelabel').show();
          //angular.element('#adverselabel').hide();

          //bindGroupCharts();
          $scope.getSpikeChartSummary($scope.search.selectedDrug.id, date);
      };

      $scope.hideSpikeInformatrion = function () {
          //angular.element('#spikeInformation').hide();
          //angular.element('#eventInformation').show();

          //angular.element('#spikelabel').hide();
          //angular.element('#adverselabel').show();
      };

      $scope.getDischargeSummary = function (drugid) {
          $scope.dischargeSummaryList = [];

          basicService.getDischargeSummary(drugid)
            .then(function (data) {
                $scope.dischargeSummaryList = data.dischargeSummary
            }, function (data) {
                console.log('getDischargeSummary', data);
            });
      };

      $scope.getERSummary = function (drugid) {
          $scope.ERSummaryList = [];

          basicService.getERSummary(drugid)
            .then(function (data) {
                $scope.ERSummaryList = data.ersummary
            }, function (data) {
                console.log('getERSummary', data);
            });
      };

      $scope.SpikeChartSummary = [];

      $scope.getSpikeChartSummary = function (drugid, date) {
          $scope.spikeChartLoading = true;
          $scope.SpikeChartSummary = [];
          basicService.getSpikeChartSummary(drugid, moment(date).unix())
            .then(function (data) {
                bindGroupCharts(data.dimensionResponse);
            }, function (data) {
                console.log('getSpikeChartSummary', data);
            });
      };

      var bindGroupCharts = function (data) {
          var ageGroupData = [];
          var weightGroupData = [];
          var genderData = [];

          if (data.ageGroup) {
              angular.forEach(data.ageGroup, function (value) {
                  ageGroupData.push([value.type, value.eventCount]);
              });
          }

          if (data.genderGroup) {
              angular.forEach(data.genderGroup, function (value) {
                  genderData.push([value.type, value.eventCount]);
              });
          }

          if (data.weightGroup) {
              angular.forEach(data.weightGroup, function (value) {
                  weightGroupData.push([value.type, value.eventCount]);
              });
          }
          $scope.spikeChartLoading = false;
          bindGraph('#ageGroupPie', 'donut', ageGroupData, 'Age Group');
          bindGraph('#wightGroupPie', 'donut', weightGroupData, 'Weight Group(in Kg)');
          bindGraph('#genderGroupPie', 'donut', genderData, 'Gender');
      };

      var bindGraph = function (divId, type, data, title) {
          c3.generate({
              bindto: divId,
              data: {
                  columns: data,
                  type: type
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
              },
              legend: {
                  item: {
                      onclick: function (id) { return false; }
                  }
              }
          });
      };
      $scope.reactionsLoading = false;
      $scope.hasMoreReactions = true;
      $scope.loadMoreReactions = function () {
          if ($scope.hasMoreReactions) {
              $scope.reactionsLoading = true;

              $scope.reactionSkip += $scope.reactionCount;

              basicService.getDrugReaction($scope.search.selectedDrug.id, $scope.reactionSkip, $scope.reactionCount + 1)
              .then(function (data) {
                  $scope.hasMoreReactions = (data.drugReactionSummaryList && data.drugReactionSummaryList.length > $scope.reactionCount);
                  var index = 0;
                  angular.forEach(data.drugReactionSummaryList, function (value) {
                      index++;
                      if (index <= $scope.reactionCount) {
                          $scope.drugReactionSummaryList.push(value);
                      }
                  });
                  $scope.reactionsLoading = false;
              }, function (data) {
                  $scope.reactionsLoading = false;
                  console.log('reactions', data);
              });
          }
      };
  });
