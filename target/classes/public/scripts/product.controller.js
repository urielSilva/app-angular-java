/**
 * 
 */

angular.module('productApp')


.controller('AppCtrl', ProductsController);

ProductsController.$inject = ['Product'];

function ProductsController(Product) {
	
	// initializing
	var vm = this;
	vm.product = {};
	vm.products = [];

	vm.delete = deleteProduct;
	vm.createOrUpdate = submitForm;
	vm.edit = editProduct;
	activate();
	
	// functions
	
	function activate() {
		Product.query().success(function (data) {
			vm.products = data;
	    });
		console.log(vm.products);
	}
	console.log(Product);
	function submitForm() {
		if(vm.product.id) {
			Product.update(vm.product).success(function (data) {
				vm.product = {};
				Product.query().success(function (data) {
					vm.products = data;
			    });
	        }).error(function (data, status) {
	            console.log('Error ' + data)
	        })
		} else {
			Product.save(vm.product).success(function(data) {
				Product.query().success(function (data) {
					vm.products = data;
			    });
				vm.product = {};
			}).error(function(data, status) {
				console.log('Error ' + data)
			})
		}
	}

	function editProduct(product) {
		vm.product = product;
		console.log(vm.product);
	}

	function deleteProduct(product) {
		console.log(product);
		Product.delete({id: product.id}).success(function(data) {
			Product.query().success(function (data) {
				vm.products = data;
		    });
		});
	}
}
