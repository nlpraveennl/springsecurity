app = angular.module('sampleApp', ['ngRoute']);
app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
	        .when('/UsersList', {
	            title: 'Users List',
	            templateUrl: contextPath+'/resources/app/view/users-list.html',
	            controller: 'UsersListController'
	        })
            .when('/', {
                title: 'Dashboard',
                templateUrl: contextPath+'/resources/app/view/dashboard.html',
                controller: 'DashboardController'
            })
            
            .otherwise({
                redirectTo: '/'
            });
    }
]);

app.run(['$rootScope', '$route', function ($rootScope, $route) 
{
	$rootScope.contextPath = contextPath;
    $rootScope.$on('$routeChangeSuccess', function () {
        document.title = $route.current.title;
    });
}]);

