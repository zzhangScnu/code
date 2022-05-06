package structure

import "testing"

func Validate(actual any, expect any, t *testing.T) {
	if actual != expect {
		t.Errorf("test failed, expect value is: %#v, acutal value is: %#v", expect, actual)
	}
}
