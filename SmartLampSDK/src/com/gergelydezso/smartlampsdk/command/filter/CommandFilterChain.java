package com.gergelydezso.smartlampsdk.command.filter;

import java.util.ArrayList;
import java.util.List;

import com.gergelydezso.smartlampsdk.command.Command;

public class CommandFilterChain {
  private List<CommandFilter> filters = new ArrayList<CommandFilter>();
  private CommandTarget target;

  public void setTarget(CommandTarget target) {
    this.target = target;
  }

  public void addFilter(CommandFilter filter) {
    filters.add(filter);
  }

  public void execute(Command command) {
    for (CommandFilter filter : filters) {
      filter.execute(command);
    }
    target.execute(command);
  }

}