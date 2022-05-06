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
	"strconv"
)

func AddBinaryNormally(a string, b string) string {
	lA, lB, carry := len(a), len(b), 0
	lM := int(math.Max(float64(lA), float64(lB))) - 1
	i := 0
	var res string
	for {
		if i > lM {
			break
		}
		if i < lA {
			carry += int(a[lA-i-1] - '0')
		}
		if i < lB {
			carry += int(b[lB-i-1] - '0')
		}
		i++
		res = strconv.Itoa(carry%2) + res
		carry /= 2
	}
	if carry != 0 {
		res = "1" + res
	}
	return res
}

func AddBinary(a string, b string) string {
	// 指定字符串为二进制格式为基础，解析为十进制数字。保存无进位相加结果
	x, _ := strconv.ParseInt(a, 2, 64)
	// 保存进位
	y, _ := strconv.ParseInt(b, 2, 64)
	for y != 0 {
		sum := x ^ y
		carry := (x & y) << 1
		x, y = sum, carry
	}
	return strconv.FormatInt(x, 2)
}
