package com.gergelydezso.smartlampsdk.command.filters;

import com.gergelydezso.smartlampsdk.command.Command;

public class AnalyticsFilter extends AbstractAnalyticsEventFilter {

  public AnalyticsFilter(AnalyticsEventFilterChain chain) {
    super(chain);
  }

  @Override
  public boolean doFilter(Command event) {

    return chain.doFilter(event);
  }

}