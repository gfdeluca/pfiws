'use strict';

/* Services */

var services = angular.module('ngpfi.services', ['ngResource']);

services.factory('UserFactory', function ($resource) {
    return $resource('/pfi/services/query', {}, {
        query: {
            method: 'GET',
            params: {},
            isArray: false
        }
    })
});
