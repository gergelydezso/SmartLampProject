package com.gergelydezso.smartlampsdk.command.filters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gergelydezso.smartlampsdk.command.Command;

public class AnalyticsEventFilterChain implements Filter<Command> {

  private List<Filter<Command>> filters;
  private Iterator<Filter<Command>> filterIterator;

  public AnalyticsEventFilterChain() {
    filters = new ArrayList<Filter<Command>>();
    filters.add(new AnalyticsFilter(this));
    filterIterator = filters.iterator();
  }

  @Override
  public boolean doFilter(Command event) {
    if (filterIterator.hasNext()) {
      return filterIterator.next().doFilter(event);
    }
    return true;
  }

  public boolean filterEvent(Command event) {
    filterIterator = filters.iterator();
    return doFilter(event);
  }

}
