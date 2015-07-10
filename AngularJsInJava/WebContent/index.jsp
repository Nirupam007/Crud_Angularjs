 <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AJAX with Servlets using AngularJS</title>
<script type="text/javascript" src="js/angular.min.js"></script>
<script>
	var app = angular.module('myApp', []);

	function MyController($scope, $http) {

		$scope.getDataFromServer = function() {
			$http({
				method : 'GET',
				url : 'javaAngularJS'
			}).success(function(data, status, headers, config) {
				$scope.assets = data;
				
			}).error(function(data, status, headers, config) {
				// called asynchronously if an error occurs
				// or server returns response with an error status.
			});

		};
	};
</script>
</head>
<body>
	<div data-ng-app="myApp">
		<div data-ng-controller="MyController">
			<button data-ng-click="getDataFromServer()">Fetch data from server</button>
			<div ng-repeat="asset in assets" style="border:1px solid #000; display:inline-block;margin:5px;padding:10px;">
				<p>Asset id : {{asset.assetId}}</p>
				<p>Asset title : {{asset.assetTitle}}</p>
				<p>Asset desc : {{asset.assetDesc}}</p>
				<p>Asset image url : {{asset.assetImageURL}}</p>
				<p>Asset price : {{asset.assetPrice}}</p>
				<p>Asset category : {{asset.assetCategory}}</p>
			</div>
		</div>
	</div>
</body>
</html>