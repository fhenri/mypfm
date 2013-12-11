package org.mypfm.core

/**
 * represents a bank - a bank is unique by the iban code
 *
 * @author fhenri
 */
class Bank {

    // field to manage multi tenant
    String realmId

    String iBanCode
    String bankName
    String webPage

    static hasMany = [account: Account]

    static constraints = {
        bankName (blank: false, unique: true)
        iBanCode (nullable: true, matches: '[a-zA-Z]{2}\\d{25,32}')
		webPage (nullable: true, url: true)
    }

    // see http://gr8fanboy.wordpress.com/2010/05/12/how-to-name-getterssetters-correctly-in-groovy-for-camel-case-field-properties/
    String getiBanCodeForDisplay () {
        iBanCode?.toUpperCase()?.replaceAll('(.{4})', '$1 ') 
    }
    
    @Override
    String toString() {
        bankName + " - " + iBanCode
    }

}