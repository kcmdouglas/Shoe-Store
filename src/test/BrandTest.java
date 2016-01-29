import java.util.Arrays;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

public class BrandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Brand.all().size(), 0);
  }

  @Test
  public void brand_SavesProperly() {
    Brand brand = new Brand("Jimmy Choo", "High Heels");
    brand.save();
    assertEquals("Jimmy Choo", Brand.find(brand.getId()).getName());
    assertEquals("High Heels", Brand.find(brand.getId()).getSpecialty());
  }

  // @Test
  // public void store_deleteWorksProperly() {
  //   Store store = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "212 826 8900");
  //   store.save();
  //   store.delete();
  //   assertEquals(Store.all().size(), 0);
  // }
  //
  // @Test
  // public void store_updateWorksProperly() {
  //   Store store = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "212 826 8900");
  //   store.save();
  //   store.update("Barney's New York", "660 Madison Ave, New York, NY 10065", "212 826 8900");
  //   assertEquals(store.getName(), "Barney's New York");
  //   assertEquals(Store.find(store.getId()).getName(), "Barney's New York");
  // }
  //
  // @Test
  // public void equals_returnsTrueIfSameNameAddressAndPhoneNumber() {
  //   Store firstStore = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "212 826 8900");
  //   firstStore.save();
  //   Store secondStore = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "212 826 8900");
  //   secondStore.save();
  //   assertTrue(firstStore.equals(secondStore));
  // }

}