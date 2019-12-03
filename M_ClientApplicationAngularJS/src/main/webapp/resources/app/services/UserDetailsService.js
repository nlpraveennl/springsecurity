app.factory('userDetailsService', function($http){
	
   var userDetailsService = {};
   var contextPath = "/angularjs";

   userDetailsService.getMyDetails = function(){
      return $http({
            url: contextPath + "/app/user/mydetails",
            method: 'GET'
           });
    };
    
    userDetailsService.getUsersList = function(){
        return $http({
              url: contextPath + "/app/admin/list-users",
              method: 'GET'
             });
      };

    return userDetailsService;

});