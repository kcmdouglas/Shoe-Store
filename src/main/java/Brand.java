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

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String deleteBrand = "DELETE FROM brands WHERE id = :id;";
    con.createQuery(deleteBrand)
      .addParameter("id", mId)
      .executeUpdate();
    }
  }

  public void update(String newName, String newSpecialty) {
    mName = newName;
    mSpecialty = newSpecialty;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE brands SET name = :name, specialty = :specialty WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", newName)
        .addParameter("specialty", newSpecialty)
        .addParameter("id", mId)
        .executeUpdate();
    }
  }

  public void addStore(int storeId) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO stores_brands(store_id, brand_id)  VALUES (:storeId, :brandId)";
    con.createQuery(sql)
      .addParameter("storeId", storeId)
      .addParameter("brandId", this.getId())
      .executeUpdate();
    }
  }
}
