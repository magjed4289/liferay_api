package tests.model.object.objectDefinition.customObjects;

public class EmployeeData {
    private String firstname;
    private String supervisor;

    public EmployeeData(String firstname, String supervisor) {
        this.firstname = firstname;
        this.supervisor = supervisor;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}
