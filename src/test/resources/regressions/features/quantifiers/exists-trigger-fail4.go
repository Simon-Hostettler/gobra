package pkg;

pure func foo (n int) bool

// invalid trigger: assigning ghost var `i` to non-ghost input parameter `n`
//:: ExpectedOutput(type_error)
requires exists i int :: { foo(i) } 0 < i
func bar () { }