package org.mypfm.user

/**
 *
 * @author fhenri
 */
class ShiroUser {

    // field to manage multi tenant
    String realmId

    String firstName
    String lastName
    String username
    String passwordHash
    String email

    Date dateCreated
    Date lastUpdated

    Boolean passwordChangeRequiredOnNextLogon = false

    static hasMany = [ roles: ShiroRole, permissions: String ]

    static constraints = {
        firstName(blank: false)
        lastName(blank: false)
        username(unique: true, blank: false, size: 4..20)
        email(unique: true, email: true)
        passwordHash(display:false)
        passwordChangeRequiredOnNextLogon(nullable: true)
    }

    static mapping = {
        autoTimestamp true
        cache true
        roles cache: true
        permissions cache: true
    }

    @Override
    String toString() {
        username
    }
}
