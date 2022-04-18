package ee.mihkel.webshop.model.output;

import ee.mihkel.webshop.model.input.OmnivaParcelMachine;
import ee.mihkel.webshop.model.input.SmartpostParcelMachine;
import lombok.Data;

import java.util.List;

@Data
public class ParcelMachines {
    private List<OmnivaParcelMachine> omnivaParcelMachines;
    private List<SmartpostParcelMachine> smartpostParcelMachines;
}
