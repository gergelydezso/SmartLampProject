package com.gergelydezso.smartlampsdk.command.filter;

public class CommandFilterClient {
  CommandFilterManager filterManager;

  public void setFilterManager(CommandFilterManager filterManager) {
    this.filterManager = filterManager;
  }

  public void sendRequest(String request) {
    filterManager.filterRequest(request);
  }
}
