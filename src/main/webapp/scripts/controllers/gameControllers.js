angular.module('game.controllers', [])

.controller('GameCtrl', function ($scope) {

    $scope.showCard = function() {
        $('#card').show();
    });

    $scope.hideCard = function() {
            $('#card').hide();
        });

});
