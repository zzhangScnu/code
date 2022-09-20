package pre_sum

import (
	. "algorithm.com/structure"
	"fmt"
	"testing"
)

func TestSumRegion(t *testing.T) {
	numMatrix := Constructor1([][]int{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}})
	Validate(numMatrix.SumRegion(2, 1, 4, 3), 8, t)
}

func TestGetProduct(t *testing.T) {
	productOfNumbers := Constructor2()
	productOfNumbers.Add(3)                     // [3]
	productOfNumbers.Add(0)                     // [3,0]
	productOfNumbers.Add(2)                     // [3,0,2]
	productOfNumbers.Add(5)                     // [3,0,2,5]
	productOfNumbers.Add(4)                     // [3,0,2,5,4]
	fmt.Println(productOfNumbers.GetProduct(2)) // 返回 20 。最后 2 个数字的乘积是 5 * 4 = 20
	fmt.Println(productOfNumbers.GetProduct(3)) // 返回 40 。最后 3 个数字的乘积是 2 * 5 * 4 = 40
	fmt.Println(productOfNumbers.GetProduct(4)) // 返回  0 。最后 4 个数字的乘积是 0 * 2 * 5 * 4 = 0
	productOfNumbers.Add(8)                     // [3,0,2,5,4,8]
	fmt.Println(productOfNumbers.GetProduct(2)) // 返回 32 。最后 2 个数字的乘积是 4 * 8 = 32
}
