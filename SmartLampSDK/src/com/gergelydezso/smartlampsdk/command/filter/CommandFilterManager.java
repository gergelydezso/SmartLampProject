package com.gergelydezso.smartlampsdk.command.filter;

import com.gergelydezso.smartlampsdk.command.Command;

public class CommandFilterManager {
  CommandFilterChain filterChain;

  public CommandFilterManager(CommandTarget target) {
    filterChain.setTarget(target);
    filterChain = new CommandFilterChain();
  }

  public void setFilter(CommandFilter filter) {
    filterChain.addFilter(filter);
  }

  public void sendCommand(Command comand) {
    filterChain.execute(comand);
  }
}
