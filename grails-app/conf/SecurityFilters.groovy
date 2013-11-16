//package org.mypfm.user


class SecurityFilters {

    /**
     * Array of controller/action combinations which will be skipped from authentication
     * if the controller and action names match. The action value can also be '*' if it
     * encompasses all actions within the controller.
     */
    static nonAuthenticatedActions = [
    ]

    /**
     * Array of controller/action combinations that will be authenticated against the user's
     * role. The map also includes the roles which the controller/action pair will match
     * against.
     */
     static authenticatedActions = [
     ]     
     
     private boolean findAction(controllerName, actionName){
         def c = nonAuthenticatedActions[controllerName]
         return(c)?c.find{(it==actionName||it=='*')}!=null:false
     }
    
     def filters = {  
         all(controller: '*', action: '*') {
             before = {
                 // Ignore direct views (e.g. the default main index page).
                 if (!controllerName) return true

                 // Access control by convention.
                 accessControl() //allows access for remembered user
             }
         }
     }
}