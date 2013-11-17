package org.mypfm.core

import org.joda.money.Money 

class Transaction {

    String transactionId
    //Account account
    String label
    Date date
    Money amount;
    //String amount
    //String parent;
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


