package org.mypfm.core

import org.springframework.dao.DataIntegrityViolationException

/**
 *
 * @author fhenri
 */
class DashboardController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static defaultAction = 'list'

    def list(Integer max) {
        def realmId = springSecurityService.currentUser.realmId
        def query = {
            like("realmId", realmId)
        }
        //params.max = Math.min(max ?: 10, 100)
        [bankInstanceList: Bank.createCriteria().list(query), bankInstanceTotal: Bank.createCriteria().count(query),
         accountInstanceList: Account.createCriteria().list(query), accountInstanceTotal: Account.createCriteria().count(query)]
    }

    def createBank() {
        def realmId = springSecurityService.currentUser.realmId
        def bankInstance = new Bank(params)
        bankInstance.realmId = realmId
        [bankInstance: bankInstance]
    }

    def saveBank() {
        def realmId = springSecurityService.currentUser.realmId
        def bankInstance = new Bank(params)
        bankInstance.realmId = realmId
        if (!bankInstance.save()) {
            render(view: "createBank", model: [bankInstance: bankInstance])
            return
        }

        redirect(action: "list")
    }

    def showBank(Long id) {
        def bankInstance = Bank.get(id)
        if (!bankInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bank.label', default: 'Bank'), id])
            redirect(action: "list")
            return
        }

        [bankInstance: bankInstance]
    }

    def editAccount(Long id) {
        def accountInstance = Account.get(id)
        if (!accountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), id])
            redirect(action: "list")
            return
        }

        [accountInstance: accountInstance]
    }

    def edit(Long id) {
        def bankInstance = Bank.get(id)
        if (!bankInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bank.label', default: 'Bank'), id])
            redirect(action: "list")
            return
        }

        [bankInstance: bankInstance]
    }

    def update(Long id, Long version) {
        def bankInstance = Bank.get(id)
        if (!bankInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bank.label', default: 'Bank'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (bankInstance.version > version) {
                bankInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'bank.label', default: 'Bank')] as Object[],
                          "Another user has updated this Bank while you were editing")
                render(view: "edit", model: [bankInstance: bankInstance])
                return
            }
        }

        bankInstance.properties = params

        if (!bankInstance.save()) {
            render(view: "edit", model: [bankInstance: bankInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'bank.label', default: 'Bank'), bankInstance.id])
        redirect(action: "show", id: bankInstance.id)
    }

    def updateAccount(Long id, Long version) {
        def accountInstance = Account.get(id)
        if (!accountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (accountInstance.version > version) {
                accountInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'account.label', default: 'Account')] as Object[],
                          "Another user has updated this Account while you were editing")
                render(view: "edit", model: [bankInstance: accountInstance])
                return
            }
        }

        accountInstance.properties = params

        if (!accountInstance.save()) {
            render(view: "editAccount", model: [bankInstance: accountInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'account.label', default: 'Bank'), accountInstance.id])
        redirect(action: "list", id: accountInstance.id)
    }

    def delete(Long id) {
        def bankInstance = Bank.get(id)
        if (!bankInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bank.label', default: 'Bank'), id])
            redirect(action: "list")
            return
        }

        try {
            bankInstance.delete()
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'bank.label', default: 'Bank'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'bank.label', default: 'Bank'), id])
            redirect(action: "show", id: id)
        }
    }
}
