package dp

import "math"

// 因为只能交易一次，所以买入的时候利润是 -prices[i]
// 		profit[i][1] = max(profit[i-1][1], -prices[i])
//		profit[i][0] = max(profit[i-1][0], profit[i-1][1]+prices[i])
func maxProfit(prices []int) int {
	profit_i_0, profit_i_1 := 0, math.MinInt
	size := len(prices)
	for i := 0; i < size; i++ {
		profit_i_1 = max(profit_i_1, -prices[i])
		profit_i_0 = max(profit_i_0, profit_i_1+prices[i])
	}
	return profit_i_0
}
