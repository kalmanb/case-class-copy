package com.kalmanb.caseclasscopy

import scala.reflect.macros.blackbox.Context

trait CaseClassCopier[S,D] {
  def copy(source: S): D
}


object CaseClassCopier {
  implicit def materializeCCC[S, D]: CaseClassCopier[S, D] = macro impl[S, D]

  def impl[S: c.WeakTypeTag, D: c.WeakTypeTag](c: Context): c.Expr[CaseClassCopier[S, D]] = {
    import c.universe._
    val sourceTpe = weakTypeOf[S]
    val destTpe = weakTypeOf[D]
    val destClass = destTpe.typeSymbol.companion

    def getFields(tpe: Type) = tpe.decls.collectFirst {
      case m: MethodSymbol if m.isPrimaryConstructor ⇒ m
    }.get.paramLists.head

    val sourceFields = getFields(sourceTpe)
    val destFields = getFields(destTpe)

    val params2 = destFields.map { field =>
      val name = TermName(field.name.decodedName.toString)
      q"$name = source.$name"
    }

    c.Expr[CaseClassCopier[S, D]] { q"""
      new CaseClassCopier[$sourceTpe, $destTpe] {
        def copy(source: $sourceTpe): $destTpe =
          $destClass(
            ..$params2
          )
      }
      """
    }
  }
}

