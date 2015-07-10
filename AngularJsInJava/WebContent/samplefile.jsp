
<!DOCTYPE html>
<html lang="en" ng-app="routerApp">
	<head>
	 <!-- CSS (load bootstrap) -->
	    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	    <style>
	        .navbar { border-radius:0; }
	        .row{margin-bottom:20px}
	        .btn-primary{margin-left:15px}
	    </style>
	
	    <!-- JS (load angular, ui-router, and our custom js file) -->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <script src="http://code.angularjs.org/1.2.13/angular.js"></script>
	    <script src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.8/angular-ui-router.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	    <script src="js/holder.min.js"></script>
		<script>
			var routerApp = angular.module('routerApp', ['ui.router']);
			routerApp.config(function($stateProvider, $urlRouterProvider) {
			
				$urlRouterProvider.otherwise('/home');
			
				$stateProvider
			
				// HOME STATES AND NESTED VIEWS ========================================
				.state('home', {
					url : '/home',
					templateUrl : 'Partials/partial-home.html'
				})
			
				.state('home.list', {
					url : '/list',
					templateUrl : 'Partials/partial-home-list.html',
					controller : function($scope) {
						$scope.dogs = [ 'Bernard', 'Huskey', 'Goldendoodle' ];
					}
				})
			
				.state('home.paragraph', {
					url : '/paragraph',
					template : 'I can use this Paragraph for anything'
				})
				.state('login',{
					url:'/login',
					templateUrl:'Partials/partial-login.html',
					controller:function($scope,$http){
				       
						$scope.info = {};
						
					}
				})
				.state('register',{
					url:'/register',
					templateUrl:'Partials/partial-register.html',
					controller:function(){
						
						
					}
				})
			
				// ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
				.state(
						'about',
						{
							url : '/about',
							templateUrl : 'Partials/partial-about.html',
							controller : function($scope, $http) {
								$scope.asset = {};
								 $scope.mySwitch = true;
								// Code for post goes here 
			                   
								$scope.empty = function(){
									
									$scope.asset ={};
								}
								
								$scope.postme = function(asset) {
									
									/* 
									//var jsonData = $scope.form;
									var collectData = asset;
									$scope.output = collectData; 
									 */
									$http.post('InsertOperationAngularJS', asset)
											.success(function(response) {
												console.log(response);
												$('#myModal').modal('hide');
												
												$scope.getAll();
												$scope.asset ={};
											});
									
			
								}
			
								$scope.editItem = function(asset) {
									$scope.asset = asset;
									
								}
			
								$scope.getAll = function() {
			
									$http.get('javaAngularJS').success(
											function(data, status, headers, config) {
												// this callback will be called asynchronously
												// when the response is available
												$scope.assets = data;
											}).error(
											function(data, status, headers, config) {
												// called asynchronously if an error occurs
												// or server returns response with an error status.
												alert(status);
											});
			
								}
			
								$scope.saveChangesItem = function(asset) {
									$http.post('UpdateAsset', asset)
											.success(function(response) {
												console.log(response);
												$scope.getAll();
												$('#myModalEdit').modal('hide');
											
											});
								}
								
								$scope.deleteItem = function(asset){
									$scope.asset = asset;
									var r = confirm("Are you sure? you want to delete the record " + asset.assetTitle);
								    if (r == true) {
								    	$http.post('DeleteAsset',{"assetId":asset.assetId}).success(function(response){
											
											
											$scope.getAll();
											
											
										});
								    } else {
								       
								    }
									
								
								
								};
								$scope.getAll();
			
							}
			
						});
			
			});
			
			routerApp.controller('aboutController',function(){
				
			});
			
			routerApp.directive('myDirective', function() {
				return {
					restrict : 'EA',
					templateUrl : 'Partials/partial-about.html',
					controller : 'aboutController',
					link : function($scope, element, attrs) {
						element.bind("click", function() {
							element.css('background-color', 'yellow');
						});
			
						element.bind("mouseenter", function() {
							element.css('background-color', '#fff');
						})
					}
			
				}
			});
		</script>
	
	</head>

	<body>
		<!-- NAVIGATION -->
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" ui-sref="#">AngularUI Router</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a ui-sref="home">Home</a></li>
				<li><a ui-sref="about" >About</a></li>
				<li><a ui-sref="login">Login</a></li>
				<li><a ui-sref="register">Register</a></li>
			</ul>
		</nav>
		<!-- MAIN CONTENT -->
		<div class="container">
	
			<!-- THIS IS WHERE WE WILL INJECT OUR CONTENT ============================== -->
			<div ui-view>
				<div my-Directive></div>
			</div>
	
		</div>
	</body>
</html>
