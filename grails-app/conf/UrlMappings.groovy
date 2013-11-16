class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/" {
            controller = 'dashboard'
            action = 'list'
        }

        //"/" (controller: 'bank')
        //view:"/index")
		"500"(view:'/error')
	}
}
