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

  @Test
  public void brand_deleteWorksProperly() {
    Brand brand = new Brand("Jimmy Choo", "High Heels");
    brand.save();
    brand.delete();
    assertEquals(Brand.all().size(), 0);
  }

  @Test
  public void store_updateWorksProperly() {
    Brand brand = new Brand("Jimmy Choo", "High Heels");
    brand.save();
    brand.update("Jimmmmmmy Choo", "High-Heels");
    assertEquals(brand.getName(), "Jimmmmmmy Choo");
    assertEquals(Brand.find(brand.getId()).getName(), "Jimmmmmmy Choo");
    assertEquals(brand.getSpecialty(), "High-Heels");
    assertEquals(Brand.find(brand.getId()).getSpecialty(), "High-Heels");
  }

  @Test
  public void equals_returnsTrueIfSameNameAddressAndPhoneNumber() {
    Brand firstBrand = new Brand("Jimmy Choo", "High Heels");
    firstBrand.save();
    Brand secondBrand = new Brand("Jimmy Choo", "High Heels");
    secondBrand.save();
    assertTrue(firstBrand.equals(secondBrand));
  }

  @Test
  public void getAllStores_returnsAllStoresThatCarryParticularBrand() {
    Store firstStore = new Store("Barneys New York", "660 Madison Ave, New York, NY 10065", "212 826 8900");
    firstStore.save();
    Store secondStore = new Store("Saks Fifth Avenue", "611 5th Ave, New York, NY 10022", "212 753 4000");
    secondStore.save();
    Brand brand = new Brand("Jimmy Choo", "High Heels");
    brand.save();
    brand.addStore(firstStore.getId());
    brand.addStore(secondStore.getId());
    Store[] stores = new Store[] {firstStore, secondStore};
    assertTrue(brand.getAllStores().containsAll(Arrays.asList(stores)));
  }



}
