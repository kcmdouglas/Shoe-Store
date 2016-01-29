import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Brand {
  private int mId;
  private String mName;
  private String mSpecialty;

  public int getId(){
    return mId;
  }

  public String getName(){
    return mName;
  }

  public String getSpecialty(){
    return mSpecialty;
  }

  public Brand(String name, String specialty) {
    this.mName = name;
    this.mSpecialty = specialty;
  }

  @Override
  public boolean equals(Object otherBrand) {
    if (!(otherBrand instanceof Brand)) {
      return false;
    } else {
      Brand newBrand = (Brand) otherBrand;
      return this.getName().equals(newBrand.getName()) &&
        this.getSpecialty().equals(newBrand.getSpecialty());
    }
  }

  public static List<Store> all() {
    String sql = "SELECT id AS mId, name AS mName, specialty as mSpecialty FROM brands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Store.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO brands(name, specialty) VALUES (:name, :specialty)";
    try(Connection con = DB.sql2o.open()) {
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("name", this.mName)
        .addParameter("specialty", this.mSpecialty)
        .executeUpdate()
        .getKey();
    }
  }

  public static Brand find(int id) {
    String sql = "SELECT id AS mId, name AS mName, specialty as mSpecialty FROM brands WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Brand brand = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Brand.class);
    return brand;
    }
  }

  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //   String deleteStore = "DELETE FROM stores WHERE id = :id;";
  //   con.createQuery(deleteStore)
  //     .addParameter("id", mId)
  //     .executeUpdate();
  //   }
  // }
  //
  // public void update(String newName, String newAddress, String newPhoneNumber) {
  //   mName = newName;
  //   mAddress = newAddress;
  //   mPhoneNumber = newPhoneNumber;
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE stores SET name = :name, address = :address, phone_number = :phoneNumber WHERE id = :id";
  //     con.createQuery(sql)
  //       .addParameter("name", newName)
  //       .addParameter("address", newAddress)
  //       .addParameter("phoneNumber", newPhoneNumber)
  //       .addParameter("id", mId)
  //       .executeUpdate();
  //   }
  // }
}
