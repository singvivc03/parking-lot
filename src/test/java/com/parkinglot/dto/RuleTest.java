package com.parkinglot.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RuleTest {

  @ParameterizedTest
  @CsvSource({
          "even_distribution, EVEN_DISTRIBUTION",
          "fill_first, FILL_FIRST",
  })
  void shouldReturnRuleForGivenValue(final String value, final String expectedRule) {
    var actualRule = Rule.getRuleByValue(value);
    assertThat(actualRule.name(), is(expectedRule));
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenNoMatchingRuleIsFound() {
    var exception = assertThrows(IllegalArgumentException.class,
      () -> Rule.getRuleByValue("Some_rule"));
    assertThat(exception, instanceOf(IllegalArgumentException.class));
    assertThat(exception.getMessage(), is("Could not find matching rule"));
  }
}
