package base

import "testing"

func validate(actual any, expect any, t *testing.T) {
	if actual != expect {
		t.Errorf("test failed, acutal value is: %#v", actual)
	}
}

func TestConvertToZ(t *testing.T) {
	res := Convert("ABCDE", 4)
	validate(res, "ABCED", t)
}

func TestRomanToInt(t *testing.T) {
	res := RomanToInt("MCMXCIV")
	validate(res, 1994, t)
}
