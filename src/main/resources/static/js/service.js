var IncomeService = angular.module('IncomeService', [])
IncomeService.factory('Income', ['$http', '$filter', function ($http, $filter) {

    var urlBase = 'http://localhost:8080/api/income';
    var incomes = {};

    incomes.getIncome = function () {
        var d = new Date();
        var end = $filter('date')(d, 'dd-MM-y');
        var start = $filter('date')(d.setDate(d.getDate()-30), 'dd-MM-y');
        var res = $http.get(urlBase+'/get/'+start+'/'+end);
        return res;
    };

    incomes.addIncome = function (income) {
        return $http.post(urlBase + '/add', income);
    };

    incomes.deleteIncome = function (id) {
        return $http.delete(urlBase + '/delete/'+ id);
    };

    return incomes;

}]);

var ExpenseService = angular.module('ExpenseService', [])
ExpenseService.factory('Expense', ['$http', '$filter', function ($http, $filter) {

    var urlBase = 'http://localhost:8080/api/expense';
    var expenses = {};

    expenses.getExpense = function () {
        var d = new Date();
        var end = $filter('date')(d, 'dd-MM-y');
        var start = $filter('date')(d.setDate(d.getDate()-30), 'dd-MM-y');
        var res = $http.get(urlBase+'/get/'+start+'/'+end);
        return res;
    };

    expenses.addExpense = function (expense) {
        return $http.post(urlBase + '/add', expense);
    };

    expenses.deleteExpense = function (id) {
            return $http.delete(urlBase + '/delete/'+ id);
    };
    return expenses;

}]);

var CategoryService = angular.module('CategoryService', [])
CategoryService.factory('Category', ['$http', function ($http) {

    var urlBase = 'http://localhost:8080/category';
    var category = {};

    category.getCategories = function (type) {
        var res = $http.get(urlBase+'/get/'+type);
        return res;
    };
    return category;

}]);