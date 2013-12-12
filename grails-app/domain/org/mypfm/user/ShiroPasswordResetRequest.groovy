package org.mypfm.user

import java.security.SecureRandom

/**
 *
 * @author fhenri
 */
class ShiroPasswordResetRequest {

	ShiroUser user
	String token
	Date requestDate

    static mapping = {
        autoTimestamp true
    }

	static beforeInsert = {
		requestDate = new Date()
		token = new BigInteger(130, new SecureRandom()).toString(32)
	}
}
