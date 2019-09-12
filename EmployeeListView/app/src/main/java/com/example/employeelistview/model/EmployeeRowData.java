package com.example.employeelistview.model;

/**
 * pojo class which contain variable name nd gatter and setter
 * this class is use to  get the data nd set
 */
public class EmployeeRowData {
private  String employeeName;
private  String contactNumber;
private  String salary;
private  String city;
private int image;
//constructor
    public EmployeeRowData(String employeeName,String contactNumber,String salary,String city,int image)
    {
        this.employeeName = employeeName;
        this.contactNumber = contactNumber;
        this.salary = salary;
        this.city = city;
        this.image = image;
    }
//getter and setter of each field
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
