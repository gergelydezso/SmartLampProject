package com.gergelydezso.smartlampsdk.command.filter;

import com.gergelydezso.smartlampsdk.command.Command;

public interface CommandFilter {
  // TODO - CODE REVIEW - andrei|Nov 18, 2013 - please avoid param names with capital letters.
  public boolean execute(Command CommandToBeFiltered);
}
