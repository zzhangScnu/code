package base

import (
	"algorithm.com/structure"
	"testing"
)

func TestConvertToZ(t *testing.T) {
	res := Convert("ABCDE", 4)
	structure.Validate(res, "ABCED", t)
}

func TestRomanToInt(t *testing.T) {
	res := RomanToInt("MCMXCIV")
	structure.Validate(res, 1994, t)
}

func TestIntToRoman(t *testing.T) {
	res := IntToRoman(20)
	structure.Validate(res, "XX", t)
}
