package dp

import (
	"algorithm.com/structure"
	"testing"
)

func TestClimbStairs(t *testing.T) {
	structure.Validate(ClimbStairs(1), 1, t)
}

func TestMaxEnvelopesDp(t *testing.T) {
	res := maxEnvelopesByDp([][]int{{5, 4}, {6, 4}, {6, 7}, {2, 3}})
	structure.Validate(res, 3, t)
	res = maxEnvelopesByDp([][]int{{1, 1}, {1, 1}, {1, 1}})
	structure.Validate(res, 1, t)
}

func TestMaxEnvelopesLis(t *testing.T) {
	res := maxEnvelopesByLis([][]int{{5, 4}, {6, 4}, {6, 7}, {2, 3}})
	structure.Validate(res, 3, t)
	res = maxEnvelopesByLis([][]int{{1, 1}, {1, 1}, {1, 1}})
	structure.Validate(res, 1, t)
}
