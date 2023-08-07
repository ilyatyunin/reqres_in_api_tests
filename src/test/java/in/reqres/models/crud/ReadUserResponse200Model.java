package in.reqres.models.crud;

import lombok.Data;
import in.reqres.models.userdata.SupportDataObject;
import in.reqres.models.userdata.UserDataList;

@Data
public class ReadUserResponse200Model {
    private UserDataList data;
    private SupportDataObject support;
}
