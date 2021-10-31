object PurityTask {

  case class Integer(var value: Int) { }

  def isPure(incrementFn: Integer => Integer): Boolean = {
    val obj = Integer(1)
    val obj2 = Integer(1)
    val obj3 = Integer(3)
    val objRes = incrementFn(obj)
    val objRes2 = incrementFn(obj2)
    val objRes3 = incrementFn(obj3)
    obj == Integer(1) && objRes == objRes2 && objRes != objRes3
  }

}