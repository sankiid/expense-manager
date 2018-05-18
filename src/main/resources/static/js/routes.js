var app = angular.module('app', ['ngRoute', 'IncomeService', 'ExpenseService', 'CategoryService']);
app.config(['$routeProvider', function($routeProvider) {
   $routeProvider
   .when('/', {
       templateUrl : 'view/home.html',
       controller  : 'homeCtrl',
       activetab: 'home'
     })
    .when('/income', {
        templateUrl : "view/income.html",
        controller  : 'incomeCtrl',
        activetab: 'income'
    })
    .when('/expense', {
        templateUrl : "view/expense.html",
        controller  : 'expenseCtrl',
        activetab: 'expense'
     })
    .when('/investment', {
        templateUrl : "view/investment.html",
        controller  : 'investmentCtrl',
        activetab: 'investment'
    });
}]);