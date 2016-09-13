angular.module('mvcApp')
    .directive('youtube', function($sce) {
        return {
            restrict: 'EA',
            scope: { code:'=' },
            replace: true,
            template: '<div style="height:315px;"><iframe style="overflow:hidden;height:315px;width:420px" width="420px" height="315px" src="{{url}}" frameborder="0" allowfullscreen></iframe></div>',
            link: function (scope) {
                scope.$watch('code', function (newVal) {
                    if (newVal) {
                        scope.url = $sce.trustAsResourceUrl("http://www.youtube.com/embed/" + newVal);
                    }
                });
            }
        };
    })
    .directive('ckEditor', function () {  //TODO poprawić bo to jest dziwne rozwiązanie
        return {
            require: '?ngModel',
            scope: {
                refreshData: "="
            },
            link: function (scope, elm, attr, ngModel) {
                var ck = CKEDITOR.replace(elm[0]);
                if (!ngModel) return;
                function updateModel() {
                    scope.$apply(function () {
                        ngModel.$setViewValue(ck.getData());
                    });
                }

                ck.on('change', updateModel);
                ck.on('key', updateModel);
                ck.on('dataReady', updateModel);

                scope.$watch('refreshData', function (oldValue, newValue) {
                    if (newValue) {
                        ck.setData(newValue);
                    }
                });

            }
        };
    });


