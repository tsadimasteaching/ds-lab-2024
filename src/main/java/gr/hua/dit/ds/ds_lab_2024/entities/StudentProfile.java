package gr.hua.dit.ds.ds_lab_2024.entities;

import jakarta.persistence.*;

@Entity
@Table
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int Id;

    @Column(name = "city")
    private String City;

    @Column(name = "street")
    private String Street;

    @Column(name = "street_number")
    private int StreetNumber;

    @Column(name = "father_name")
    private String FatherName;

    @Column(name = "mother_name")
    private String MotherName;

    @OneToOne(mappedBy = "studentProfile", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Student student;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public int getStreetNumber() {
        return StreetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        StreetNumber = streetNumber;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getMotherName() {
        return MotherName;
    }

    public void setMotherName(String motherName) {
        MotherName = motherName;
    }

    public StudentProfile() {
    }

    public StudentProfile(String city, String street, int streetNumber, String fatherName, String motherName) {
        City = city;
        Street = street;
        StreetNumber = streetNumber;
        FatherName = fatherName;
        MotherName = motherName;
    }

    @Override
    public String toString() {
        return "StudentProfile{" +
                "Id=" + Id +
                ", City='" + City + '\'' +
                ", Street='" + Street + '\'' +
                ", StreetNumber=" + StreetNumber +
                ", FatherName='" + FatherName + '\'' +
                ", MotherName='" + MotherName + '\'' +
                ", student=" + student +
                '}';
    }
}
