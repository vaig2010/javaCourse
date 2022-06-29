package users;

import java.text.ParseException;

public class Patient {
    private final Integer id;
    private final String name;
    private final String registrationDate;

    public Patient(Integer id, String name, String registrationDate) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;

//        //String date_s = "2011-01-18";
//        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = dt.parse(registrationDate);
//        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(dt1.format(date));

    }

    public String getName() {return name;}

    public Integer getId() {
        return id;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

}
