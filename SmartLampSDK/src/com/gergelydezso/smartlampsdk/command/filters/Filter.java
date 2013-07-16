package com.gergelydezso.smartlampsdk.command.filters;

public interface Filter<T> {

  public boolean doFilter(T toBeFiltered);

}
