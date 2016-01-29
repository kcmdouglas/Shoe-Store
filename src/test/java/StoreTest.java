import java.util.Arrays;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

public class StoreTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Store.all().size(), 0);
  }

  @Test
  public void stores_haveName() {
    Store store = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "212 826 8900");
    store.save();
    assertEquals("Barneys New York", Store.find(store.getId()).getName());
  }

  @Test
  public void store_deleteWorksProperly() {
    Store store = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "212 826 8900");
    store.save();
    store.delete();
    assertEquals(Store.all().size(), 0);
  }

  @Test
  public void store_updateWorksProperly() {
    Store store = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "212 826 8900");
    store.save();
    store.update("Barney's New York", "660 Madison Ave, New York, NY 10065", "212 826 8900");
    assertEquals(store.getName(), "Barney's New York");
    assertEquals(Store.find(store.getId()).getName(), "Barney's New York");
  }

}
