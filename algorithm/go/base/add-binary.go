//给你两个二进制字符串，返回它们的和（用二进制表示）。
//
// 输入为 非空 字符串且只包含数字 1 和 0。
//
//
//
// 示例 1:
//
// 输入: a = "11", b = "1"
//输出: "100"
//
// 示例 2:
//
// 输入: a = "1010", b = "1011"
//输出: "10101"
//
//
//
// 提示：
//
//
// 每个字符串仅由字符 '0' 或 '1' 组成。
// 1 <= a.length, b.length <= 10^4
// 字符串如果不是 "0" ，就都不含前导零。
//
// Related Topics 位运算 数学 字符串 模拟 👍 809 👎 0

package base

import (
	"math"
	"strings"
)

func AddBinary(a string, b string) string {
	intsA, lA := reverseStr(a), len(a)
	intsB, lB := reverseStr(b), len(b)
	i, j, k := 0, 0, 0
	res := make([]int, int(math.Max(float64(lA), float64(lB)))+1)
	var carry int
	var sum int
	for i < lA && j < lB {
		sum = intsA[i] + intsB[i] + carry
		res[k], carry = sum%2, sum/2
		i, j, k = i+1, j+1, k+1
	}
	for i < lA {
		sum = intsA[i] + carry
		res[k], carry = sum%2, sum/2
		i, k = i+1, k+1
	}
	for j < lB {
		sum = intsB[j] + carry
		res[k], carry = sum%2, sum/2
		j, k = j+1, k+1
	}
	for carry == 1 {
		sum = res[k] + carry
		res[k], carry = sum%2, sum/2
		k = k + 1
	}
	return reverseInts(res)
}

func reverseStr(str string) []int {
	runes := []rune(str)
	l := len(runes)
	ints := make([]int, l)
	for i, j := 0, l-1; i <= j; i, j = i+1, j-1 {
		ints[i], ints[j] = int(runes[j]-'0'), int(runes[i]-'0')
	}
	return ints
}

func reverseInts(ints []int) string {
	var res string
	for _, v := range ints {
		res = string(rune(v)+'0') + res
	}
	res = strings.TrimPrefix(res, "0")
	if res == "" {
		return "0"
	} else {
		return res
	}
}
