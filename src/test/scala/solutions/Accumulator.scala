package solutions

import Chisel.iotesters._

class AccumulatorTests(c: Accumulator, b: Option[Backend] = None) extends PeekPokeTester(c, _backend=b) {
  var tot = 0
  for (t <- 0 until 16) {
    val in = rnd.nextInt(2)
    poke(c.io.in, in)
    step(1)
    if (in == 1) tot += 1
    expect(c.io.out, tot)
  }
}

class AccumulatorTester extends ChiselFlatSpec {
  "Accumulator" should "correctly accumulate randomly generated numbers" in {
    runPeekPokeTester(() => new Accumulator){
      (c,b) => new AccumulatorTests(c,b)}
  }
}
