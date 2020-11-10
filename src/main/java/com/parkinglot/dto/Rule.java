package com.parkinglot.dto;

import static java.util.Arrays.asList;

public enum Rule {

  EVEN_DISTRIBUTION ("even_distribution", "Even Distribution"),
  FILL_FIRST ("fill_first", "Fill First");

  private final String value;
  private final String description;

  Rule(final String value, final String description) {
    this.value = value;
    this.description = description;
  }

  public String getValue() {
    return value;
  }

  public String getDescription() {
    return description;
  }

  public static Rule getRuleByValue(final String value) {
    var optionalRule = asList(Rule.values()).stream()
            .filter(rule -> value.equalsIgnoreCase(rule.getValue())).findFirst();
    return optionalRule.orElseThrow(() -> new IllegalArgumentException("Could not find matching rule"));
  }
}
