'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('indexApp',[]);


app.controller("IndexController", function($scope,$http) {
               $scope.index = {};

               $scope.inputs = [];

                $scope.addfield = function () {
                    $scope.inputs.push({})
                }
                $scope.getValue = function (item) {
                    alert(item.value)
                }

               $scope.submitForm = function(){

                     $http({
                         method : 'POST',
                         url : 'http://localhost:8080/saveIndexConfig',
                         data : $scope.index ,
                         headers : {'Content-Type':'application/json'}



                     })

                     .success(function(data){
                         if(data.errors){
                             console.log(data.errors);
                         }else{
                            console.log(data.message);
                         }
                     })

               }
             })


