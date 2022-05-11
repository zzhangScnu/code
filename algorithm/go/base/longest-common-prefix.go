//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
//
//
// 示例 1：
//
//
//输入：strs = ["flower","flow","flight"]
//输出："fl"
//
//
// 示例 2：
//
//
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。
//
//
//
// 提示：
//
//
// 1 <= strs.length <= 200
// 0 <= strs[i].length <= 200
// strs[i] 仅由小写英文字母组成
//
// Related Topics 字符串 👍 2234 👎 0

package base

// todo：简单的纵向对比写法；分治；二分查找
func LongestCommonPrefix(strs []string) string {
	res, i := "", 0
Loop:
	for {
		pre := ""
		//for idx, str := range strs {
		for idx := 0; idx < len(strs); idx++ {
			str := strs[idx]
			if len(str) <= i {
				break Loop
			}
			if idx == 0 {
				pre = string(str[i])
			}
			if pre != string(str[i]) {
				break Loop
			}
		}
		res += pre
		i++
	}
	return res
}
