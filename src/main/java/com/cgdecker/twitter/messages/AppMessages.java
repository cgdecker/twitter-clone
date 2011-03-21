package com.cgdecker.twitter.messages;

import com.google.sitebricks.i18n.Message;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public interface AppMessages {
  @Message(message = "Twitter Clone", description = "App name")
  String appName();

  @Message(message = "Welcome to my stupid Twitter clone! Please register and log in to post.",
           description = "Welcome message to users who are not logged in.")
  String welcome();

  @Message(message = "Invalid username or password.")
  String invalidLogin();
}
