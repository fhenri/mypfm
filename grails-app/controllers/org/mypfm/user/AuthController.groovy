package org.mypfm.user

import static java.util.UUID.randomUUID
/**
 *
 * @author fhenri
 */
class AuthController {

    def springSecurityService

    def updatePassword() {
        render(view:'updatePassword')
    }

    def doUpdatePassword() {
		if (params.password1!=params.password2) {
			flash.message = "Please enter same passwords."
			flash.status = "error"
			redirect(action:'updatePassword')
		} else {
			def user = springSecurityService.currentUser
			if (user) {
                /*
                def passwordHash = springSecurityService.encodePassword(params.oldpassword)
				if (user.password == passwordHash) {
					if (user.save()){
						flash.message = "Password successfully updated"
						redirect(uri:'/')
					} else {
						flash.message = "Password update failed."
						flash.status = "error"
						redirect(action:'updatePassword')
					}
				} else {
					flash.message = "Incorrect old password ."
					flash.status = "error"
					redirect(action:'updatePassword')
				}
				*/
                user.password = params.password1
                if (user.save()){
                    flash.message = "Password successfully updated"
                    redirect(uri:'/')
                } else {
                    flash.message = "Password update failed."
                    flash.status = "error"
                    redirect(action:'updatePassword')
                }
			} else {
				flash.message = "Unknown user."
				flash.status = "error"
				redirect(action:'updatePassword')
			}
		}
	}

    def signup() {
        User user = new User()
        [user: user]
    }

    def register() {

        // Check to see if the username already exists
        def user = User.findByUsername(params.username)
        if (user) {
            flash.message = "User already exists with the username '${params.username}'"
            redirect(action:'signup')
        }

        // User doesn't exist with username. Let's create one
        else {

            // Make sure the passwords match
            if (params.password != params.password2) {
                flash.message = "Passwords do not match"
                redirect(action:'signup')
            }

            // Passwords match. Let's attempt to save the user
            else {
                // Create user
                user = new User (
                        username: params.username,
                        password: params.password,
                        realmId: randomUUID() as String
                )

                if (user.save()) {

                    // Add USER role to new user
                    def userRole = Role.findByAuthority("ROLE_USER")
                    UserRole.create user, userRole, true

                    // Login user
                    springSecurityService.reauthenticate user.username

                    // If a controller redirected to this page, redirect back
                    // to it. Otherwise redirect to the root URI.
                    def targetUri = params.targetUri ?: "/"

                    log.info "Redirecting to '${targetUri}'."
                    redirect(uri: targetUri)
                }
                else {
                    //user.errors.allErrors.each {println it.defaultMessage; msg +=it.defaultMessage}
                    flash.message = "Cannot create your account"
                    redirect(action:'signup')
                }
            }
        }
    }
}
