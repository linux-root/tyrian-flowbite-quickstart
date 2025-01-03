package com.example.tyrianflowbitequickstart.util

import tyrian.Cmd
import tyrian.cmds.*
import zio.*
import com.example.tyrianflowbitequickstart.model.Msg
import zio.interop.catz.*

object Authentication {

  def authenticate(username: String, password: String): Cmd[Task, Nothing] =
    PrettyLogger.success(
      s"mocking authentication for username: $username, password: $password"
    )

}
