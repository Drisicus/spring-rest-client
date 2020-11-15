
package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

}
