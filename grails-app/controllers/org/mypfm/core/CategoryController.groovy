package org.mypfm.core

/**
 *
 * @author fhenri
 */
class CategoryController {

    static defaultAction = 'list'

    def list(Integer max) {
        //params.max = Math.min(max ?: 30, 100)
        [categoryList: Category.list(params), categoryTotal: Category.count()]
    }
}
