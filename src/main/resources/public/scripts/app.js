var app = angular.module('productApp', [ 'ngCookies', 'ngResource', 'ngSanitize',
		'ngRoute' ])

.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'views/products.html',
		controller : 'AppCtrl',
		controllerAs : 'ctrl'
	}).otherwise({
		redirectTo : '/'
	})
});

