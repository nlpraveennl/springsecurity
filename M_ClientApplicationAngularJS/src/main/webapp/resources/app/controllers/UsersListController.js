//$scope, $window, $http, $location, $route, $compile, $timeout,

app.controller("UsersListController", function ($scope, $http, $location, userDetailsService) 
{
	alert();
	userDetailsService.getUsersList().then(function (response){
		console.log(response.data)
        $scope.usersList = response.data;
    },function (error){

    });
});
