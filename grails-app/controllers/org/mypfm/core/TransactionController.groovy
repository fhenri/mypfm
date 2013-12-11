package org.mypfm.core

import org.apache.shiro.SecurityUtils
import org.mypfm.user.ShiroUser

/**
 *
 * @author fhenri
 */
class TransactionController {

    static allowedMethods = [update: "POST"]
    
    static defaultAction = 'list'

    def list(Integer max) {
        //params.max = Math.min(max ?: 30, 100)
        def realmId = ShiroUser.findByUsername( SecurityUtils.subject.principal ).realmId
        //[transactionList: Transaction.list(params), transactionTotal: Transaction.count()]

        def query = {
            like("realmId", realmId)
        }
        [transactionList: Transaction.createCriteria().list(query), transactionTotal: Transaction.createCriteria().count(query)]
    }
    
    def transactionService
    def upload() {
        def sourceFile = request.getFile('bankFile')
        
        if (sourceFile.empty) {
            flash.message = 'File cannot be empty'
        } 
        // only support ofx file for now
        else if (sourceFile.originalFilename.endsWith("ofx")) {
            String realmId = ShiroUser.findByUsername( SecurityUtils.subject.principal ).realmId
            def countTransaction = transactionService.importOFXStatement(sourceFile, realmId)
            flash.message = 'ofx File imported successfully: ' + countTransaction + ' transaction(s) loaded'
        }  else {
            flash.message = "only support ofx file"
        }
        
        redirect(action: "list", params: params)
    }

    def show(Long id) {
        def transactionInstance = Transaction.get(id)
        if (!transactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), id])
            redirect(action: "list")
            return
        }

        [transactionUpdate: transactionInstance]
    }

    def update(Long id, Long version) {
        def transactionInstance = Transaction.get(id)
        if (!transactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (transactionInstance.version > version) {
                transactionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'transaction.label', default: 'Transaction')] as Object[],
                        "Another user has updated this Transaction while you were editing")
                redirect(action: "list")
                return
            }
        }

        transactionInstance.properties = params

        if (!transactionInstance.save(flush: true)) {
            flash.message = message(code: 'default.updated.message', args: [message(code: 'transaction.label', default: 'Transaction'), transactionInstance.id])
            redirect(action: "list")
            return
        }

        redirect(action: "list")
    }

}