import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Welcome to the Epicodus Shoe Compendium");
  }

  @Test
  public void storesTest() {
    goTo("http://localhost:4567/stores");
    assertThat(pageSource()).contains("All Stores");
  }

  @Test
  public void addStoreTest() {
    Store store = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "2128268900");
    store.save();
    goTo("http://localhost:4567/stores");
    assertThat(pageSource()).contains("Barneys New York");
    assertThat(pageSource()).contains("660 Madison Ave, New York, NY 10065");
    assertThat(pageSource()).contains("2128268900");
  }

  @Test
  public void updateStoreTest() {
    Store store = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "2128268900");
    store.save();
    goTo("http://localhost:4567/stores");
    assertThat(pageSource()).contains("Barneys New York");
    assertThat(pageSource()).contains("660 Madison Ave, New York, NY 10065");
    assertThat(pageSource()).contains("2128268900");
    store.update("Saks Fifth Avenue", "611 5th Ave, New York, NY 10022", "2127534000");
    goTo("http://localhost:4567/stores");
    assertThat(pageSource()).contains("Saks Fifth Avenue");
    assertThat(pageSource()).contains("611 5th Ave, New York, NY 10022");
    assertThat(pageSource()).contains("2127534000");
  }

  @Test
  public void deleteStoreTest() {
    Store store = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "2128268900");
    store.save();
    goTo("http://localhost:4567/stores");
    assertThat(pageSource()).contains("Barneys New York");
    assertThat(pageSource()).contains("660 Madison Ave, New York, NY 10065");
    assertThat(pageSource()).contains("2128268900");
    store.delete();
    goTo("http://localhost:4567/stores");
    assertThat(pageSource()).contains("All Stores");
  }

  @Test
  public void individualStorePagesLinkProperly() {
    Store store = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "2128268900");
    store.save();
    goTo("http://localhost:4567/stores");
    click("a", withText("Barneys New York"));
    assertThat(pageSource()).contains("Barneys New York");
  }

}
