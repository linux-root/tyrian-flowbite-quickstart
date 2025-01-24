package com.example.tyrianflowbitequickstart.util

import tyrian.Cmd
import tyrian.cmds.*
import zio.*
object Authentication {

  def authenticate(username: String, password: String): Cmd[Task, Nothing] =
    PrettyLogger.success(
      s"mocking authentication for username: $username, password: $password"
    )

}
