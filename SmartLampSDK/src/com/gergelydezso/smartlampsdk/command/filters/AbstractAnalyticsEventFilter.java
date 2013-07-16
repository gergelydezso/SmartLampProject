package com.gergelydezso.smartlampsdk.command.filters;

import com.gergelydezso.smartlampsdk.command.Command;

public abstract class AbstractAnalyticsEventFilter implements Filter<Command> {

  protected AnalyticsEventFilterChain chain;

  public AbstractAnalyticsEventFilter(AnalyticsEventFilterChain chain) {
    super();
    this.chain = chain;
  }

  public abstract boolean doFilter(Command toBeFiltered);

}