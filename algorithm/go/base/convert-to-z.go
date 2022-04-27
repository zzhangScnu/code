//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
//
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
//
// 请你实现这个将字符串进行指定行数变换的函数：
//
//
//string convert(string s, int numRows);
//
//
//
// 示例 1：
//
//
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
//
//示例 2：
//
//
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
//
//
// 示例 3：
//
//
//输入：s = "A", numRows = 1
//输出："A"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 由英文字母（小写和大写）、',' 和 '.' 组成
// 1 <= numRows <= 1000
//
// Related Topics 字符串 👍 1658 👎 0

package main

import (
	"fmt"
	"math"
)

/*
字符串长度：n
行数：r
一个周期占多少个字符：p = r + r - 1 - 1 = 2r - 2
一个周期占多少列：c = p - r + 1 = r - 1
多少个周期：t = n / (2r - 2)，向上取整
多少列：t * c
i mod p < r - 1，向下，否则向右上
*/
func convert(s string, numRows int) string {
	if numRows == 1 {
		return s
	}
	sLen := len(s)
	period := 2*numRows - 2
	numCols := int(math.Ceil(float64(sLen)/float64(period))) * (numRows - 1) //等价于 (sLen + period - 1) / period * (numRows - 1)
	mtx := make([][]byte, numRows)
	for i := 0; i < numRows; i++ {
		mtx[i] = make([]byte, numCols)
	}
	r, c := 0, 0
	for i := 0; i < sLen; i++ {
		mtx[r][c] = s[i]
		if lastIndex := numRows - 1; i%period < lastIndex {
			r++
		} else {
			r--
			c++
		}
	}
	res := make([]byte, 0, sLen) // 第一次写成了make([]byte, sLen)，导致append的res前面有一堆的零值
	for _, strings := range mtx {
		for _, element := range strings {
			if element > 0 {
				res = append(res, element)
			}
		}
	}
	return string(res)
}

/*
一开始的想法是按确定每个字符的坐标来解决。但是想不清楚推进纵坐标的时机
4行的情况下：
numRows - 1 = 3
列0：全满（0 % 3）
列1：一个字母，横坐标位置在3 - （1 % 3）
列2：一个字母，横坐标位置在3 - （2 % 3）
列3：全满（3 % 3）
列4：一个字母，横坐标位置在3 - （4 % 3）
*/
func main() {
	res := convert("ABCDE", 4)
	fmt.Println(res)
}
