package com.example.tyrianflowbitequickstart.view

import tyrian.Html.*
import tyrian.Html
import com.example.tyrianflowbitequickstart.model.Msg

object MainContainer:

  def apply(content: Html[Msg]): Html[Msg] =
    div(cls := "flex items-center justify-center min-h-screen")(
      content
    )
