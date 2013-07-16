package com.gergelydezso.smartlampsdk.command.filter;

public class CapacityCommandFilter implements CommandFilter {
  public void execute(String request, CommandFilterCallback calback) {
    System.out.println("Authenticating request: " + request);

    if (request.equals("lala")) {
      calback.requestOk();
    }
  }
}
