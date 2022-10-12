package design

import (
	"fmt"
	"testing"
)

/**
测试用例:["LRUCache","get","put","get","put","put","get","get"]
		[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
期望结果:[null,-1,null,-1,null,null,2,6]
*/
func TestLRUCache(t *testing.T) {
	cache := LRUConstructor(2)
	fmt.Println(cache.Get(2))
	cache.Put(2, 6)
	fmt.Println(cache.Get(1))
	cache.Put(1, 5)
	cache.Put(1, 2)
	fmt.Println(cache.Get(1))
	fmt.Println(cache.Get(2))
}
