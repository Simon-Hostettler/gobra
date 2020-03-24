
type cell struct{
	val int
}

func foo() {
	x := &cell{42}
	x.val = 17
	assert x.val == 17

	y := cell{42}
	z! := y
	y.val = 17
	assert y.val == 17 && z.val == 42

    a! := cell{42}
    z = a
    ap := &a
    zp := &z
    a.val = 17
    assert a.val == 17 && z.val == 42



    //:: ExpectedOutput(assert_error:assertion_error)
    assert false
}

func indirectAddressability() {
    x! := cell{42}
    p := &x
    p.val = 17
    assert x.val == 17

    //:: ExpectedOutput(assert_error:assertion_error)
    assert false
}

func bar() {
    x := cell{42}
    p := &x.val
    *p = 17
    assert x.val == 17
    assert x == cell{17}
    y := x
    *p = 29
    assert y.val == 17 && x.val == 29

    //:: ExpectedOutput(assert_error:assertion_error)
    assert false
}

