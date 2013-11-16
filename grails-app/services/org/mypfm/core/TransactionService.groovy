package org.mypfm.core

import net.sf.ofx4j.io.OFXHandler;
import net.sf.ofx4j.io.OFXSyntaxException;
import net.sf.ofx4j.io.nanoxml.NanoXMLOFXReader
import java.text.SimpleDateFormat
import org.joda.money.Money;


class TransactionService {

    /**
     *
     * @param file
     * @return
     */
    def importOFXStatement(uploadFile) {

        // backup file so we can treat it
        def saveFile = new java.io.File("/tmp/${uploadFile.originalFilename}")
        uploadFile.transferTo(saveFile)

        def reader = new NanoXMLOFXReader()
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd")

        int countTransaction = 0
        def ofxHandler = new OFXHandler() {

            boolean isInAccount     = false
            boolean existingAccount = false
            boolean isInTransaction = false
            boolean accountAmount   = false

            Account accountInstance
            Transaction transactionInstance

            // leave the header - we dont have much to care
            public void onHeader(String name, String value) throws OFXSyntaxException {
            }

            public void onElement(String name, String value) throws OFXSyntaxException {
                switch (name) {
                    case "ACCTID" :
                        // check if this account already exists
                        Account anAccount = Account.findByAccountId(value)
                        if (anAccount == null) {
                            accountInstance?.accountId = value
                        } else {
                            accountInstance = anAccount
                            existingAccount = true
                        }
                        accountInstance.save(flush: true)
                        break
                    case "ACCTTYPE" :
                        accountInstance?.type = value
                        break
                    case "BALAMT" :
                        if (accountAmount) {
                            accountInstance?.balanceAmount = Money.parse("EUR " + value)
                        }
                        break

                    // handles transaction
                    case "FITID" :
                        transactionInstance?.transactionId = value
                        break
                    case "NAME" :
                        transactionInstance?.label = value
                        break
                    case "DTPOSTED" :
                            if (value != null) {
                            Date transactionDate = sdf.parse(value.substring(0, 8))
                            transactionInstance?.date = transactionDate
                        }
                        break
                    case "TRNAMT" :
                        transactionInstance?.amount = Money.parse("EUR " + value)
                        break
                }
            }

            public void startAggregate(String aggregateName) throws OFXSyntaxException {
                switch (aggregateName) {
                    case "STMTRS" :
                        isInAccount = true
                        accountInstance = new Account()
                        existingAccount = false
                        break
                    case "STMTTRN" :
                        isInTransaction = true
                        transactionInstance = new Transaction()
                        transactionInstance.account = accountInstance
                        break
                    case "LEDGERBAL" :
                        accountAmount = true
                }
            }

            public void endAggregate(String aggregateName) throws OFXSyntaxException {
                switch (aggregateName) {
                    case "STMTRS" :
                        isInAccount = false
                        accountInstance.save(flush: true)
                        break
                    case "STMTTRN" :
                        isInTransaction = false
                        // leave hibernate handles cases if transaction already existed
                        if (transactionInstance.save()) {
                            countTransaction++
                        }
                        break
                    case "LEDGERBAL" :
                        accountAmount = false
                }
            }
        }

        reader.setContentHandler(ofxHandler)
        reader.parse(new FileInputStream(saveFile))

        countTransaction
    }


}