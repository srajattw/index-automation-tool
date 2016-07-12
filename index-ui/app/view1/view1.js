'use strict';

angular.module("myApp", [])

    .controller("HeadlineController", function($scope) {
      $scope.headline = {};
      $scope.headline.title = "AngularJS is here";
    });