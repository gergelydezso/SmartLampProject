package com.gergelydezso.smartlampsdk.command.filter;

import java.util.ArrayList;
import java.util.List;

import com.gergelydezso.smartlampsdk.command.Command;

public class CommandFilterChain {
  private List<CommandFilter> filters = new ArrayList<CommandFilter>();
  // private Iterator<CommandFilter> filterIterator = filters.iterator();
  private CommandTarget target;
  private boolean ok;

  public void setTarget(CommandTarget target) {
    this.target = target;
  }

  public void addFilter(CommandFilter filter) {
    filters.add(filter);
  }

  public void execute(Command command) {

    ok = true;

    for (CommandFilter filter : filters) {
      if (!filter.execute(command)) {
        ok = false;
        break;
      }
    }

    if (ok)
      target.execute(command);
  }

}