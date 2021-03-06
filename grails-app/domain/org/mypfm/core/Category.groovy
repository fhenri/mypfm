package org.mypfm.core

class Category {
    
    String name

    static belongsTo = [parent: Category]
    static hasMany = [childs: Category]

    static constraints = {
        name (blank:false, unique:false)
    }

    @Override
    String toString() {
        if (this.parent == null) {
            this.name
        } else {
            "----" + this.name
        }
    }
}