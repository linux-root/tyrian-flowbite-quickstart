package com.example.tyrianflowbitequickstart

import tyrian.Cmd
import zio.*
import tyrian.Html
import tyrian.Html.div
import java.util.UUID
import com.example.tyrianflowbitequickstart.model.*
import com.example.tyrianflowbitequickstart.view.pages.*
import tyrian.cmds.Logger
import zio.interop.catz.*
import com.example.tyrianflowbitequickstart.util.PrettyLogger

package object page {
  enum Page(
    val path: String,
    val render: Model => Html[Msg],
    beforeEnter: Model => Cmd[Task, Msg] = _ => Cmd.None, // e.g: side effect for loading data
    val isSecured: Boolean = true
  ):
    def doNavigate(model: Model): Cmd[Task, Msg] = beforeEnter(model) |+| Cmd.emit(Msg.DoNavigate(this))

    case Home      extends Page("/", model => Welcome("Hello world"), _ => PrettyLogger.info("Entering Home page"))
    case Alerts    extends Page("/components/alerts", model => AlertsView(), _ => PrettyLogger.info("Entering Alerts page"))
    case Accordion extends Page("/components/accordion", model => AccordionView(), _ => PrettyLogger.info("Entering Accordion page"))
    case Buttons   extends Page("/components/buttons", model => ButtonsView(), _ => PrettyLogger.info("Entering Buttons page"))
    case Badges    extends Page("/components/badges", model => BadgesView(), _ => PrettyLogger.info("Entering Badges page"))
    case Cards     extends Page("/components/cards", model => CardsView(), _ => PrettyLogger.info("Entering Cards page"))
    case Carousel  extends Page("/components/carousel", model => CarouselView(), _ => PrettyLogger.info("Entering Carousel page"))

  object Page {}

}