package com.gergelydezso.smartlampsdk.command.filter;

import com.gergelydezso.smartlampsdk.command.Command;

public interface CommandFilter {
  public boolean execute(Command CommandToBeFiltered);
}
