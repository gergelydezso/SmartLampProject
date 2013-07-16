package com.gergelydezso.smartlampsdk.command.filter;

public interface CommandFilter {
  public void execute(String request, CommandFilterCallback callback);
}
