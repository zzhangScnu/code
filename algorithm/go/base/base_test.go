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

func TestAddBinary(t *testing.T) {
	structure.Validate(AddBinary("11", "1"), "100", t)
	structure.Validate(AddBinary("0", "1"), "1", t)
	structure.Validate(AddBinary("1111", "1111"), "11110", t)
	structure.Validate(AddBinary("100", "110010"), "110110", t)
}
