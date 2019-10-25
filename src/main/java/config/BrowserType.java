package config;

import reporting.Log;

public enum BrowserType {
  CHROME("chrome"),
  FIREFOX("firefox");

  private String type;

  BrowserType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return getType();
  }

  public String getType() {
    return type;
  }

  public static BrowserType fromString(String browserType) {
    if (browserType != null) {
      for (BrowserType browserTypeIterator : BrowserType.values()) {
        if (browserType.equalsIgnoreCase(browserTypeIterator.type)) {
          return browserTypeIterator;
        }
      }
    }
    Log.info("Can't convert string to browser type");
    return null;
  }
}
