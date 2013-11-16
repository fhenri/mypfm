package org.mypfm.core

class CategoryController {

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        //params.max = Math.min(max ?: 30, 100)
        [categoryList: Category.list(params), categoryTotal: Category.count()]
    }
}
