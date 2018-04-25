package q192870;

/*
 * I have a method which allows to create user account. It contains some  
 * errorSet = false;

    @FXML
    private void createAccount() throws  
 * {

        if (!lastNameField.getText().isEmpty() &&  
 * && userRole.getValue() != null && !emailField.getText().isEmpty() &&  
 * {
            if (SharedRegexController.isEmailSyntax(emailField.getText()) &&  
 * {
                if (passwordField.getText().length() >  
 * {
                    if (passwordField.getText().matches(".*[a-z]*.") &&  
 * && passwordField.getText().matches(".*[1-9]*.")) {
                        if  
 * {
                            //Get values from text fields
                     
 *        String lastNameValue =  
 *                            String firstNameValue =  
 *                            String userRoleValue = (String)  
 *                            String emailValue =  
 *                            //Encrypt password
                             
 * passwordValue = Encapsulation.encrypt(passwordField.getText());
                 
 *            //Process adding operation
                            entrepreneur  
 * new EntrepreneurModel(lastNameValue, firstNameValue, userRoleValue, emailValue,  
 * DateService.getLocalDate(), userAccountImageFile);
                             
 *                            
                        } else {
                    
 *         termsPopOver = new PopOver();
                            Label  
 * = new Label(SignUpBundle.getAcceptTermsText());
                             
 * FontPosture.REGULAR, 13));
                             
 * Insets(5));
                            termsPopOver.setContentNode(new  
 *                            termsPopOver.show(termsAgreementCheckBox);
           
 *              }
                    } else {
                         
 *                    }
                } else {
                    pinError();
   
 *              }
            } else {
                pinError();
            }

  
 * PinError() is a method that display errors of user. This method should be  
 * private void pinError() {

        if (!errorSet) {

             
 * SignUpBundle.getFirstNameErrorProvider());

             
 * SignUpBundle.getLastNameErrorProvider());

             
 * SignUpBundle.getRoleErrorProvider());

             
 * SignUpBundle.getEmailErrorProvider());

             
 * SignUpBundle.getPasswordErrorProvider());

             
 * SignUpBundle.getUnvalidateEmail());

             
 * SignUpBundle.getSmallPassword());

             
 * SignUpBundle.getUnvalidatePassword());

             
 * @FXML
    private void createAccount() throws IOException {
      
 * Hence I am not sure which way is better to choose, and considering my use case.  
 */
public class Q192870 {
   public static void main(String[] args) {
       System.out.println(
           /* TODO, describe usage */);
       
       
   }
}
