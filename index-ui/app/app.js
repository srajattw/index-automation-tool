'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('indexApp',[]);


app.controller("IndexController", function($scope,$http) {

               $scope.index = {};
               $scope.index.methodology = {}
               $scope.index.methodology.attributes = []

                $scope.addMethodology = function () {
                    $scope.index.methodology.attributes.push({})
                }

                $scope.removeMethodology = function(m){
                     var index = $scope.index.methodology.attributes.indexOf(m);
                      $scope.index.methodology.attributes.splice(index, 1);
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


