package com.kalmanb.caseclasscopy

import com.kalmanb.test.TestSpec

class CaseClassCopyTest extends TestSpec {

  case class Rest(name: String, age: Int)
  case class DB(name: String, age: Int)

  object Test {
    val Copier = new CaseClassCopier[Rest, DB] {
      def copy(rest: Rest): DB =
        DB(
          name = rest.name,
          age = rest.age
        )
    }
  }

  describe("copy case classes") {
    it("should explicit declaration") {
      val mapper = CaseClassCopier.materializeCCC[Rest, DB]
      mapper.copy(Rest("john", 123)).name shouldBe "john"
    }

    it("should be avalible implicitly") {
      def copy[S, D](t: S)(implicit mapper: CaseClassCopier[S, D]) = mapper.copy(t)
      DB("john", 15) shouldBe copy[Rest, DB](Rest("john", 15))
    }
  }
}
