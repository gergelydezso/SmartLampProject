package com.gergelydezso.smartlampsdk.command.filter;

public class CommandFilterManager {
  CommandFilterChain filterChain;

  public CommandFilterManager(CommandTarget target) {
    filterChain = new CommandFilterChain();
    filterChain.setTarget(target);
  }

  public void setFilter(CommandFilter filter) {
    filterChain.addFilter(filter);
  }

  public void filterRequest(String request) {
    filterChain.execute(request);
  }
}
