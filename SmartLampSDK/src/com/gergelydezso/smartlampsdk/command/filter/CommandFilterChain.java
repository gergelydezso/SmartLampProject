package com.gergelydezso.smartlampsdk.command.filter;

import java.util.ArrayList;
import java.util.List;

public class CommandFilterChain {
  private List<CommandFilter> filters = new ArrayList<CommandFilter>();
  private CommandTarget target;

  public void setTarget(CommandTarget target) {
    this.target = target;
  }

  public void addFilter(CommandFilter filter) {
    filters.add(filter);
  }

  public void execute(String request) {
    for (CommandFilter filter : filters) {
      filter.execute(request, new CommandFilterCallback() {

        @Override
        public void requestOk() {
          target.execute("alma");

        }
      });
    }

  }

}