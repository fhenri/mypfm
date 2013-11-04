/*
 * This file represents all kind of account
 */

package org.mypfm.core

import org.joda.money.Money 

/**
 * General definition of account.
 *
 * @author fhenri
 *
 */
class Account {

    /* example of an account from the bank report :
     * [Depot
     * C07255 CM LA TOUR DE SALVAGNY
     * REUR102780725500052829961
     * D26/04/2006
     * LPLAN D'EPARGNE EN ACTIONS M FREDERIC HENRI
     * $EUR
     * M585,98
     * E1,000000
     * ]
     */
    /*
    enum AccountType {
        CHECKING, SAVINGS, MONEYMRKT, CREDITLINE
    }
    */
     
    String accountId
    String type
    String label

    Date opendate
    Currency currency
    Money balanceAmount
    Date balanceDate

    static belongsTo = [bank: Bank]

    static constraints = {
        accountId(blank: false, unique: true)
        bank(nullable: true)
        type(nullable: true)
        label(nullable: true)
        opendate(nullable: true)
        currency(nullable: true)
        balanceAmount(nullable: true)
        balanceDate(nullable: true)
    }

    String getaccountIdForDisplay () {
        accountId.substring(0, 9).concat(" ").concat(accountId.substring(9))
    }

    String getbalanceAmountForDisplay () {
        balanceAmount.toString()
    }

    @Override
    String toString() {
        label
    }

}
