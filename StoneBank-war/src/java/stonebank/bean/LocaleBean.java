package stonebank.bean;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {
  private final static Locale ENGLISH = new Locale("en");
  private final static Locale SPANISH = new Locale("es");
  private Locale currentLocale; 
  
  public Locale getCurrentLocale() {
    return(currentLocale);
  }

  public String setEnglish() {
    currentLocale=ENGLISH;
    changeLocale();
    return null;
  }
  
  public String setSpanish() {
    currentLocale=SPANISH;
    changeLocale();    
    return null;
  }
  
  private void changeLocale () {
      UIViewRoot view = FacesContext.getCurrentInstance()
        			.getViewRoot();
      view.setLocale(currentLocale);
  }
  
}
