package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.dto.JournalForm;
import ua.service.JournalService;

import java.util.regex.Pattern;

/**
 * Created by Admin on 1/31/2017.
 */
public class JournalFormValidator implements Validator {

    private final static Pattern VOLUME =Pattern.compile("^\\d{1,4}$");
    private final static Pattern YEAR =Pattern.compile("^\\d{4}$");
    private final static Pattern PRICE =Pattern.compile("^\\d+$");
    private final JournalService journalService;

    public JournalFormValidator(JournalService journalService){
        this.journalService = journalService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return JournalForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        JournalForm journal=(JournalForm) o;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"volume","","Can't be empty!");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"year","","Can't be empty!");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"price","","Can't be empty!");



        if(journal.getId()==null){
            JournalForm foundedJournal=journalService.findByVolumeAndYear(journal.getTitle(),Integer.parseInt(journal.getVolume()),
                    Integer.parseInt(journal.getYear()));
            if(foundedJournal!=null) {
                errors.rejectValue("volume", "", "Already exists!");
                errors.rejectValue("year", "", "Already exists!");
            }
        }
            if (!VOLUME.matcher(journal.getVolume()).matches()){
                errors.rejectValue("volume","","Volume include not more than 4 digits and could not be zero!");
            }
            if (!YEAR.matcher(journal.getYear()).matches()){
                errors.rejectValue("year","","Year include only 4 digits and could not be zero!");
            }
            if (!PRICE.matcher(journal.getPrice()).matches()){
                errors.rejectValue("price","","This is not a price!");
            }
    }
}
