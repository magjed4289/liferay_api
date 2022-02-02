package tests.model.object.objectDefinition.customObjects;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Employee {
private String firstname;
private int r_supervisor_c_managerId;
}
