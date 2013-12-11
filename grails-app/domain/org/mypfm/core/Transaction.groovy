package org.mypfm.core

import org.joda.money.Money 

/**
 * Manage a banking transaction
 *
 * XXX we assume that a transaction is unique by the id so we cannot import twice the same transactions
 *     even if there's 2 different realms.
 *
 * @author fhenri
 */
class Transaction {

    // field to manage multi tenant
    String realmId

    String transactionId
    //Account account
    String label
    Date date
    Money amount;
    //String parent; TODO a transaction could be linked to a parent transaction (use case when you pay at the end of the month)
    Category category
    String comment

    static belongsTo = [account: Account]
    //static hasMany = [category: Category]

    static constraints = {
        transactionId(blank: false, unique: true)
        account(nullable: true)
        label(nullable: true)
        date(nullable: true)
        amount(nullable: true)
        category(nullable: true)
        comment(nullable: true)
    }
}


