package ee.mihkel.webshop.service;

import ee.mihkel.webshop.model.input.OmnivaParcelMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParcelMachineService {
    String omnivaUrl = "https://www.omniva.ee/locations.json";

    @Autowired
    RestTemplate restTemplate;

    public List<OmnivaParcelMachine> getParcelMachines(String country) {
        ResponseEntity<OmnivaParcelMachine[]> response = restTemplate
                .exchange(omnivaUrl, HttpMethod.GET, null,OmnivaParcelMachine[].class);

        List<OmnivaParcelMachine> omnivaParcelMachines = new ArrayList<>();
        if (response.getBody() != null) {
//            System.out.println(response.getBody());
            omnivaParcelMachines = Arrays.asList(response.getBody());
            omnivaParcelMachines = omnivaParcelMachines.stream()
                    .filter(p -> p.getA0_NAME().equals(country))
                    .collect(Collectors.toList());


//            List<OmnivaParcelMachine> omnivaParcelMachinesFiltered = new ArrayList<>();
//            for (OmnivaParcelMachine p: omnivaParcelMachines) {
//                if (p.getA0_NAME().equals(country)) {
//                    omnivaParcelMachinesFiltered.add(p);
//                }
//            }
        }
        return omnivaParcelMachines;
    }
}
