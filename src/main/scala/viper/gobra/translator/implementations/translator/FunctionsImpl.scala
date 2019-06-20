package viper.gobra.translator.implementations.translator

import viper.gobra.ast.{internal => in}
import viper.gobra.translator.interfaces.translator.Functions
import viper.gobra.translator.interfaces.{Collector, Context}
import viper.gobra.util.ViperWriter.{ExprWriter => ew, StmtWriter => sw}
import viper.silver.{ast => vpr}

class FunctionsImpl extends Functions {

  override def finalize(col: Collector): Unit = ()

  override def translate(x: in.Function)(ctx: Context): vpr.Method = {

    val args    = x.args    map (ctx.loc.formalArg(_)(ctx))
    val results = x.results map (ctx.loc.formalRes(_)(ctx))

    val pres  = x.pres  map (ctx.ass.precondition(_)(ctx))
    val posts = x.posts map (ctx.ass.postcondition(_)(ctx))

    val wBody = x.body map (ctx.stmt.translate(_)(ctx))
    assert(wBody forall (_.global.isEmpty))

    val vBody = wBody map (w => vpr.Seqn(Vector(w.res), w.global)(w.res.pos))

    vpr.Method(
      name = x.name,
      formalArgs = args,
      formalReturns = results,
      pres = pres,
      posts = posts,
      body = vBody
    )(x.src.vprSrc)
  }
}