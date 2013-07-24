package com.gergelydezso.smartlampsdk.command.filter;

import com.gergelydezso.smartlampsdk.command.Command;

public class CommandFilterManager {
  CommandFilterChain filterChain;

  public CommandFilterManager(CommandTarget target) {
    filterChain = new CommandFilterChain();
    filterChain.setTarget(target);
  }

  public void setFilter(CommandFilter filter) {
    filterChain.addFilter(filter);
  }

  public void sendCommand(Command command) {

    filterChain.execute(command);
  }
}
