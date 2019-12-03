//$scope, $window, $http, $location, $route, $compile, $timeout,

app.controller("DashboardController", function ($scope, $http, $location, userDetailsService) 
{
	alert("Dashboard");
	userDetailsService.getMyDetails().then(function (response){
        $scope.user = response.data;
    },function (error){

    });
});
