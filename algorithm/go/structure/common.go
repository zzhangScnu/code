package structure

import "testing"

func Validate(actual any, expect any, t *testing.T) {
	if actual != expect {
		t.Errorf("test failed, acutal value is: %#v", actual)
	}
}
