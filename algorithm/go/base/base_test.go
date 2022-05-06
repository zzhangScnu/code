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
	structure.Validate(AddBinary("11", "1"), "100", t) // 7
	structure.Validate(AddBinary("0", "1"), "1", t)
	structure.Validate(AddBinary("1111", "1111"), "11110", t)
	// todo: 这个用例会上溢
	structure.Validate(AddBinary("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101", "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"), "110111101100010011000101110110100000011101000101011001000011011000001100011110011010010011000000000", t)
}
