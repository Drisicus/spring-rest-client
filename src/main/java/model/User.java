
package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    private final static long serialVersionUID = -268530001130358615L;

}
