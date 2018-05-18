app.controller('homeCtrl', function($scope, $route, Income, Expense) {
   $scope.$route = $route;
   getIncomes();
   getExpenses();

   function getIncomes() {
        Income.getIncome()
            .success(function (income) {
                $scope.incomes = income.data;
                drawIncomeChart($scope.incomes);
            })
            .error(function (error) {
                $scope.status = 'Unable to load income data: ' + error.message;
            });
   }

   function getExpenses() {
       Expense.getExpense()
           .success(function (expense) {
               $scope.expenses = expense.data;
               drawExpenseChart($scope.expenses);
           })
           .error(function (error) {
               $scope.status = 'Unable to load expense data: ' + error.message;
           });
   }

});

app.controller('incomeCtrl', function($scope, $route, Income, Category) {
    $scope.$route = $route;
    $scope.action = 'Add';
    getIncomes();
    getCategory();

     function getIncomes() {
         Income.getIncome()
             .success(function (income) {
                 $scope.incomes = income.data;
             })
             .error(function (error) {
                 $scope.status = 'Unable to load income data: ' + error.message;
             });
      }

     function getCategory() {
         Category.getCategories('income')
             .success(function (response) {
                 $scope.categories = response.data;
             })
             .error(function (error) {
                 $scope.status = 'Unable to load category data: ' + error.message;
             });
      }

      $scope.addIncome = function () {
          var income = {
              amount: $scope.amount,
              category: {
                    id: $scope.category.id,
                    name: $scope.category.name,
                    type: $scope.category.type
                },
              date: $scope.date,
              notes: $scope.notes
          };
          Income.addIncome(income)
              .success(function () {
                  $scope.status = 'Added new Income! Refreshing Income list.';
                  getIncomes();
              }).
              error(function (error) {
                  $scope.status = 'Unable to insert Income: ' + error.message;
              });
      };

      $scope.deleteIncome = function (id) {
            Income.deleteIncome(id)
                  .success(function () {
                      $scope.status = 'Removed the Income record.';
                      getIncomes();
                  }).
                  error(function (error) {
                      $scope.status = 'Unable to delete Income: ' + error.message;
                  });
      };
});


app.controller('expenseCtrl', function($scope, Expense, Category) {
    $scope.action = 'Add';
    getExpenses();
    getCategory();

     function getExpenses() {
         Expense.getExpense()
             .success(function (expense) {
                 $scope.expenses = expense.data;
             })
             .error(function (error) {
                 $scope.status = 'Unable to load income data: ' + error.message;
             });
      }

     function getCategory() {
         Category.getCategories('expense')
             .success(function (response) {
                 $scope.categories = response.data;
             })
             .error(function (error) {
                 $scope.status = 'Unable to load category data: ' + error.message;
             });
      }

      $scope.addExpense = function () {
          var expense = {
              amount: $scope.amount,
              category: {
                    id: $scope.category.id,
                    name: $scope.category.name,
                    type: $scope.category.type
                },
              date: $scope.date,
              notes: $scope.notes
          };
          Expense.addExpense(expense)
              .success(function () {
                  $scope.status = 'Added new Income! Refreshing Income list.';
                  getExpenses();
              }).
              error(function (error) {
                  $scope.status = 'Unable to insert Income: ' + error.message;
              });
      };

      $scope.deleteExpense = function (id) {
              Expense.deleteExpense(id)
                    .success(function () {
                        $scope.status = 'Removed the Expense record.';
                        getExpenses();
                    }).
                    error(function (error) {
                        $scope.status = 'Unable to delete Expense: ' + error.message;
                    });
       };
});


app.controller('investmentCtrl', function($http, $filter, $scope) {
    $scope.action = 'Investment';
});
