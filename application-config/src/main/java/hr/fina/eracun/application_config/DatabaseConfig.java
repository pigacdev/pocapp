package hr.fina.eracun.application_config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    @Value("${database.shema.B2G}")
    private String iB2GSchema;
    @Value("${database.shema.B2B}")
    private String iB2BSchema;
    @Value("${database.shema.JT}")
    private String iJTSchema;
    
    @Autowired
    private Environment iEnv;

    /**
     * Dohvaća vrijednost iz properties datoteke za zadani ključ za <b>defaultnu</b> schemu baze.
     *
     * @param pKey Ključ u properties datoteci
     * @return Vrijednost upita
     */
    public String getProperty(final String pKey){
        return this.getValue(pKey);
    }

    /**
     * Dohvaća vrijednost iz properties datoteke za zadani ključ i zamijenjuje placeholder vrijednosti
     * sa odabranom shemom koju učitava iz aplikativne properties datoteke.
     *
     * @param pKey Ključ u properties datoteci
     * @return Konačnu vrijednost upita
     */
    private String getValue(final String pKey){
        String value = iEnv.getProperty(pKey);
        HashMap<String, String> tPlaceholderMap = new HashMap<String, String>();
        tPlaceholderMap.put("@ERACB2G@", iB2GSchema);
        tPlaceholderMap.put("@ERACB2B@", iB2BSchema);
        tPlaceholderMap.put("@ERACJT@", iJTSchema);

        for (Map.Entry<String, String> entry : tPlaceholderMap.entrySet()) {
            value = value.replaceAll(entry.getKey(), entry.getValue());
        }

        return value;
    }

}
