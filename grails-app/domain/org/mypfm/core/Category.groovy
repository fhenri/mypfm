package org.mypfm.core

import i18nfields.I18nFields

@I18nFields
class Category {

    String name

    static belongsTo = [parent: Category]
    static hasMany = [childs: Category]

    static constraints = {
        name (blank:false, unique:true)
    }

    static i18nFields = ['name']

    @Override
    String toString() {
        if (this.parent == null) {
            name
        } else {
            "----" + name
        }
    }

}