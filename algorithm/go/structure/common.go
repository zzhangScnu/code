package structure

import (
	"reflect"
	"testing"
)

func Validate(actual any, expect any, t *testing.T) {
	if actual != expect {
		t.Errorf("test failed, expect value is: %#v, actual value is: %#v", expect, actual)
	}
}

// ValidateDeep 引用类型如切片、字典的比较
func ValidateDeep(actual any, expect any, t *testing.T) {
	if !reflect.DeepEqual(actual, expect) {
		t.Errorf("test failed, expect value is: %#v, actual value is: %#v", expect, actual)
	}
}
