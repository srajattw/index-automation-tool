'use strict';

angular.module("indexApp", [])

    .controller("IndexController", function($scope,$http) {
      $scope.index = {};
      $scope.submitForm = function(){

            $http({
                method : 'POST'
                url : 'http://localhost:8080/saveIndexConfig'
                data : $scope.index
                headers : {'Content-Type':'application/json'}

            })

            .success(function(data){
                if(data.errors){
                    console.log(data.errors);
                }
            })

      }
    });