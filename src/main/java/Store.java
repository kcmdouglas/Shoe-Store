import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Store {
  private int mId;
  private String mName;
  private String mAddress;
  private String mPhoneNumber;

  public int getId(){
    return mId;
  }

  public String getName(){
    return mName;
  }

  public String getAddress(){
    return mAddress;
  }

  public String getPhoneNumber(){
    return mPhoneNumber;
  }

  public Store(String name, String address, String phoneNumber) {
    this.mName = name;
    this.mAddress = address;
    this.mPhoneNumber = phoneNumber;
  }

  @Override
  public boolean equals(Object otherStore) {
    if (!(otherStore instanceof Store)) {
      return false;
    } else {
      Store newStore = (Store) otherStore;
      return this.getName().equals(newStore.getName()) &&
        this.getAddress().equals(newStore.getAddress())  &&
        this.getPhoneNumber().equals(newStore.getPhoneNumber());
    }
  }

  public static List<Store> all() {
    String sql = "SELECT id AS mId, name AS mName, address AS mAddress, phone_number AS mPhoneNumber FROM stores";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Store.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO stores(name, address, phone_number) VALUES (:name, :address, :phoneNumber)";
    try(Connection con = DB.sql2o.open()) {
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("name", this.mName)
        .addParameter("address", this.mAddress)
        .addParameter("phoneNumber", this.mPhoneNumber)
        .executeUpdate()
        .getKey();
    }
  }

  public static Store find(int id) {
    String sql = "SELECT id AS mId, name AS mName, address AS mAddress, phone_number AS mPhoneNumber FROM stores WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Store store = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Store.class);
    return store;
    }
  }
}









  //
  // String  = "044 668 18 00"
  // PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
  // try {
  //   PhoneNumber swissNumberProto = phoneUtil.parse(swissNumberStr, "CH");
  // } catch (NumberParseException e) {
  //   System.err.println("NumberParseException was thrown: " + e.toString());
  // }
