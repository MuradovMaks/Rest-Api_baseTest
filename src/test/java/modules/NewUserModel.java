package modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
public class NewUserModel
{
    String name, job,id;
    String createdAt;
}
