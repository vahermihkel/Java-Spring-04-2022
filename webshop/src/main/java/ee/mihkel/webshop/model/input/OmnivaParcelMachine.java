package ee.mihkel.webshop.model.input;

import lombok.Data;

@Data
public class OmnivaParcelMachine {
    private String ZIP;
    private String NAME;
    private String TYPE;
    private String A0_NAME;
    private String A1_NAME;
    private String A2_NAME;
    private String A3_NAME;
    private String A4_NAME;
    private String A5_NAME;
    private String A6_NAME;
    private String A7_NAME;
    private String A8_NAME;
    private String X_COORDINATE;
    private String Y_COORDINATE;
    private String SERVICE_HOURS;
    private String TEMP_SERVICE_HOURS;
    private String TEMP_SERVICE_HOURS_UNTIL;
    private String TEMP_SERVICE_HOURS_2;
    private String TEMP_SERVICE_HOURS_2_UNTIL;
    private String comment_est;
    private String comment_eng;
    private String comment_rus;
    private String comment_lav;
    private String comment_lit;
    private String MODIFIED;
}
