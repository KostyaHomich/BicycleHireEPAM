package epam.project.builder;

import epam.project.entity.Bicycle;
import epam.project.entity.PointHire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class BicycleBuilder implements Builder<Bicycle> {
    private static final String ID = "bicycleId";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String LINK = "link";
    private static final String POINT_HIRE_ID = "pointHireId";

    @Autowired
    private Bicycle bicycle;

    @Override
    public Bicycle build(Map<String, String> params) {
        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case NAME:
                    bicycle.setName(value);
                    break;
                case DESCRIPTION:
                    bicycle.setDescription(value);
                    break;
                case ID:
                    bicycle.setId(Integer.parseInt(value));
                    break;
                case POINT_HIRE_ID:
                    PointHire pointHire=new PointHire();
                    pointHire.setId(Integer.parseInt(value));
                    bicycle.setPointHire(pointHire);
                    break;
                case LINK:
                    bicycle.setLink(value);
                    break;
                default:
                    break;
            }

        }

        return bicycle;
    }

}

