package main

import (
	"reflect"
	"testing"
)

func TestSearchRange(t *testing.T) {
	res := searchRangeOptimize([]int{5, 7, 7, 8, 8, 10}, 6)
	exp := []int{-1, -1}
	if !reflect.DeepEqual(res, exp) {
		t.Errorf("test failed, actual result is %v", res)
	}
	res = searchRangeOptimize([]int{}, 0)
	exp = []int{-1, -1}
	if !reflect.DeepEqual(res, exp) {
		t.Errorf("test failed, actual result is %v", res)
	}
}
