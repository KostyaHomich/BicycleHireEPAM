package epam.project.builder;


import epam.project.entity.PointHire;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class PointHireBuilder implements Builder<PointHire> {

    private static final String ID = "id";
    private static final String LOCATION = "location";
    private static final String TELEPHONE = "telephone";
    private static final String DESCRIPTION = "description";




    @Override
    public PointHire build(Map<String, String> params) {
        PointHire pointHire = new PointHire();
        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case ID:
                    pointHire.setId(Integer.parseInt(value));
                    break;
                case LOCATION:
                    pointHire.setLocation(value);
                    break;
                case TELEPHONE:
                    pointHire.setTelephone(value);
                    break;
                case DESCRIPTION:
                    pointHire.setDescription(value);
                    break;
                default:
                    break;
            }

        }

        return pointHire;

    }

}
