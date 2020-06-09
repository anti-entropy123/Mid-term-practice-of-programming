package demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "default.balance")
public class ConfigData {
    private int annualLeave;
    private int maternityLeave;
    private int homeLeave;
    private int sickLeave;
    private int absenceLeave;

    public ConfigData(){ }
    public ConfigData(
            int annualLeave,
            int maternityLeave,
            int homeLeave,
            int sickLeave,
            int absenceLeave){ 
        this.annualLeave = annualLeave;
        this.maternityLeave = maternityLeave;
        this.homeLeave = homeLeave;
        this.sickLeave = sickLeave;
        this.absenceLeave = absenceLeave;
    }

    public int getAnnualLeave    () { return this.annualLeave;    }
    public int getMaternityLeave () { return this.maternityLeave; }
    public int getHomeLeave      () { return this.homeLeave;      }
    public int getSickLeave      () { return this.sickLeave;      }
    public int getAbsenceLeave   () { return this.absenceLeave;   }

    public void setAnnualLeave    (int b) { this.annualLeave    = b; }
    public void setMaternityLeave (int b) { this.maternityLeave = b; }
    public void setHomeLeave      (int b) { this.homeLeave      = b; }
    public void setSickLeave      (int b) { this.sickLeave      = b; }
    public void setAbsenceLeave   (int b) { this.absenceLeave   = b; }
}