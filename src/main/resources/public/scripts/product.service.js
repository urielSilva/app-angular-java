/**
 * 
 */

angular.module('productApp').factory('Product', Product);

Product.$inject = [ '$http' ];
// Factory for Product model. service $http was used instead of $resource,
// in order to maintain project simplicity.
function Product($http) {

	return {
		save : function(product) {
			return $http.post('/api/products', product);
		},
		update : function(product) {
			return $http.put('/api/products/' + product.id, product);
		},
		query: function() {
			return $http.get('/api/products');
		},
		delete: function(product) {
			return $http.delete('/api/products/' + product.id);
		}
	};
};


